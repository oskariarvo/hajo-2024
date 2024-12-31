package fi.utu.tech.assignment6;


import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class ClientHandler extends Thread {

    Socket cs;
    Hub hub;

    // Pieni vinkki
    public ClientHandler(Socket cs, Hub h) {
        this.hub = h;
        this.cs = cs;
    }
    // TODO: Käytä edellisen tehtävän ratkaisua pohjana
    public void run() {
        InputStream is;
        try {
            is = cs.getInputStream();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        OutputStream os;
        try {
            os = cs.getOutputStream();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        PrintWriter sendMsg = new PrintWriter(new BufferedWriter(new OutputStreamWriter(os, StandardCharsets.UTF_8)), true);
        BufferedReader receive = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));

        String[] commands = {"ON", "OFF", "QUERY"};
        List<String> cmd = Arrays.asList(commands);

        while (true) {
            String line;
            try {
                line = receive.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }


            String[] lightCmd = line.split(";");


            if (Objects.equals(line, "Hello")) {
                System.out.println(line);
                sendMsg.println("Ack");
            } else if (Objects.equals(line, "quit")) {
                System.out.println(line);
                break;
            } else if (lightCmd.length == 2 || lightCmd.length == 3) {
                if (Objects.equals(lightCmd[0], "LIGHT") && cmd.contains(lightCmd[1])) {
                    if (lightCmd.length > 2 && !Objects.equals(lightCmd[1], "QUERY")) {
                        int index = Integer.parseInt(lightCmd[2]);
                        if (Objects.equals(lightCmd[1], "ON") && index >= 0 && index < 5) {
                            hub.turnOnLight(index);
                        } else if (index >= 0 && index < 5) {
                            hub.turnOffLight(index);
                        }
                    } else if (Objects.equals(lightCmd[1], "QUERY") && lightCmd.length <= 2) {
                        List<Light> currectStatus = new ArrayList<>(hub.getLights());
                        List<String> uusLista = new ArrayList<>();
                        for (int i = 0; i < 5; i++) {
                            String toAdd;
                            String singleStatus = String.valueOf(currectStatus.get(i));
                            List<String> osat = Arrays.asList(singleStatus.split(" "));
                            if (osat.contains("ON")) {
                                toAdd = i + ":ON";
                                uusLista.add(toAdd);
                            } else {
                                toAdd = i + ":OFF";
                                uusLista.add(toAdd);
                            }
                        }
                        String queryString = String.join(";", uusLista);
                        //System.out.println(queryString);
                        sendMsg.println(queryString);


                    } else {
                        //System.out.println("Kyselykomento vastaanotettu");
                    }
                }
            } else if (!line.isEmpty()) {
                System.out.println(line);
            }
        }
        System.out.println("Säie lopettaa");
    }
}

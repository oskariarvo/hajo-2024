package fi.utu.tech.assignment5;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class ClientHandler extends Thread {
    // TODO: Kopioi edellisen tehtäväsi vastaus tähän pohjalle

    Socket sc;
    public ClientHandler(Socket sc) {
        this.sc = sc;
    }
    public void run() {
        InputStream is;
        try {
            is = sc.getInputStream();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        OutputStream os;
        try {
            os = sc.getOutputStream();
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
                //System.out.println(lightCmd[0]);
                //System.out.println(lightCmd[1]);
                if (Objects.equals(lightCmd[0], "LIGHT") && cmd.contains(lightCmd[1])) {
                    //System.out.println(!lightCmd[2].isEmpty());
                    //System.out.println(!Objects.equals(lightCmd[1], "QUERY"));
                    if (lightCmd.length > 2 && !Objects.equals(lightCmd[1], "QUERY")) {
                        int index = Integer.parseInt(lightCmd[2]);
                        System.out.printf("Kytketään lamppu %d %s %n", index, lightCmd[1]);
                    } else {
                        System.out.println("Kyselykomento vastaanotettu");
                    }
                }
            } else if (!line.isEmpty()) {
                System.out.println(line);
            }


        }
        System.out.println("Säie lopettaa");
    }
}

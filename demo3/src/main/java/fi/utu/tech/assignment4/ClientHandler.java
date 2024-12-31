package fi.utu.tech.assignment4;


import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
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

        while (true) {
            String line;
            try {
                line = receive.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if (Objects.equals(line, "Hello")) {
                System.out.println(line);
                sendMsg.println("Ack");
            } else if (Objects.equals(line, "quit")) {
                System.out.println(line);
                break;
            } else if (!line.isEmpty()) {
                System.out.println(line);
            }
        }
        System.out.println("Säie lopettaa");
    }
}

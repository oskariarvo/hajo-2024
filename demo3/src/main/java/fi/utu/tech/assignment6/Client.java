package fi.utu.tech.assignment6;


import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class Client {

    public static void main(String[] args) {
        // TODO: Kopioi edellisen tehtäväsi vastaus tähän pohjalle
        try (Socket sc = new Socket("localhost", 23456)) {
            InputStream is = sc.getInputStream();
            OutputStream os = sc.getOutputStream();
            BufferedReader receive = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
            PrintWriter sendMsg = new PrintWriter(new BufferedWriter(new OutputStreamWriter(os, StandardCharsets.UTF_8)), true);
            sendMsg.println("LIGHT;ON;3");
            sendMsg.println("LIGHT;Ofcd;3");
            sendMsg.println("LIGHT;Ofcd");
            sendMsg.println("LIGHT;OFF;2");
            sendMsg.println("LIGHT;OFF");
            sendMsg.println("LIGHT;QUERY");
            sendMsg.println("LIGHT;ON;6");
            sendMsg.println("LIGHT;ON;2");
            sendMsg.println("LIGHT;ON;1");
            sendMsg.println("LIGHT;OFF;3");
            sendMsg.println("LIGHT;QUERY");
            sendMsg.println("LIGHT;QUERY;2");
            while (true) {
                String received = receive.readLine();
                if (!received.isEmpty()) {
                    System.out.println(received);
                } else if (Objects.equals(received, "quit")) {
                    System.out.println("Varmistus saatu");
                    sendMsg.println("quit");
                    sendMsg.flush();
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

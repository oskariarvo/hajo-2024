package fi.utu.tech.assignment4;


import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class Client {

    public static void main(String[] args) {
        // TODO: Kopioi edellisen tehtäväsi vastaus tähän pohjalle
        try (Socket sc = new Socket("localhost", 23456)) {
            InputStream is = sc.getInputStream();
            OutputStream os = sc.getOutputStream();
            BufferedReader receive = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
            PrintWriter sendMsg = new PrintWriter(new BufferedWriter(new OutputStreamWriter(os, StandardCharsets.UTF_8)), true);
            sendMsg.println("Hello");
            while (true) {
                String received = receive.readLine();
                if (!received.isEmpty()) {
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

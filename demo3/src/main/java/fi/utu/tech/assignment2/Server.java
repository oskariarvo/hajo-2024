package fi.utu.tech.assignment2;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class Server {

    public static void main(String[] args) {
        // TODO: Kopioi edellisen tehtäväsi vastaus tähän pohjalle
        try (ServerSocket sc = new ServerSocket(23456)) {
            //System.out.println("1");
            Socket commSc = sc.accept();
            //System.out.println("2");
            var is = commSc.getInputStream();
            //System.out.println("3");
            byte [] receiveBytes = is.readAllBytes();
            //System.out.println("4");
            String receivedString = new String(receiveBytes, StandardCharsets.UTF_8);
            //System.out.println("!!!");
            System.out.println(receivedString);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

package fi.utu.tech.assignment4;

import fi.utu.tech.assignment4.ClientHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) {
        // TODO: Kopioi edellisen tehtäväsi vastaus tähän pohjalle
        try (ServerSocket sc = new ServerSocket(23456)) {
            while (true) {
                Socket commSc = sc.accept();
                new Thread(new ClientHandler(commSc)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

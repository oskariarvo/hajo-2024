package fi.utu.tech.assignment6;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) {
        Hub h = new Hub();
        // TODO: Käytä edellisen tehtävän ratkaisua pohjana
        try (ServerSocket sc = new ServerSocket(23456)) {
            while (true) {
                Socket commSc = sc.accept();
                new Thread(new ClientHandler(commSc, h)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}

package fi.utu.tech.assignment1;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) throws IOException {
        // TODO: Palvelinohjelma
        try (ServerSocket sc = new ServerSocket(23456)) {
            Socket commSc = sc.accept();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}

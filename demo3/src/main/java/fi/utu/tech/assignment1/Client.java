package fi.utu.tech.assignment1;

import java.io.IOException;
import java.net.Socket;

public class Client {

    public static void main(String[] args) throws IOException {
        // TODO: Asiakasohjelma
    try (Socket sc = new Socket("localhost", 23456)) {

    } catch (IOException e) {
        e.printStackTrace();
    }
    }

}

package fi.utu.tech.assignment3;

import java.io.IOException;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class Client {

    public static void main(String[] args) {
        // TODO: Kopioi edellisen tehtäväsi vastaus tähän pohjalle
        try (Socket sc = new Socket("localhost", 23456)) {
            var os = sc.getOutputStream();
            String sendString = "Hello";
            byte [] sendBytes = sendString.getBytes(StandardCharsets.UTF_8);
            os.write(sendBytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

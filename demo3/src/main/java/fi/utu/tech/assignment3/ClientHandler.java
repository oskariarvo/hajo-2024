package fi.utu.tech.assignment3;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class ClientHandler extends Thread {
    // TODO: Toteuta asiakaspalvelija tänne
    Socket sc;
    public ClientHandler(Socket sc) {
        this.sc = sc;
    }
    public void run () {
        //System.out.println("!!!");
        InputStream is = null;
        try {
            is = sc.getInputStream();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            OutputStream os = sc.getOutputStream();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //System.out.println("1");
        byte [] receiveBytes = new byte[0];
        try {
            receiveBytes = is.readAllBytes();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String receivedString = new String(receiveBytes, StandardCharsets.UTF_8);
        System.out.println(receivedString);

    }
    
}

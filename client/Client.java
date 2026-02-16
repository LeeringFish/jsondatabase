package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client {
    private String address;
    private int port;

    public Client(String address, int port) {
        this.address = address;
        this.port = port;
    }

    public void run() {
        String msg = "Give me a record # 12";
        System.out.println("Client started!");

        try (Socket socket = new Socket(address, port);
             DataInputStream input = new DataInputStream(socket.getInputStream());
             DataOutputStream output = new DataOutputStream(socket.getOutputStream())
        ) {
            output.writeUTF(msg);
            System.out.println("Sent: " + msg);
            String response = input.readUTF();
            System.out.println("Received: " + response);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}

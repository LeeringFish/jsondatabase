package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client {
    private final String address;
    private final int port;
    private final String request;

    public Client(String address, int port, String request) {
        this.address = address;
        this.port = port;
        this.request = request;
    }

    public void run() {
        System.out.println("Client started!");

        try (Socket socket = new Socket(address, port);
             DataInputStream input = new DataInputStream(socket.getInputStream());
             DataOutputStream output = new DataOutputStream(socket.getOutputStream())
        ) {
            output.writeUTF(request);
            System.out.println("Sent: " + request);
            String response = input.readUTF();
            System.out.println("Received: " + response);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}

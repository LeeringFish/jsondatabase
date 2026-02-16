package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

public class Server {
    private String address;
    private int port;
    private String[] data;

    public Server(String address, int port) {
        this.address = address;
        this.port = port;
        this.data = new String[1000];
        Arrays.fill(data, "");
    }

    public void run() {
        System.out.println("Server started!");
        try (ServerSocket server = new ServerSocket(port, 50, InetAddress.getByName(address));
             Socket socket = server.accept();
             DataInputStream input = new DataInputStream(socket.getInputStream());
             DataOutputStream output = new DataOutputStream(socket.getOutputStream());
             ) {

            String receivedMsg = input.readUTF();
            System.out.println("Received: " + receivedMsg);
            String[] parts = receivedMsg.split("\\s");
            String recordNum = parts[parts.length - 1];
            String response = String.format("A record # %s was sent!", recordNum);
            output.writeUTF(response);
            System.out.println("Sent: " + response);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public String set(int index, String text) {
        if (index < 0 || index > 999) {
            return "ERROR";
        }

        data[index] = text;
        return "OK";
    }

    public String get(int index) {
        if (index < 0 || index > 999 || "".equals(data[index])) {
            return "ERROR";
        }

        return data[index];
    }

    public String delete(int index) {
        if (index < 0 || index > 999) {
            return "ERROR";
        }

        data[index] = "";
        return "OK";
    }

}

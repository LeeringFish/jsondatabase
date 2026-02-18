package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private final String address;
    private final int port;
    private final Database db;

    public Server(String address, int port) {
        this.address = address;
        this.port = port;
        this.db = new Database();
    }

    public void run() {
        String clientCmd, newText, response;
        int index;
        String[] parts;
        Command cmd = null;
        Controller controller = new Controller();

        try (ServerSocket server = new ServerSocket(port, 50, InetAddress.getByName(address))) {
            System.out.println("Server started!");
            while (true) {
                try (Socket socket = server.accept();
                     DataInputStream input = new DataInputStream(socket.getInputStream());
                     DataOutputStream output = new DataOutputStream(socket.getOutputStream())
                ) {
                    String receivedMsg = input.readUTF().trim();

                    parts = receivedMsg.split("\\s+", 3);
                    clientCmd = parts[0];

                    if ("exit".equals(clientCmd)) {
                        output.writeUTF("OK");
                        break;
                    }

                    index = Integer.parseInt(parts[1]);

                    if ("get".equals(clientCmd)) {
                        cmd = new GetCommand(db, index);
                    } else if ("delete".equals(clientCmd)) {
                        cmd = new DeleteCommand(db, index);
                    } else if ("set".equals(clientCmd)) {
                        newText = parts[2];
                        cmd = new SetCommand(db, index, newText);
                    }

                    if (cmd != null) {
                        controller.setCommand(cmd);
                        controller.executeCommand();
                        response = cmd.getResult();
                        output.writeUTF(response);
                    } else {
                        output.writeUTF("ERROR");
                    }

                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }


}

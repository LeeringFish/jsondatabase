package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import com.google.gson.Gson;

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

                    Request request = new Gson().fromJson(receivedMsg, Request.class);

                    clientCmd = request.type();

                    if ("exit".equals(clientCmd)) {
                        output.writeUTF("OK");
                        break;
                    }


                    if ("get".equals(clientCmd)) {
                        cmd = new GetCommand(db, request.key());
                    } else if ("delete".equals(clientCmd)) {
                        cmd = new DeleteCommand(db, request.key());
                    } else if ("set".equals(clientCmd)) {
                        cmd = new SetCommand(db, request.key(), request.value());
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

    record Request(String type, String key, String value) {}

    record Response(String response, String value, String reason) {}

}

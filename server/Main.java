package server;

public class Main {
    public static void main(String[] args) {
        String address = "127.0.0.1";
        int port = 23456;
        Server server = new Server(address, port);
        server.run();
    }
}

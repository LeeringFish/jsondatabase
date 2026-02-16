package server;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String address = "127.0.0.1";
        int port = 23456;
        Server server = new Server(address, port);
        server.run();

        /*Scanner scan = new Scanner(System.in);
        String userInput, command;
        int index;
        String[] parts;
        boolean running = true;

        while (running) {
            userInput = scan.nextLine();

            if ("exit".equals(userInput)) {
                running = false;
            } else {
                parts = userInput.split("\\s", 3);
                command = parts[0];
                index = Integer.parseInt(parts[1]) - 1;

                if ("get".equals(command)) {
                    System.out.println(server.get(index));
                } else if ("set".equals(command)) {
                    System.out.println(server.set(index, parts[2]));
                } else if ("delete".equals(command)) {
                    System.out.println(server.delete(index));
                } else {
                    System.out.println("ERROR");
                }
            }
        }*/

    }
}

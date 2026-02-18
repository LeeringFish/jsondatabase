package client;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;

public class Main {
    @Parameter(names = "-t", description = "Name of command")
    String commandName;

    @Parameter(names = "-i", description = "Index of database")
    Integer index;

    @Parameter(names = "-m", description = "Text to use with set command")
    String newText = "";

    public static void main(String[] args) {
        String address = "127.0.0.1";
        int port = 23456;

        Main main = new Main();
        JCommander.newBuilder()
                .addObject(main)
                .build()
                .parse(args);

        String request = main.buildRequest();
        Client client = new Client(address, port, request);
        client.run();
    }

    String buildRequest() {
        return String.format("%s %d %s", commandName, index, newText);
    }
}

package client;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;

public class Main {
    @Parameter(names = "-t", description = "Type of command")
    String commandName;

    @Parameter(names = "-k", description = "Key")
    Integer key;

    @Parameter(names = "-v", description = "Value to use with set command")
    String value = "";

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
        return String.format("%s %d %s", commandName, key, value);
    }
}
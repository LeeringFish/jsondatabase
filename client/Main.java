package client;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.google.gson.Gson;

public class Main {
    @Parameter(names = "-t", description = "Type of command", required = true)
    String type;

    @Parameter(names = "-k", description = "Key")
    String key = null;

    @Parameter(names = "-v", description = "Value to use with set command")
    String value = null;

    public static void main(String[] args) {
        String address = "127.0.0.1";
        int port = 23456;

        Main main = new Main();
        JCommander.newBuilder()
                .addObject(main)
                .build()
                .parse(args);

        Request request = new Request(main.type, main.key, main.value);
        Gson gson = new Gson();
        String requestJson = gson.toJson(request);
        Client client = new Client(address, port, requestJson);
        client.run();
    }

    record Request(String type, String key, String value) {}

}
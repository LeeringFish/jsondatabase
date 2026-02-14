package server;

import java.util.Arrays;

public class Server {
    private String[] data;

    public Server() {
        data = new String[1000];
        Arrays.fill(data, "");
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

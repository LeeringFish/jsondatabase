package server;

import java.util.Arrays;

public class Database {
    private String[] data;

    public Database() {
        this.data = new String[1000];
        Arrays.fill(data, "");
    }

    public String get(int index) {
        if (index < 0 || index > 999 || "".equals(data[index])) {
            return "ERROR";
        }

        return data[index];
    }

    public String set(int index, String text) {
        if (index < 0 || index > 999) {
            return "ERROR";
        }

        data[index] = text;
        return "OK";
    }

    public String delete(int index) {
        if (index < 0 || index > 999) {
            return "ERROR";
        }

        data[index] = "";
        return "OK";
    }

}

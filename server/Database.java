package server;

import java.util.Arrays;

public class Database {
    private String[] data;

    public Database() {
        this.data = new String[1000];
        Arrays.fill(data, "");
    }

    public String get(int index) {
        if (index <= 0 || index > 999 || "".equals(data[index - 1])) {
            return "ERROR";
        }

        return data[index - 1];
    }

    public String set(int index, String text) {
        if (index <= 0 || index > 999) {
            return "ERROR";
        }

        data[index - 1] = text;
        return "OK";
    }

    public String delete(int index) {
        if (index <= 0 || index > 999) {
            return "ERROR";
        }

        data[index - 1] = "";
        return "OK";
    }

}

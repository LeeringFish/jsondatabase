package server;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Database {
    private Map<String, String> data;

    public Database() {
        data = new HashMap<>();
    }

    public String get(String key) {
        String result = data.get(key);

        if (result == null) {
            return "ERROR";
        }

        return result;
    }

    public String set(String key, String value) {
        data.put(key, value);
        return "OK";
    }

    public String delete(String key) {
        String removed = data.remove(key);

        if (removed == null) {
            return "ERROR";
        }

        return "OK";
    }

}

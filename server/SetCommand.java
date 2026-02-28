package server;

public class SetCommand implements Command {
    private final Database db;
    private final String key;
    private final String value;
    private String result;

    public SetCommand(Database db, String key, String value) {
        this.db = db;
        this.key = key;
        this.value = value;
    }

    public void execute() {
        this.result = db.set(key, value);
    }

    public String getResult() {
        return this.result;
    }
}

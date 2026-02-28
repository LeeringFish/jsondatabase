package server;

public class GetCommand implements Command {
    private final Database db;
    private final String key;
    private String result;

    public GetCommand(Database db, String key) {
        this.db = db;
        this.key = key;
    }

    public void execute() {
        this.result = db.get(key);
    }

    public String getResult() {
        return result;
    }

}

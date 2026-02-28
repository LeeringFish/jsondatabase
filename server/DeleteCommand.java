package server;

public class DeleteCommand implements Command {
    private final Database db;
    private final String key;
    private String result;

    public DeleteCommand(Database db, String key) {
        this.db = db;
        this.key = key;
    }

    public void execute() {
        this.result = db.delete(key);
    }

    public String getResult() {
        return this.result;
    }

}

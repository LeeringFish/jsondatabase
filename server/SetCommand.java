package server;

public class SetCommand implements Command {
    private final Database db;
    private final int index;
    private final String newText;
    private String result;

    public SetCommand(Database db, int index, String newText) {
        this.db = db;
        this.index = index;
        this.newText = newText;
    }

    public void execute() {
        this.result = db.set(index, newText);
    }

    public String getResult() {
        return this.result;
    }
}

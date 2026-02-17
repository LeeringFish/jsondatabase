package server;

public class DeleteCommand implements Command {
    private final Database db;
    private final int index;
    private String result;

    public DeleteCommand(Database db, int index) {
        this.db = db;
        this.index = index;
    }

    public void execute() {
        this.result = db.delete(index);
    }

    public String getResult() {
        return this.result;
    }

}

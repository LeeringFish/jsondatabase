package server;

public class GetCommand implements Command {
    private final Database db;
    private final int index;
    private String result;

    public GetCommand(Database db, int index) {
        this.db = db;
        this.index = index;
    }

    public void execute() {
        this.result = db.get(index);
    }

    public String getResult() {
        return result;
    }

}

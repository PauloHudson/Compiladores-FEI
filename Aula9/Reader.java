public class Reader implements  Runnable {
    int ID;
    Database database;

    public Reader(int ID, Database database) {
        this.ID = ID;
        this.database = database;
    }

    @Override
    public void run() {
        ProcessUtil.atrasar(5);
        database.read(ID);
        }
    }
    
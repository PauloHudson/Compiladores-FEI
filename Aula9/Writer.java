public class Writer implements Runnable {
    int ID;
    Database database;

    public Writer(int ID, Database database) {
        this.ID = ID;
        this.database = database;
    }

    @Override
    public void run() {
        ProcessUtil.atrasar(5);
        database.write(ID);
    }
}
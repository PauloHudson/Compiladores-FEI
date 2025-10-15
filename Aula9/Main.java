public class Main{
    public static void main(String[] args) {
        Database database = new Database();

        Thread[] readerArray = new Thread[5];
        Thread[] writerArray = new Thread[2];

        for (int i = 0; i < 3; i++) {
            readerArray[i] = new Thread(new Reader(i, database));
            readerArray[i].start();
        }


        for (int i = 0; i < 2; i++) {
            writerArray[i] = new Thread(new Writer(i, database));
            writerArray[i].start();
        }
    }
}
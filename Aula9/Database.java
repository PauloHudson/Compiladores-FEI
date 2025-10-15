public class Database {
    int nrReaders;
    boolean isWriting;
    Object lock = new Object();

    public Database() {
       this.nrReaders = 0;
       this.isWriting = false;
       lock = new Object();
    }

    public void read(int ID) {
        synchronized (lock) {
            while (isWriting) {
                bloqueia();
            }
            nrReaders++;
        }
        System.out.println("R: is reading " + ID + " qtde: " + nrReaders);
        ProcessUtil.atrasar(2);
        System.out.println("R: finished reading " + ID + " qtde: " + nrReaders);
        synchronized (lock) {
            nrReaders--;
            if (nrReaders == 0) {
                lock.notifyAll();
            }
         }
    }
    
    //----

    private void bloqueia(){
     try {
         lock.wait();
    } catch (InterruptedException e) {
        e.printStackTrace();
         }   
    }

    //----

    public void write(int ID) {
        synchronized (lock) {
            if(isWriting || nrReaders > 0) {
                bloqueia();
            }
            isWriting = true;
        }
        System.out.println("W: is writing " + ID );
        ProcessUtil.atrasar(2);
        System.out.println("W: finished writing " + ID );
        synchronized (lock) {
            lock.notifyAll();
            isWriting = false;

        }
        
    }
}   
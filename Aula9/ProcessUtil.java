public class ProcessUtil {
    public static void atrasar(int tempo) {
    int tempoAtraso = (int) (Math.random() * tempo); 
        try {
            Thread.sleep(tempoAtraso * 1000);
        } catch (InterruptedException e) {
        }
    }
}
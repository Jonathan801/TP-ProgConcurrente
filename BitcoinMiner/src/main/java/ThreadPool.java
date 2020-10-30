import java.util.ArrayList;

public class ThreadPool {

    private Buffer buffer;
    private int cantidadThreads;
    private ArrayList<PowWorker> workers = new ArrayList<PowWorker>();

    public ThreadPool(Buffer buffer,int cantidadThreads,int dificultad) {
        this.buffer = buffer;
        this.cantidadThreads = cantidadThreads;
        for (int i = 0; i < cantidadThreads; i++) {
            PowWorker pow = new PowWorker(this.buffer,this,dificultad);
            pow.start();
            workers.add(pow);//me guardo el pow para avisarle despues
            System.out.println("Etapa : Se creo y inicio el thread " + i);
        }
    }
    public void seEncontroNonce(PowWorker pow,int nonce){
        for (int indice=0; indice<cantidadThreads; indice ++){
            workers.get(indice).seEncontro();
        }
        pow.seEncontroNonceCorrecto(nonce);
    }

    public void noSeEncontroNonce(PowWorker pow) {
        if (cantidadThreads > 1) {
            cantidadThreads--;
        } else {
            pow.noSeEncontroNonceCorrecto();
        }
    }
}

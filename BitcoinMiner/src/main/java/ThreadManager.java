public class ThreadManager {
    int cantidadDeThreads;
    boolean encontrado = false;

    public ThreadManager(int cantidad){
        this.cantidadDeThreads = cantidad;
    }

    public void seEncontroNonce(PowWorker pow){
        if(!encontrado){
            pow.algo();
        }
    }

    public void noSeEncontroNonce(PowWorker pow){
        if(cantidadDeThreads>0){
            cantidadDeThreads -- ;
        }else{
            pow.otroAlgo();
        }
    }
}

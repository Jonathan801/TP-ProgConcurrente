public class ThreadManager {
    int cantidadDeThreads;
    boolean encontrado = false;

    public ThreadManager(int cantidad){
        this.cantidadDeThreads = cantidad;
    }

    public void seEncontroNonce(PowWorker pow,int nonce){
        if(!encontrado){
            pow.seEncontroNonceCorrecto(nonce);
        }
    }

    public void noSeEncontroNonce(PowWorker pow){
        if(cantidadDeThreads>1){
            cantidadDeThreads -- ;
        }else{
            pow.noSeEncontroNonceCorrecto();
        }
    }
}

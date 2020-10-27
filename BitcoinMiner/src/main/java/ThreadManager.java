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
        System.out.println("Cantidad de threads en manager " + cantidadDeThreads);
        if(cantidadDeThreads>1){
            cantidadDeThreads -- ;
        }else{
            pow.otroAlgo();
        }
        System.out.println("Comprobacion de perdida de sumas " + cantidadDeThreads);
    }
}

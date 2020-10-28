public class ThreadPool {

    private Buffer buffer;
    private int cantidadThreads;
    private ThreadManager manager ;
    private  int dificultad ;

    public ThreadPool(Buffer buffer,int cantidadThreads,int dificultad) {
        this.buffer = buffer;
        this.cantidadThreads = cantidadThreads;
        this.manager = new ThreadManager(cantidadThreads);
        this.dificultad = dificultad;
    }

    public void launch(){
        for (int i = 0; i < cantidadThreads; i++) {
            PowWorker pow = new PowWorker(this.buffer,this,dificultad,manager);
            pow.start();
            System.out.println("Etapa : Se creo y inicio el thread " + i);
        }
    }
}

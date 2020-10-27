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
        //System.out.println("Etapa : Justo antes del for de la creacon de worker");
        //Logica de tiempo/reloj?
    }

    public void launch(){
        for (int i = 0; i < cantidadThreads; i++) {
            System.out.println("Etapa : Justo antes del new" + " " +i);
            PowWorker pow = new PowWorker(this.buffer,this,dificultad,manager);
            pow.run();
            System.out.println("TERMINO EL RUN DEL THREAD " + i);
        }
    }

    public void killAll() {
        //Logica de detener los threads
        for(int i=0;i < cantidadThreads;i++){
            //TODO
        }
    }
}

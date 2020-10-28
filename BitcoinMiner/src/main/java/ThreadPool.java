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
        //Logica de tiempo/reloj?
    }

    public void launch(){
        for (int i = 0; i < cantidadThreads; i++) {
            System.out.println("Etapa : Justo antes del new" + " " +i);
            PowWorker pow = new PowWorker(this.buffer,this,dificultad,manager);
            pow.start();
        }
    }

    public void killAll() {
        //Logica de detener los threads
        for(int i=0;i < cantidadThreads;i++){
            //hacer
        }
    }
}

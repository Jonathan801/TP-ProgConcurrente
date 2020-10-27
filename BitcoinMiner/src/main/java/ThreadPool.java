public class ThreadPool {

    private Buffer buffer;
    private int cantidadThreads;

    public ThreadPool(Buffer buffer,int cantidadThreads,int dificultad) {
        this.buffer = buffer;
        this.cantidadThreads = cantidadThreads;
        for (int i = 0; i < cantidadThreads; i++) {
            new PowWorker(this.buffer,this,dificultad);
        }
        //Logica de tiempo/reloj?
    }

    public void killAll() {
        //Logica de detener los threads
        for(int i=0;i < cantidadThreads;i++){
            //TODO
        }
    }
}

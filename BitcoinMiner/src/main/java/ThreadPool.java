public class ThreadPool {

    private Buffer buffer;

    public ThreadPool(Buffer buffer){
        this.buffer = buffer;
    }

    public void iniciar(int threadsAmmount){
        //create the powWorkers
        for(int indice = 0;indice<threadsAmmount;indice++){
            new PowWorker(buffer);
        }

    }
}

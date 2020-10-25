public class ThreadPool {

    private Buffer buffer;

    public ThreadPool(Buffer buffer){
        this.buffer = buffer;
    }

    public iniciar(int threadsAmmount){
        //create the powWorkers
        for(threadsAmmount){
            new PowWorker(buffer);
        }

    }
}

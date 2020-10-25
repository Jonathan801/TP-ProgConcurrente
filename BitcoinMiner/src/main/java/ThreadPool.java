public class ThreadPool {

    private Buffer buffer;

    public ThreadPool(Buffer buffer){
        this.buffer = buffer;

    }

    public iniciar(int threads){
        //create the powWorkers
        for(threads){
            new PowWorker();
        }

    }
}

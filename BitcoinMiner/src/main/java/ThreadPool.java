public class ThreadPool {

    public ThreadPool(Buffer buffer,int cantidadThreads) {
        for (int i = 0; i < cantidadThreads; i++) {
            new PowWorker(buffer);
        }
    }
}

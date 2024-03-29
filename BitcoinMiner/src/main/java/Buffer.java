public class Buffer {
    int cantidadBuffer;
    private UnidadDeTrabajo[] data ;
    private int begin = 0, end = 0;

    public Buffer(int n){
        if(n>=0 && n<2){
            cantidadBuffer=n;
        }else{
            cantidadBuffer=2;
        }
        data = new UnidadDeTrabajo[cantidadBuffer + 1];
    }

    public synchronized void write ( UnidadDeTrabajo o ){
        while (isFull()){
            try { wait();
            }
            catch (InterruptedException ignored) {
            }
        }
        data [begin] = o;
        begin = next (begin);
        notifyAll();
    }
    public synchronized UnidadDeTrabajo read(){
        while (isEmpty()) {
            try { wait(); }
                catch (InterruptedException ignored) {
            }
        }
        UnidadDeTrabajo result = data[end];
        end = next ( end );
        notifyAll();
        return result ;
    }

    private boolean isEmpty() {return begin == end;}
    private boolean isFull() {return next (begin) == end;}
    private int next(int i) {return (i +1) % (cantidadBuffer + 1);}
}



public class Buffer {
    private Object [] data = new Object[N +1];
    private int begin = 0, end = 0;

    public synchronized void write ( Object o ) {
        while ( isFull()) wait();
        data [begin] = o;
        begin = next (begin);
        notifyAll();
    }
    public synchronized Object read () {
        while (isEmpty()) wait();
        Object result = data [ end ];
        end = next ( end );
        notifyAll();
        return result ;
    }

    private boolean isEmpty() {return begin == end;}
    private boolean isFull() {return next (begin) == end;}
    private int next(int i) {return (i +1) % (N +1);}
}

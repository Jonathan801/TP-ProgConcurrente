import com.sun.org.apache.xpath.internal.objects.XObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PowWorker extends Thread implements Runnable {

    //algo = MessageDigest.getInstance("SHA-256")
    //obtiene una unidad de trabajo
    //agarra aplica el hash
    //Bytes bytes =  algo.digest(unidadDeTrabajo)

    //retorna posible nonce?
    private final Buffer buffer;
    private MessageDigest algo;
    public PowWorker(Buffer buffer) {
        this.buffer = buffer;
        try {
            algo = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
    public void run() {
        UnidadDeTrabajo unidadDeTrabajo = buffer.read();
        for (int indice = unidadDeTrabajo.getMinimo(); indice < unidadDeTrabajo.getMaximo(); indice++){
            Byte[] bytes =  algo.digest(indice);
            return (bytes, unidadDeTrabajo);
        }
        Object numero = 15.toBytes();
    }
}
/*
int resto = 2^32%cantThreads;
int r = 2^32/cantThreads;
for (int i = 0; i < cantThreads; i++){
    min = i*r;
    max = (r * (i +1)) -1;
    if (i == cantThreads -1){max = max + resto}
}

int resto = 2^32%cantThreads;
int r = 2^32/cantThreads;
min = 0;
max =
for (int i = 0; i < cantThreads; i++){
    buffer.write()
    min = i*r;
    max = (r * (i +1)) -1;
    if (i == cantThreads -1){max = max + resto}
}
        */

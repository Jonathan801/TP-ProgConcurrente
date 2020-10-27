import com.sun.org.apache.xpath.internal.objects.XObject;

import java.math.BigInteger;
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
        int maximo = unidadDeTrabajo.getMaximo();
        int minimo = unidadDeTrabajo.getMinimo();
        for (int indice = minimo; indice < maximo; indice++){
            //Esto de aca serve para concatenar las dos cadenas de byte
//            byte[] one = unidadDeTrabajo.getTexto().getBytes();
//            byte[] two = BigInteger.valueOf(indice).toByteArray();
//            byte[] combined = new byte[one.length + two.length];
//            System.arraycopy(one,0,combined,0         ,one.length);
//            System.arraycopy(two,0,combined,one.length,two.length);
            //Seria result = algo.digest(combined);
            byte[] bytes =  algo.digest(BigInteger.valueOf(indice).toByteArray());
            buffer2.write(new Intento(bytes, indice,indice == maximo));


        }
    }
}

import com.sun.org.apache.xpath.internal.objects.XObject;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PowWorker extends Thread implements Runnable {

    private final Buffer buffer;
    private MessageDigest algo;
    private ThreadPool pool;
    private UnidadDeTrabajo unidad;
    private boolean encontrado = false;
    private int dificultad;

    public PowWorker(Buffer buffer,ThreadPool pool,int dificultad) {
        this.buffer = buffer;
        this.pool = pool;
        this.unidad = buffer.read(); //?????
        this.dificultad = dificultad;
        try {
            algo = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
    public void run() {
        int maximo = unidad.getMaximo();
        int minimo = unidad.getMinimo();
        int indice = minimo;
        //for (int indice = minimo; indice < maximo; indice++){
        while(!encontrado && indice < maximo ){
            //Esto de aca serve para concatenar las dos cadenas de byte
            byte[] one = unidad.getTexto().getBytes();
            byte[] two = BigInteger.valueOf(indice).toByteArray();
            byte[] combined = new byte[one.length + two.length];
            System.arraycopy(one,0,combined,0         ,one.length);
            System.arraycopy(two,0,combined,one.length,two.length);
            byte[] result = algo.digest(combined);
            indice++;
            //Logica de comprobacion
            this.validation(result);
        }
        if(!encontrado){
            //Logica de fallo
        }else{
            pool.killAll();

        }
    }

    public void validation(byte[] resultado){
        for(int indice = 0;indice<this.dificultad;indice++){
            encontrado=resultado[indice] == 0;
        }
    }


}

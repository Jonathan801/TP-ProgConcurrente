import com.sun.org.apache.xpath.internal.objects.XObject;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PowWorker extends Thread implements Runnable {

    private final Buffer buffer;
    private MessageDigest algo;
    private UnidadDeTrabajo unidad;
    private ThreadManager manager;
    private boolean encontrado = false;
    private int dificultad;
    private ThreadPool pool;//Posible borrada

    public PowWorker(Buffer buffer,ThreadPool pool,int dificultad,ThreadManager manager) {
        this.buffer = buffer;
        this.pool = pool;
        this.dificultad = dificultad;
        this.manager = manager;
        try {
            algo = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

    }
    public void run() {
        System.out.println("Etapa : dentro del run del worker");
        this.unidad = buffer.read();
        System.out.println("Etapa : dentro del run del worker y desoues del buffer");
        int maximo = unidad.getMaximo();
        int minimo = unidad.getMinimo();
        int indice = minimo;
        System.out.println("Antes del while " + minimo);
        System.out.println("Antes del while " + maximo);
        while(!encontrado && indice < maximo ){ // No esta recorriendo todos los numeros(corriendolo solo recorrio 1..4)
            this.validation(indice);
            System.out.println("Estoy mirando el numero " + indice);
            indice++;
            ;
        }
        System.out.println("El valor de encontrado es " + encontrado);
        if(!encontrado){
            manager.noSeEncontroNonce(this);
        }else{
            manager.seEncontroNonce(this);

        }
    }

    private void validation(int nonceCandidato){
        boolean local = true;
        //Esto de aca serve para concatenar las dos cadenas de byte
        byte[] one = unidad.getTexto().getBytes();
        byte[] two = BigInteger.valueOf(nonceCandidato).toByteArray();
        byte[] combined = new byte[one.length + two.length];
        System.arraycopy(one,0,combined,0         ,one.length);
        System.arraycopy(two,0,combined,one.length,two.length);
        byte[] result = algo.digest(combined);

        for(int indice = 0;indice<this.dificultad;indice++){
            local = local && result[indice] == 0;
        }
        encontrado = local;
    }


    public void algo() { // caso exitoso
        //Paro mi reloj interno y lo imprimo con el nonce
        System.out.println("We are the champions");
    }

    public void otroAlgo() { //caso F , si llego a este mensaje fui el ultimo thread en fallar
        //Paro mi reloj interno y lo imprimo sin el nonce(ya que falle dahhh) y pidiendo perdon
        System.out.println("No me peges");
    }
}

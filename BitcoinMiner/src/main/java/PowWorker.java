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
    private long inicio;
    private ThreadPool pool;//Posible borrada
    private int nonceCorrecto;

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
        //System.out.println("Worker : Antes de obtener la unidad de trabajo");
        this.unidad = buffer.read();
        this.inicio = System.currentTimeMillis();
        //System.out.println("Worker : Ya con la unidad de trabajo");
        int maximo = unidad.getMaximo();
        int minimo = unidad.getMinimo();
        int indice = minimo;
        //System.out.println("Worker : Antes del while , el valor minimo es " + minimo);
        //System.out.println("Worker : Antes del while , el valor maximo es " + maximo);
        while(!encontrado && indice < maximo ){
            this.validation(indice);
            //System.out.println("Actualmente el nonse es " + indice);
            indice++;
        }
        System.out.println("Worker : Se hallo el nonce correcto es " + encontrado);
        if(!encontrado){
            manager.noSeEncontroNonce(this);
        }else{
            manager.seEncontroNonce(this,indice - 1);

        }
    }

    private void validation(int nonceCandidato){
        boolean local = true;
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

    public void seEncontroNonceCorrecto(int nonce) { // caso exitoso
        long fin = System.currentTimeMillis();
        double tiempo = (double) ((fin - this.inicio));
        System.out.println(tiempo + " segundos");
        System.out.println("Worker : Se enconctro el nonce correcto y es " + nonce);
    }

    public void noSeEncontroNonceCorrecto() { //caso F , si llego a este mensaje fui el ultimo thread en fallar
        //Paro mi reloj interno y lo imprimo sin el nonce(ya que falle dahhh) y pidiendo perdon
        long fin = System.currentTimeMillis();
        double tiempo = (double) ((fin - this.inicio));
        System.out.println(tiempo + " segundos");
        System.out.println("Worker : No se logro encontrar en ningun Worker el Nonce Correcto");
    }
}

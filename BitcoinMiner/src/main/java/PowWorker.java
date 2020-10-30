import com.sun.org.apache.xpath.internal.objects.XObject;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PowWorker extends Thread implements Runnable {

    private ThreadPool pool;
    private MessageDigest sha256;
    private UnidadDeTrabajo unidad;
    private final Buffer buffer;
    private boolean encontrado = false;
    private boolean alguienLoEncontro = false;
    private int dificultad;
    private long inicioTimer;

    public PowWorker(Buffer buffer,ThreadPool pool,int dificultad) {
        this.buffer = buffer;
        this.pool = pool;
        this.dificultad = dificultad;
        try {
            sha256 = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

    }
    public void run() {
        this.inicioTimer = System.currentTimeMillis();
        this.unidad = buffer.read();

        int maximo = unidad.getMaximo();
        int minimo = unidad.getMinimo();
        int indice = minimo;

        while(!encontrado && !alguienLoEncontro && indice < maximo ){
            this.validation(indice);
            indice++;
        }
        if(!encontrado&&!alguienLoEncontro){
            pool.noSeEncontroNonce(this);
        }else if(encontrado){
            pool.seEncontroNonce(this,indice - 1);
        }
        //System.out.println("Worker Se termino mi ejecucion");
    }

    private void validation(int nonceCandidato){
        boolean local = true;
        byte[] one = unidad.getTexto().getBytes();
        byte[] two = BigInteger.valueOf(nonceCandidato).toByteArray();
        byte[] combined = new byte[one.length + two.length];
        System.arraycopy(one,0,combined,0         ,one.length);
        System.arraycopy(two,0,combined,one.length,two.length);
        byte[] result = sha256.digest(combined);
        for(int indice = 0;indice<this.dificultad;indice++){
            local = local && result[indice] == 0;
        }
        encontrado = local;
    }

    public void seEncontroNonceCorrecto(int nonce) {
        long finTimer = System.currentTimeMillis();
        int tiempo = (int) ((finTimer - this.inicioTimer));
        String rta = String.format("Worker: Se encontro el nonce correcto y es %s y tardo %d milisegundos", nonce, tiempo);
        System.out.println(rta);
    }

    public void noSeEncontroNonceCorrecto() {
        long finTimer = System.currentTimeMillis();
        double tiempo = (double) ((finTimer - this.inicioTimer));
        String rta = String.format("No se logro encontrar en ningun Worker el Nonce Correcto y se tardo %d milisegundos",tiempo);
        System.out.println(rta);
    }

    public void seEncontro(){
        alguienLoEncontro = true;
    }
}

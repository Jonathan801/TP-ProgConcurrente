import java.math.BigInteger;
import java.util.Scanner;


public class Main {

    private static boolean loEncontro=false;
    private int nonce;
    private int dificultad;

    public void main(String[] args) {
        //Atrapar los input del usuario
        Scanner input = new Scanner(System.in);
        System.out.print("Ingresar cantidad de threads: ");
        int cantidadThreads = input.nextInt();
        System.out.print("Ingresar dificultad: ");
        dificultad = input.nextInt();
        System.out.print("Ingresar un String : ");
        String texto = input.next();

        Buffer buffer = new Buffer();

        //Logica de calcular/preparar las unidades de trabajo
        int r = 2 ^ 32 / cantidadThreads;
        int resto = 2 ^ 32 % cantidadThreads;
        for (int i = 0; i < cantidadThreads; i++) {
            int minimo = i * r;
            int maximo = (r * (i + 1));
            if (i == cantidadThreads - 1) {
                maximo = maximo + resto;
            }
            UnidadDeTrabajo unidad = new UnidadDeTrabajo(minimo,maximo,texto);
            buffer.write(unidad);
        }

        //Creo los threads y les paso el buffer
        ThreadPool threadPool = new ThreadPool(buffer, cantidadThreads);
        //Logica de comprobacion si se encontro el nonse
        int threadsFailed = 0;
        while (loEncontro || (threadsFailed < cantidadThreads)) {
            Intento intento = buffer2.read();
            loEncontro = this.validar(intento.numero(), intento.nonce());
            if (!loEncontro && intento.ultimo()) {
                threadsFailed++;
            }
        }

        if (loEncontro) {
            System.out.println("lo encontre! Es: " + nonce);
        } else {
            System.out.println("no se encontro nonce");
        }
    }

    private boolean validar(byte[] numeroAValidar, int nonce){
        boolean local = true;
        for(int i = 0; i <this.dificultad; i++){
            local = local && (numeroAValidar[i] == 0);
        }
        if (local){this.nonce = nonce;}
        return local;
    }


}


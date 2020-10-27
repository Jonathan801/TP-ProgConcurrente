import java.math.BigInteger;
import java.util.Scanner;


public class Main {

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
            UnidadDeTrabajo unidad = new UnidadDeTrabajo(minimo, maximo, texto);
            buffer.write(unidad);
        }

        //Creo los threads y les paso el buffer
        ThreadPool threadPool = new ThreadPool(buffer, cantidadThreads, dificultad);
    }


}


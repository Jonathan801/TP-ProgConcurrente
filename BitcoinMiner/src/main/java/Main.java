import java.math.BigInteger;
import java.util.Scanner;


public class Main {

    private static int dificultad;

    public static void main(String[] args) {
//        //Atrapar los input del usuario
//        Scanner input = new Scanner(System.in);
//        System.out.print("Ingresar cantidad de threads: ");
//        int cantidadThreads = input.nextInt();
//        System.out.print("Ingresar dificultad: ");
//        dificultad = input.nextInt();
//        System.out.print("Ingresar un String : ");
//        String texto = input.next();
//        System.out.println("1");
//        Buffer buffer = new Buffer();
//        System.out.println("2");

        //Atrapar los input del usuario
        int cantidadThreads = 2;
        dificultad = 2;
        String texto = "";

        Buffer buffer = new Buffer(2);

        System.out.println("Pre-Etapa : Creo los workers con el pool");
        ThreadPool threadPool = new ThreadPool(buffer, cantidadThreads, dificultad);
        System.out.println("Los Workers se lanzaron");

        long elevado = (long) Math.pow(2,32); // 2^32
        System.out.println(elevado);
        long r = elevado / cantidadThreads;
        long resto = elevado % cantidadThreads;
        int i = 0;
        while(i<cantidadThreads) {
            long minimo = i * r;
            long maximo = (r * (i + 1));
            //bit = resto>0? 1 : 0;
            //resto--;
            //int maximo = (r * (i + 1)) + bit;
            if (i == cantidadThreads - 1) {
                maximo = maximo + resto;
            }
            UnidadDeTrabajo unidad = new UnidadDeTrabajo(minimo, maximo, texto);
            buffer.write(unidad);
            //System.out.println("Se creo la Unidad de trabajo numero " + i);
            i++;
        }

    }


}


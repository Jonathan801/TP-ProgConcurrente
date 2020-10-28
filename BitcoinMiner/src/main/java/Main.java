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
        int cantidadThreads = 10;
        dificultad = 4;
        String texto = "";

        Buffer buffer = new Buffer();

        ThreadPool threadPool = new ThreadPool(buffer, cantidadThreads, dificultad);
        System.out.println("Pre-Etapa : Creo los workers con el pool");

        threadPool.launch();

        System.out.println("Los Workers se lanzaron");

        int elevado = (int) Math.pow(2,32);
        int r = elevado / cantidadThreads;
        int resto = elevado % cantidadThreads;
        int i = 0;
        while(i<cantidadThreads) {
            int minimo = i * r;
            int maximo = (r * (i + 1));
            if (i == cantidadThreads - 1) {
                maximo = maximo + resto;
            }
            UnidadDeTrabajo unidad = new UnidadDeTrabajo(minimo, maximo, texto);
            buffer.write(unidad);
            System.out.println("Se creo la Unidad de trabajo numero " + i);
            i++;
        }

    }


}


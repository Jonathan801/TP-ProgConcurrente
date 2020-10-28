import java.math.BigInteger;
import java.util.Scanner;


public class Main {

    private static int dificultad;
    private int nonce;

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
        int cantidadThreads = 4;
        dificultad = 2;
        String texto = "";
        System.out.println("Etapa : Asignacion Variables de Input");

        Buffer buffer = new Buffer();
        System.out.println("Etapa : Creo Buffer");


        ThreadPool threadPool = new ThreadPool(buffer, cantidadThreads, dificultad);
        System.out.println("Etapa : Creo los workers con el pool");

        threadPool.launch();


        //Logica de calcular/preparar las unidades de trabajo

        int elevado = (int) Math.pow(2,32);
        int r = elevado / cantidadThreads;
        int resto = elevado % cantidadThreads;
        int i = 0;
        while(i<cantidadThreads) {
            int minimo = i * r;
            int maximo = (r * (i + 1));
            if (i == cantidadThreads - 1) {
                maximo = maximo + resto;
                i++;
            }
            UnidadDeTrabajo unidad = new UnidadDeTrabajo(minimo, maximo, texto);
            buffer.write(unidad);
            System.out.println("Dentro del creador de unidad  i= " + i);
            i++;
        }




    }


}


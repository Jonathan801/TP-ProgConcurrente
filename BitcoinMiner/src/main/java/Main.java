import java.util.Scanner;


public class Main {

    private static int dificultad;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Ingresar cantidad de threads: ");
        int cantidadThreads = input.nextInt();
        System.out.print("Ingresar dificultad: ");
        dificultad = input.nextInt();
        System.out.print("Ingresar un String : ");
        String texto = input.next();

        Buffer buffer = new Buffer(2);

        ThreadPool threadPool = new ThreadPool(buffer, cantidadThreads, dificultad);

        int elevado = (int) Math.pow(2,32);
        int r = elevado / cantidadThreads;
        int resto = elevado % cantidadThreads;
        int i = 0;
        while(i<cantidadThreads) {
            int minimo = i * r;
            int maximo = (r * (i + 1));
            //bit = resto>0? 1 : 0;
            //resto--;
            //int maximo = (r * (i + 1)) + bit;
            if (i == cantidadThreads - 1) {
                maximo = maximo + resto;
            }
            UnidadDeTrabajo unidad = new UnidadDeTrabajo(minimo, maximo, texto);
            buffer.write(unidad);
            i++;
        }

    }


}


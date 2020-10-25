import java.util.Scanner;


public class Main {

    public static void main ( String [] args ) {
        Scanner input = new Scanner(System.in);
        System.out.print("Ingresar cantidad de threads: ");
        int threads = input.nextInt();
        System.out.print("Ingresar dificultad: ");
        int dificultad = input.nextInt();
        System.out.print("Ingresar un String : ");
        String texto = input.next();
        Buffer buffer = new Buffer ();
        int r = 2^32/threads;
        int resto = 2^32%threads;
        for (int i = 0; i < threads; i++){
            int minimo = i*r;
            int maximo = (r * (i +1));
            if (i == threads -1){
                maximo = maximo + resto;
            }
        }
        ThreadPool threadPool = new ThreadPool(buffer);




        //
//        if(encontro){
//            System.out.println("el nonce encontrado es: "+ nonce);
//        }
//        else{
//            System.out.println("no se encontro nonce");
//        }
    }


}

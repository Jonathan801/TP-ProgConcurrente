import java.util.Scanner;


public class Main {

    private static boolean loEncontro=false;
    private int dificultad;
    private int nonce;

    public void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Ingresar cantidad de threads: ");
        int threads = input.nextInt();
        System.out.print("Ingresar dificultad: ");
        dificultad = input.nextInt();
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
        int threadsFailed = 0;
        while (loEncontro || (threadsFailed < threads)){
            Intento intento = buffer2.read();
            loEncontro = this.validar(intento.numero(), intento.nonce());
            if (!loEncontro && intento.ultimo()){
                threadsFailed ++;
            }
        }
        if (loEncontro){
            System.out.println("lo encontre! Es: "+ nonce);
        }else{System.out.println("no se encontro nonce");}


        //
//        if(encontro){
//            System.out.println("el nonce encontrado es: "+ nonce);
//        }
//        else{
//            System.out.println("no se encontro nonce");
//        }
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

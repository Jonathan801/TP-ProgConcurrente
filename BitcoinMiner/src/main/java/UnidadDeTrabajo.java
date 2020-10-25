public class UnidadDeTrabajo {

    private final int minimo;
    private final int maximo;
    private final String texto;

    public UnidadDeTrabajo(int minimo, int maximo, String texto){
        this.minimo = minimo;
        this.maximo = maximo;
        this.texto  = texto;
    }
    public int getMinimo(){
        return this.minimo;
    }
    public int getMaximo(){
        return this.maximo;
    }
    public String getTexto(){
        return this.texto;
    }
}

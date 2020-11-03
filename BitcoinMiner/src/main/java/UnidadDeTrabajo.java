public class UnidadDeTrabajo {

    private final long minimo;
    private final long maximo;
    private final String texto;

    public UnidadDeTrabajo(long minimo, long maximo, String texto){
        this.minimo = minimo;
        this.maximo = maximo;
        this.texto  = texto;
    }

    public long getMinimo(){
        return this.minimo;
    }
    public long getMaximo(){
        return this.maximo;
    }
    public String getTexto(){
        return this.texto;
    }
}

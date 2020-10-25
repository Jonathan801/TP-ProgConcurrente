public class Intento {
    private final boolean ultimo;
    private final int nonce;
    private final byte[] numero;

    public Intento(byte[] bytes, int nonce, boolean b) {
        this.numero = bytes;
        this.nonce = nonce;
        this.ultimo = b;
    }

    public byte[] numero(){return numero;}
    public int nonce(){return nonce;}
    public boolean ultimo(){return ultimo;}
}

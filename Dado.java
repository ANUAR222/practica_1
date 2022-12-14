import java.util.Random;


public class Dado {
    private Random Numerodado = new Random();
    private int ultimoResultado = 1 + Numerodado.nextInt(6);
    private boolean debug;
    private static int SalidaCarcel = 5;
    private Dado instance;

    public int tirar(){
       if (debug == true) {
           ultimoResultado = 1;
       } return ultimoResultado;
    }

    public boolean salgoDeLaCarcel() {
        if(debug == true){
            boolean salgoDeLaCarcel = true;
        }
        return false;
    }
    public int quienEmpieza(int n){
        return 0;
    }

    public void setDebug (Boolean d){
        debug=d;
        if(debug==true) {
            civitas.Diario.ocurreEvento("debug");
        }

}
    public int getUltimoResultado(){
    return ultimoResultado;
    }
    public Dado getInstance(){
        return instance;
    }
}
import java.util.ArrayList;
import java.util.Random;


public class Dado {

    private ArrayList<Jugador> jugadores;

    private Random Numerodado = new Random();
    private int ultimoResultado = 1 + Numerodado.nextInt(6);
    private boolean debug;
    private static int SalidaCarcel = 5;
    private static Dado instance;



    public int tirar(){
       if (debug == true) {
           return 1;
       } else {return ultimoResultado;}
    }
    public boolean salgoDeLaCarcel() {
        if(debug == true){
            return true;
        } else if (tirar() >= SalidaCarcel){
            return true;
        } else{
            return false;
        }
    }
    public int quienEmpieza(int n){
        if(n>1){
            return n-1;
        } else {
            return 0;
        }
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
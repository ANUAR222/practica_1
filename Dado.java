import java.util.ArrayList;
import java.util.Random;


public class Dado {


    private Random Numerodado = new Random();
    private static int ultimoResultado;
    private static boolean debug;
    private static int SalidaCarcel = 5;
    private static Dado instance;



    public static int tirar(){
        ultimoResultado = (int)Math.floor(Math.random()*(6-0)+1);
       if (debug == true) {
           return 1;
       } else {return ultimoResultado;}
    }
    public static boolean salgoDeLaCarcel() {
        if(debug == true){
            return true;
        } else if (tirar() >= SalidaCarcel){
            return true;
        } else{
            return false;
        }
    }
    public int quienEmpieza(int n){
        return (int)Math.floor(Math.random()*(n-0));
    }

    public void setDebug (Boolean d){
        debug=d;
        if(debug==true) {
            Diario.ocurreEvento("debug");
        }
}
    public int getUltimoResultado(){
    return ultimoResultado;
    }
    public static Dado getInstance(){
        return instance;
    }

}
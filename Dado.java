import java.util.Random;


public class Dado {
    private Random Numerodado = new Random();
    private int ultimoResultado = 1 + Numerodado.nextInt(6);
    private boolean debug;
    private static int SalidaCarcel = 5;

    public int tirar(){
       if (debug == true) {
           ultimoResultado = 1;
       }
           return 0;
    }

    public static boolean salgoDeLaCarcel() {
        if(debug == true){
            boolean salgoDeLaCarcel = true;
        }
        return false;
    }
    public int quienEmpieza(int n){
        n=0;
        for (int e=1;e>=4;n--){
            System.out.println("El ");
            System.out.println(e);
            System.out.println("º en salir es ");
            System.out.println(n);
        }
        return -1;
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
}
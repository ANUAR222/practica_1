import java.util.Random;

public class Dado {
        int SalidaCarcel = 5;
    public static void main(String [] args){
        int inmovil;
        inmovil = 0;
        Random Numerodado = new Random();
        int ultimoResultado = 1 + Numerodado.nextInt(6);
        boolean permisodado;
        if (inmovil>0){
            permisodado = false;
            inmovil -= 1;
        } else {
            permisodado = true;
        }
        if(permisodado) {
            System.out.println(ultimoResultado);
        }
    }
}

import java.util.ArrayList;



public class Casilla {

    public int descanso;
    public int calle;
    public float impuesto;
    public float juez;
    public float sorpresa;


    public void init (){
        descanso = 0;
        calle = 0;
        impuesto = 100;
        juez = 0;
        sorpresa = 0;
    }


    public void informe (int actual, ArrayList<Jugador> todos){

        civitas.Diario.getInstance().ocurreEvento("La casilla es "+actual);
    }

    public void recibeJugador_impuesto (int actual, ArrayList<Jugador> todos){
         boolean pagaImpuesto;
         if (pagaImpuesto = true){
             impuesto = 500;
         }
    }

    public void recibeJugador_juez (int actual, ArrayList<Jugador> todos){
        boolean encarcelar;
        if (encarcelar = true){
            juez = 5;
        }
    }

    public String toString () {
        if (juez > 0) {
            return "Casilla que encarcela al jugador";
        } else if (descanso > 0) {
            return "Casilla para que el jugador descanse";
        } else {
            return "Casilla normal";
        }
    }

    Boolean jugadorCorrecto (int actual, ArrayList<Jugador> todos){
        if(juez == 0 && descanso == 0){
            return true;
        } else {
            return false;
        }
    }

}

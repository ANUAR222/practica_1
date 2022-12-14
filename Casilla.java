import java.util.ArrayList;



public class Casilla {

    private int carcel;
    private float importe;
    private String nombre;
    private TituloPropiedad titulo;
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

    public String getNombre(){
        return nombre;
    }

    private TituloPropiedad getTituloPropiedad(){
        return titulo;
    }


    public void informe (int actual, ArrayList<Jugador> todos){

        civitas.Diario.getInstance().ocurreEvento("La casilla es "+actual);

    }

    Boolean jugadorCorrecto (int actual, ArrayList<Jugador> todos){
        if(juez == 0 && descanso == 0){
            return true;
        } else {
            return false;
        }
    }
    private void recibeJugador (int actual, ArrayList<Jugador> todos){
        todos.add(new Jugador(getNombre()));
    }

    private void recibeJugador_calle (int actual, ArrayList<Jugador> todos){
        if (Sorpresas.jugadorCorrecto(actual,todos)){
            calle = 0;
        }
    }

    private void recibeJugador_impuesto (int actual, ArrayList<Jugador> todos){
            if (Sorpresas.jugadorCorrecto(actual,todos)) {
                impuesto = 500;
            }

    }

    private void recibeJugador_juez (int actual, ArrayList<Jugador> todos){
        if (Sorpresas.jugadorCorrecto(actual,todos)){
            juez = 5;
        }
    }
    private void recibeJugador_sorpresa (int actual, ArrayList<Jugador> todos){
        if (Sorpresas.jugadorCorrecto(actual,todos)){
            sorpresa = 3;
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



}

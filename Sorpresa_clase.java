

// Path: Sorpresa.java

import java.util.ArrayList;

public abstract class Sorpresa_clase {

    protected tablero tablero;
    private String texto;
    int valor;

    Sorpresa_clase(String texto){
        this.texto = texto;
    }

    public abstract void aplicarAJugador(int actual, ArrayList<Jugador> todos);

    void informe(int actual, ArrayList<Jugador> todos){
        civitas.Diario.getInstance().ocurreEvento("Se aplica la sorpresa " + texto + " al jugador " + todos.get(actual).getNombre());
    }

    public String toString(){
        return texto;
    }
}



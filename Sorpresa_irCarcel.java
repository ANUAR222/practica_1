import java.util.ArrayList;

public class Sorpresa_irCarcel extends Sorpresa_clase {

    Sorpresa_irCarcel(tablero tablero) {
        super("Ve a la carcel");
        this.tablero = tablero;
        valor = tablero.getCarcel();
    }

    @Override
    public void aplicarAJugador(int actual, ArrayList<Jugador> todos) {
        if (Sorpresas.jugadorCorrecto(actual, todos)) {
            informe(actual, todos);
            todos.get(actual).encarcelar(valor);
        }
    }
}

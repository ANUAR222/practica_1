import java.util.ArrayList;

public class Sorpresa_porJugador extends Sorpresa_clase {

    Sorpresa_porJugador(int valor, String texto) {
        super(texto);
        super.valor = valor;
    }

    @Override
    public void aplicarAJugador(int actual, ArrayList<Jugador> todos) {
        if (Sorpresas.jugadorCorrecto(actual, todos)) {
            informe(actual, todos);
            for (Jugador jugador : todos) {
                if (jugador != todos.get(actual)) {
                    jugador.modificarSaldo(valor);
                    todos.get(actual).modificarSaldo(-valor);
                }
            }
        }
    }
}

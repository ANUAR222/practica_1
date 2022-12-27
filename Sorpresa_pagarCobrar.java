import java.util.ArrayList;

public class Sorpresa_pagarCobrar extends Sorpresa_clase {

    Sorpresa_pagarCobrar(int valor, String texto) {
        super(texto);
        super.valor = valor;
    }

    @Override
    public void aplicarAJugador(int actual, ArrayList<Jugador> todos) {
        if (Sorpresas.jugadorCorrecto(actual, todos)) {
            informe(actual, todos);
            todos.get(actual).modificarSaldo(valor);
        }
    }
}

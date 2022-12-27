import java.util.ArrayList;

public class Sorpresa_porCasaHotel extends Sorpresa_clase {

    private int cantidad;

    Sorpresa_porCasaHotel(int cantidad, String texto) {
        super(texto);
        this.cantidad = cantidad;
    }

    @Override
    public void aplicarAJugador(int actual, ArrayList<Jugador> todos) {
        if (Sorpresas.jugadorCorrecto(actual, todos)) {
            informe(actual, todos);
            todos.get(actual).modificarSaldo(cantidad * todos.get(actual).cantidadCasasHoteles());
        }
    }
}

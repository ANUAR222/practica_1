import java.util.ArrayList;

public class CasillaSorpresa extends Casilla {
    private MazoSorpresas mazo;
    private Sorpresa sorpresa;

    public CasillaSorpresa(String nombre, MazoSorpresas mazo) {
        super();
        this.mazo = mazo;
    }

    protected void recibeJugador(int iactual, ArrayList<Jugador> todos) {
        Sorpresa_porCasaHotel sorpresa = this.mazo.siguiente();
        this.informe(iactual, todos);
        sorpresa.aplicarAJugador(iactual, todos);
    }

    public String toString() {
        String cad = "";
        cad = cad + this.getNombre();
        return cad;
    }
}

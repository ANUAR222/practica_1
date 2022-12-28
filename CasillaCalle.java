import java.util.ArrayList;

public class CasillaCalle extends Casilla {
    private static final int VALOR_DEF = 0;
    private static final float FACTORALQUILERCALLE = 1.0F;
    private static final float FACTORALQUILERCASA = 1.0F;
    private static final float FACTORALQUILERHOTEL = 4.0F;
    private float precioCompra;
    private float precioEdificar;
    private float precioBaseAlquiler;
    private int numCasas;
    private int numHoteles;
    private Jugador propietario;

    CasillaCalle(String nombre, float precioCompra, float precioEdificar, float precioBaseAlquiler) {
        super();
        this.init();
        this.precioCompra = precioCompra;
        this.precioEdificar = precioEdificar;
        this.precioBaseAlquiler = precioBaseAlquiler;
    }

    public int cantidadCasasHoteles() {
        return this.numCasas + this.numHoteles;
    }

    public boolean comprar(Jugador jugador) {
        this.propietario = jugador;
        return this.propietario.paga(this.getPrecioCompra());
    }

    boolean construirCasa(Jugador jugador) {
        jugador.paga(this.getPrecioEdificar());
        ++this.numCasas;
        return true;
    }

    boolean construirHotel(Jugador jugador) {
        jugador.paga(this.getPrecioEdificar());
        ++this.numHoteles;
        return true;
    }

    boolean derruirCasas(int numero, Jugador jugador) {
        if (this.esEsteElPropietario(jugador) && this.numCasas >= numero) {
            this.numCasas -= numero;
            return true;
        } else {
            return false;
        }
    }

    public boolean esEsteElPropietario(Jugador jugador) {
        return jugador == this.propietario;
    }

    public int getNumCasas() {
        return this.numCasas;
    }

    public int getNumHoteles() {
        return this.numHoteles;
    }

    float getPrecioAlquilerCompleto() {
        return this.precioBaseAlquiler * (1.0F + (float)this.numCasas * 1.0F + (float)this.numHoteles * 4.0F);
    }

    public float getPrecioBaseAlquiler() {
        return this.precioBaseAlquiler;
    }

    public float getPrecioCompra() {
        return this.precioCompra;
    }

    float getPrecioEdificar() {
        return this.precioEdificar;
    }

    public String getNombre() {
        return this.getNombre();
    }

    public void init() {
        this.precioCompra = 0.0F;
        this.precioBaseAlquiler = 0.0F;
        this.precioEdificar = 0.0F;
        this.numCasas = 0;
        this.numHoteles = 0;
        this.propietario = new Jugador("");
    }

    public boolean tienePropietario() {
        return !"".equals(this.propietario.getNombre());
    }

    public void tramitarAlquiler(Jugador jugador) {
        if (this.tienePropietario() && !this.esEsteElPropietario(jugador)) {
            jugador.pagaAlquiler(this.getPrecioAlquilerCompleto());
            this.propietario.recibe(this.getPrecioAlquilerCompleto());
        }

    }

    protected void recibeJugador(int iactual, ArrayList<Jugador> todos) {
        this.informe(iactual, todos);
        Jugador jugador = (Jugador)todos.get(iactual);
        if (!this.tienePropietario()) {
            jugador.puedeComprarCasilla();
        } else {
            this.tramitarAlquiler((Jugador)todos.get(iactual));
        }

    }

    public String toString() {
        String cad = "";
        cad = "(CALLE) " + this.getNombre() + ". Precios: Compra: " + this.precioCompra + ", Edificar: " + this.precioEdificar + ", Alquiler base: " + this.precioBaseAlquiler + ", Casas: " + this.numCasas + ", Hoteles: " + this.numHoteles + "\n";
        return cad;
    }

    public void actualizaPropietarioPorConversion(Jugador jugador) {
        this.propietario = jugador;
    }
}

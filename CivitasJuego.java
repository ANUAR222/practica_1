import java.util.ArrayList;

public class CivitasJuego {

    private int numJugadores;
    private ArrayList<Jugador> jugadores;
    private tablero tablero;
    private EstadoJuego estado;
    private int indiceJugadorActual;
    private GestorEstados gestorEstados;
    private MazoSorpresas mazo;
    private Sorpresa ultimaSorpresa;
    private Dado dado;
    private static final int CASAS_MAX = 4;
    private static final int HOTELES_MAX = 4;

    public CivitasJuego(ArrayList<String> nombres) {
        jugadores = new ArrayList<>();
        for (String nom : nombres) {
            jugadores.add(new Jugador(nom));
        }
        gestorEstados = new GestorEstados();
        estado = gestorEstados.estadoInicial();
        indiceJugadorActual = Dado.getInstance().quienEmpieza(jugadores.size());
        mazo = new MazoSorpresas();
        inicializaTablero(mazo);
        inicializaMazoSorpresas(tablero);
        dado = Dado.getInstance();
    }

    void inicializaTablero(MazoSorpresas mazo) {
        tablero = new tablero(5);
        tablero.añadeCasilla(new Casilla("Calle 1"));
        tablero.añadeCasilla(new CasillaSorpresa("Sorpresa", mazo));
        tablero.añadeCasilla(new Casilla("Calle 2"));
        tablero.añadeCasilla(new Casilla("Calle 3"));
        tablero.añadeCasilla(new CasillaImpuesto("Impuesto", 100));
        tablero.añadeJuez();
    }

    void inicializaMazoSorpresas(Tablero tablero) {
        mazo.alMazo(new Sorpresa("¡Suerte!", 0, TipoSorpresa.SALIRCARCEL));
        mazo.alMazo(new Sorpresa("¡Suerte!", 100, TipoSorpresa.PAGARCOBRAR));
        mazo.alMazo(new Sorpresa("¡Suerte!", 100, TipoSorpresa.PAGARCOBRAR));
        mazo.alMazo(new Sorpresa("¡Suerte!", 100, TipoSorpresa.PAGARCOBRAR));
        mazo.alMazo(new Sorpresa("¡Suerte!", 100, TipoSorpresa.PAGARCOBRAR));
        mazo.alMazo(new Sorpresa("¡Suerte!", 100, TipoSorpresa.PAGARCOBRAR));
    }

    public Jugador getJugadorActual() {
        return jugadores.get(indiceJugadorActual);
    }

    public Sorpresa getUltimaSorpresa() {
        return ultimaSorpresa;
    }

    public tablero getTablero() {
        return tablero;
    }

    public EstadoJuego getEstado() {
        return estado;
    }

    public ArrayList<Jugador> getJugadores() {
        return jugadores;
    }

    public int getNumJugadores() {
        return numJugadores;
    }

    public int getIndiceJugadorActual() {
        return indiceJugadorActual;
    }

    public void contabilizarPasosPorSalida(Jugador jugadorActual) {
        while (tablero.getPorSalida() > 0) {
            jugadorActual.pasaPorSalida();
        }
    }

    public void pasarTurno() {
        if (indiceJugadorActual == jugadores.size() - 1) {
            indiceJugadorActual = 0;
        } else {
            indiceJugadorActual++;
        }
    }

    public void siguientePasoCompletado(OperacionesJuego operacion) {
        estado = gestorEstados.siguienteEstado(getJugadorActual(), estado, operacion);
    }

    public OperacionesJuego siguientePaso() {
        Jugador jugadorActual = getJugadorActual();
        OperacionesJuego operacion = gestorEstados.operacionesPermitidas(jugadorActual, estado);
        if (operacion == OperacionesJuego.PASAR_TURNO) {
            pasarTurno();
            siguientePasoCompletado(operacion);
        } else if (operacion == OperacionesJuego.AVANZAR) {
            avanzaJugador();
            siguientePasoCompletado(operacion);
        }
        return operacion;
    }

    public Boolean construirCasa(int ip) {
        Boolean result = getJugadorActual().construirCasa(ip);
        if (result) {
            siguientePasoCompletado(OperacionesJuego.CONSTRUIR_CASA);
        }
        return result;
    }

    public Boolean construirHotel(int ip) {
        Boolean result = getJugadorActual().construirHotel(ip);
        if (result) {
            siguientePasoCompletado(OperacionesJuego.CONSTRUIR_HOTEL);
        }
return result;
    }

    public Boolean vender(int ip) {
        Boolean result = getJugadorActual().vender(ip);
        if (result) {
            siguientePasoCompletado(OperacionesJuego.VENDER);
        }
        return result;
    }

    public Boolean hipotecar(int ip) {
        Boolean result = getJugadorActual().hipotecar(ip);
        if (result) {
            siguientePasoCompletado(OperacionesJuego.HIPOTECAR);
        }
        return result;
    }

    public Boolean cancelarHipoteca(int ip) {
        Boolean result = getJugadorActual().cancelarHipoteca(ip);
        if (result) {
            siguientePasoCompletado(OperacionesJuego.CANCELAR_HIPOTECA);
        }
        return result;
    }

    public Boolean salirCarcelPagando() {
        Boolean result = getJugadorActual().salirCarcelPagando();
        if (result) {
            siguientePasoCompletado(OperacionesJuego.SALIR_CARCEL_PAGANDO);
        }
        return result;
    }

    public Boolean salirCarcelTirando() {
        Boolean result = getJugadorActual().salirCarcelTirando();
        if (result) {
            siguientePasoCompletado(OperacionesJuego.SALIR_CARCEL_TIRANDO);
        }
        return result;
    }

    public Boolean finalDelJuego() {
        for (Jugador j : jugadores) {
            if (j.enBancarrota()) {
                return true;
            }
        }
        return false;
    }

    public Boolean comprar() {
        Boolean result = getJugadorActual().comprar(getTablero().getCasillaActual());
        if (result) {
            siguientePasoCompletado(OperacionesJuego.COMPRAR);
        }
        return result;
    }

    public void avanzaJugador() {
        Jugador jugadorActual = getJugadorActual();
        int posicionActual = jugadorActual.getNumCasillaActual();
        int tirada = dado.tirar();
        int posicionNueva = tablero.nuevaPosicion(posicionActual, tirada);
        Casilla casilla = tablero.getCasilla(posicionNueva);
        contabilizarPasosPorSalida(jugadorActual);
        jugadorActual.moverACasilla(posicionNueva);
        casilla.recibeJugador(indiceJugadorActual, jugadores);
        contabilizarPasosPorSalida(jugadorActual);
    }

}

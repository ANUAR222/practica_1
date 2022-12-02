import java.util.ArrayList;

public class CivitasJuego {
    /*CivitasJuego
El único constructor de esta clase tiene las siguientes responsabilidades:
• Inicializar el atributo jugadores creando y añadiendo un jugador por cada nombre
suministrado como parámetro.
• Crear el gestor de estados y fijar el estado actual como el estado inicial (método
estadoInicial()) indicado por este gestor.
• Inicializar el índice del jugador actual (que será quien tenga el primer turno). Para obtener
ese valor se utilizará el método adecuado del dado.
• Crear el mazo de sorpresas
• Llamar al método de inicialización del tablero.
• Llamar al método de inicialización del mazo de sorpresas.
Los métodos que debes implementar son:
• void actualizarInfo(): consulta y visualiza en la consola toda la información importante del
jugador actual, sus propiedades y la casilla actual. Si algún jugador cae en bancarrota,
muestra también el ranking.
• void inicializaTablero (MazoSorpresas mazo): este método crea el tablero (guardando la
referencia en el atributo correspondiente), indicando qué posición ocupará la cárcel y
añadiéndole todas las casillas, las cuales se van creando conforme se añaden.
• void inicializaMazoSorpresas (Tablero tablero): este método crea todas las cartas sorpresa
y las almancena en el mazo de sorpresas ya creado en el constructor.
• void contabilizarPasosPorSalida (Jugador jugadorActual): mientras el método
getPorSalida del tablero devuelva un valor mayor que 0 se llama al método pasaPorSalida
del jugador pasado como parámetro. Al llamar a ese método el jugador actual cobra por
todas las veces que ha pasado por la salida en su turno actual.
• void pasarTurno (): actualiza el índice del jugador actual como consecuencia del cambio de
turno. Se debe poner atención al caso en que el jugador actual sea el último de la lista.
• void siguientePasoCompletado (OperacionesJuego operación): se actualiza el estado del
juego obteniendo el siguiente estado del gestor de estados (método siguienteEstado). Para
ello es necesario obtener el jugador actual.
• Boolean construirCasa (int ip): este método delega totalmente en el método con el mismo
nombre del jugador actual.
• Boolean construirHotel(int ip): este método delega totalmente en el método con el mismo
nombre del jugador actual.
• Boolean vender (int ip): este método delega totalmente en el método con el mismo nombre
del jugador actual.
• Boolean hipotecar (int ip): este método delega totalmente en el método con el mismo
nombre del jugador actual.
• Boolean cancelarHipoteca (int ip); este método delega totalmente en el método con el
mismo nombre del jugador actual.
• Boolean salirCarcelPagando (): este método delega totalmente en el método con el mismo
nombre del jugador actual.
• Boolean salirCarcelTirando (): este método delega totalmente en el método con el mismo
nombre del jugador actual.
• Boolean finalDelJuego (): este método devuelve true si alguno de los jugadores está en
bancarrota
• ArrayList<Jugador> ranking(): este método produce la lista ordenada de jugadores en
función de su saldo. Investiga como ordenar una colección en Java y Ruby teniendo en
cuenta que ya creaste los métodos compareTo y <=> (respectivamente) para las instancias
de la clase Jugador*/

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

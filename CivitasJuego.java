import java.util.ArrayList;
import java.util.Collections;
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

import java.util.Iterator;

public class CivitasJuego {
    private int indiceJugadorActual;
    private Tablero tablero;
    private MazoSorpresas mazo;
    private ArrayList<Jugador> jugadores = new ArrayList();
    private EstadoJuego estado;
    private GestorEstados gestor;
    private OperacionesJuego operacion;

    public static civitas.Diario getDiario() {
        return civitas.Diario.getInstance();
    }

    private void avanzaJugador() {
        Jugador jugadorActual = this.getJugadorActual();
        int posicionActual = jugadorActual.getCasillaActual();
        int tirada = Dado.getInstance().tirar();
        int posicionNueva = this.tablero.nuevaPosicion(posicionActual, tirada);
        Casilla casilla = this.tablero.getCasilla(posicionNueva);
        this.contabilizaPasosPorSalida();
        jugadorActual.moverACasilla(posicionNueva);
        casilla.recibeJugador(this.indiceJugadorActual, this.jugadores);
    }

    public CivitasJuego(ArrayList<String> nombres, boolean debug) {
        Iterator var3 = nombres.iterator();

        while(var3.hasNext()) {
            String nombre = (String)var3.next();
            this.jugadores.add(new Jugador(nombre));
        }

        this.gestor = new GestorEstados();
        this.estado = this.gestor.estadoInicial();
        Dado dado = Dado.getInstance();
        dado.setDebug(debug);
        this.indiceJugadorActual = dado.quienEmpieza(4);
        this.mazo = new MazoSorpresas(debug);
        this.tablero = new Tablero();
        this.inicializaTablero(this.mazo);
        this.inicializaMazoSorpresas();
    }

    public boolean Comprar() {
        boolean res = false;
        Jugador jugadorActual = this.getJugadorActual();
        int numCasillaActual = jugadorActual.getCasillaActual();
        Casilla casilla = this.tablero.getCasilla(numCasillaActual);
        res = jugadorActual.comprar(casilla);
        return res;
    }

    public boolean construirCasa(int ip) {
        return this.getJugadorActual().construirCasa(ip);
    }

    public boolean construirHotel(int ip) {
        return this.getJugadorActual().construirHotel(ip);
    }

    private void contabilizaPasosPorSalida() {
        if (this.tablero.computarPasoPorSalida()) {
            ((Jugador)this.jugadores.get(this.indiceJugadorActual)).pasaPorSalida();
        }

    }

    public boolean finalDelJuego() {
        boolean fin = false;
        Iterator var2 = this.jugadores.iterator();

        while(var2.hasNext()) {
            Jugador jugador = (Jugador)var2.next();
            if (jugador.enBancarrota()) {
                fin = true;
            }
        }

        return fin;
    }

    public int getIndiceJugadorActual() {
        return this.indiceJugadorActual;
    }

    public Jugador getJugadorActual() {
        return (Jugador)this.jugadores.get(this.indiceJugadorActual);
    }

    public ArrayList<Jugador> getJugadores() {
        return this.jugadores;
    }

    public Tablero getTablero() {
        return this.tablero;
    }

    public Casilla getCasillaActual() {
        return this.tablero.getCasilla(this.getJugadorActual().getCasillaActual());
    }

    public OperacionesJuego getSiguienteOperacion() {
        Jugador jugadorActual = (Jugador)this.jugadores.get(this.indiceJugadorActual);
        this.operacion = this.gestor.siguienteOperacion(jugadorActual, this.estado);
        return this.operacion;
    }

    private void inicializaMazoSorpresas() {
        this.mazo.alMazo(new Sorpresa_pagarCobrar(10, "Cobrar"));
        this.mazo.alMazo(new Sorpresa_pagarCobrar(50, "Cobrar"));
        this.mazo.alMazo(new Sorpresa_pagarCobrar(100, "Cobrar"));
        this.mazo.alMazo(new Sorpresa_pagarCobrar(-10, "Pagar"));
        this.mazo.alMazo(new Sorpresa_pagarCobrar(-50, "Pagar"));
        this.mazo.alMazo(new Sorpresa_pagarCobrar(-100, "Pagar"));
        this.mazo.alMazo(new Sorpresa_porCasaHotel(80, "Cobrar"));
        this.mazo.alMazo(new Sorpresa_porCasaHotel(100, "Cobrar"));
        this.mazo.alMazo(new Sorpresa_porCasaHotel(-10, "Pagar"));
        this.mazo.alMazo(new Sorpresa_porCasaHotel(-100, "Pagar"));
        this.mazo.alMazo(new Sorpresa_porCasaHotel(0, "Sorpresa_3"));
        this.mazo.alMazo(new Sorpresa_porCasaHotel(0, "Sorpresa_3"));
    }

    private void inicializaTablero(MazoSorpresas mazo) {
        this.tablero.aniadeCasilla(new CasillaCalle("Velazquez", 60.0F, 20.0F, 50.0F));
        this.tablero.aniadeCasilla(new CasillaCalle("Salamanca", 80.0F, 30.0F, 100.0F));
        this.tablero.aniadeCasilla(new CasillaCalle("Castellana", 100.0F, 40.0F, 200.0F));
        this.tablero.aniadeCasilla(new CasillaCalle("Tirso de M.", 120.0F, 50.0F, 300.0F));
        this.tablero.aniadeCasilla(new CasillaSorpresa("Sorpresa", mazo));
        this.tablero.aniadeCasilla(new CasillaCalle("Lavapies", 140.0F, 60.0F, 400.0F));
        this.tablero.aniadeCasilla(new CasillaCalle("Goya", 150.0F, 70.0F, 500.0F));
        this.tablero.aniadeCasilla(new CasillaSorpresa("Sorpresa", mazo));
        this.tablero.aniadeCasilla(new CasillaCalle("Mataelpino", 200.0F, 80.0F, 600.0F));
        this.tablero.aniadeCasilla(new Casilla());
        this.tablero.aniadeCasilla(new CasillaCalle("Leganitos", 220.0F, 90.0F, 700.0F));
        this.tablero.aniadeCasilla(new CasillaCalle("Serrano", 240.0F, 100.0F, 800.0F));
        this.tablero.aniadeCasilla(new CasillaCalle("P. del Prado", 260.0F, 110.0F, 900.0F));
        this.tablero.aniadeCasilla(new CasillaSorpresa("Sorpresa", mazo));
        this.tablero.aniadeCasilla(new CasillaCalle("Valdelatas", 280.0F, 120.0F, 1000.0F));
        this.tablero.aniadeCasilla(new CasillaCalle("Mendez A.", 300.0F, 130.0F, 1100.0F));
        this.tablero.aniadeCasilla(new CasillaSorpresa("Sorpresa", mazo));
        this.tablero.aniadeCasilla(new CasillaCalle("O'Donell", 350.0F, 140.0F, 1200.0F));
        this.tablero.aniadeCasilla(new CasillaCalle("San Jerónimo", 400.0F, 150.0F, 1300.0F));
    }

    private void pasarTurno() {
        this.indiceJugadorActual = (this.indiceJugadorActual + 1) % 4;
    }

    public ArrayList<Jugador> ranking() {
        Collections.sort(this.jugadores, Jugador::compareTo);
        return this.jugadores;
    }

    public OperacionesJuego siguientePaso() {
        Jugador jugadorActual = this.getJugadorActual();
        OperacionesJuego operacion = this.gestor.siguienteOperacion(jugadorActual, this.estado);
        if (operacion == OperacionesJuego.PASAR_TURNO) {
            this.pasarTurno();
            this.siguientePasoCompletado(operacion);
        }

        if (operacion == OperacionesJuego.AVANZAR) {
            this.avanzaJugador();
            this.siguientePasoCompletado(operacion);
        }

        return operacion;
    }

    public void siguientePasoCompletado(OperacionesJuego operacion) {
        this.estado = this.gestor.siguienteEstado((Jugador)this.jugadores.get(this.indiceJugadorActual), this.estado, operacion);
    }

    public boolean getOperacion() {
        return this.operacion == OperacionesJuego.PASAR_TURNO;
    }
}


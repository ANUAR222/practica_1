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

    private static final int CASAS_MAX = 4;
    private static final int HOTELES_MAX = 4;
    private static final int PRECIO_LIBERTAD = 200;
    private static final int SALDO_SALIDA = 1000;

    private ArrayList<Jugador> jugadores;
    private tablero tablero;
    private MazoSorpresas mazo;
    private EstadosJuego estado;
    private int indiceJugadorActual;
    private GestorEstados gestorEstados;
    private Dado dado;

    CivitasJuego(ArrayList<String> nombres) {
        Dado Dado = new Dado();
        jugadores = new ArrayList<>();
        for (String nombre: nombres) {
            jugadores.add(new Jugador(nombre));
        }
        gestorEstados = new GestorEstados();
        estado = gestorEstados.estadoInicial();
        indiceJugadorActual = Dado.quienEmpieza(jugadores.size());
        mazo = new MazoSorpresas();
        inicializaTablero(mazo);
        inicializaMazoSorpresas(tablero);
    }


}

import java.util.Random;


public class Dado {
    // Atributos de instancia
    private Random random;
    private int ultimoResultado;
    private boolean debug;

    // Atributos de clase
    private static Dado instance;
    private static final int SalidaCarcel = 5;

    // Constructor privado sin argumentos
    private Dado() {
        random = new Random();
        ultimoResultado = 0;
        debug = false;
    }

    // Método de clase para obtener la única instancia de Dado
    public static Dado getInstance() {
        if (instance == null) {
            instance = new Dado();
        }

        return instance;
    }

    // Método tirar
    public int tirar() {
        if (debug) {
            ultimoResultado = 1;
        } else {
            ultimoResultado = random.nextInt(6) + 1;
        }

        return ultimoResultado;
    }

    // Método salgoDeLaCarcel
    public boolean salgoDeLaCarcel() {
        return tirar() >= SalidaCarcel;
    }

    // Método quienEmpieza
    public int quienEmpieza(int n) {
        return tirar() % n;
    }

    // Método setDebug
    public void setDebug(boolean d) {
        debug = d;
        Diario.getInstance().ocurreEvento("Modo debug " + (debug ? "activado" : "desactivado"));
    }

    // Método getUltimoResultado
    public int getUltimoResultado() {
        return ultimoResultado;
    }
}

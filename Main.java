public class Main {
    public static void main(String[] args) {
        int[] contador = new int[5];
        for (int i = 0; i < 100; ++i) {
            contador[Dado.getInstance().quienEmpieza(4)]++;
        }
        for (int i = 0; i < 5; ++i) {
            System.out.println("El jugador " + i + " ha salido " + contador[i] + " veces.");
        }
        //2
        System.out.println("Modo debug activado");
        Dado.getInstance().setDebug(true);
        for (int i = 0; i < 5; ++i) {
            System.out.println(Dado.getInstance().tirar());
        }
        System.out.println("Modo debug desactivado");
        Dado.getInstance().setDebug(false);
        for (int i = 0; i < 5; ++i) {
            System.out.println(Dado.getInstance().tirar());
        }
        //3
        System.out.println("El último resultado ha sido " + Dado.getInstance().getUltimoResultado());
        System.out.println("¿Salgo de la cárcel? " + Dado.getInstance().salgoDeLaCarcel());
        //4
        System.out.println("El valor de la casilla de Salida es " + tablero.Casilla.SALIDA);
        System.out.println("El valor de la casilla de Cárcel es " + tablero.Casilla.CARCEL);
        System.out.println("El valor de la casilla de Impuesto es " + tablero.Casilla.IMPUESTO);
        System.out.println("El valor de la casilla de Juez es " + tablero.Casilla.JUEZ);
        System.out.println("El valor de la casilla de Sorpresa es " + tablero.Casilla.SORPRESA);
        //5
        MazoSorpresas mazo = new MazoSorpresas();
        mazo.alMazo(new Sorpresa("¡Ganaste 1000 euros!", 1000, tablero.Sorpresa.PAGARCOBRAR));
        mazo.alMazo(new Sorpresa("¡Vas a la cárcel!", 0, tablero.Sorpresa.IRACASILLA));
        System.out.println(mazo.siguiente().toString());
        mazo.inhabilitarCartaEspecial(mazo.siguiente());
        mazo.habilitarCartaEspecial(mazo.siguiente());

    }
}

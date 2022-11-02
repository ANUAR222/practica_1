public class Main {
    public static void main(String[] args) {
        /*rea una clase denominada TestP1 que tenga asociado un método de clase denominado
main para hacer las funciones de programa principal.
En Ruby, utilizaremos el mismo esquema aunque no exista el mismo concepto de
programa principal asociado a un método con un nombre específico. Así, en el mismo
fichero test_p1.rb donde se haya definido la clase TestP1, deberás añadir una línea de
código al final que produzca la ejecución del método main de la misma clase al ejecutar el
fichero test_p1.rb
En el método main crea el código para realizar las siguientes tareas:
1. Llama 100 veces al método quienEmpieza() de Dado considerando que hay 4
jugadores, y calcula cuantas veces se obtiene cada uno de los valores posibles.
Comprueba si se cumplen a nivel práctico las probabilidades de cada valor.
2. Asegúrate de que funciona el modo debug del dado activando y desactivando ese
modo, y realizando varias tiradas en cada modo.
3. Prueba al menos una vez los métodos getUltimoResultado() y salgoDeLaCarcel()
de Dado.
4. Muestra al menos un valor de cada tipo enumerado.
5. Crea un objeto MazoSorpresas y haz las siguientes pruebas: añade dos sorpresas
al mazo, obtén la siguiente sorpresa en juego, inhabilita y habilita la segunda carta
añadida. Dado que MazoSorpresas
6. usa la clase Diario, aprovecha y prueba todos los métodos de Diario.
7. Crea un tablero, añádele varias casillas y comprueba con el depurador que
efectivamente la estructura del mismo es la que esperabas. Intenta provocar las
situaciones erróneas controladas en la clase Tablero (por ejemplo, que la posición
de la cárcel sea mayor que el tamaño del tablero) y comprueba que la gestión de
las mismas es la correcta. Finalmente, realiza distintas tiradas con el dado y
asegúrate de que se calcula correctamente la posición de destino en el tablero.
Llegado este punto te habrás dado cuenta que ante un error tipográfico habitual como
escribir mal un atributo o el nombre de un método, Netbeans en Java avisa mientras se
escribe pudiendo subsanar el error al instante. Sin embargo, desarrollando en Ruby no se
tiene esa ayuda del IDE, cobrando especial importancia la prueba del software. De todos
modos, en Java, el hecho de que un programa compile sin errores no significa que esté
libre de errores.
Se aconseja realizar pequeños programas principales, en ambos lenguajes, que permitan
probar todo el código desarrollado. Cada alumno debe ser su principal crítico y diseñar las
pruebas para intentar encontrar errores en su código. Una prueba de código se considera
que ha tenido éxito si produce la detección de un error.
Para probar el código en Java es muy útil hacer uso del depurador y del método toString()
(investiga como funciona. En Ruby es muy útil utilizar los métodos to_s e inspect
(investiga cómo funcionan).
En Java, usando el depurador, sigue paso a paso la creación de los objetos del primer
punto y observa como se va modificando el valor de los atributos.
4. Incorporando código suministrado por los profesores a tu proyecto
Como parte del desarrollo de las prácticas de la asignatura, se te proporcionarán ya
implementadas algunas clases para que solo tengáis que incorporarlas a vuestro
proyecto.
En esta práctica se os proporciona el enumerado EstadosJuego y la clase Diario.
Sigue los siguientes pasos para incorporar el código que te proporcionamos para ser
incorporado a tu proyecto:
• Averigua la ruta de tu proyecto: haz clic con el botón derecho sobre el proyecto en
NetBeans y selecciona la opción “Properties”. Debes hacer clic sobre el nodo del
que cuelgan todos los elementos del proyecto en el panel izquierdo.
• En Java: si la ruta de tu proyecto es R, en R/src verás una carpeta por cada
paquete de tu proyecto. Ahora mismo solo debes tener la carpeta civitas
correspondiente al paquete con el mismo nombre. Copia los ficheros fuente
suministrados en R/src/civitas/
• En Ruby: copia los ficheros fuente suministrados en R/lib/
No debes modificar ninguna de las clases suministradas por los profesores. Si hay un
problema de concordancia repasa en primer lugar las clases que ya has creado para ver
si siguen totalmente las especificaciones dadas. Si después de ese repaso persiste el
problema, consulta con tu profesor*/
        //1
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
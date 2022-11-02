import java.util.ArrayList;

public class tablero {
    /*Tablero
Esta clase tiene la responsabilidad de representar el tablero de juego imponiendo las
restricciones existentes sobre el mismo en las reglas de juego. Dispone de atributos y
métodos específicos cuya finalidad es asegurar que el tablero construido sea correcto y
que no se intente acceder a posiciones no válidas del mismo. Finalmente, también es
responsabilidad de la clase Tablero el cálculo de la posición de destino después de una
tirada con el dado.
Sus atributos de instancia (todos privados) son los siguientes:
• numCasillaCarcel de tipo entero para representar el número de la casilla donde se
encuentra la cárcel
• casillas cuyo tipo será ArrayList<Casilla>. Este atributo es el contenedor de las
casillas del del juego
• porSalida de tipo entero para representar el número de veces que se ha pasado
por la salida en un turno
• tieneJuez de tipo Boolean para representar si el tablero dispone de la casilla de ese
tipo.
Como esta clase depende de la clase Casilla, de la que aún no dispones, vamos a crear
una temporal para hacer las pruebas. Añade a la clase Casilla un atributo de instancia
privado llamado nombre, y un constructor, con visibilidad de paquete, que acepte como
parámetro una cadena de caracteres y la guarde en el atributo nombre. Crea también un
consultor con visibilidad de paquete para el nombre. Posteriormente sustituirás esta clase
Casilla temporal por la real.
La clase Tablero dispone de un único constructor con visibilidad de paquete que recibe
como parámetro un entero que representa el índice de la casilla de la cárcel. El
constructor realiza lo siguiente:
• Guarda el valor del parámetro en el atributo numCasillaCarcel, siempre que ese
valor sea mayor o igual que 1. En caso contrario se ignora el parámetro y
numCasillaCarcel será igual a 1
• Inicializa casillas a un ArrayList vacío (Array en Ruby) y añade una nueva casilla de
nombre “Salida”
• Inicializa porSalida a cero
• Inicializa tieneJuez a false
Añade los métodos de instancia privados siguientes:
- Boolean correcto (): devuelve true si el número de elementos en casillas es mayor que el
índice de la casilla de la cárcel (numCasillaCarcel) (en ese caso se tiene asegurado que
se ha añadido la cárcel en la posición indicada en el constructor) y que se dispone de una
casilla tipo Juez. Si se cumplen todas las condiciones el tablero es correcto y puede
usarse para jugar.
- Boolean correcto (int numCasilla): devuelve true si el método anterior también lo hace y
además su parámetro es un índice válido para acceder a elementos de casillas.
El resto de métodos de instancia de esta clase son los que se detallan a continuación.
Todos tendrán visibilidad de paquete. En Ruby puedes usar attr_xxx para los consultores
y modificadores básicos de atributos de la clase.
- int getCarcel (): es un consultor del atributo numCasillaCarcel
- int getPorSalida (): si el valor de porSalida es mayor que 0, decrementa su valor en una
unidad y devuelve el valor que tenía porSalida antes de ser decrementado. En caso
contrario, simplemente devuelve el valor de porSalida
- void añadeCasilla (Casilla casilla): si el tamaño de casillas es igual a numCasillaCarcel,
se añade primero a casillas una casilla denominada “Cárcel”. En cualquier caso, después
se añade a casillas la casilla pasada como parámetro, y se vuelve a comprobar si el
tamaño de casillas es igual a numCasillaCarcel, en cuyo caso se añade a casillas una
casilla denominada “Cárcel”.
- void añadeJuez(): si aún no se ha añadido una casilla juez, se añade y se actualiza el
atributo tieneJuez. Se impide por tanto que se puedan añadir varias casillas de este tipo.
- Casilla getCasilla (int numCasilla): devuelve la casilla de la posición numCasilla si este
índice es válido. Devuelve null en otro caso. Utiliza internamente el método Boolean
correcto (int numCasilla).
- int nuevaPosicion (int actual, int tirada): si el tablero no es correcto devuelve -1. En caso
contrario se calcula la nueva posición en el tablero asumiendo que se parte de la posición
actual y se avanza una tirada de unidades. Esta nueva posición se devuelve.
Debes tener en cuenta que si se llega a la última posición del tablero, la siguiente casilla
es la de salida (la primera) . Utiliza el operador módulo para realizar el cálculo de la
posición de destino.
Adicionalmente, el método incrementa el atributo porSalida si se ha producido un nuevo
paso por la salida. Este hecho puede comprobarse fácilmente: si la nueva posición no es
el resultado de sumar los parámetros actual y tirada, necesariamente se ha terminado una
vuelta al tablero y pasado de nuevo por la salida.
- int calcularTirada (int origen, int destino): devuelve la tirada de dado que se habría tenido
que obtener para ir desde la casilla número origen a la casilla número destino. En la
mayor parte de los casos el cálculo necesario se limita a restar el origen del destino. Sin
embargo, si de esta resta se obtiene una valor negativo, quiere decir que se ha producido
un paso por la salida y al resultado anterior es necesario sumarle el número de casillas
del tablero para obtener el valor correcto.
Ejemplo: En un tablero de 20 casillas, si origen=18 y destino=3, el resultado de la tirada
sería 3-18=-15. En ese caso, sumando el tamaño del tablero, se obtendría -15+20=5.
Efectivamente, para ir de la casilla 18 a la número 3 se avanzan 5 casillas.*/
    public enum Sorpresa {
        IRCARCEL, IRCASILLA, PAGARCOBRAR, PORCASAHOTEL, PORJUGADOR, IRACASILLA, SALIRCARCEL
    }
    public enum Casilla {
        CALLE, JUEZ, SORPRESA, IMPUESTO, SALIDA, CARCEL, DESCANSO
    }
    public class Tablero {
        private int numCasillaCarcel;
        private ArrayList<Casilla> casillas;
        private int porSalida;
        private Boolean tieneJuez;
        public Tablero(int numCasillaCarcel){
            this.numCasillaCarcel = numCasillaCarcel;
            casillas = new ArrayList<>();
            casillas.add(Casilla.SALIDA);
            porSalida = 0;
            tieneJuez = false;
        }
        private Boolean correcto(){
            return casillas.size() > numCasillaCarcel && tieneJuez;
        }
        private Boolean correcto(int numCasilla){
            return correcto() && numCasilla >= 0 && numCasilla < casillas.size();
        }
        int getCarcel(){
            return numCasillaCarcel;
        }
        int getPorSalida(){
            if (porSalida > 0){
                porSalida--;
            }
            return porSalida;
        }
        void añadeCasilla(Casilla casilla){
            if (casillas.size() == numCasillaCarcel){
                casillas.add(Casilla.CARCEL);
            }
            casillas.add(casilla);
            if (casillas.size() == numCasillaCarcel){
                casillas.add(Casilla.CARCEL);
            }
        }
        void añadeJuez(){
            if (!tieneJuez){
                casillas.add(Casilla.JUEZ);
                tieneJuez = true;
            }
        }
        Casilla getCasilla(int numCasilla){
            if (correcto(numCasilla)){
                return casillas.get(numCasilla);
            }
            return null;
        }
        int nuevaPosicion(int actual, int tirada){
            if (correcto()){
                int nuevaPosicion = actual + tirada;
                if (nuevaPosicion >= casillas.size()){
                    porSalida++;
                    nuevaPosicion = nuevaPosicion % casillas.size();
                }
                return nuevaPosicion;
            }
            return -1;
        }
        int calcularTirada(int origen, int destino){
            if (origen < destino){
                return destino - origen;
            } else {
                return casillas.size() - (origen - destino);
            }
        }
    }

}
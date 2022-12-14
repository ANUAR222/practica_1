import civitas.Diario;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

class VistaTextual {
  /* Deberás implementar los siguientes métodos de la vista:
• void setCivitasJuego(CivitasJuego civitas): da valor al atributo civitas, el modelo, para que
lo conozca la vista y pueda consultarlo directamente, de cara a mostrar información sobre él.
También llama a actualizarVista() para mostrar el estado actual del juego.
• void actualizarVista(): muestra información en forma de texto del jugador actual y sus
propiedades, y de la casilla actual. Utiliza getJugador y getCasillaActual (de CivitasJuego) y
aplica el método toString a los objetos devueltos por esos métodos para construir la salida.
• SalidasCarcel salirCarcel (): debe mostrar un menú preguntando por la forma de salida de
la cárcel elegida y devolver el valor del enumerado correspondiente a esa opción.
• Respuestas comprar (): debe mostrar un menú preguntando si se desea comprar la calle a la
que se ha llegado y devolver el valor del enumerado correspondiente a SI ó NO.
• void gestionar (): debe mostrar un menú (usando el método menu) preguntando por el
número de gestión inmobiliaria elegida (e incluyendo la acción de TERMINAR) y después
obtener el índice de la propiedad del jugador actual, sobre la que se desea realizar la gestión.
Almacena ambos valores respectivamente en los atributos de instancia iGestion e
iPropiedad.
• int getGestion(): devuelve el valor del atributo iGestion.
• int getPropiedad(): devuelve el valor del atributo iPropiedad.
• void mostrarSiguienteOperacion (OperacionesJuego operación): mostrar en consola la
siguiente operación que va a realizar el juego. Dicha operación es la que recibe como
argumento (o parámetro).
• void mostrarEventos() : mientras el diario tenga eventos pendientes, obtenerlos y mostrarlos
en consola */
  CivitasJuego juegoModel; 
  int iGestion=-1;
  int iPropiedad=-1;
  private static String separador = "=====================";
  private Scanner in;
  
  VistaTextual () {
    in = new Scanner (System.in);
  }
  
  void mostrarEstado(String estado) {
    System.out.println (estado);
  }
              
  void pausa() {
    System.out.print ("Pulsa una tecla");
    in.nextLine();
  }

  int leeEntero (int max, String msg1, String msg2) {
    Boolean ok;
    String cadena;
    int numero = -1;
    do {
      System.out.print (msg1);
      cadena = in.nextLine();
      try {  
        numero = Integer.parseInt(cadena);
        ok = true;
      } catch (NumberFormatException e) { // No se ha introducido un entero
        System.out.println (msg2);
        ok = false;  
      }
      if (ok && (numero < 0 || numero >= max)) {
        System.out.println (msg2);
        ok = false;
      }
    } while (!ok);

    return numero;
  }

  int menu (String titulo, ArrayList<String> lista) {
    String tab = "  ";
    int opcion;
    System.out.println (titulo);
    for (int i = 0; i < lista.size(); i++) {
      System.out.println (tab+i+"-"+lista.get(i));
    }

    opcion = leeEntero(lista.size(),
                          "\n"+tab+"Elige una opción: ",
                          tab+"Valor erróneo");
    return opcion;
  }

  SalidasCarcel salirCarcel() {
    int opcion = menu ("Elige la forma para intentar salir de la carcel",
      new ArrayList<> (Arrays.asList("Pagando","Tirando el dado")));
    return (SalidasCarcel.values()[opcion]);
  }

  Respuestas comprar() {
    int opcion = menu ("¿Deseas comprar la calle?",
      new ArrayList<> (Arrays.asList("Si","No")));
    return (Respuestas.values()[opcion]);
  }

  void gestionar () {
    ArrayList<String> lista = new ArrayList<>();
    for (GestionesInmobiliarias g: GestionesInmobiliarias.values()) {
      lista.add(g.toString());
    }
    iGestion = menu ("Elige la gestión inmobiliaria a realizar",
      lista);
    if (iGestion != lista.size()-1) {
      iPropiedad = leeEntero(juegoModel.getJugadorActual().getNumPropiedades(),
        "Elige la propiedad sobre la que realizar la gestión: ",
        "Valor erróneo");
    }
  }
  
  public int getGestion(){
        return iGestion;
  }
  
  public int getPropiedad(){
        return iPropiedad;
  }
    

  void mostrarSiguienteOperacion(OperacionesJuego operacion) {
    System.out.println ("Siguiente operación: " + operacion);
  }


  void mostrarEventos() {
    while (CivitasJuego.getDiario().eventosPendientes()) {
      System.out.println (CivitasJuego.getDiario().leerEvento());
    }
  }
  
  public void setCivitasJuego(CivitasJuego civitas){ 
        juegoModel=civitas;
        this.actualizarVista();
  }
  
  void actualizarVista(){
      System.out.println(juegoModel.getJugadorActual().toString());
      System.out.println(juegoModel.getCasillaActual().toString());
  }

}

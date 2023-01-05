package GUI;

import civitas.Casilla;
import civitas.CivitasJuego;
import civitas.Diario;
import civitas.OperacionJuego;
import civitas.OperacionImobiliaria;
import civitas.Jugador;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class VistaTextual  {
    
  private static String separador = "=====================";
  int iGestion = -1;
  int iPropiedad = -1;
  private Scanner in;
  
  CivitasJuego juegoModel;
  
  public VistaTextual (CivitasJuego juegoModel) {
    in = new Scanner (System.in);
    this.juegoModel=juegoModel;
  }
  
  // MÉTODO pausa
  
  /** Indica al usuario y espera a que pulse cualquier tecla.**/
           
 public  void pausa() {
    System.out.print ("\nPulsa una tecla");
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
                          "\n"+tab+"Elige una opcion: ",
                          tab+"Valor erroneo");
    return opcion;
  }

  
    // MÉTODO actualiza
  
    /** Muestra información en forma de texto del jugador actual y sus 
     * propiedades, y de la casilla actual, si ya se ha llegado al 
     * finalDelJuego, muestra la lista completa de jugadores, que estará 
     * ordenada por orden de saldo, con el ganador en primer lugar.**/
  
    public void actualiza() {
        if(this.juegoModel.finalDelJuego()){
        ArrayList<Jugador> ranking = this.juegoModel.ranking();
            for (int i=ranking.size()-1; i>=0 ;i--){
                System.out.println(ranking.get(i).toString());
         }
        }else{
            System.out.println(this.juegoModel.getJugadorActual().toString());
        }
    }

    // Método comprar
    
    /** Debe mostrar un menú preguntando si se desea comprar la calle a la 
    que se ha llegado y devolver el valor del enumerado correspondiente a SI ó 
    * NO.**/
    
    public Respuesta comprar() {
        int indice = juegoModel.getJugadorActual().getCasillaActual();
        String casillaActual = juegoModel.getTablero().getCasilla(indice).toString();
      
        int opcion = menu ("Has llegado a la casilla\n" + casillaActual + "\nQuieres comprarla?",
                        new ArrayList<> (Arrays.asList("SI","NO") ) 
                      );

        return (Respuesta.values()[opcion]);
    }

    // MÉTODO elegirOperacion
    
    /** Debe mostrar un menú (usando el método menu) preguntando por el número 
     * de gestión inmobiliaria elegida (e incluyendo la acción de TERMINAR),
     * devolverá su valor convertido en enumerado de operación inmobiliaria.**/
    
    public OperacionImobiliaria elegirOperacion() {
        int opcionOperacion = menu ("Que numero de gestion inmobiliara quieres realizar?",
                        new ArrayList<> (Arrays.asList("-> CONSTRUIR_CASA", 
                                                       "-> CONSTRUIR_HOTEL",
                                                       "-> TERMINAR")
                        )
      );
      
      if(opcionOperacion != 2){
        this.elegirPropiedad(); 
      }
      return OperacionImobiliaria.values()[opcionOperacion];
    }

    // MÉTODO elegirPropiedad
    
    /** Devolverá el índice de la propiedad del jugador actual, sobre la que se
    desea realizar la gestión (también pidiéndo al usuario con el método menu), 
    * de forma opcional puede pedirse al modelo el número total de propiedades 
    * del jugador para controlar que el usuario introduzca un número dentro del 
    * rango apropiado.**/
    
    public int elegirPropiedad() {
       int numProps   = this.juegoModel.getJugadorActual().getPropiedades().size();
       int opcionProp = this.leeEntero(numProps, 
                       "Sobre que propiedad quieres hacer la gestion? ", 
                        "Valor Erroneo");
        return opcionProp;
    }

    // MÉTODO mostrarSiguienteOperacion
    
    /** Mmuestra en consola el valor del argumento, que contiene la siguiente 
     * operación que va a realizar el juego.**/
    
    public void mostrarSiguienteOperacion(OperacionJuego operacion) {
        String opera = "Siguiente operacion: " + operacion.toString();
        System.out.println(opera);
    }

    // MÉTODO mostrarEventos
    
    /** Mientras el diario tenga eventos pendientes, los lee y muestra en 
    consola.**/
    
    public void mostrarEventos() {
        while(Diario.getInstance().eventosPendientes()){
            System.out.println(Diario.getInstance().leerEvento());
        }
    }
}


import GUI.OperacionImobiliaria;
import GUI.Respuesta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class VistaTextual {
  private static String separador = "=====================";
  int iGestion = -1;
  int iPropiedad = -1;
  private Scanner in;
  CivitasJuego juegoModel;

  public VistaTextual(CivitasJuego juegoModel) {
    this.in = new Scanner(System.in);
    this.juegoModel = juegoModel;
  }

  public void pausa() {
    System.out.print("\nPulsa una tecla");
    this.in.nextLine();
  }

  int leeEntero(int max, String msg1, String msg2) {
    int numero = -1;

    Boolean ok;
    do {
      System.out.print(msg1);
      String cadena = this.in.nextLine();

      try {
        numero = Integer.parseInt(cadena);
        ok = true;
      } catch (NumberFormatException var8) {
        System.out.println(msg2);
        ok = false;
      }

      if (ok && (numero < 0 || numero >= max)) {
        System.out.println(msg2);
        ok = false;
      }
    } while(!ok);

    return numero;
  }

  int menu(String titulo, ArrayList<String> lista) {
    String tab = "  ";
    System.out.println(titulo);

    for(int i = 0; i < lista.size(); ++i) {
      System.out.println(tab + i + "-" + (String)lista.get(i));
    }

    int opcion = this.leeEntero(lista.size(), "\n" + tab + "Elige una opcion: ", tab + "Valor erroneo");
    return opcion;
  }

  public void actualiza() {
    if (this.juegoModel.finalDelJuego()) {
      ArrayList<Jugador> ranking = this.juegoModel.ranking();

      for(int i = ranking.size() - 1; i >= 0; --i) {
        System.out.println(((Jugador)ranking.get(i)).toString());
      }
    } else {
      System.out.println(this.juegoModel.getJugadorActual().toString());
    }

  }

  public Respuesta comprar() {
    int indice = this.juegoModel.getJugadorActual().getCasillaActual();
    String casillaActual = this.juegoModel.getTablero().getCasilla(indice).toString();
    int opcion = this.menu("Has llegado a la casilla\n" + casillaActual + "\nQuieres comprarla?", new ArrayList(Arrays.asList("SI", "NO")));
    return Respuesta.values()[opcion];
  }

  public OperacionImobiliaria elegirOperacion() {
    int opcionOperacion = this.menu("Que numero de gestion inmobiliara quieres realizar?", new ArrayList(Arrays.asList("-> CONSTRUIR_CASA", "-> CONSTRUIR_HOTEL", "-> TERMINAR")));
    if (opcionOperacion != 2) {
      this.elegirPropiedad();
    }

    return OperacionImobiliaria.values()[opcionOperacion];
  }

  public int elegirPropiedad() {
    int numProps = this.juegoModel.getJugadorActual().getPropiedades().size();
    int opcionProp = this.leeEntero(numProps, "Sobre que propiedad quieres hacer la gestion? ", "Valor Erroneo");
    return opcionProp;
  }

  public <OperacionJuego> void mostrarSiguienteOperacion(OperacionJuego operacion) {
    String opera = "Siguiente operacion: " + operacion.toString();
    System.out.println(opera);
  }

  public void mostrarEventos() {
    while(Diario.getInstance().eventosPendientes()) {
      System.out.println(Diario.getInstance().leerEvento());
    }

  }
}

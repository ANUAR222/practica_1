import GUI.CivitasView;
import GUI.OperacionImobiliaria;
import GUI.Respuesta;

import java.util.ArrayList;

public class Controlador {
    private CivitasJuego juegoModel;
    private CivitasView vista;
    private VistaTextual vista2;

    public Controlador(CivitasJuego juegoModel, CivitasView vista) {
        this.juegoModel = juegoModel;
        this.vista = vista;
    }

    public Controlador(CivitasJuego juegoModel, VistaTextual vista) {
        this.juegoModel = juegoModel;
        this.vista2 = vista;
    }

    public void Juega() {
        while(!this.juegoModel.finalDelJuego()) {
            this.vista.actualiza();
            this.vista.pausa();
            OperacionesJuego sigPaso = this.juegoModel.siguientePaso();
            this.vista.mostrarSiguienteOperacion(sigPaso);
            if (sigPaso != OperacionesJuego.PASAR_TURNO) {
                this.vista.mostrarEventos();
            }

            switch (sigPaso) {
                case COMPRAR:
                    if (this.vista.comprar() == Respuesta.SI) {
                        this.juegoModel.Comprar();
                    }

                    this.juegoModel.siguientePasoCompletado(sigPaso);
                    break;
                case GESTIONAR:
                    OperacionImobiliaria operacion = this.vista.elegirOperacion();
                    if (operacion != OperacionImobiliaria.TERMINAR) {
                        int numero = this.vista.elegirPropiedad();
                        if (operacion == OperacionImobiliaria.CONSTRUIR_CASA) {
                            this.juegoModel.construirCasa(numero);
                        } else {
                            this.juegoModel.construirHotel(numero);
                        }
                    } else {
                        this.juegoModel.siguientePasoCompletado(sigPaso);
                    }
            }
        }

        System.out.println("\n--RANKING--\n");
        ArrayList<Jugador> ranking = this.juegoModel.ranking();
        this.vista.actualiza();
    }
}


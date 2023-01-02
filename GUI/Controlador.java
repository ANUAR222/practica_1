package GUI;

import civitas.CivitasJuego;
import GUI.VistaTextual;
import civitas.OperacionImobiliaria;
import civitas.OperacionJuego;
import civitas.Jugador;
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

    public Controlador(CivitasJuego juego, CivitasView vista) {
    }


    public void Juega(){
        while(!juegoModel.finalDelJuego()){
            vista.actualiza();
            vista.pausa();
            OperacionJuego sigPaso = juegoModel.siguientePaso();
            vista.mostrarSiguienteOperacion(sigPaso);
            if(sigPaso != OperacionJuego.PASAR_TURNO){
                vista.mostrarEventos();
            }
            
            switch(sigPaso){
                case COMPRAR:
                    if(vista.comprar()==Respuesta.SI){
                        juegoModel.Comprar();
                    }
                    juegoModel.siguientePasoCompletado(sigPaso);
                    break;
                    
                case GESTIONAR:
                    OperacionImobiliaria operacion = vista.elegirOperacion();
                    if(operacion != OperacionImobiliaria.TERMINAR){
                        int numero = vista.elegirPropiedad();
                        if(operacion == OperacionImobiliaria.CONSTRUIR_CASA){
                            juegoModel.construirCasa(numero);
                        }else{
                            juegoModel.construirHotel(numero);
                        }
                    }else{
                        juegoModel.siguientePasoCompletado(sigPaso);
                    }
                    break;

            }
        }
        
        System.out.println("\n--RANKING--\n");
        ArrayList<Jugador> ranking = juegoModel.ranking();
        vista.actualiza();
    }
}
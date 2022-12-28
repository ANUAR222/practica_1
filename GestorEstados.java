/*~estadoInicial() : EstadosJuego
~operacionesPermitidas(jugador : Jugador, estado : EstadosJuego) : OperacionesJuego
~siguienteEstado(jugador : Jugador, estado : EstadosJuego, operacion : OperacionesJuego) : EstadosJuego*/
public class GestorEstados {
    private EstadoJuego estado;
    public GestorEstados(){
        estado = EstadoJuego.INICIO_TURNO;
    }
    public EstadoJuego estadoInicial(){
        return EstadoJuego.INICIO_TURNO;
    }
    public OperacionesJuego operacionesPermitidas(Jugador jugador, EstadoJuego estado){
        OperacionesJuego operacion = null;
        if(estado == EstadoJuego.INICIO_TURNO){
            operacion = OperacionesJuego.PASAR_TURNO;
        }
        else if(estado == EstadoJuego.DESPUES_CARCEL){
            operacion = OperacionesJuego.PASAR_TURNO;
        }
        else if(estado == EstadoJuego.DESPUES_AVANZAR){
            operacion = OperacionesJuego.PASAR_TURNO;
        }
        else if(estado == EstadoJuego.DESPUES_COMPRAR){
            operacion = OperacionesJuego.PASAR_TURNO;
        }
        else if(estado == EstadoJuego.DESPUES_GESTIONAR){
            operacion = OperacionesJuego.PASAR_TURNO;
        }
        return operacion;
    }
    public EstadoJuego siguienteEstado(Jugador jugador, EstadoJuego estado, OperacionesJuego operacion) {
        EstadoJuego siguiente = null;
        if (estado == EstadoJuego.INICIO_TURNO) {
            if (operacion == OperacionesJuego.PASAR_TURNO) {
                siguiente = EstadoJuego.DESPUES_CARCEL;
            }
        } else if (estado == EstadoJuego.DESPUES_CARCEL) {
            if (operacion == OperacionesJuego.PASAR_TURNO) {
                siguiente = EstadoJuego.DESPUES_AVANZAR;
            }
        } else if (estado == EstadoJuego.DESPUES_AVANZAR) {
            if (operacion == OperacionesJuego.PASAR_TURNO) {
                siguiente = EstadoJuego.DESPUES_COMPRAR;
            }
        } else if (estado == EstadoJuego.DESPUES_COMPRAR) {
            if (operacion == OperacionesJuego.PASAR_TURNO) {
                siguiente = EstadoJuego.DESPUES_GESTIONAR;
            }
        } else if (estado == EstadoJuego.DESPUES_GESTIONAR) {
            if (operacion == OperacionesJuego.PASAR_TURNO) {
                siguiente = EstadoJuego.INICIO_TURNO;
            }
        }
        return siguiente;
    }

    public OperacionesJuego siguienteOperacion(Jugador jugadorActual, EstadoJuego estado) {
        OperacionesJuego operacion = null;
        if (estado == EstadoJuego.INICIO_TURNO) {
            operacion = OperacionesJuego.PASAR_TURNO;
        } else if (estado == EstadoJuego.DESPUES_CARCEL) {
            operacion = OperacionesJuego.PASAR_TURNO;
        } else if (estado == EstadoJuego.DESPUES_AVANZAR) {
            operacion = OperacionesJuego.PASAR_TURNO;
        } else if (estado == EstadoJuego.DESPUES_COMPRAR) {
            operacion = OperacionesJuego.PASAR_TURNO;
        } else if (estado == EstadoJuego.DESPUES_GESTIONAR) {
            operacion = OperacionesJuego.PASAR_TURNO;
        }
        return operacion;
    }
}

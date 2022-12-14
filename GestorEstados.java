/*~estadoInicial() : EstadosJuego
~operacionesPermitidas(jugador : Jugador, estado : EstadosJuego) : OperacionesJuego
~siguienteEstado(jugador : Jugador, estado : EstadosJuego, operacion : OperacionesJuego) : EstadosJuego*/
public class GestorEstados {
    private EstadosJuego estado;
    public GestorEstados(){
        estado = EstadosJuego.INICIO_TURNO;
    }
    public EstadosJuego estadoInicial(){
        return EstadosJuego.INICIO_TURNO;
    }
    public OperacionesJuego operacionesPermitidas(Jugador jugador, EstadosJuego estado){
        OperacionesJuego operacion = null;
        if(estado == EstadosJuego.INICIO_TURNO){
            operacion = OperacionesJuego.PASAR_TURNO;
        }
        else if(estado == EstadosJuego.DESPUES_CARCEL){
            operacion = OperacionesJuego.PASAR_TURNO;
        }
        else if(estado == EstadosJuego.DESPUES_AVANZAR){
            operacion = OperacionesJuego.PASAR_TURNO;
        }
        else if(estado == EstadosJuego.DESPUES_COMPRAR){
            operacion = OperacionesJuego.PASAR_TURNO;
        }
        else if(estado == EstadosJuego.DESPUES_GESTIONAR){
            operacion = OperacionesJuego.PASAR_TURNO;
        }
        return operacion;
    }
    public EstadosJuego siguienteEstado(Jugador jugador, EstadosJuego estado, OperacionesJuego operacion) {
        EstadosJuego siguiente = null;
        if (estado == EstadosJuego.INICIO_TURNO) {
            if (operacion == OperacionesJuego.PASAR_TURNO) {
                siguiente = EstadosJuego.DESPUES_CARCEL;
            }
        } else if (estado == EstadosJuego.DESPUES_CARCEL) {
            if (operacion == OperacionesJuego.PASAR_TURNO) {
                siguiente = EstadosJuego.DESPUES_AVANZAR;
            }
        } else if (estado == EstadosJuego.DESPUES_AVANZAR) {
            if (operacion == OperacionesJuego.PASAR_TURNO) {
                siguiente = EstadosJuego.DESPUES_COMPRAR;
            }
        } else if (estado == EstadosJuego.DESPUES_COMPRAR) {
            if (operacion == OperacionesJuego.PASAR_TURNO) {
                siguiente = EstadosJuego.DESPUES_GESTIONAR;
            }
        } else if (estado == EstadosJuego.DESPUES_GESTIONAR) {
            if (operacion == OperacionesJuego.PASAR_TURNO) {
                siguiente = EstadosJuego.INICIO_TURNO;
            }
        }
        return siguiente;
    }
}

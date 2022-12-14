
public class Controlador {
    private CivitasJuego juego;
    private VistaTextual vista;

    public Controlador(CivitasJuego juego, VistaTextual vista) {
        this.juego = juego;
        this.vista = vista;
    }

    public void juega() {
        vista.setCivitasJuego(juego);

        while(!juego.finalDelJuego()) {
            vista.actualizarVista();
            vista.pausa();
            vista.siguientePaso(juego);

            Object EstadoJuego = juego.getEstadoJuego();
            if (juego.getEstado() != EstadoJuego &&
                    juego.getEstado() != EstadoJuego) {
                vista.mostrarEventos();
            }

            OperacionesJuego operacion = juego.getOperacionesPendientes();

            if(operacion == OperacionesJuego.COMPRAR) {
                if(vista.comprar()) {
                    juego.comprar();
                }

                juego.siguientePasoCompletado(operacion);
            } else if(operacion == OperacionesJuego.GESTIONAR) {
                vista.gestionar();
                int propiedad = vista.getGestion();
                TipoGestion gestion = vista.getPropiedad();

                OperacionInmobiliaria operacionInmobiliaria = new OperacionInmobiliaria(gestion, propiedad);

                if (gestion == TipoGestion.TERMINAR) {
                    juego.siguientePasoCompletado(operacionInmobiliaria);
                } else {
                    juego.gestionar(operacionInmobiliaria);
                }
            } else if(operacion == OperacionesJuego.SALIRCARCEL) {
                if(vista.salirCarcel()) {
                    juego.salirCarcelPagando();
                } else {
                    juego.salirCarcelTirando();
                }

                juego.siguientePasoCompletado(operacion);
            }
        }

        vista.mostrarRanking();
    }
}
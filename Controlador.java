import java.util.ArrayList;

public class Controlador {
    private static final boolean COMPRAR =  true;
    private static final boolean GESTIONAR = true;
    private static final boolean SALIR_CARCEL = true;
    private CivitasJuego juego;
    private VistaTextual vista;
    public Controlador(CivitasJuego juego, VistaTextual vista){
        this.juego = juego;
        this.vista = vista;
    }
    public void juega(){
        vista.setCivitasJuego(juego);
        while(!juego.finalDelJuego()){
            vista.actualizarVista();
            vista.pausa();
            juego.siguientePaso();
            if(juego.getJugadorActual().getEncarcelado()){
                vista.mostrarEventos();
            }
            if(!juego.finalDelJuego()){
                boolean operacion = juego.getOperacion();
                if (operacion == COMPRAR) {
                    if (vista.comprar()) {
                        juego.comprar();
                    }
                    juego.siguientePasoCompletado(OperacionesJuego.COMPRAR);
                } else if (operacion == GESTIONAR) {
                    vista.gestionar();
                    int gestion = vista.getGestion();
                    int propiedad = vista.getPropiedad();
                    OperacionInmobiliaria op = new OperacionInmobiliaria(gestion, propiedad);
                    juego.gestionar(op);
                    if (gestion == 5) {
                        juego.siguientePasoCompletado(OperacionesJuego.GESTIONAR);
                    }
                } else if (operacion == SALIR_CARCEL) {
                    if (vista.salirCarcel() == SalidasCarcel.PAGANDO) {
                        juego.salirCarcelPagando();
                    } else {
                        juego.salirCarcelTirando();
                    }
                    juego.siguientePasoCompletado(OperacionesJuego.SALIR_CARCEL);
                }
            }
        }
        ArrayList<Jugador> ranking = juego.ranking();
        System.out.println("Ranking de jugadores: ");
        for(int i = 0; i < ranking.size(); i++){
            System.out.println((i+1) + "º " + ranking.get(i).getNombre());
        }
    }
}

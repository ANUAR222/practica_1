import java.util.ArrayList;

public class Sorpresas {
    private String texto;
    private int valor;
    private Tablero tab;
    private Sorpresa tipo;
    private MazoSorpresas mazoS;
    Sorpresas(Sorpresa TipoSorpresa, Tablero tabler) {
        init();
        valor=tabler.getCarcel();
        texto="La fortuna te sonrie, consigues un viaje gratuito a la Carcel. Disfruta tu falta de libertad";
        tipo=TipoSorpresa;
        tab=tabler;
    }
    Sorpresas(Sorpresa TipoSorpresa, Tablero tabler, int value){
        init();
        valor=value;
        texto="Sientes que tu casilla actual es muy aburrida. Toca teletransportarse a una nueva.";
        tipo=TipoSorpresa;
        tab=tabler;
    }
    Sorpresas(Sorpresa TipoSorpresa, MazoSorpresas mazo){
        init();
        init();
        texto="El nuevo guardia de la carcel es el amigo de la hermana de la madre de tu vecino. Evita la Cárcel 1 vez";
        tipo=TipoSorpresa;
        mazoS=mazo;
    }
    Sorpresas(Sorpresa TipoSorpresa, String text,int value){
        init();
        tipo=TipoSorpresa;
        texto=text;
        valor=value;
    }
    //Sorpresa que indica que el jugador debe convertirse. Al aplicar esta sorpresa
    //se sustituirá al jugador por un nuevo JugadorEspeculador en la lista de jugadores, invocando para
    //ello al constructor, que recibirá como parámetros el jugador de partida y la fianza especificada en el
    //valor de la carta sorpresa.
    Sorpresas (Sorpresa TipoSorpresa,int fianza){
        init();
        tipo=TipoSorpresa;
        texto="Felicidades, te has convertido en un Jugador Especulador";
        valor=fianza;
    }
    void init(){
        tab= null;
        mazoS= null;
        valor=-1;

    }
   
    public static boolean jugadorCorrecto(int actual, ArrayList<Jugador> todos){
        return actual>=0 && actual<todos.size();
    }
    public void informe(int actual, ArrayList<Jugador> todos){
        Diario.getInstance().ocurreEvento("Se ha aplicado la sorpresa "+texto+" al jugador "+todos.get(actual).getNombre());
    }
    public void aplicarAJugador(int actual, ArrayList<Jugador> todos){
        if(jugadorCorrecto(actual,todos)){
            informe(actual,todos);
            switch(tipo){
                case IRCARCEL:
                    aplicarAJugador_irCarcel(actual,todos);
                    break;
                case IRCASILLA:
                    aplicarAJugador_irACasilla(actual,todos);
                    break;
                case PAGARCOBRAR:
                    aplicarAJugador_pagarCobrar(actual,todos);
                    break;
                case PORCASAHOTEL:
                    aplicarAJugador_porCasaHotel(actual,todos);
                    break;
                case PORJUGADOR:
                    aplicarAJugador_porJugador(actual,todos);
                    break;
                case SALIRCARCEL:
                    aplicarAJugador_salirCarcel(actual,todos);
                    break;
                case ESPECULADOR:
                    aplicarAJugador_especulador(actual,todos);
                    break;
            }
        }
    }
    public void aplicarAJugador_irCarcel(int actual, ArrayList<Jugador> todos){
        if(jugadorCorrecto(actual,todos)){
            informe(actual,todos);
            todos.get(actual).encarcelar(tab.getCarcel());
        }
    }

    public void aplicarAJugador_especulador(int actual, ArrayList<Jugador> todos){
        if(jugadorCorrecto(actual,todos)){
            informe(actual,todos);
            JugadorEspeculador nuevo = new JugadorEspeculador(todos.get(actual),valor);
            todos.set(actual, nuevo);
        }
    }
    public void aplicarAJugador_irACasilla(int actual, ArrayList<Jugador> todos){
        if(jugadorCorrecto(actual,todos)){
            informe(actual,todos);
            int casillaActual=todos.get(actual).getCasillaActual();
            int tirada=tab.calcularTirada(casillaActual,valor);
            int nuevaPosicion=tab.nuevaPosicion(casillaActual,tirada);
            todos.get(actual).moverACasilla(nuevaPosicion);
            tab.getCasilla(nuevaPosicion).recibeJugador(actual,todos);
        }
    }
    public void aplicarAJugador_pagarCobrar(int actual, ArrayList<Jugador> todos){
        if(jugadorCorrecto(actual,todos)){
            informe(actual,todos);
            todos.get(actual).modificarSaldo(valor);
        }
    }
    public void aplicarAJugador_porCasaHotel(int actual, ArrayList<Jugador> todos){
        if(jugadorCorrecto(actual,todos)){
            informe(actual,todos);
            todos.get(actual).modificarSaldo(valor*todos.get(actual).cantidadCasasHoteles());
        }
    }
    public void aplicarAJugador_porJugador(int actual, ArrayList<Jugador> todos){
        if(jugadorCorrecto(actual,todos)){
            informe(actual,todos);
            Sorpresas sorpresa=new Sorpresas(Sorpresa.PAGARCOBRAR,"",-valor);
            for(int i=0;i<todos.size();i++){
                if(i!=actual){
                    sorpresa.aplicarAJugador(i,todos);
                }
            }
            sorpresa=new Sorpresas(Sorpresa.PAGARCOBRAR,"",valor*todos.size());
            sorpresa.aplicarAJugador(actual,todos);
        }
    }
    public void aplicarAJugador_salirCarcel(int actual, ArrayList<Jugador> todos){
        if(jugadorCorrecto(actual,todos)){
            informe(actual,todos);
            boolean tieneSalvoconducto=false;
            for(int i=0;i<todos.size() && !tieneSalvoconducto;i++){
                tieneSalvoconducto=todos.get(i).tieneSalvoconducto();
            }
            if(!tieneSalvoconducto){
                todos.get(actual).obtenerSalvoconducto(this);
                salirDelMazo();
            }
        }
    }
    public void salirDelMazo(){
        if(tipo==Sorpresa.SALIRCARCEL){
            mazoS.inhabilitarCartaEspecial(this);
        }
    }
    public void usada(){
        if(tipo==Sorpresa.SALIRCARCEL){
            mazoS.habilitarCartaEspecial(this);
        }
    }



}

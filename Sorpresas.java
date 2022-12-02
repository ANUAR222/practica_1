import java.util.ArrayList;

public class Sorpresas {
    private String texto;
    private int valor;

    private Sorpresa tipo;
    Sorpresas(Sorpresa TipoSorpresa, tablero tabler) {
        valor=tabler.getCarcel();
        texto="La fortuna te sonrie, consigues un viaje gratuito a la Carcel. Disfruta tu falta de libertad";
        tipo=TipoSorpresa;
    }
    Sorpresas(Sorpresa TipoSorpresa, tablero tabler,int value){
        valor=value;
        texto="Sientes que tu casilla actual es muy aburrida. Toca teletransportarse a una nueva.";
        tipo=TipoSorpresa;
    }
    Sorpresas(Sorpresa TipoSorpresa, MazoSorpresas mazo){
        init();
        texto="El nuevo guardia de la carcel es el amigo de la hermana de la madre de tu vecino. Evita la Cárcel 1 vez";
        tipo=TipoSorpresa;
    }
    Sorpresas(Sorpresa TipoSorpresa, String text,int value){
        tipo=TipoSorpresa;
        texto=text;
        valor=value;
    }
    void init(){
        valor=-1;
    }
    /*boolean jugadorCorrecto (int actual, ArrayList<Jugador> todos): comprueba si el primer
parámetro es un índice válido para acceder a los elementos del segundo parámetro.
• void informe (int actual, ArrayList<Jugador> todos) : informa al diario que se está
aplicando una sorpresa a un jugador (se indica el nombre de este)
• void aplicarAJugador(int actual, ArrayList<Jugador> todos): llama al método
aplicarAJugador_<tipo_de_sorpresa> adecuado en función del valor del tipo de atributo
sorpresa de que se trate.
• void aplicarAJugador_irCarcel (int actual, ArrayList<Jugador> todos) : actual es el índice
del jugador sobre el que se va a actuar. Si el jugador es correcto, se utiliza el método
informe y se encarcela al jugador (método encarcelar) indicado.

• void aplicarAJugador_irACasilla (int actual, ArrayList<Jugador> todos):
◦ si el jugador es correcto, se utiliza el método informe y obtiene la casilla actual del
jugador
◦ se calcula la tirada utilizando el método calcularTirada(casillaActual, valor) del tablero.
◦ se obtiene la nueva posición del jugador con el método nuevaPosicion(casillaActual,
tirada) del tablero.
◦ se mueve al jugador a esa nueva posición (método moverACasilla)
◦ se indica a la casilla que está en la posición del valor de la sorpresa que reciba al jugador
(método recibeJugador)
Aunque a simple vista pueda parecer innecesario utilizar los métodos calcularTirada y
nuevaPosicion de Tablero, ya que la sorpresa dispone directamente del número de casilla al que hay
que ir, es necesario utilizarlos para que quede registrado un posible paso por la salida como
consecuencia del salto a la casilla nueva.

• void aplicarAJugador_pagarCobrar (int actual, ArrayList<Jugador> todos): si el jugador
es correcto, se utiliza el método informe y se modifica el saldo del jugador actual(método
modificarSaldo) con el valor de la sorpresa.
• void aplicarAJugador_porCasaHotel (int actual, ArrayList<Jugador> todos): si el
jugador es correcto, se utiliza el método informe y se modifica el saldo del jugador
actual(método modificarSaldo) con el valor de la sorpresa multiplicado por el número de
casas y hoteles del jugador.
• void aplicarAJugador_porJugador (int actual, ArrayList<Jugador> todos): en este tipo
de sorpresa todos los jugadores dan dinero al jugador actual. Para ello, si el jugador es actual
es correcto, se utiliza el método informe y además:
◦ se crea una sorpresa de tipo PAGARCOBRAR con el valor de la sorpresa multiplicado
por -1 y se aplica a todos los jugadores menos el actual.
◦ se crea una sorpresa de tipo PAGARCOBRAR con el valor de la sorpresa multiplicado
por el número de jugadores excluyendo al actual y se aplica solo al jugador actual.

• void aplicarAJugador_salirCarcel (int actual, ArrayList<Jugador> todos): si el jugador es
correcto, se utiliza el método informe y se pregunta a todos los jugadores si alguien tiene la
sorpresa para evitar la cárcel (método tieneSalvoconducto). Si nadie la tiene, la obtiene el
jugador actual (método obtenerSalvoconducto) y se llama al método salirDelMazo.
• void salirDelMazo (): si el tipo de la sorpresa es la que evita la cárcel, inhabilita la carta
especial en el mazo de sorpresas.
• void usada (): si el tipo de la sorpresa es la que evita la cárcel, habilita la carta especial en
el mazo de sorpresas.
• String toString (): devuelve el nombre de la sorpresa*/
    public boolean jugadorCorrecto(int actual, ArrayList<Jugador> todos){
        return actual>=0 && actual<todos.size();
    }
    public void informe(int actual, ArrayList<Jugador> todos){
        civitas.Diario.getInstance().ocurreEvento("Se ha aplicado la sorpresa "+texto+" al jugador "+todos.get(actual).getNombre());
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
            }
        }
    }
    public void aplicarAJugador_irCarcel(int actual, ArrayList<Jugador> todos){
        if(jugadorCorrecto(actual,todos)){
            informe(actual,todos);
            todos.get(actual).encarcelar(tablero.getCarcel());
        }
    }
    public void aplicarAJugador_irACasilla(int actual, ArrayList<Jugador> todos){
        if(jugadorCorrecto(actual,todos)){
            informe(actual,todos);
            int casillaActual=todos.get(actual).getCasillaActual();
            int tirada=tablero.calcularTirada(casillaActual,valor);
            int nuevaPosicion=tablero.nuevaPosicion(casillaActual,tirada);
            todos.get(actual).moverACasilla(nuevaPosicion);
            tablero.getCasilla(nuevaPosicion).recibeJugador(actual,todos);
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
            MazoSorpresas.inhabilitarCartaEspecial(this);
        }
    }
    public void usada(){
        if(tipo==Sorpresa.SALIRCARCEL){
            MazoSorpresas.habilitarCartaEspecial(this);
        }
    }



}

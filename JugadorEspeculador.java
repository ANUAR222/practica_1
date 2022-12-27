public class JugadorEspeculador extends Jugador {
    /* Se desea añadir al juego un nuevo tipo de jugador que se llamará JugadorEspeculador. Los jugadores
especuladores se comportan como los jugadores de los que ya dispones, salvo en las siguientes
cuestiones:
• Pueden construir el doble de casas y hoteles respecto a un jugador normal (atributo de clase
FactorEspeculador=2).
• En caso de que se les requiera ser encarcelados (método encarcelar), si no disponen del salvo
conducto o carta de libertad, pueden intentar pagar una fianza de su saldo (atributo fianza) y
así evitar la cárcel. El pago de la fianza siempre implica que se pueda hacer frente al pago con
el saldo sin caer en bancarrota.
• Los especuladores solo pagan la mitad de impuestos cuando se les solicita.
Los jugadores especuladores solo se generan por conversión de un jugador normal. En Java, se
recomienda que el constructor de JugadorEspeculador herede del constructor de copia de Jugador
para hacer la conversión, añadiendo un nuevo parámetro que será la fianza.
Es importante tener en cuenta que durante la creación de un especulador hay que indicar a sus
propiedades que tienen un nuevo propietario (actualizaPropietarioPorConversion), ya que a nivel de
identidad el jugador especulador es un objeto distinto al jugador que se usa como base en el proceso
de conversión.
Se añade un nuevo tipo de sorpresa que indica que el jugador debe convertirse. Al aplicar esta sorpresa
se sustituirá al jugador por un nuevo JugadorEspeculador en la lista de jugadores, invocando para
ello al constructor, que recibirá como parámetros el jugador de partida y la fianza especificada en el
valor de la carta sorpresa.
También es conveniente que el método que convierte la información de un jugador a una cadena de
caracteres se redefina en el jugador especulador para añadir información relativa a que se trata de un
tipo de jugador.
Por último, debes prestar también atención a si es necesario volver a declarar consultores de
CasasMax y HotelesMax en JugadorEspeculador y cambiar la visibilidad de esos métodos en la clase
Jugador*/
    int fianza;
    static int FactorEspeculador=2;
    private Jugador propietario;
    JugadorEspeculador(Jugador jugador, int fianza) {
        super(jugador);
        this.fianza=fianza;
        propietario=jugador;
    }

    //metodo ToString
    @Override
    public String toString() {
        return "JugadorEspeculador{" + "fianza=" + fianza + ", propietario=" + propietario + '}';
    }

    //consultor de CasasMax
    @Override
    int getCasasMax() {
        return super.getCasasMax()*FactorEspeculador;
    }
    //consultor de HotelesMax
    @Override
    int getHotelesMax() {
        return super.getHotelesMax()*FactorEspeculador;
    }

    //metodo encarcelar
    @Override
    public Boolean encarcelar(int numCasillaCarcel) {
        if (super.tengoSalvoconducto()) {
            super.perderSalvoconducto();
            civitas.Diario.getInstance().ocurreEvento("El jugador especulador " + super.getNombre() + " se libra de la cárcel");
            return false;
        } else {
            if (super.puedoPagarFianza(fianza)) {
                super.pagarFianza(fianza);
                civitas.Diario.getInstance().ocurreEvento("El jugador especulador " + super.getNombre() + " paga la fianza y se libra de la cárcel");
                return false;
            } else {
                return super.encarcelar(numCasillaCarcel);
            }
        }
    }

    //metodo pagaImpuesto
    @Override
    public Boolean pagaImpuesto(float cantidad) {
        return super.pagaImpuesto(cantidad/2);
    }
}

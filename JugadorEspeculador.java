public class JugadorEspeculador extends Jugador {

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

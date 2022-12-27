import java.util.ArrayList;

public class Jugador {
    private int Casasmax=4;
    private int casasporHotel=4;
    private int HotelesMax=4;
    private boolean encarcelado;
    private String nombre;
    private int numCasillaActual;
    private boolean puedeComprar;
    private float saldo;
    private Sorpresas salvoconducto;
    private float SaldoInicial=7500;
    private float PasoPorSalida=1000;
    private float PrecioLibertad=200;

    private ArrayList<TituloPropiedad> propiedades;
    Jugador(String nombre) {
        this.nombre=nombre;
        this.saldo=SaldoInicial;
        this.encarcelado=false;
        this.numCasillaActual=0;
        this.puedeComprar=true;
        this.salvoconducto=null;
    }
    Jugador(Jugador otro){
        this.nombre=otro.nombre;
        this.saldo=otro.saldo;
        this.encarcelado=otro.encarcelado;
        this.numCasillaActual=otro.numCasillaActual;
        this.puedeComprar=otro.puedeComprar;
        this.salvoconducto=otro.salvoconducto;
    }
    public Boolean debeSerEncarcelado()
    {
        if (encarcelado)
            return false;
        else
        {
            if (tieneSalvoconducto())
            {
                perderSalvoconducto();
                civitas.Diario.ocurreEvento("El jugador " + nombre + " se libra de la carcel");
                return false;
            }
            else
            {
                return true;
            }
        }
    }
    public Boolean encarcelar(int numCasillaCarcel)
    {
        if (debeSerEncarcelado())
        {
            moverACasilla(numCasillaCarcel);
            encarcelado = true;
            civitas.Diario.ocurreEvento("El jugador " + nombre + " ha sido encarcelado");
        }
        return encarcelado;
    }
    public Boolean obtenerSalvoconducto(Sorpresas salvoconducto)
    {
        if (encarcelado)
            return false;
        else
        {
            this.salvoconducto = salvoconducto;
            return true;
        }
    }
    public Boolean perderSalvoconducto()
    {
        if (tieneSalvoconducto())
        {
            salvoconducto.usada();
            salvoconducto = null;
            return true;
        }
        else
            return false;
    }
    public Boolean tieneSalvoconducto()
    {
        return salvoconducto != null;
    }
    public Boolean paga(float cantidad)
    {
        return modificarSaldo(-cantidad);
    }
    public Boolean pagaImpuesto(float cantidad)
    {
        if (encarcelado)
            return false;
        else
            return paga(cantidad);
    }
    public Boolean pagaAlquiler(float cantidad)
    {
        if (encarcelado)
            return false;
        else
            return paga(cantidad);
    }
    public Boolean recibe(float cantidad)
    {
        if (encarcelado)
            return false;
        else
            return modificarSaldo(cantidad);
    }
    public Boolean modificarSaldo(float cantidad)
    {
        saldo += cantidad;
        civitas.Diario.ocurreEvento("El jugador " + nombre + " ha modificado su saldo en " + cantidad);
        return true;
    }
    public Boolean moverACasilla(int numCasilla)
    {
        if (encarcelado)
            return false;
        else
        {
            numCasillaActual = numCasilla;
            puedeComprar = false;
            civitas.Diario.ocurreEvento("El jugador " + nombre + " se ha movido a la casilla " + numCasilla);
            return true;
        }
    }
    public Boolean puedoGastar(float precio)
    {
        if (encarcelado)
            return false;
        else
            return saldo >= precio;
    }
    public Boolean vender(int ip)
    {
        if (encarcelado)
            return false;
        else
        {
            if (existeLaPropiedad(ip))
            {
                TituloPropiedad titulo = propiedades.get(ip); ;
                if (titulo.vender(this))
                {
                    propiedades.remove(ip);
                    civitas.Diario.ocurreEvento("El jugador " + nombre + " ha vendido la propiedad " + titulo.getNombre());
                    return true;
                }
                else
                    return false;
            }
            else
                return false;
        }
    }
    public String toString(){
        return "Jugador{" + "nombre=" + nombre + ", encarcelado=" + encarcelado + ", saldo=" + saldo + ", numCasillaActual=" + numCasillaActual + ", puedeComprar=" + puedeComprar + ", salvoconducto=" + salvoconducto + ", propiedades=" + propiedades + '}';
    }
    public boolean existeLaPropiedad(int ip) {
        return ip >= 0 && ip < propiedades.size();
    }

    Boolean tieneAlgoQueGestionar()
    {
        return propiedades.size() > 0;
    }
    public Boolean puedeSalirCarcelPagando()
    {
        return saldo >= PrecioLibertad;
    }
    public Boolean salirCarcelPagando()
    {
        if (encarcelado && puedeSalirCarcelPagando())
        {
            paga(PrecioLibertad);
            encarcelado = false;
            civitas.Diario.ocurreEvento("El jugador " + nombre + " ha pagado para salir de la carcel");
            return true;
        }
        else
            return false;
    }
    Boolean salirCarcelTirando()
    {
        if (Dado.salgoDeLaCarcel())
        {
            encarcelado = false;
            civitas.Diario.ocurreEvento("El jugador " + nombre + " ha salido de la carcel");
            return true;
        }
        else
            return false;
    }
    Boolean pasaPorSalida()
    {
        modificarSaldo(PasoPorSalida);
        civitas.Diario.ocurreEvento("El jugador " + nombre + " ha pasado por la salida");
        return true;
    }
    int compareTo(Jugador otro)
    {
        return Float.compare(saldo, otro.saldo);
    }
    int getCasillaActual()
    {
        return numCasillaActual;
    }
    String getNombre()
    {
        return nombre;
    }
    boolean getEncarcelado()
    {
        return encarcelado;
    }
    public int cantidadCasasHoteles() {
        int total = 0;

        total += cantidadCasasHoteles();

        return total;
    }

    public int getNumCasillaActual() {
        return numCasillaActual;
    }
    public int getNumPropiedades() {
        return propiedades.size();
    }
    boolean cancelarHipoteca (int ip){
        boolean result = false;
        if (encarcelado)
            return result;
        if (existeLaPropiedad (ip)){
            TituloPropiedad propiedad = propiedades.get(ip);
            float cantidad = propiedad.getImporteCancelarHipoteca();
            boolean puedoGastar = puedoGastar(cantidad);
            if (puedoGastar){
                propiedades.get(ip).cancelarHipoteca(this);
                result = true;
            }
        }
        return result;
    }
    boolean comprar (TituloPropiedad titulo){
        if (encarcelado)
            return false;
        else
        {
            if (puedeComprar)
            {
                float precio = titulo.getPrecioCompra();
                if (puedoGastar(precio))
                {
                    titulo.comprar(this);
                    propiedades.add(titulo);
                    civitas.Diario.ocurreEvento("El jugador " + nombre + " ha comprado la propiedad " + titulo.getNombre());
                    return true;
                }
                else
                    return false;
            }
            else
                return false;
        }
    }
    boolean construirHotel (int ip) {
        if (encarcelado)
            return false;
        else {
            if (existeLaPropiedad(ip)) {
                TituloPropiedad titulo = propiedades.get(ip);
                titulo.construirHotel(this);
                civitas.Diario.ocurreEvento("El jugador " + nombre + " ha construido un hotel en la propiedad " + titulo.getNombre());
                return true;
            } else
                return false;
        }
    }
    boolean construirCasa (int ip){
        if (encarcelado)
            return false;
        else
        {
            if (existeLaPropiedad(ip))
            {
                TituloPropiedad titulo = propiedades.get(ip);

                titulo.construirCasa(this);
                civitas.Diario.ocurreEvento("El jugador " + nombre + " ha construido una casa en la propiedad " + titulo.getNombre());
                return true;
            }
            else
                return false;
        }
    }
    Boolean hipotecar (int ip){
        if (encarcelado)
            return false;
        else
        {
            if (existeLaPropiedad(ip))
            {
                TituloPropiedad titulo = propiedades.get(ip);
                boolean hipotecado= titulo.hipotecar(this);
                civitas.Diario.ocurreEvento("El jugador " + nombre + " ha hipotecado la propiedad " + titulo.getNombre());
                return true;
            }
            else
                return false;
        }
    }

     int getCasasMax() {
        return Casasmax;
    }

    int getHotelesMax() {
        return HotelesMax;
    }

    protected boolean tengoSalvoconducto() {
        return salvoconducto != null;
    }

    protected boolean puedoPagarFianza(int fianza) {
        return saldo >= fianza;
    }

    protected void pagarFianza(int fianza) {
        paga(fianza);
    }
}

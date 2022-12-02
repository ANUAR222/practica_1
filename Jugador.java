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
    /*Boolean debeSerEncarcelado (): este método siempre devuelve false si el jugador ya está
encarcelado. En caso contrario, si no tiene la carta sorpresa que permite evitar la cárcel
(tieneSalvoconducto()) devolverá true. En caso de que disponga de dicha carta para librarse
de la cárcel, la pierde por usarla (perderSalvoconducto()), se informa al diario de que el
jugador se libra de la cárcel y se devuelve false.
• Boolean encarcelar (int numCasillaCarcel): si el jugador debe ser encarcelado
(debeSerEncarcelado ()), mueve (moverACasilla) al jugador a la casilla indicada como
parámetro (que debería ser el índice de la cárcel), cambia el valor del atributo encarcelado a
true e informa al diario de lo ocurrido. Siempre devuelve el valor del citado atributo
encarcelado.
• Boolean obtenerSalvoconducto (Sorpresa s): si el jugador está encarcelado no se realiza
ninguna acción salvo devolver false. En caso contrario, se guarda la referencia al parámetro
en el atributo salvoconducto y se devuelve true.
• void perderSalvoconducto (): se indica al salvoconducto que ha sido usado (método
usada()) y se hace nulo este atributo.
• Boolean tieneSalvoconducto (): indica si la referencia al salvoconducto no es nula.
• Boolean puedeComprarCasilla (): si el jugador está encarcelado fija el atributo
puedeComprar a false y a true en caso contrario. Se devuelve el valor de este atributo.
• Boolean paga (float cantidad): llama al método modificarSaldo con el valor del parámetro
multiplicado por -1 y devuelve el valor devuelto por modificarSaldo.
• Boolean pagaImpuesto (float cantidad): si el jugador está encarcelado devuelve false y no
se realiza ninguna otra acción. En caso contrario se llama al método paga con el mismo
parámetro y se devuelve lo que devuelve este último método.
• Boolean pagaAlquiler (float cantidad): el cuerpo de este método tiene el mismo código
(por ahora) que pagaImpuesto.
• Boolean recibe (float cantidad): si el jugador está encarcelado devuelve false y no se
realiza ninguna otra acción. En caso contrario se llama al método modificarSaldo con el
mismo parámetro y se devuelve lo que devuelve este último método.
• Boolean modificarSaldo (float cantidad): incrementa el saldo en la cantidad indicada por el
parámetro e informa al diario. Siempre devuelve true.
• Boolean moverACasilla (int numCasilla): si el jugador está encarcelado devuelve false y
no se realiza ninguna otra acción. En caso contrario se fija el atributo numCasillaActual al
valor del parámetro, el valor de puedeComprar a false, se informa al diario del movimiento
del jugador y se devuelve true.
• Boolean puedoGastar (float precio): si el jugador está encarcelado devuelve false y no se
realiza ninguna otra acción. En caso contrario el método indica si el saldo el mayor o igual
que el parámetro.
• Boolean vender (int ip): si el jugador está encarcelado devuelve false y no se realiza
ninguna otra acción. En caso contrario, si existe la propiedad (método existeLaPropiedad) se
indica al título de propiedad con número ip que es vendida por el jugador(método vender de
TituloPropiedad). Si ese proceso de venta se ha realizado satisfactoriamente se elimina la
propiedad de la lista de propiedades del jugador , se informa al diario de la venta y se
devuelve true. Si la propiedad no existe o no se puede realizar la venta se devuelve false.
• Boolean tieneAlgoQueGestionar (): este método indica si el jugador tiene propiedades
• Boolean puedeSalirCarcelPagando (): este método informa de si el saldo del jugador es
mayor o igual que el precio que hay que pagar por salir de la cárcel
• Boolean salirCarcelPagando (): si el jugador está encarcelado y puede salir de la cárcel
pagando, paga ese precio (método paga), deja de estar encarcelado, se informa al diario de
este hecho y se devuelve true. En cualquier otro caso devuelve false.
• Boolean salirCarcelTirando (): se pregunta al dado si el jugador debe salir de la cárcel. En
caso afirmativo se fija el valor de encarcelado a false y se informa al diario. El valor
devuelto indica si se ha conseguido salir o no.
• Boolean pasaPorSalida (): se incrementa el saldo con el método modificarSaldo tanto como
indique el premio por pasar por la salida. También se informa al diario del evento y siempre
se devuelve true.
• int compareTo (Jugador otro): este método delega en el método compare de clase Float
para comparar el saldo del jugador con el saldo del jugador pasado como parámetro. En
Ruby se utilizará el operador <=> en vez de el método compareTo y este delegará en el uso
del mismo operador entre los mismos saldos. Investiga lo que devuelve el método
compareTo de la interfaz Comparable y el operador <=>*/
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

    public boolean existeLaPropiedad(int ip) {
        return ip >= 0 && ip < propiedades.size();
    }

    Boolean tieneAlgoQueGestionar()
    {
        return propiedades.size() > 0;
    }
    public Boolean puedeSalirCarcelPagando()
    {
        return saldo >= CasillaCarcel.PRECIO_LIBERTAD;
    }
    public Boolean salirCarcelPagando()
    {
        if (encarcelado && puedeSalirCarcelPagando())
        {
            paga(CasillaCarcel.PRECIO_LIBERTAD);
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
        modificarSaldo(Casilla.Salida.PREMIO_SALIDA);
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
}

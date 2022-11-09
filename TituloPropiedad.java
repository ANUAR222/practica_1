import java.util.ArrayList;

/*Esta clase tiene visibilidad pública. Aquí se proporciona la información adicional que se requiere y
que no está reflejada en el diagrama de clases.
• Constructor: los parámetros que recibe el constructor son los siguientes y en este orden:
nombre, precio base de alquiler, factor de revalorización, precio base de hipoteca, precio de
compra y precio por edificar. Debes tener en cuenta que todos los títulos de propiedad
comienzan existiendo sin propietario, casas ni hoteles y sin hipotecar.
• String toString (): este método debe proporcionar una representación en forma de cadena de
caracteres del estado completo del objeto.
• float getPrecioAlquiler (): devuelve el precio del alquiler calculado según las reglas del
juego. Si el título se encuentra hipotecado o si el propietario está encarcelado (ver
propietarioEncarcelado()) el precio del alquiler será cero.
• float getImporteCancelarHipoteca (): devuelve el importe que se obtiene al hipotecar el
título multiplicado por factorInteresesHipoteca
• Boolean cancelarHipoteca (Jugador jugador): si el título está hipotecado y el jugador
pasado como parámetro es el propietario del título (esEsteElPropietario(jugador)), el
propietario debe pagar (método paga de la clase jugador) el importe de cancelar la hipoteca
y el título dejará de estar hipotecado. En caso contrario no se realiza la operación. El valor
devuelto indica si se ha realizado la operación o no.
• Boolean hipotecar (Jugador jugador): si el título no está hipotecado y el jugador pasado
como parámetro es el propietario del título, el propietario recibe (método recibe de la clase
jugador) el importe de la hipoteca y el título pasa a estar hipotecado. En caso contrario no se
realiza la operación. El valor devuelto indica si se ha realizado la operación o no.
• void tramitarAlquiler (Jugador jugador): si el título tiene propietario, y el jugador pasado
como parámetro no es el propietario del título, ese jugador paga el alquiler (método
pagaAlquiler) y el propietario recibe ese mismo importe (método recibe)
• Boolean propietarioEncarcelado (): devuelve true si el propietario está encarcelado. En
caso contrario, o si no tiene propietario devuelve false.
• int cantidadCasasHoteles (): devuelva la suma del número de las casas y hoteles
construidos.
• Boolean derruirCasas(int n, Jugador jugador): si el jugador pasado como parámetro es el
propietario del título y el número de casas construidas es mayor o igual que el parámetro n,
se decrementa el contador de casas construidas en n unidades. En caso contrario no se
realiza la operación. El valor devuelto indica si se ha realizado la operación o no
• float getPrecioVenta(): devuelve la suma del precio de compra con el precio de edificar las
casas y hoteles que tenga, multiplicado éste último por el factor de revalorización.
• Boolean construirCasa(Jugador jugador): este método devuelve un booleano que se
inicializa a false al comenzar. Se comprueba si el jugador que recibe como parámetro es su
propietario. Si lo es, paga el precio de la casa, incrementa en uno el número de casas y pone
a true el booleano que devuelve.
• Boolean construirHotel(Jugador jugador): este método devuelve un booleano que se
inicializa a false al comenzar. Se comprueba si el jugador que recibe como parámetro es su
propietario. Si lo es, paga el precio de del hotel, incrementa en uno el número de hoteles y
pone a true el booleano que devuelve.
• Comprar(Jugador jugador): este método devuelve un booleano. Comprueba si la
propiedad tiene propietario, si tiene propietario devuelve FALSE, de no ser así asigna
propietario y paga el precio de la propiedad y devuelve TRUE.
La implementación del resto de métodos de esta clase queda ya especificada por su nombre y se
pueden realizar sin instrucciones adicionales.

alquilerBase : float
-factorInteresesHipoteca : float = 1.1
-factorRevalorizacion : float
-hipotecaBase : float
-hipotecado : boolean
-nombre : string
-numCasas : int
-numHoteles : int
-precioCompra : float
-precioEdificar : float
~actualizaPropietarioPorConversion(jugador : Jugador) : void
~cancelarHipoteca(jugador : Jugador) : boolean
~cantidadCasasHoteles() : int
~comprar(jugador : Jugador) : boolean
~construirCasa(jugador : Jugador) : boolean
~construirHotel(jugador : Jugador) : boolean
~derruirCasas(n : int, jugador : Jugador) : boolean
-esEsteElPropietario(jugador : Jugador) : boolean
+getHipotecado() : boolean
~getImporteCancelarHipoteca() : float
-getImporteHipoteca() : float
~getNombre() : string
~getNumCasas() : int
~getNumHoteles() : int
-getPrecioAlquiler() : float
~getPrecioCompra() : float
~getPrecioEdificar() : float
-getPrecioVenta() : float
~getPropietario() : Jugador
~hipotecar(jugador : Jugador) : boolean
-propietarioEncarcelado() : boolean
~tienePropietario() : boolean
~TituloPropiedad(nom : string, ab : float, fr : float, hb : float, pc : float, pe : float)
+toString() : string
~tramitarAlquiler(jugador : Jugador) : void
~vender(jugador : Jugador) : boolean
*/
public class TituloPropiedad {
    private String nombre;
private float alquilerBase;
private float factorRevalorizacion;
private float factorInteresesHipoteca;
private float hipotecaBase;
private boolean hipotecado;
private Jugador propietario;
private int numCasas;
private int numHoteles;
private float precioCompra;
private float precioEdificar;
public TituloPropiedad(String nom, float ab, float fr, float hb, float pc, float pe) {
nombre = nom;
alquilerBase = ab;
factorRevalorizacion = fr;
factorInteresesHipoteca = 1.1f;
hipotecaBase = hb;
hipotecado = false;
numCasas = 0;
numHoteles = 0;
precioCompra = pc;
precioEdificar = pe;
}
public String getNombre() {
return nombre;
}
public float getPrecioCompra() {
return precioCompra;
}
public float getPrecioEdificar() {
return precioEdificar;
}
public boolean getHipotecado() {
return hipotecado;
}
public Jugador getPropietario() {
return propietario;
}
public int getNumCasas() {
return numCasas;
}
public int getNumHoteles() {
return numHoteles;
}
public float getPrecioAlquiler() {
if (hipotecado || propietarioEncarcelado()) {
return 0;
} else {
return alquilerBase * (1 + (numCasas * 0.5f) + (numHoteles * 2.5f));
}
}
public float getImporteHipoteca() {
return hipotecaBase * (1 + (numCasas * 0.5f) + (numHoteles * 2.5f));
}
public float getImporteCancelarHipoteca() {
return getImporteHipoteca() * factorInteresesHipoteca;
}
public float getPrecioVenta() {
return precioCompra + (precioEdificar * factorRevalorizacion * (numCasas + numHoteles));
}
public boolean propietarioEncarcelado() {
return propietario != null && propietario.getEncarcelado();
}
public boolean tienePropietario() {
return propietario != null;
}
public boolean esEsteElPropietario(Jugador jugador) {
    return propietario == jugador;
}
public boolean actualizarPropietarioPorConversion(Jugador jugador) {
if (propietario != null && propietario.getClass() != jugador.getClass()) {
propietario = jugador;
return true;
}
return false;
}
public boolean comprar(Jugador jugador) {
if (propietario == null) {
propietario = jugador;
propietario.modificarSaldo(-precioCompra);
return true;
}
return false;
}
public boolean construirCasa(Jugador jugador) {
if (propietario == jugador) {
propietario.modificarSaldo(-precioEdificar);
numCasas++;
return true;
}
return false;
}
public boolean construirHotel(Jugador jugador) {
if (propietario == jugador) {
propietario.modificarSaldo(-precioEdificar);
numHoteles++;
return true;
}
return false;
}
public boolean derruirCasas(int n, Jugador jugador) {
if (propietario == jugador && numCasas >= n) {
numCasas -= n;
return true;
}
return false;
}
public boolean hipotecar(Jugador jugador) {
if (!hipotecado && propietario == jugador) {
propietario.modificarSaldo(getImporteHipoteca());
hipotecado = true;
return true;
}
return false;
}
public boolean cancelarHipoteca(Jugador jugador) {
if (hipotecado && propietario == jugador) {
propietario.modificarSaldo(-getImporteCancelarHipoteca());
hipotecado = false;
return true;
}
return false;
}
public void tramitarAlquiler(Jugador jugador) {
if (propietario != null && propietario != jugador) {
propietario.modificarSaldo(getPrecioAlquiler());
jugador.modificarSaldo(-getPrecioAlquiler());
}
}
public boolean vender(Jugador jugador) {
if (propietario == jugador && !hipotecado) {
propietario.modificarSaldo(getPrecioVenta());
propietario = null;
numCasas = 0;
numHoteles = 0;
return true;
}
return false;
}
public int cantidadCasasHoteles() {
    return numCasas + numHoteles;
}
@Override
public String toString() {
return "TituloPropiedad{" + "nombre=" + nombre + ", alquilerBase=" + alquilerBase + ", factorRevalorizacion=" + factorRevalorizacion + ", factorInteresesHipoteca=" + factorInteresesHipoteca + ", hipotecaBase=" + hipotecaBase + ", hipotecado=" + hipotecado + ", propietario=" + propietario + ", numCasas=" + numCasas + ", numHoteles=" + numHoteles + ", precioCompra=" + precioCompra + ", precioEdificar=" + precioEdificar + '}';
}

}

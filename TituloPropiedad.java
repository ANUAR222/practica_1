import java.util.ArrayList;

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

import civitas.Diario;
import java.util.ArrayList;
import java.util.Collections;

public class MazoSorpresas {
    private boolean barajada;
    private int usadas;

    private static ArrayList<Sorpresa_porCasaHotel> sorpresas;
    private boolean debug;
    private static ArrayList<Sorpresas> cartasEspeciales;
    private Sorpresa_porCasaHotel ultimaSorpresa;
    private void init(){
        sorpresas = new ArrayList<Sorpresa_porCasaHotel>();
        cartasEspeciales = new ArrayList<Sorpresas>();
        usadas=0;
        barajada=false;
    }
    MazoSorpresas(boolean debugStatus){
        debug=debugStatus;
        init();
        if(debug==true){
            Diario.ocurreEvento("debug");

        }
    }
    MazoSorpresas(){
        init();
        debug=false;
    }
    void alMazo(Sorpresa_porCasaHotel s){
        if(barajada==false){
            sorpresas.add(s);
        }
    }
    Sorpresa_porCasaHotel siguiente(){
        if(barajada==false || usadas== sorpresas.size() ){
            if(debug==false){
                Collections.shuffle(sorpresas);
            }
            usadas = 0;
            barajada = true;
        }
        usadas++;
        ultimaSorpresa= sorpresas.get(0);
        sorpresas.add(ultimaSorpresa);
        sorpresas.remove(0);
        return ultimaSorpresa;
    }
    public static void inhabilitarCartaEspecial(Sorpresas sorpresa){
        boolean existe = contains(sorpresa);
        if(existe){
            remove(sorpresa);
            cartasEspeciales.add(sorpresa);
            Diario.ocurreEvento("Carta especial inhabilitada");
        }
    }
    public static void habilitarCartaEspecial(Sorpresas sorpresa){
        boolean existe = cartasEspeciales.contains(sorpresa);
        if(existe==true){
            cartasEspeciales.remove(sorpresa);
            addSorpresa(sorpresa);
            Diario.ocurreEvento("Carta especial habilitada");
        }
    }
    public static void addSorpresa(Sorpresa_porCasaHotel sorpresa){
        sorpresas.add(sorpresa);
    }
    public static boolean contains(Sorpresas sorpresa){
        return sorpresas.contains(sorpresa);
    }
    public static void remove(Sorpresas sorpresa){
        sorpresas.remove(sorpresa);
    }

    public void alMazo(Sorpresa_pagarCobrar pagar) {


    }
}

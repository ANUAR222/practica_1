import civitas.Diario;
import java.util.ArrayList;
import java.util.Collections;

public class MazoSorpresas {
    private boolean barajada;
    private int usadas;

    private ArrayList<Sorpresas> sorpresas;
    private boolean debug;
    private static ArrayList<Sorpresas> cartasEspeciales;
    private Sorpresas ultimaSorpresa;
    private void init(){
        sorpresas = new ArrayList<Sorpresas>();
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
    void alMazo(Sorpresas s){
        if(barajada==false){
            sorpresas.add(s);
        }
    }
    Sorpresas siguiente(){
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
        if(existe==true){
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
    public void addSorpresa(Sorpresas sorpresa){
        sorpresas.add(sorpresa);
    }
    public boolean contains(Sorpresas sorpresa){
        return sorpresas.contains(sorpresa);
    }
    public void remove(Sorpresas sorpresa){
        sorpresas.remove(sorpresa);
    }

}

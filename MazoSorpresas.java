import civitas.Diario;
import java.util.ArrayList;
import java.util.Collections;

public class MazoSorpresas {
    private boolean barajada;
    private int usadas;
    private ArrayList<SorpresaTemp> sorpresas;
    private boolean debug;
    private ArrayList<SorpresaTemp> cartasEspeciales;
    private SorpresaTemp ultimaSorpresa;
    private void init(){
        sorpresas = new ArrayList<SorpresaTemp>();
        cartasEspeciales = new ArrayList<SorpresaTemp>();
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
    void alMazo(SorpresaTemp s){
        if(barajada==false){
            sorpresas.add(s);
        }
    }
    SorpresaTemp siguiente(){
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
    void inhabilitarCartaEspecial(SorpresaTemp sorpresa){
        boolean existe = sorpresas.contains(sorpresa);
        if(existe==true){
            sorpresas.remove(sorpresa);
            cartasEspeciales.add(sorpresa);
            Diario.ocurreEvento("Carta especial inhabilitada");
        }
    }
    void habilitarCartaEspecial(SorpresaTemp sorpresa){
        boolean existe = cartasEspeciales.contains(sorpresa);
        if(existe==true){
            cartasEspeciales.remove(sorpresa);
            sorpresas.add(sorpresa);
            Diario.ocurreEvento("Carta especial habilitada");
        }
    }
}

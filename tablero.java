import java.util.ArrayList;

public class tablero {
    private int numCasillaCarcel;
        private int porSalida;
        protected ArrayList<Casilla> casillas;
        private Boolean tieneJuez;
        public tablero(int numCasillaCarcel){
            this.numCasillaCarcel = numCasillaCarcel;
            porSalida = 0;
            tieneJuez = false;
        }
        Casilla csa = new Casilla();

        private Boolean correcto(){
            return casillas.size() > numCasillaCarcel && tieneJuez;
        }
        private Boolean correcto(int numCasilla){
            return correcto() && numCasilla >= 0 && numCasilla < casillas.size();
        }
        int getCarcel(){
            return numCasillaCarcel;
        }
        int getPorSalida(){
            if (porSalida > 0){
                porSalida--;
            }
            return porSalida;
        }
        void añadeCasilla(Casilla casilla){
            if (numCasillaCarcel == casillas.size()){
                casilla.getCarcel();
            }
            casillas.add(casilla);
            if (casillas.size() == numCasillaCarcel){
                casilla.getCarcel();
            }
        }
        void añadeJuez(){

            if (!tieneJuez){
                csa.juez = 0;
                tieneJuez = true;
            }
        }
        Casilla getCasilla(int numCasilla){
            if (correcto(numCasilla)){
                return casillas.get(numCasilla);
            }
            return null;
        }
        int nuevaPosicion(int actual, int tirada){
            if (correcto()){
                int nuevaPosicion = actual + tirada;
                if (nuevaPosicion >= casillas.size()){
                    porSalida++;
                    nuevaPosicion = nuevaPosicion % 8;
                }
                return nuevaPosicion;
            }
            return -1;
        }
        int calcularTirada(int origen, int destino){
            if (origen < destino){
                return destino - origen;
            } else {
                return casillas.size() - (origen - destino);
            }
        }
}


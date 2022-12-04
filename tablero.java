import java.util.ArrayList;

public class tablero {
        private int numCasillaCarcel;
        private ArrayList<TipoCasilla> casillas;
        private int porSalida;
        private Boolean tieneJuez;
        public tablero(int numCasillaCarcel){
            this.numCasillaCarcel = numCasillaCarcel;
            casillas = new ArrayList<>();
            casillas.add(TipoCasilla.SALIDA);
            porSalida = 0;
            tieneJuez = false;
        }
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
        void añadeCasilla(TipoCasilla casilla){
            if (casillas.size() == numCasillaCarcel){
                casillas.add(TipoCasilla.CARCEL);
            }
            casillas.add(casilla);
            if (casillas.size() == numCasillaCarcel){
                casillas.add(TipoCasilla.CARCEL);
            }
        }
        void añadeJuez(){
            if (!tieneJuez){
                casillas.add(TipoCasilla.JUEZ);
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
                    nuevaPosicion = nuevaPosicion % casillas.size();
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


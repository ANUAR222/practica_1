
        public class Main {
            public static void main(String[] args) {
                int[] contador = new int[5];
                Dado Dado = new Dado();
                for (int i = 0; i < 100; ++i) {
                    contador[Dado.quienEmpieza(4)]++;
                }
                for (int i = 0; i < 5; ++i) {
                    System.out.println("El valor " + i + " ha salido " + contador[i] + " veces.");
                }
                //2
                System.out.println("Modo debug activado");
                Dado.setDebug(true);
                for (int i = 0; i < 5; ++i) {
                    System.out.println(Dado.tirar());
                }
                System.out.println("Modo debug desactivado");
                Dado.setDebug(false);
                for (int i = 0; i < 5; ++i) {
                    System.out.println(Dado.tirar());
                }
                //3
                System.out.println("El último resultado ha sido " + Dado.getUltimoResultado());
                System.out.println("¿Salgo de la cárcel? " + Dado.salgoDeLaCarcel());
                //4
                System.out.println("El valor de la casilla de Salida es " + Casilla.SALIDA);
                System.out.println("El valor de la casilla de Cárcel es " + Casilla.CARCEL);
                System.out.println("El valor de la casilla de Impuesto es " + Casilla.IMPUESTO);
                System.out.println("El valor de la casilla de Juez es " + Casilla.JUEZ);
                System.out.println("El valor de la casilla de Sorpresa es " + Casilla.SORPRESA);
                System.out.println("El valor de la casilla de Descanso es " + Casilla.DESCANSO);
                System.out.println("El valor de la casilla de Calle es " + Casilla.CALLE);
                System.out.println("El valor de la casilla de Aparcmiento es " + Casilla.APARCAMIENTO);
                //printea los enmumerados sorpresa
                System.out.println("El valor de la sorpresa de ir a la cárcel es " + Sorpresa.IRCARCEL);
                System.out.println("El valor de la sorpresa de ir a la casilla es " + Sorpresa.IRCASILLA);
                System.out.println("El valor de la sorpresa de ir a la PAGARCOBRAR es " + Sorpresa.PAGARCOBRAR);
                System.out.println("El valor de la sorpresa de ir a la POR CASA HOTEL es " + Sorpresa.PORCASAHOTEL);
                System.out.println("El valor de la sorpresa de ir a la POR JUGADOR es " + Sorpresa.PORJUGADOR);
                System.out.println("El valor de la sorpresa de ir a la SALIR CARCEL es " + Sorpresa.SALIRCARCEL);


                /*6. usa la clase Diario, aprovecha y prueba todos los métodos de Diario*/
                civitas.Diario diario = civitas.Diario.getInstance();
                diario.getInstance().ocurreEvento("Evento 1");
                diario.getInstance().ocurreEvento("Evento 2");
                diario.getInstance().ocurreEvento("Evento 3");



            }
        }
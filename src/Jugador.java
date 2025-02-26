import java.util.Random;
//import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Jugador {

    private int DISTANCIA = 40;
    private int MARGEN = 10;
    private int TOTAL_CARTAS = 10;
    private String MENSAJE_PREDETERMINADO = "No se encontraron grupos";
    private String ENCABEZADO_MENSAJE = "Se encontraron los siguientes grupos:\n";
    private String MENSAJE_ESCALERAS = "El jugador tiene las siguientes escaleras:\n";
    private int MINIMA_CANTIDAD_DE_GRUPOS=2;
    private Carta[] cartas = new Carta[TOTAL_CARTAS];//Vector de objetos, hasta este momento el vector no contiene valores
    private Random r = new Random(); //Se dede instancia el objeto random ya que en la otra clase se definicio que el metodo constructor de carta recibe un numero random
    
    public void repartir() {//Es diferente al otro método repartir.
        for(int i=0; i<TOTAL_CARTAS; i++) {
            cartas[i]=new Carta(r);
        }
    }

    public void mostrar(JPanel pnl) {
        pnl.removeAll();//El panel debe ser limpiado cada vez que se quiera volver a repartir
        int x = MARGEN + (TOTAL_CARTAS - 1)*DISTANCIA;
        for(Carta carta : cartas) {//Ciclo para recorrer todas las cartas. se debe instanciar de nuevo
            carta.mostrar(pnl, x, MARGEN);
            x -= DISTANCIA;
        }
        pnl.repaint();//Refrescar la pantalla para mostrar las cartas        
    }

    //Como programar un DRAP AND DROP (poder coger las cartas y moverlas) BUSCAR

    //Mostrar grupos de dos cartas en adelante que se tengan en las diez cartas
    public String getGrupos() {
        String mensaje=MENSAJE_PREDETERMINADO;

        //Hay tantos contadores como nombres de cartas haya
        int[] contadores=new int[NombreCarta.values().length];

        //Llenado de los contadores
        for(Carta carta : cartas) {//Ciclo para recorrer todas las cartas. se debe instanciar de nuevo
            contadores[carta.getNombre().ordinal()]++;//getNombre me permite saber el nombre d ela carta que estoy analizando y con ordinal puedo saber el número ya que la carta hace parte de un metodo enumerado
        }

        //Verificar su hubo grupos
        boolean hayGrupos = false;
        for(int contador : contadores) {
            if(contador >= MINIMA_CANTIDAD_DE_GRUPOS){
                hayGrupos = true;
                break;
            }
        }

        //El indice del contador es el que dará el indice de la carta
        if(hayGrupos){
            mensaje = ENCABEZADO_MENSAJE;
            int posicion = 0;
            for(int contador : contadores) {
                if(contador >= MINIMA_CANTIDAD_DE_GRUPOS){
                    mensaje += Grupo.values()[contador] + " de " + NombreCarta.values()[posicion] + "\n";
                }
                posicion++;
            }
        }

        return mensaje;
    }

    int puntajeARestar = 0;
    public String getEscaleras() {

        String mensaje = MENSAJE_ESCALERAS;

        for (Pinta pinta : Pinta.values()) {

            boolean[] cartasEnEscalera = new boolean[NombreCarta.values().length];
            boolean escaleraEncontrada = false;

            for (NombreCarta nombre : NombreCarta.values()) {

                int contador = 0;

                for (int i = 0; i < 10; i++) {
                    if (cartas[i].getPinta() == pinta && cartas[i].getNombre() == nombre) {
                        contador++;
                    }
                }

                if (contador >= 1) {

                    cartasEnEscalera[nombre.ordinal()] = true;             
                }

                if (nombre == NombreCarta.AS || nombre == NombreCarta.JACK || nombre == NombreCarta.QUEEN || nombre == NombreCarta.KING) {
                    puntajeARestar += puntajeARestar + 10;
                } else {
                    puntajeARestar += puntajeARestar + nombre.ordinal() + 1;
                }   
            }

            for (int i = 1; i <= cartasEnEscalera.length - 4; i++) {
                if (cartasEnEscalera[i] && cartasEnEscalera[i + 1] && cartasEnEscalera[i + 2] && cartasEnEscalera[i + 3]) {
                    escaleraEncontrada = true;
                    mensaje += "Pinta " + pinta.name() + ": " + 
                                NombreCarta.values()[i] + ", " + 
                                NombreCarta.values()[i + 1] + ", " + 
                                NombreCarta.values()[i + 2] + ", " + 
                                NombreCarta.values()[i + 3] + "\n";
            
                    i += 3; // Saltamos las cartas ya contadas en la escalera
                }
            }
            

            if (!escaleraEncontrada) {
                mensaje += "No hay escaleras en la pinta " + pinta.name() + "\n";
            }
        }

        return mensaje;
    }


    public int calcularPuntaje() {
        int puntaje = 0;
        int cantidadVerificaciones = 0;
        for (int i = 0; i < TOTAL_CARTAS; i++) {
            NombreCarta nombre = cartas[i].getNombre();
            
            if (nombre == NombreCarta.AS || nombre == NombreCarta.JACK || nombre == NombreCarta.QUEEN || nombre == NombreCarta.KING) {
                puntaje += 10;
            } else {
                puntaje += nombre.ordinal() + 1;
            }
        }

        if (cantidadVerificaciones == 1) {
            puntaje = puntaje + puntajeARestar;
        }

        return puntaje;
    }
    
}

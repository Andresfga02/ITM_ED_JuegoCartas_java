import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.WindowConstants;

public class FrmJuego extends JFrame { //Se le agrega el extends ya que se quiere que heredes las funciones de JFrame

    JPanel pnlJugador1, pnlJugador2;
    JTabbedPane tpJugadores;

    //1er tarea: programar el método constructor (no tiene ningun tipo de dato y se llama igual que la clase), este metodo permite dibujar la interfaz (tamaño,)

    public FrmJuego() {
        setSize(700,250);
        setTitle("Juguemos al apuntado");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//Para que se cierre la aplicacion por completo cerrando la ventana
        setLayout(null);// va de la mano de los Bounds

        //Definicion de botones
        JButton btnRepartir = new JButton("Repartir");
        btnRepartir.setBounds(10,10,100,25);
        getContentPane().add(btnRepartir);

        JButton btnVerificar = new JButton("Verificar");
        btnVerificar.setBounds(120,10,100,25);
        getContentPane().add(btnVerificar);

        JButton btnCalcularPuntaje = new JButton("Cálcular Puntaje");
        btnCalcularPuntaje.setBounds(230,10,130,25);
        getContentPane().add(btnCalcularPuntaje);

        //Definicion de pestañas
        tpJugadores = new JTabbedPane();
        tpJugadores.setBounds(10,40,650,150);
        getContentPane().add(tpJugadores);
        //Lo anterior es para crear el recuadro donde se colocaran las pestañas

        pnlJugador1 = new JPanel();
        pnlJugador1.setBackground(new Color(16,139,37)); //Las coordenadas del color son a elección
        pnlJugador1.setLayout(null);//Como en el panel se distribuiran las cartas con coordenadas tambien debe anularse el layotu

        pnlJugador2 = new JPanel();
        pnlJugador2.setBackground(new Color(0,255,255));
        pnlJugador2.setLayout(null);

        //Agregacion de las pestañas al TabbedPane
        tpJugadores.addTab("Martin Estrada Contreras", pnlJugador1);
        tpJugadores.addTab("Raúl Vidal", pnlJugador2);

        //Programacion de eventos, para que al hacer clic en algun boton se hace una accion mediante escuchadores de eventos mediante la funcion Action Listener que identifica cuando se hace clic en el boton por medio del metodo Action Performed, se hace para boton

        btnRepartir.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                //Siempre se deben crear subrutinas con la accion que se desea hacer, en este caso repartir cartas
                repartirCartas();
            }
        });

        btnVerificar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                verificarJugador();
            }
        });

        btnCalcularPuntaje.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                calcularPuntaje();
            }
        });
    }

    Jugador jugador1 = new Jugador();
    Jugador jugador2 = new Jugador();

    //Defincion de clases para las subrutinas
    private void repartirCartas() {
        jugador1.repartir();
        jugador1.mostrar(pnlJugador1);
        jugador2.repartir();
        jugador2.mostrar(pnlJugador2);

    }

    private void verificarJugador() {
        int pestañaSeleccionada = tpJugadores.getSelectedIndex();//getSelectIndex me permite saber el nomnbre de la pestañna que esté seleccionada
        switch (pestañaSeleccionada) {
            case 0:
                JOptionPane.showConfirmDialog(null, jugador1.getGrupos()+"\n"+ jugador1.getEscaleras());
                break;
            case 1:
                JOptionPane.showConfirmDialog(null, jugador2.getGrupos()+"\n"+ jugador2.getEscaleras());
                break;
            default:
                break;
        }

    }

    //----------------------------------------------------------------
    
    private void calcularPuntaje() {
        int pestana = tpJugadores.getSelectedIndex();

        int puntaje = 0;
        switch(pestana){
            case 0:
                puntaje = jugador1.calcularPuntaje();
                break;
            case 1:
                puntaje = jugador2.calcularPuntaje();
                break;   
        }
        JOptionPane.showMessageDialog(null, "Puntaje del jugador: " + puntaje);
    }
    
}

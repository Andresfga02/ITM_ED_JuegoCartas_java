import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Carta {
    private int indice;//El enunciado lo pide private con el candado

    public Carta(Random r) { //Este método constructor es el que incializa el indice de la carta de manera aleatoria por esta razon se usa el random
        indice = r.nextInt(52) + 1;//genera numeros del cero al 51 y se le suma el 1 para que sea de 1 a 52
    }

    //Método para saber la pinta de la carta
    public Pinta getPinta(){
        if(indice<=13){
            return Pinta.TREBOL;
        } else if (indice <= 26){
            return Pinta.PICA;
        } else if (indice <= 39){
            return Pinta.CORAZON;
        } else {
            return Pinta.DIAMANTE;
        }
    }

    public NombreCarta getNombre(){
        int posicion = indice % 13;
        if (posicion == 0){
            posicion = 13;
        }
        return NombreCarta.values()[posicion - 1];
    }

    public void mostrar(JPanel pnl, int x, int y){ //Método mostrar cartas, se debe crear el panel para mostrar la carta y las coordenadas de esta, para mostrar las imagenes necesito el JLabel
        String nombreArchivo="/Imagenes/CARTA" + String.valueOf(indice) + ".jpg";//El numero se convierte a texto
        ImageIcon imagen = new ImageIcon(getClass().getResource(nombreArchivo));//Se carga la imagen, con getClass().getResource(nombreArchivo) permite cargar la imagen como un recurso del sistema.

        JLabel lbl = new JLabel(imagen);//Instancia lbl para mostrar la carta
        lbl.setBounds(x, y, imagen.getIconWidth(), imagen.getIconHeight());//Este metodo permite que al ser llamado desde otra clase esta pregunte por el ancho y alto
        pnl.add(lbl);
    }

    //ENSAYO CUARTO PUNTO

}

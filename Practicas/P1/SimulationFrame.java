/*
* @author Guillermo Girón García
* Clase que implementa la ventana base y de opciones
* del software de simulación de las prácticas de la asignatura
*/

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.ImageIcon;

public class SimulationFrame extends JFrame
{
    public SimulationFrame(BaseFrame bs)
    {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize((int) screenSize.getWidth() / 3, 
        (int) screenSize.getHeight() / 2);
        iniScreen(bs);
        iniComponents(); 
    }

    // Inicializa los parámetros de la ventana
    // La coloca a la derecha de la ventana de parametros
     private void iniScreen(BaseFrame bs)
     {
         setMinimumSize(new Dimension(300, 300));
         setVisible(true);
         setDefaultCloseOperation(EXIT_ON_CLOSE);
         setTitle("Simulation screen");
         setLocation(bs.getX()+bs.getWidth(), bs.getY());
     }

    // Añade componentes a la ventana principal
    private void iniComponents()
    {
        JPanel mainPanel = new JPanel();
        getContentPane().add(mainPanel);

        setLayout(null);
    
        //JLabel label1 = new JLabel("RubberDuck.png");
        //mainPanel.add(label1);
    }
}
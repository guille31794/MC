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

public class BaseFrame extends JFrame
{
    Dimension screenSize;
    
    // Establece la ventana a la mitad de la resolución de la pantalla
    // Y la coloca en el centro
    // Le aplica
    public BaseFrame()
    {   
        screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize((int) screenSize.getWidth() / 6, 
        (int) screenSize.getHeight() / 2);
        iniScreen();
        iniComponents();
    }

    public BaseFrame(int height, int width)
    {
        if(width < 300 || height < 300)
            setSize(300, 300);
        else
            setSize(width, height);

        iniScreen();
        iniComponents();
    }

    // Inicializa los parámetros de la ventana
    private void iniScreen()
    {
        setMinimumSize(new Dimension(300, 300));
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Simulation parameters");
        setLocationRelativeTo(null);
    }
    
    // Añade componentes a la ventana principal
    private void iniComponents()
    {
        JPanel mainPanel = new JPanel();
        getContentPane().add(mainPanel);

        //setLayout(null);
    
        iniLabels(mainPanel);
        iniButtons(mainPanel);
    }

    // Añade etiquetas a la ventana
    private void iniLabels(JPanel mainPanel)
    {
        JLabel label1 = new JLabel("Testing");
        mainPanel.add(label1);
    }

    // Añade botones a la ventana
    private void iniButtons(JPanel mainPanel)
    {

    }
}
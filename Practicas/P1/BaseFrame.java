/*
* @author Guillermo Girón García
* Clase que implementa la ventana base del software
* de simulación de las prácticas de la asignatura
*/

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class BaseFrame extends JFrame
{
    // Establece la ventana a la mitad de la resolución de la pantalla
    // Y la coloca en el centro
    // Le aplica
    public BaseFrame()
    {   
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize((int) screenSize.getWidth() / 2, 
        (int) screenSize.getHeight() / 2);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Practices MC Simulation");
        setLocationRelativeTo(null);
        setMinimumSize(new Dimension(300, 300));
    }

    public BaseFrame(int height, int width)
    {
        setMinimumSize(new Dimension(300, 300));
        
        if(width < 300 || height < 300)
            setSize(300, 300);
        else
            setSize(width, height);

        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Practices MC Simulation");
        setLocationRelativeTo(null);
    }
    
    //Añade componentes a la ventana principal
    private void iniComponents()
    {
        JPanel mainPanel = new JPanel();
        getContentPane().add(mainPanel);
    }
}
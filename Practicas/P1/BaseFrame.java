/*
* @author Guillermo Girón García
* Clase que implementa la ventana base y de opciones
* del software de simulación de las prácticas de la asignatura
*/

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.SwingConstants;
import javax.swing.JMenuItem;

public class BaseFrame extends JFrame
{
    Dimension screenSize;
    JMenuItem menuItems[];
    JMenu menu[];
    JMenuBar menuBar;
    JPanel mainPanel;
    JRadioButton[] buttons;
    JRadioButtonMenuItem menuJbuttons;

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

        iniComponents();
        iniScreen();
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
        mainPanel = new JPanel();
        getContentPane().add(mainPanel);

        //setLayout(null);
    
        //iniLabels();
        //iniButtons();
        iniMenu();
        //iniJButtons();
    }

    // Añade etiquetas a la ventana
    private void iniLabels()
    {
        JLabel label1 = new JLabel("Testing");
        mainPanel.add(label1);
    }

    // Añade botones a la ventana
    private void iniButtons()
    {
        
    }

    // Añade Jbuttons
    private void iniJButtons()
    {

    }

    // Añade Menu
    private void iniMenu()
    {
        menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        menu = new JMenu[2];
        menu[0] = new JMenu("File");
        menu[1] = new JMenu("Options");
        
        menuItems = new JMenuItem[2];
        menuItems[0] = new JMenuItem("Exit");
        menuItems[1] = new JMenuItem("Help");
        
        ActionListener exit = new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                System.exit(0);
            }
        };

        ActionListener help = new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                JFrame helpWindow = new JFrame("Help");
                helpWindow.setSize(300, 300);
                helpWindow.setVisible(true);
                helpWindow.setLocationRelativeTo(null);
                helpWindow.setDefaultCloseOperation(HIDE_ON_CLOSE);
                helpWindow.setLayout(new BorderLayout());
                JPanel helpPanel = new JPanel(new BorderLayout());
                helpWindow.getContentPane().add(helpPanel);
                JLabel devLabel = new JLabel("Developed by Guillermo Girón García", SwingConstants.CENTER);
                JLabel versionLabel = new JLabel("version 1.0", SwingConstants.CENTER);
                JLabel siteLabel = new JLabel("http://github.com/guille31794/MC", SwingConstants.CENTER);
                helpPanel.add(devLabel, BorderLayout.NORTH);
                helpPanel.add(versionLabel, BorderLayout.SOUTH);
                helpPanel.add(siteLabel, BorderLayout.CENTER);
            }
        };

        menuItems[0].addActionListener(exit);
        menuItems[1].addActionListener(help);

        menu[0].add(menuItems[0]);
        menu[1].add(menuItems[1]);

        menuBar.add(menu[0]);
        menuBar.add(menu[1]);

    }
}
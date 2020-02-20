/*
* @author Guillermo Girón García
* Clase que implementa la ventana base y de opciones
* del software de simulación de las prácticas de la asignatura
*/

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.*;

public class BaseFrame extends JFrame
{
    private Dimension screenSize;
    private JMenuItem menuItems[];
    private JMenu menu[];
    private JMenuBar menuBar;
    private JPanel mainPanel[];
    private ButtonGroup buttonGroup;
    private JTextField text;
    private JButton exec;
    private JLabel parameter, nMode, sfLabel;
    private JComboBox generatorMenu;
    private SimulationFrame sf;
    private randomGenerator rg;
    private int toGenerate, option;

    // Establece la ventana a la mitad de la resolución de la pantalla
    // Y la coloca en el centro
    // Le aplica
    public BaseFrame()
    {   
        screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        
        setSize((int) screenSize.getWidth() / 6, 
        (int) screenSize.getHeight() / 2);
        sf = new SimulationFrame();
        
        iniComponents();
        iniScreen();
    }

    public BaseFrame(int height, int width)
    {
        if(width < 300 || height < 300)
            setSize(300, 300);
        else
            setSize(width, height);

        sf = new SimulationFrame();
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
        setLocation(sf.getX() + sf.getWidth(), sf.getY());
        setLayout(new GridLayout(3,1));
    }
    
    // Añade componentes a la ventana principal
    private void iniComponents()
    {   
        iniPanels();
        iniLabels();
        iniTexts();
        iniButtons();
        iniMenu();
        iniCombobox();
    }

    // Añade paneles a la ventana
    private void iniPanels()
    {
        mainPanel = new JPanel[4];

        mainPanel[0] = new JPanel();
        getContentPane().add(mainPanel[0]);
        mainPanel[1] = new JPanel();
        getContentPane().add(mainPanel[1]);
        mainPanel[2] = new JPanel();
        getContentPane().add(mainPanel[2]);

        mainPanel[3] = new JPanel();
        sf.getContentPane().add(mainPanel[3]);
    }

    // Añade etiquetas a la ventana
    private void iniLabels()
    {
        parameter = new JLabel("Numbers to generate:", SwingConstants.RIGHT);
        mainPanel[0].add(parameter);
        nMode = new JLabel("Night Mode:", SwingConstants.CENTER);
        mainPanel[1].add(nMode);
    }

    // Añade textos a la ventana
    private void iniTexts()
    {
        text = new JTextField("  ");
        text.setSize(60, 20);
        mainPanel[0].add(text);
    }

    // Añade botones a la ventana
    private void iniButtons()
    {
        exec = new JButton("Apply");
        mainPanel[2].add(exec);

        ActionListener exec_ = new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                toGenerate = Integer.parseInt(text.getText());
                option = generatorMenu.getSelectedIndex();
                rg = new randomGenerator(toGenerate, option);
            }
        };

        exec.addActionListener(exec_);
    }

    // Añade menu para seleccionar los generadores de numeros
    private void iniCombobox()
    {
        String[] gMenu = {"lcg 26.1a", "lcg 26.1b",
        "lcg 26.2", "lcg 26.3", "Combined generator",
        "Fishman", "Moore", "RANDU"};
        generatorMenu = new JComboBox(gMenu);
        mainPanel[1].add(generatorMenu, SwingConstants.CENTER);
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
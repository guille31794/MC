/*
* @author Guillermo Girón García
* Clase que implementa la ventana base y de opciones
* del software de simulación de las prácticas de la asignatura
*/

import java.awt.Graphics;
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
    /**
     * Atributo relativo a la serialización de la clase
     * insertado automáticamente por el editor
     */
    private static final long serialVersionUID = 1L;
    private Dimension screenSize;
    private JMenuItem menuItems[];
    private JMenu menu[];
    private JMenuBar menuBar;
    private JPanel mainPanel[];
    private ButtonGroup buttonGroup;
    private JTextField toGenerat,
    seedText;
    private JButton exec;
    private JLabel parameter, nMode, sfLabel,
    seedLabel;
    private JComboBox<String> generatorMenu;
    private SimulationFrame sf;
    private randomGenerator rg;
    private int toGenerate, option,
    seed;
    private drawNumbers draw;

    // Establece la ventana a la mitad de la resolución de la pantalla
    // Y la coloca en el centro
    // Le aplica
    public BaseFrame()
    {   
        screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        
        setSize((int) screenSize.getWidth() / 6, 
        (int) screenSize.getHeight() / 2 + (int) screenSize.getHeight() / 3);
        sf = new SimulationFrame();
        
        iniComponents();
        iniScreen();
    }

    public BaseFrame(final int height, final int width)
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
        setLocation(sf.getX() + sf.getWidth(), sf.getY()+sf.getY()/3);
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
        mainPanel = new JPanel[3];

        mainPanel[0] = new JPanel();
        mainPanel[0].setLayout(null);
        getContentPane().add(mainPanel[0]);
        mainPanel[1] = new JPanel();
        sf.getContentPane().add(mainPanel[1]);

        mainPanel[2] = new JPanel();
        mainPanel[2].setLayout(null);
        getContentPane().add(mainPanel[2]);
    }

    // Añade etiquetas a la ventana
    private void iniLabels()
    {
        parameter = new JLabel("Numbers to generate:", SwingConstants.RIGHT);
        parameter.setBounds(50, 25, 175, 30);
        seedLabel = new JLabel("Seed:");
        seedLabel.setBounds(75, 75, 100, 30);
        mainPanel[0].add(parameter);
        mainPanel[0].add(seedLabel);
    }

    // Añade textos a la ventana
    private void iniTexts()
    {
        toGenerat = new JTextField();
        toGenerat.setBounds(100, 50, 100, 30);
        seedText = new JTextField("1");
        seedText.setBounds(100, 100, 100, 30);
        mainPanel[0].add(toGenerat);
        mainPanel[0].add(seedText);
    }

    // Añade botones a la ventana
    private void iniButtons()
    {
        exec = new JButton("Apply");
        exec.setBounds(100, 95, 100, 50);
        //mainPanel[2].add(exec, BorderLayout.SOUTH);
        mainPanel[2].add(exec);

        final ActionListener exec_ = new ActionListener()
        {
            @Override
            public void actionPerformed(final ActionEvent ae)
            {
                toGenerate = Integer.parseInt(toGenerat.getText());
                option = generatorMenu.getSelectedIndex();
                seed = Integer.parseInt(seedText.getText());
                try
                {
                    rg = new randomGenerator(toGenerate, option, seed);
                }catch(final Exception exception){}

                draw = new drawNumbers(rg.getGenerated(), screenSize);
                   
                sf.add(draw);
            }
        };

        exec.addActionListener(exec_);
    }

    // Añade menu para seleccionar los generadores de numeros
    private void iniCombobox()
    {
        final String[] gMenu = {"lcg 26.1a", "lcg 26.1b",
        "lcg 26.2", "lcg 26.3", "Combined generator",
        "Fishman", "Moore", "RANDU"};
        generatorMenu = new JComboBox<String>(gMenu);
        generatorMenu.setBounds(80, 20, 125, 50);
        mainPanel[2].add(generatorMenu);
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
        
        final ActionListener exit = new ActionListener()
        {
            @Override
            public void actionPerformed(final ActionEvent ae)
            {
                System.exit(0);
            }
        };

        final ActionListener help = new ActionListener()
        {
            @Override
            public void actionPerformed(final ActionEvent ae)
            {
                final JFrame helpWindow = new JFrame("Help");
                helpWindow.setSize(300, 300);
                helpWindow.setVisible(true);
                helpWindow.setLocationRelativeTo(null);
                helpWindow.setDefaultCloseOperation(HIDE_ON_CLOSE);
                helpWindow.setLayout(new BorderLayout());
                final JPanel helpPanel = new JPanel(new BorderLayout());
                helpWindow.getContentPane().add(helpPanel);
                final JLabel devLabel = new JLabel("Developed by Guillermo Girón García", SwingConstants.CENTER);
                final JLabel versionLabel = new JLabel("version 1.0", SwingConstants.CENTER);
                final JLabel siteLabel = new JLabel("http://github.com/guille31794/MC", SwingConstants.CENTER);
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
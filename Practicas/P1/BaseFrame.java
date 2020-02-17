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
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.*;

public class BaseFrame extends JFrame
{
    Dimension screenSize;
    JMenuItem menuItems[];
    JMenu menu[];
    JMenuBar menuBar;
    JPanel mainPanel[];
    JRadioButton[] buttons;
    ButtonGroup buttonGroup;
    JTextField text;
    JButton apply;
    JLabel parameter, nMode, sfLabel;
    JToggleButton nightMode;
    SimulationFrame sf;

    // Establece la ventana a la mitad de la resolución de la pantalla
    // Y la coloca en el centro
    // Le aplica
    public BaseFrame()
    {   
        screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize((int) screenSize.getWidth() / 6, 
        (int) screenSize.getHeight() / 2);
        sf = new SimulationFrame();
        iniScreen();
        iniComponents();
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
        iniJButtons();
        iniToogleButtons();
    }

    // Añade paneles a la ventana
    private void iniPanels()
    {
        mainPanel = new JPanel[4];

        mainPanel[0] = new JPanel();
        getContentPane().add(mainPanel[0]);
        mainPanel[1] = new JPanel();
        getContentPane().add(mainPanel[1]);
        mainPanel[2] = new JPanel(new GridLayout(3,2));
        getContentPane().add(mainPanel[2]);

        mainPanel[3] = new JPanel();
        sf.getContentPane().add(mainPanel[3]);

        // setLayout(null);
        /*ImageIcon rubberduck = new ImageIcon("RubberDuck.jpg");
        JLabel RubberDuck = new JLabel(new ImageIcon(rubberduck.getImage().getScaledInstance(
                (int) screenSize.getWidth() / 3, (int) screenSize.getHeight() / 2, Image.SCALE_SMOOTH)));
        mainPanel[3].add(RubberDuck);*/
    }

    // Añade etiquetas a la ventana
    private void iniLabels()
    {
        parameter = new JLabel("Parameter:", SwingConstants.RIGHT);
        mainPanel[0].add(parameter);
        nMode = new JLabel("Night Mode:", SwingConstants.CENTER);
        mainPanel[1].add(nMode);
        sfLabel = new JLabel("Hi!");
        mainPanel[3].add(sfLabel);
    }

    // Añade textos a la ventana
    private void iniTexts()
    {
        text = new JTextField("Write here...");
        mainPanel[0].add(text);
    }

    // Añade botones a la ventana
    private void iniButtons()
    {
        apply = new JButton("Apply");
        mainPanel[0].add(apply);

        ActionListener apply_ = new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                sfLabel.setText("Parameter " + text.getText() + " applied");
            }
        };

        apply.addActionListener(apply_);
    }

    // Añade Jbuttons
    private void iniJButtons()
    {
        buttons = new JRadioButton[2];
        buttons[0] = new JRadioButton("Hello!", true);
        buttons[1] = new JRadioButton("Bye!", false);

        mainPanel[2].add(buttons[0]);
        mainPanel[2].add(buttons[1]);

        buttonGroup = new ButtonGroup();
        buttonGroup.add(buttons[0]);
        buttonGroup.add(buttons[1]);

        ActionListener greet = new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                sfLabel.setText("Hi! How are you?");
            }
        };

        ActionListener salut = new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent ae) 
            {
                sfLabel.setText("Bye, bye friend!");
            }
        };

        buttons[0].addActionListener(greet);
        buttons[1].addActionListener(salut);
    }

    // Añade botones On/Off
    private void iniToogleButtons()
    {
        nightMode = new JToggleButton("On");
        mainPanel[1].add(nightMode);

        ActionListener on_off = new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                if(nightMode.getText() == "On")
                {
                    mainPanel[0].setBackground(Color.DARK_GRAY);
                    mainPanel[1].setBackground(Color.DARK_GRAY);
                    mainPanel[2].setBackground(Color.DARK_GRAY);
                    nightMode.setText("Off");
                }
                else
                {
                    mainPanel[0].setBackground(Color.WHITE);
                    mainPanel[1].setBackground(Color.WHITE);
                    mainPanel[2].setBackground(Color.WHITE);
                    nightMode.setText("On");
                }
            }
        };

        nightMode.addActionListener(on_off);
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
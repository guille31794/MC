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
    seedText, kText, rText, ruleText,
    generationText;
    private JButton exec, clean;
    private JLabel parameter, nMode, sfLabel,
    seedLabel, rule, boundCondition, k, r,
    generations;
    private JComboBox<String> generatorMenu, bCMenu;
    private SimulationFrame sf;
    private randomGenerator rg;
    private int toGenerate, option,
    seed, rule_, k_, r_, generations_;
    private boolean bCOption;
    private drawNumbers draw;
    private ca1DSimulator ca;

    // Establece la ventana a la mitad de la resolución de la pantalla
    // Y la coloca en el centro
    // Le aplica
    public BaseFrame()
    {   
        screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        
        setSize((int) screenSize.getWidth() / 6, 
        (int) screenSize.getHeight() / 3);
        sf = new SimulationFrame();
        
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
        setLocation(sf.getX() + sf.getWidth() + 10, sf.getY());
        setLayout(new GridLayout(2,1));
    }
    
    // Añade componentes a la ventana principal
    private void iniComponents()
    {   
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            @Override
            public void run() 
            {
                iniPanels();
                iniLabels();
                iniTexts();
                iniButtons();
                iniMenu();
                iniCombobox();
            }
        });        
    }

    // Añade paneles a la ventana
    private void iniPanels()
    {
        mainPanel = new JPanel[2];

        mainPanel[0] = new JPanel();
        mainPanel[0].setLayout(null);
        //mainPanel[0].setBounds(10, 10, 200, 200);
        getContentPane().add(mainPanel[0]);
        
        mainPanel[1] = new JPanel();
        sf.getContentPane().add(mainPanel[1]); 
    }

    // Añade etiquetas a la ventana
    private void iniLabels()
    {
        parameter = new JLabel("C.A. Size:", SwingConstants.RIGHT);
        parameter.setBounds(-10, 10, 100, 25);
        seedLabel = new JLabel("Seed:", SwingConstants.RIGHT);
        seedLabel.setBounds(40, 35, 50, 25);
        rule = new JLabel("Rule:", SwingConstants.RIGHT);
        rule.setBounds(40, 60, 50, 25);
        r = new JLabel("R:", SwingConstants.RIGHT);
        r.setBounds(205, 10, 25, 25);
        k = new JLabel("K:", SwingConstants.RIGHT);
        k.setBounds(205, 35, 25, 25);
        generations = new JLabel("Generations:", SwingConstants.RIGHT);
        generations.setBounds(135, 60, 100, 25);
        mainPanel[0].add(parameter);
        mainPanel[0].add(seedLabel);
        mainPanel[0].add(rule);
        mainPanel[0].add(r);
        mainPanel[0].add(k);
        mainPanel[0].add(generations);
    }

    // Añade textos a la ventana
    private void iniTexts()
    {
        toGenerat = new JTextField();
        toGenerat.setBounds(90, 10, 50, 25);
        seedText = new JTextField();
        seedText.setBounds(90, 35, 50, 25);
        rText = new JTextField();
        rText.setBounds(235, 10, 50, 25);
        kText = new JTextField();
        kText.setBounds(235, 35, 50, 25); 
        ruleText = new JTextField();
        ruleText.setBounds(90, 60, 50, 25);
        generationText = new JTextField();
        generationText.setBounds(235, 60, 50, 25);
        mainPanel[0].add(toGenerat);
        mainPanel[0].add(seedText);
        mainPanel[0].add(rText);
        mainPanel[0].add(kText);
        mainPanel[0].add(ruleText);
        mainPanel[0].add(generationText);
    }

    // Añade botones a la ventana
    private void iniButtons()
    {
        exec = new JButton("Apply");
        exec.setBounds(40, 125, 75, 25);
        mainPanel[0].add(exec);

        final ActionListener exec_ = new ActionListener()
        {
            @Override
            public void actionPerformed(final ActionEvent ae)
            {
                toGenerate = Integer.parseInt(toGenerat.getText());
                option = generatorMenu.getSelectedIndex();
                seed = Integer.parseInt(seedText.getText());
                k_ = Integer.parseInt(kText.getText());
                r_ = Integer.parseInt(rText.getText());
                rule_ = Integer.parseInt(ruleText.getText());
                generations_ = Integer.parseInt(generationText.getText());

                if(bCMenu.getSelectedIndex() == 0)
                    bCOption = true;
                else
                    bCOption = false;

                if(toGenerate > sf.getWidth())
                    toGenerate = sf.getWidth();

                try
                {
                    rg = new randomGenerator(toGenerate, option, seed);
                }catch(final Exception exception){}

                //draw = new drawNumbers(rg.getGenerated(), screenSize);
                ca = new ca1DSimulator(rg.getGenerated(), k_, r_, bCOption,
                rule_);
                
                //sf.add(draw);
                clean.setEnabled(true);
            }
        };

        exec.addActionListener(exec_);

        clean = new JButton("Clean");
        clean.setBounds(185, 125, 75, 25);

        final ActionListener clean_ = new ActionListener() 
        {
            @Override
            public void actionPerformed(final ActionEvent ae) 
            {
                toGenerat.setText("");
                seedText.setText("");
                kText.setText("");
                rText.setText("");
                ruleText.setText("");
                generationText.setText("");
                sf.remove(draw);
                clean.setEnabled(false);
            }
        };

        clean.addActionListener(clean_);
        clean.setEnabled(false);
        mainPanel[0].add(clean);
    }

    // Añade menu para seleccionar los generadores de numeros
    private void iniCombobox()
    {
        final String[] gMenu = {"lcg 26.1a", "lcg 26.1b",
        "lcg 26.2", "lcg 26.3", "Combined generator",
        "Fishman", "Moore", "RANDU"};
        generatorMenu = new JComboBox<String>(gMenu);
        generatorMenu.setBounds(185, 85, 100, 25);
        
        String[] boundConditionMenu = {"Cylindrical", "Null"};
        bCMenu = new JComboBox<String>(boundConditionMenu);
        bCMenu.setBounds(40, 85, 100, 25);

        mainPanel[0].add(generatorMenu);
        mainPanel[0].add(bCMenu);
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
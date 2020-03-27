/*
* @author Guillermo Girón García 
* Clase que implementa la ventana
* donde se ejecutará el cifrado
*/

import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.time.temporal.Temporal;
import java.util.Random;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.*;
import javax.swing.plaf.DimensionUIResource;

import java.util.Random;
import java.util.concurrent.*;

public class BaseFrame extends JFrame
{

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private Dimension d;
    private JPanel panel;


    BaseFrame()
    {
        d = Toolkit.getDefaultToolkit().getScreenSize();
        iniScreen();
    }

    void iniScreen()
    {
        setSize(d);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Text Cipher");
        setLayout(new GridLayout(1,2));
    }

}
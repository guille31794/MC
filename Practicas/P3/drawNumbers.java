/*
 * @author Guillermo Girón García 
 * Software que permite pintar pares de 
 * coordenadas en un gráfico
 */

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.awt.Canvas;
import java.awt.Graphics;

 public class drawNumbers extends Canvas
 {
     /**
     *
     */
     private static final long serialVersionUID = 1L;
     private ArrayList<Integer> array;
     private Dimension screenSize;

     public drawNumbers(ArrayList<Integer> arr, Dimension scre)
     {
        array = arr;
        screenSize = scre;
        //setBackground(Color.WHITE);
        setSize((int) screenSize.getWidth() / 3, 
        (int) screenSize.getHeight() / 2);
        setVisible(true);
     }

     public void paint(Graphics g)
     {
        super.paint(g);

        double x, y, resX = screenSize.getWidth(), resY = screenSize.getHeight();

        g.setColor(Color.BLACK);
        
        for(int i = 0; i < array.size() - 1; ++i)
        {
            x = array.get(i).doubleValue() * resX;
            ++i;
            y =  array.get(i).doubleValue() * resY;
            g.fillRect((int)x, (int)y, 5, 5);
        }
        
        array.clear();
     }
 }
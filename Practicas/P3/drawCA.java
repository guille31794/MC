/*
 * @author Guillermo Girón García 
 * Software que permite pintar pares de 
 * coordenadas en un gráfico
 */

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Canvas;
import java.awt.Graphics;

 public class drawCA extends Canvas
 {
     /**
     *
     */
     private static final long serialVersionUID = 1L;
     private int[][] ca;
     private Dimension screenSize;
     Color[] color;
     int x, y;

     public drawCA(int[][] arr, Dimension scre, Color[] c)
     {
        color = c;
        ca = arr;
        screenSize = scre;
        setSize((int) screenSize.getWidth() / 3, 
        (int) screenSize.getHeight() / 2);
        setVisible(true);
        x = 0;
        y = 0;
     }

     public void paint(Graphics g)
     {
        super.paint(g);
        
        for(int i = 0; i < ca.length - 1; ++i)
        {
            for (int j = 0; j < ca[i].length - 1; ++j) 
            {
                g.setColor(color[ca[i][j]]);
                g.drawRect(x, y, 5, 5);
                g.fillRect(x, y, 5, 5);
                x += 5;    
            }
            x = 0;
            y += 5;
        }
     }
 }
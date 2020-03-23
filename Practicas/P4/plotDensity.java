/*
 * @author Guillermo Girón García 
 * Clase que implementa la grafica
 * de densidad del autómata celular
 */

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Canvas;
import java.awt.Graphics;

 public class plotDensity extends Canvas
 {
     /**
     *
     */
     private static final long serialVersionUID = 1L;
     private final int[][] ca;
     private final Color[] color;
     private final int k;

     public plotDensity(int[][] matrix, Color[] c,
     Dimension screen, int k_)
     {
        ca = matrix;
        color = c;
        k = k_;
        setSize((int)screen.getWidth() / 3, 
        (int)screen.getHeight() / 4);
     }

     public void paint(Graphics g)
     {
        super.paint(g);
        double density = 0.0, prevDensity = density;
        int counter = 0;

        for (int z = 0; z < k; ++z)
        {
            g.setColor(color[z]);
            prevDensity = 0;
            counter = 0;

            for (int i = 0; i < ca.length; ++i)
            {
                for(int j = 0; j < ca[i].length; ++j)
                    if(ca[i][j] == z)
                        ++density;

                density /= ca[i].length;
                density *= 100;

                g.drawLine(counter, (int)prevDensity, ++counter, (int)density);
            
                prevDensity = density;
                density = 0;
            }
        }
     }
 }
/*
 * @author Guillermo Girón García 
 * Clase que implementa la curva de Hamming
 * del autómata celular
 */

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.BasicStroke;

public class hammingCurve extends Canvas
{
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private int[] distance;
    private Dimension size;

    public hammingCurve(int[] d, Dimension hs)
    {
        distance = d;
        size = hs;
    }

    public void paint(Graphics g)
    {
        super.paint(g);
        //Graphics2D g2D = (Graphics2D) g;

        setBackground(Color.WHITE);
        //g2D.setColor(Color.BLACK);

        int[] xPoints = new int[distance.length];
        int frame = (int)(size.width / distance.length);

        distance[0] = (int)(size.height / 2) + (int)(distance[0] * size.height / 100);
        for(int i = 1; i < xPoints.length; ++i)
        {
            xPoints[i] = xPoints[i-1] + frame + 2;
            distance[i] = (int)(size.height / 2) + (int)(distance[i] * size.height / 100);
        }
        
        //g2D.setStroke(new BasicStroke(2));
        //g2D.drawPolyline(xPoints, distance, distance.length);
        for(int i = 1; i < distance.length; ++i)
        {
            g.drawLine(xPoints[i-1], distance[i-1], xPoints[i], distance[i]);
        }
    }
}
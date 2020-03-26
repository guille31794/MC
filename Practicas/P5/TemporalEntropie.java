/*
 * @author Guillermo Girón García 
 * Clase que implementa 
 * la entropia temporal de una celula
 * del autómata celular
 */

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class TemporalEntropie extends Canvas
{
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private int cell, entropie;
    private static int[][] states;

    public static void setParameters(int[][] s)
    {
        states = s;
    }

    public TemporalEntropie(int c)
    {
        cell = c;
        entropie = 0;

        for(int i = 1; i < states.length; ++i)
            if(states[i][cell] != states[i-1][cell])
                ++entropie;
    }

    public int Cell()
    {
        return cell;
    }

    public int Entropie()
    {
        return entropie;
    }

    public void paint(Graphics g)
    {
        super.paint(g);
        Graphics2D g2D = (Graphics2D) g;
        g2D.setColor(Color.BLACK);
        g2D.drawString(Integer.toString(entropie), 45, 25);
    }
}
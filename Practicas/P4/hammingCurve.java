/*
 * @author Guillermo Girón García 
 * Clase que implementa la curva de Hamming
 * del autómata celular
 */

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Canvas;
import java.awt.Graphics;

public class hammingCurve extends Canvas
{
    private int[] distance;

    public hammingCurve(int[] d)
    {
        distance = d;
    }

    public void paint(Graphics g)
    {
        super.paint(g);
    }
}
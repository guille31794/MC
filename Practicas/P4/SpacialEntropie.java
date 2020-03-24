/*
 * @author Guillermo Girón García 
 * Clase que implementa la entropia 
 * espacial de un autómata celular
 */

 import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;

 public class SpacialEntropie extends Canvas implements Runnable 
 {
     /**
     *
     */
    private static final long serialVersionUID = 1L;
    private static int[][] ca;
    private static double[][] entropie;
    private static int k;
    private static Dimension window;
    private int start, end;

    public static void setParameters(int[][] ca_, int k_, Dimension d)
    {
        ca = ca_;
        k = k_;
        window = d;
        entropie = new double[ca.length][k];
    }

    public SpacialEntropie(int s, int e)
    {
        start = s;
        end = e;
    }

    @Override
    public void run() 
    {
        for(int i = 0; i < k; ++i)
        {
            for (int j = start; j < end; ++j)
            {
                for (int z = 0; z < ca[j].length; ++z)
                    if (ca[j][z] == i)
                        entropie[j][i]++;

                entropie[j][i] /= ca[j].length;
            }
        }
    }

    public static void Entropie()
    {
        for(int i = 0; i < entropie.length; ++i)
        {
            entropie[i][0] /= ca[i].length;

            for(int j = 1; j < k; ++j)
                entropie[i][0] += entropie[i][j] / ca[i].length;
        }
    }

    public void paint(Graphics g)
    {
        super.paint(g);

        int frame = (int)(window.width / entropie.length);
        int[] xPoints = new int[entropie.length];

        for(int i = 1; i < xPoints.length; ++i)
        {
            xPoints[i] = xPoints[i-1] + frame;
            entropie[i][0] *= 100;
            g.drawLine(xPoints[i-1], (int)entropie[i-1][0], xPoints[i], (int)entropie[i][0]);
        }
    }
 }
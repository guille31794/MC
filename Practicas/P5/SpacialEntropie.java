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
    private static double[][] entropie_state;
    private static double[] entropie_sum;
    private static int k;
    private static Dimension window;
    private int start, end;

    public static void setParameters(int[][] ca_, int k_, Dimension d)
    {
        ca = ca_;
        k = k_;
        window = d;
        entropie_state = new double[ca.length][k];
        entropie_sum = new double[ca.length];
    }

    public static void setParameters(int[][] ca_, int k_) 
    {
        ca = ca_;
        k = k_;
        entropie_state = new double[ca.length][k];
        entropie_sum = new double[ca.length];
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
            for (int j = start; j < end; ++j)
            {
                for (int z = 0; z < ca[j].length; ++z)
                    if (ca[j][z] == i)
                        entropie_state[j][i]++;
            }
    }

    public static void Entropie()
    {
        for(int i = 0; i < entropie_state.length; ++i)
            for(int j = 0; j < entropie_state[i].length; ++j)
                entropie_sum[i] += entropie_state[i][j];
    }

    public static void Entropie2()
    {
        for (int i = 0; i < k; ++i)
            for (int j = 0; j < ca.length; ++j) 
            {
                for (int z = 0; z < ca[j].length; ++z)
                    if (ca[j][z] == i)
                        entropie_state[j][i]++;

                entropie_state[j][i] /= ca[i].length;
                entropie_state[j][i] *= (Math.log(entropie_state[j][i]) / Math.log(2.0));
                entropie_state[j][i] *= (-1.0);
                entropie_sum[j] += entropie_state[j][i];
            }
    }

    public static double[] getEntropie()
    {
        return entropie_sum;
    }

    public static double meanEntropie()
    {
        double mean = 0.0;
        
        for(int i = 0; i < 4000; ++i)
            mean = mean + entropie_sum[i];

        return (mean / entropie_sum.length);
    }

    public void paint(Graphics g)
    {
        super.paint(g);

        int frame = (int)(window.width / entropie_sum.length);
        int[] xPoints = new int[entropie_sum.length];

        for(int i = 1; i < xPoints.length; ++i)
        {
            xPoints[i] = xPoints[i-1] + frame;
            entropie_sum[i] *= 100;
            g.drawLine(xPoints[i-1], (int)entropie_sum[i-1], xPoints[i], (int)entropie_sum[i]);
        }
    }
 }
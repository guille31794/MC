/*
 * @author Guillermo Girón García 
 * Clase que implementa la entropia 
 * espacial de un autómata celular
 */

 import java.awt.Canvas;

 public class SpacialEntropie extends Canvas implements Runnable 
 {
     /**
     *
     */
    private static final long serialVersionUID = 1L;
    private static int[][] entropie, ca;
    private static int k;
    private int start, end;

    public static void setParameters(int[][] ca_, int k_)
    {
        ca = ca_;
        k = k_;
        entropie = new int[ca.length][k];
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
            for(int j = start; j < end; ++j)
                for(int z = 0; z < ca[j].length; ++z)
                    if(ca[j][z] == i)
                        entropie[j][i]++;
    }

    public void paint(Graphics g)
    {
        super.paint(g);

        
    }
 }
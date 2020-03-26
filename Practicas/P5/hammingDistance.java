/*
 * @author Guillermo Girón García 
 * Clase que implementa la distancia de Hamming 
 * del autómata celular
 */

 public class hammingDistance implements Runnable
 {
    private static int[][] ca;
    private static int[] distance;
    private final int start, end;
    
    public static void setParameters(int[][]ca_)
    {
        ca = ca_;
        distance = new int[ca.length];
    }

    public static double meanDistance()
    {
        double mean = 0.0;
        
        for(int i = 0; i < distance.length; ++i)
            mean += distance[i];

        return mean /= distance.length;
    }

    public static int[] distance()
    {
        return distance;
    }

    public hammingDistance(int s, int e)
    {
        start = s;
        end = e;
    }

    @Override
    public void run() 
    {
        for(int i = start; i < end; ++i)
            for(int j = 0; i < ca[i].length; ++i)
                if(ca[i-1][j] != ca[i][j])
                    ++distance[i];
    }
 }


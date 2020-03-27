/*
* @author Guillermo Girón García
* Software que crea la ventana base del software
* de simulación de las prácticas de la asignatura
*/

import java.time.temporal.Temporal;
import java.util.concurrent.*;
import java.io.*;

public class Main
{
    public static void main(String[] args) throws Exception
    {
        int nThreads = Runtime.getRuntime().availableProcessors(),
        start, end, frame, maxDistanceRule = -1, maxSpaEntropieRule = -1,
        maxTempEntropieRule = -1;
        TemporalEntropie ce;
        randomGenerator r;
        FileWriter f = null;
        PrintWriter p;
        double maxDistance = 0.0;

        // Randu
        r = new randomGenerator(1000l, 7, 45);

        frame = 1000 / nThreads;

        for(int rule = 0; rule < 256; rule++)
        {
            start = 0;
            end = frame;
            ThreadPoolExecutor tpe = (ThreadPoolExecutor) Executors.newFixedThreadPool(nThreads);

            // Cellular automaton 
            // Bound Condition <- Null
            ca1DSimulator.setCA(r.getGenerated(), 2, 1, false, rule, 4000, nThreads);
            
            for(int i = 0; i < nThreads; ++i)
            {
                tpe.execute(new ca1DSimulator(start, end));
                start = end + 1;
                end += frame;
            }

            tpe.shutdown();
            try 
            {
                if(!tpe.awaitTermination(20, TimeUnit.SECONDS))
                    tpe.shutdownNow();
            } catch (InterruptedException e) {}
            
            // Hamming distance
            
            hammingDistance.setParameters(ca1DSimulator.status());
            
            hammingDistance.distance();
            double meanDistance = hammingDistance.meanDistance();

            // Spacial entropie
            SpacialEntropie.setParameters(ca1DSimulator.status(), 2);
            SpacialEntropie.Entropie2();
            double meanSpaEntropie = SpacialEntropie.meanEntropie();

            // Temporal entropie
            double meanTempEntropie = 0.0;
            TemporalEntropie.setParameters(ca1DSimulator.status());
            
            ce = new TemporalEntropie(499);
            meanTempEntropie += ce.Entropie();

            meanTempEntropie /= 4000.0;

            if(meanDistance > 500.0 && meanSpaEntropie > 0.85 
            && meanTempEntropie > 0.9)
                try 
                {
                    f = new FileWriter("analisis.txt", true);
                    p = new PrintWriter(f);

                    p.println("Rule " + rule + " has:");
                    p.println("Distance: " + meanDistance);
                    p.println("Spacial Entropie: " + meanSpaEntropie);
                    p.println("Temporal Entropie: " + meanTempEntropie);
                } catch (Exception e) 
                {
                    e.printStackTrace();
                } finally
                {
                    try 
                    {
                        if (null != f)
                            f.close();
                    } catch (Exception e2) 
                    {
                        e2.printStackTrace();
                    }
                }
        }
    }
}
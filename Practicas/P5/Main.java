/*
* @author Guillermo Girón García
* Software que crea la ventana base del software
* de simulación de las prácticas de la asignatura
*/

import java.time.temporal.Temporal;
import java.util.concurrent.*;

//import sun.jvm.hotspot.gc.shared.Space;

public class Main
{
    public static void main(String[] args) throws Exception
    {
        int nThreads = Runtime.getRuntime().availableProcessors(),
        start, end, frame, maxDistanceRule = -1, maxSpaEntropieRule = -1,
        maxTempEntropieRule = -1;
        double maxDistance = 0.0, maxSpaEntropie = 0.0,
        maxTempEntropie = 0.0;
        TemporalEntropie ce;
        randomGenerator r;

        // Randu
        r = new randomGenerator(1000l, 7, 45);

        frame = 1000 / nThreads;

        for(int rule = 0; rule < 256; ++rule)
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
                while (!tpe.awaitTermination(5, TimeUnit.SECONDS));
            } catch (InterruptedException e) {}
            
            // Hamming distance
            tpe = (ThreadPoolExecutor) Executors.newFixedThreadPool(nThreads);
            hammingDistance.setParameters(ca1DSimulator.status());
            start = 1;
            end = frame;

            for (int i = 0; i < nThreads; ++i) 
            {
                tpe.execute(new hammingDistance(start, end));
                start = end;
                end += frame;
            }

            tpe.shutdown();
            try 
            {
                while (!tpe.awaitTermination(5, TimeUnit.SECONDS));
            } catch (InterruptedException e) {}

            double meanDistance = hammingDistance.meanDistance();

            // Spacial entropie
            SpacialEntropie.setParameters(ca1DSimulator.status(), 2);
            tpe = (ThreadPoolExecutor) Executors.newFixedThreadPool(nThreads);
            start = 0;
            end = frame;

            for (int i = 0; i < nThreads; ++i) 
            {
                tpe.execute(new SpacialEntropie(start, end));
                start = end + 1;
                end += frame;
            }

            tpe.shutdown();
            try 
            {
                while (!tpe.awaitTermination(5, TimeUnit.SECONDS));
            } catch (InterruptedException e) {}

            double meanSpaEntropie = SpacialEntropie.meanEntropie();

            // Temporal entropie
            double meanTempEntropie = 0.0;
            TemporalEntropie.setParameters(ca1DSimulator.status());
            for(int i = 0; i < 1000; ++i)
            {
                ce = new TemporalEntropie(i);
                meanTempEntropie += ce.Entropie();
            }

            meanTempEntropie /= 1000.0;

            if(maxDistance < meanDistance)
            {
                maxDistance = meanDistance;
                maxDistanceRule = rule;
            }

            if(maxSpaEntropie < meanSpaEntropie)
            {
                maxSpaEntropie = meanSpaEntropie;
                maxSpaEntropieRule = rule;
            }
            
            if(maxTempEntropie < meanTempEntropie)
            {
                maxTempEntropie = meanTempEntropie;
                maxSpaEntropieRule = rule;
            }
        }

        System.out.println("Max. Hamming Distance: " + maxDistance + 
        " Rule: " + maxDistanceRule);
        System.out.println("Max. Spa. Entropie: " + maxSpaEntropie +
        " Rule: " + maxSpaEntropieRule);
        System.out.println("Max. Temp. Entropie: " + maxTempEntropie +
        " Rule: " + maxTempEntropieRule);
    }
}
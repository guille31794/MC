/*
* @author Guillermo Girón García
* Clase que implementa el automata 
* celular 1D
*/

import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class ca1DSimulator implements Runnable 
{
    private static int[] rulesTable;
    private static int k, r, gen, nThreads; 
    private static int[][] caMutated;
    // Condicion cilindrica -> True
    // Condicion nula -> False
    private static boolean boundCondition;
    private int prevGen,
    currentGen, start, end;
    private static CyclicBarrier barrier;
    private static Runnable reUse;

    private static void createBarrier() 
    {
        barrier = new CyclicBarrier(nThreads);
    }
    
    // Instanciación de los parámetros de clase
    public static void setCA(ArrayList<Double> ar,
    int k_, int r_, boolean bc, int adr, int g, int nt)
    {
        k = k_;
        r = r_;
        gen = g;
        Double[] caDouble = new Double[ar.size()];
        caDouble = ar.toArray(caDouble);
        caMutated = new int[gen+1][caDouble.length];
        rulesTable = new int[(int)Math.pow(k, (2 * r) + 1)];
        boundCondition = bc;
        nThreads = nt;
        reUse = () -> createBarrier();
        barrier = new CyclicBarrier(nThreads, reUse);

        int cont = 0;

        for(int i = rulesTable.length - 1; i >= 0; adr/=k, --i)
        {
            ++cont;
            rulesTable[i] = adr % k;
        }

        for(int i = rulesTable.length - 1; i >= 0; --cont, --i)
        {
            int aux = rulesTable[i];
            rulesTable[i] = rulesTable[rulesTable.length - cont];
            rulesTable[rulesTable.length - cont] = aux; 
        }
        
        for(int i = 0; i < caMutated[0].length; ++i)
            caMutated[0][i] = (int)Math.floor(caDouble[i] * k);
    }

    // Creación de hebras
    public ca1DSimulator(int s, int e)
    {
        prevGen = 0;
        currentGen = 1;
        start = s;
        end = e;
    }

    @Override
    public void run() 
    {
        caComputation();
    }

    public void nextGen()
    {
        String differentBase;
        int index, pow;

        for (int i = start; i < end; ++i) 
        {
            differentBase = new String();
            index = 0;

            for (int j = i - r; j <= (i + r); ++j)
            {
                if (boundCondition)
                    // Condicion de frontera cilindrica
                    differentBase += nextGenCilindrical(j);
                else
                    // Condicion de frontera nula
                    differentBase += nextGenNull(j);
            }

            pow = differentBase.length() - 1;

            for (int j = 0; j < differentBase.length(); ++j, --pow)
                index += Character.getNumericValue(differentBase.charAt(j)) * Math.pow(k, pow);

            caMutated[currentGen][i] = rulesTable[rulesTable.length - 1 - index];
        }
        prevGen = currentGen;
        ++currentGen;
    }

    private char nextGenCilindrical(int j)
    {
        if (j < 0)
             return (char)(caMutated[prevGen][caMutated[prevGen].length - 1 + j] + '0');
        else if (j >= caMutated[prevGen].length)
            return (char)(caMutated[prevGen][j % k] + '0');
        else
            return (char)(caMutated[prevGen][j] + '0');
    }

    private char nextGenNull(int j)
    {            
        if (j < 0)
            return '0';
        else if (j >= caMutated[prevGen].length)
            return '0';
        else
            return (char)(caMutated[prevGen][j] + '0');
    }

    public void caComputation()
    {
        while(currentGen < gen)
        {
            nextGen();
            try { barrier.await(); } catch (InterruptedException e) {}
            catch(BrokenBarrierException e) {}
        }
    }

    public static int[][] status()
    {
        return caMutated;
    }
}
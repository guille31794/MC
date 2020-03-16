/*
* @author Guillermo Girón García
* Clase que implementa el automata 
* celular 1D
*/

import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.*;

public class ca1DSimulator extends ca1DSim implements Runnable 
{
    private static int[] rulesTable;
    private static int k, r; 
    private static int[][] caMutated;
    // Condicion cilindrica -> True
    // Condicion nula -> False
    private boolean boundCondition;
    private int gen, prevGen,
    currentGen, start, end;
    
    // Instanciación de los parámetros de clase
    public ca1DSimulator(ArrayList<Double> ar,
    int k_, int r_, boolean bc, int adr, int g)
    {
        k = k_;
        r = r_;
        gen = g;
        prevGen = 0;
        currentGen = 1;
        Double[] caDouble = new Double[ar.size()];
        caDouble = ar.toArray(caDouble);
        caMutated = new int[gen+1][caDouble.length];
        rulesTable = new int[(int)Math.pow(k, (2 * r) + 1)];
        boundCondition = bc;

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



    @Override
    public void run() 
    {

    }

    @Override
    public void nextGen()
    {
        String differentBase;
        int index, pow;

        for (int i = 0; i < caMutated[prevGen].length; ++i) 
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

    @Override
    public void caComputation()
    {
        while(currentGen < gen)
            nextGen();
    }

    public int[][] status()
    {
        return caMutated;
    }
}
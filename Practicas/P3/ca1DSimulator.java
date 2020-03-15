/*
* @author Guillermo Girón García
* Clase que implementa el automata 
* celular 1D
*/

import java.util.ArrayList;

public class ca1DSimulator implements ca1DSim
{
    private int[] ca, rulesTable;
    private int k, r;
    // Condicion cilindrica -> True
    // Condicion nula -> False
    private boolean boundCondition;
    
    public ca1DSimulator(ArrayList<Integer> ar,
    int k_, int r_, boolean bc, int adr)
    {
        k = k_;
        r = r_;
        ca = ar.toArray();
        rulesTable = new int[(int)Math.pow(k, (2 * r) + 1)];
        boundCondition = bc;
        additiveRule = adr;

        for(int i = rulesTable.length - 1; adr >= 0; adr/=k, --i)
            rulesTable[i] = adr % k;
    }

    @Override
    public void nextGen()
    {
        String differentBase;
        int index, pow;

        for (int i = 0; i < ca.length; ++i) 
        {
            differentBase = new String();
            index = 0;

            for (int j = i - r; j < i + r; ++j)
            {
                if (boundCondition)
                    // Condicion de frontera cilindrica
                    differentBase += nextGenCilindrical(j);
                else
                    // Condicion de frontera nula
                    differentBase += nextGenNull(j);
            }

            pow = differentBase.length();

            for (int j = 0; j < differentBase.length(); ++j, --pow)
                index += Character.getNumericValue(differentBase.charAt(j)) * Math.pow(k, index);

            ca[i] = rulesTable[index];
        }
    }

    private char nextGenCilindrical(int j)
    {
        if (j < 0)
             return ca[ca.length - 1 - j];
        else if (j >= ca.length)
            return ca[j % k];
        else
            return ca[j];
    }

    private char nextGenNull(int j)
    {            
        if (j < 0)
            return "0";
        else if (j >= ca.length)
            return "0";
        else
            return ca[j];
    }

    @Override
    public void caComputation(int nGen)
    {
        for(int i = 0; i < nGen; ++i)
        {
            nextGen();
        }
    }

    public int[] status()
    {
        return ca;
    }
}
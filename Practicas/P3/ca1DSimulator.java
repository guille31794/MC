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
    private int[][] caMutated;
    
    public ca1DSimulator(ArrayList<Double> ar,
    int k_, int r_, boolean bc, int adr)
    {
        k = k_;
        r = r_;
        Double[] caDouble = new Double[ar.size()];
        caDouble = ar.toArray(caDouble);
        ca = new int[caDouble.length];
        rulesTable = new int[(int)Math.pow(k, (2 * r) + 1)];
        boundCondition = bc;

        for(int i = rulesTable.length - 1; i >= 0; adr/=k, --i)
            rulesTable[i] = adr % k;

        for(int i = 0; i < ca.length; ++i)
            ca[i] = (int)Math.floor(caDouble[i] * k);
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
             return (char)(ca[ca.length - 1 - j] + '0');
        else if (j >= ca.length)
            return (char)(ca[j % k] + '0');
        else
            return (char)(ca[j] + '0');
    }

    private char nextGenNull(int j)
    {            
        if (j < 0)
            return '0';
        else if (j >= ca.length)
            return '0';
        else
            return (char)(ca[j] + '0');
    }

    @Override
    public void caComputation(int nGen)
    {
        caMutated = new int[nGen][ca.length];
        caMutated[0] = ca;

        for(int i = 1; i < nGen; ++i)
        {
            nextGen();
            caMutated[i] = ca;
        }
    }

    public int[][] status()
    {
        return caMutated;
    }
}
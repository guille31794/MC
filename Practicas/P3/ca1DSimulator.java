import java.util.ArrayList;

/*
* @author Guillermo Girón García
* Clase que implementa el automata 
* celular 1D
*/

// Reglas
// k ^ k^ (2 * r + 1)
// Tamaño vector
// K ^ 2 * r + 1

public class ca1DSimulator implements ca1DSim
{
    private int[] ca, rulesTable;
    private int k, r ;

    public ca1DSimulator(ArrayList<Integer> ar,
    int k_, int r_)
    {
        k = k_;
        r = r_;
        ca = ar.toArray();
        rulesTable = new int[(int)Math.pow(k, (2 * r) + 1)];
    }

    @Override
    public void nextGen()
    {

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
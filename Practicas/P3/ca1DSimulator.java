import java.util.ArrayList;

/*
* @author Guillermo Girón García
* Clase que implementa el automata 
* celular 1D
*/

public class ca1DSimulator implements ca1DSim
{
    private int[] ca;

    public ca1DSimulator(ArrayList<Double> ar)
    {
        ca = ar.toArray();
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
}
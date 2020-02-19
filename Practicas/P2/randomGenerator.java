import java.math.BigInteger;
import java.math.BigDecimal;
import java.util.ArrayList;

/*
* @author Guillermo Girón García 
* Clase que implementa los distintos generadores
* de números pseudoaleatorios estudiados
*/

public class randomGenerator
{
    private ArrayList<Double> generated;
    private int n, // Integers to generate
    p; // Period 

    public randomGenerator(int n_, int o) throws Exception
    {
        generated = new ArrayList<Double>(n_);
        n = n_;
        
        switch(o)
        {
            case 0: lcg261a();
                break;
            case 1: lcg261b();
                break;
            case 2: lcg262();
                break;
            case 3: lcg263();
                break;
            case 4: combinedGenerator();
                break;
            case 5: Fishman();
                break;
            case 6: Moore();
                break;
            case 7: RANDU();
                break;
            default: 
                throw new Exception("No random generator selected");
        }
    }

    public void lcg261a()
    {
        int a, // Multiplier
        m, // Modulus
        x,
        prev_X, // Seed
        startPoint;
        final int k = 5; // Maximum supported 28

        m = 2;
        m = (int)Math.pow(m, k);
        a = 5; // 8 * i - 3 (i = 1) General form: (8 * i + o - 3)
        prev_X = 1; 
        p = 0;

        x = (a * prev_X) % m;
        startPoint = x;
        generated.add(Double(x / m));
        prev_X = x;

        do
        {
            x = (a * prev_X) % m;
            generated.add(Double(x / m));
            prev_X = x;
            ++p;
        }while(x != startPoint && p != n);
    }

    public void lcg261b()
    {
        int a, // Multiplier
        m, // Modulus
        x,
        prev_X, // Seed
        startPoint;
        final int k = 5; // Maximum supported 28

        m = 2;
        m = (int)Math.pow(m, k);
        a = 7; // 8 * i - 3 (i = 1) General form: (8 * i + o - 3)
        prev_X = 1; 
        p = 0;

        x = (a * prev_X) % m;
        startPoint = x;
        generated.add(Double(x / m));
        prev_X = x;

        do
        {
            x = (a * prev_X) % m;
            generated.add(Double(x / m));
            prev_X = x;
            ++p;
        }while(x != startPoint && p != n);
    }

    public void lcg262()
    {
        int a, // Multiplier
        m, // Modulus
        x,
        prev_X, // Seed
        startPoint;

        m = 31;
        a = 3;
        prev_X = 1; 
        p = 0;

        x = (a * prev_X) % m;
        startPoint = x;
        generated.add(Double(x / m));
        prev_X = x;

        do
        {
            x = (a * prev_X) % m;
            generated.add(Double(x / m));
            prev_X = x;
            ++p;
        }while(x != startPoint && p != n);
    }

    public void lcg263()
    {
        int     a, // Multiplier
                m, // Modulus
                x, 
                prev_X, // Seed
                startPoint;
        
        m = Integer.MAX_VALUE; 
        a = 16807;
        prev_X = 1; 
        p = 0;

        x = (a * prev_X) % m;
        startPoint = x;
        generated.add(Double(x / m));
        prev_X = x;

        do
        {
            x = (a * prev_X) % m;
            generated.add(Double(x / m));
            prev_X = x;
            ++p;
        }while(x != startPoint && p != n);
    }

    public void combinedGenerator()
    {

    }

    public void Fishman()
    {

    }

    public void Moore()
    {

    }

    public void RANDU()
    {

    }

    public long getPeriod()
    {
        return p.longValue();
    }

    public ArrayList<BigInteger> getGenerated()
    {
        return generated;
    }

    public static void main(String[] args) throws Exception
    {
        //randomGenerator r = new randomGenerator("100000000", 1);
        
        //System.out.println(r.getPeriod());
        System.out.println(d);
    }
}
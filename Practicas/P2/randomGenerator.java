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
    private long n, // Integers to generate
    p; // Generated counter 

    public randomGenerator(long n_, int o) throws Exception
    {
        generated = new ArrayList<Double>();
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
        prev_X; // Seed
        final int k = 5; // Maximum supported 28

        m = 2;
        m = (int)Math.pow(m, k);
        a = 5; // 8 * i - 3 (i = 1) General form: (8 * i + o - 3)
        prev_X = 1; 
        p = 0;

        do
        {
            x = (a * prev_X) % m;
            generated.add((double) (x / m));
            prev_X = x;
            ++p;
        }while(p != n);
    }

    public void lcg261b()
    {
        int a, // Multiplier
        m, // Modulus
        x,
        prev_X; // Seed
        final int k = 5; // Maximum supported 28

        m = 2;
        m = (int)Math.pow(m, k);
        a = 7; // 8 * i - 3 (i = 1) General form: (8 * i + o - 3)
        prev_X = 1; 
        p = 0;

        do
        {
            x = (a * prev_X) % m;
            generated.add((double) (x / m));
            prev_X = x;
            ++p;
        }while(p != n);
    }

    public void lcg262()
    {
        int a, // Multiplier
        m, // Modulus
        x,
        prev_X; // Seed

        m = 31;
        a = 3;
        prev_X = 1; 
        p = 0;

        do
        {
            x = (a * prev_X) % m;
            generated.add((double)(x / m));
            prev_X = x;
            ++p;
        }while(p != n);
    }

    public void lcg263()
    {
        int     a, // Multiplier
                m, // Modulus
                x, 
                prev_X; // Seed
        
        m = Integer.MAX_VALUE; 
        a = 16807;
        prev_X = 1; 
        p = 0;

        do
        {
            x = (a * prev_X) % m;
            generated.add((double) (x / m));
            prev_X = x;
            ++p;
        }while(p != n);
    }

    public void combinedGenerator()
    {
        long ax, ay, x, y, 
        prev_X, prev_Y, mx,
        my, m, w;

        prev_X = 1;
        prev_Y = 1;
        m = 2147483562;
        mx = 2147483563;
        my = 2147483399;
        ax = 40014;
        ay = 40692;
        
        do
        {
            x = (ax * prev_X) % mx;
            prev_X = x;
            y = (ay * prev_Y) % my;
            prev_Y = y;
            w = (Math.abs(x-y)) % m;
            generated.add((double)(w/m));
            ++p;
        } while(p < n);
        
    }

    public void Fishman()
    {
        long x, a, prev_X, m;

        a = 48271;
        m = Integer.MAX_VALUE;
        prev_X = 1;

        do
        {
            x = (a * prev_X) % m;
            prev_X = x;
            ++p;
            generated.add((double)(x/m));
        } while(p < n);
    }

    public void Moore()
    {
        long x, a, prev_X, m;

        a = 69621;
        m = Integer.MAX_VALUE;
        prev_X = 1;

        do 
        {
            x = (a * prev_X) % m;
            prev_X = x;
            ++p;
            generated.add((double) (x / m));
        } while (p < n);
    }

    public void RANDU()
    {
        long x, a, prev_X, m;

        a = (long)Math.pow(2, 16) + 3L;
        m = Integer.MAX_VALUE;
        prev_X = 1;

        do 
        {
            x = (a * prev_X) % m;
            prev_X = x;
            ++p;
            generated.add((double) (x / m));
        } while (p < n);
    }

    public ArrayList<Double> getGenerated()
    {
        return generated;
    }
}
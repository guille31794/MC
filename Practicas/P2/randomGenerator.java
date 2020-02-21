/*
* @author Guillermo Girón García 
* Clase que implementa los distintos generadores
* de números pseudoaleatorios estudiados
*/

import java.util.ArrayList;

public class randomGenerator
{
    private ArrayList<Double> generated;
    private long n, // Integers to generate
    p, seed; // Generated counter 

    public randomGenerator(long n_, int o, int s) throws Exception
    {
        generated = new ArrayList<Double>();
        n = n_;
        seed = s;
        

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
        x;
        final int k = 5; // Maximum supported 28

        m = 2;
        m = (int)Math.pow(m, k);
        a = 5; // 8 * i - 3 (i = 1) General form: (8 * i + o - 3) 
        p = 0;

        do
        {
            x = (long)((a * seed)) % m;
            generated.add((double) (x / m));
            seed = x;
            ++p;
        }while(p != n);
    }

    public void lcg261b()
    {
        int a, // Multiplier
        m, // Modulus
        x;
        final int k = 5; // Maximum supported 28

        m = 2;
        m = (int)Math.pow(m, k);
        a = 7; // 8 * i - 3 (i = 1) General form: (8 * i + o - 3)
        p = 0;

        do
        {
            x = (long)((a * seed)) % m;
            generated.add((double) (x / m));
            seed = x;
            ++p;
        }while(p != n);
    }

    public void lcg262()
    {
        int a, // Multiplier
        m, // Modulus
        x;

        m = 31;
        a = 3;
        p = 0;

        do
        {
            x = (a * seed) % m;
            generated.add((double)(x / m));
            seed = x;
            ++p;
        }while(p != n);
    }

    public void lcg263()
    {
        int     a, // Multiplier
                m, // Modulus
                x;
        
        m = Integer.MAX_VALUE; 
        a = 16807;
        p = 0;

        do
        {
            x = (a * seed) % m;
            generated.add((double) (x / m));
            seed = x;
            ++p;
        }while(p != n);
    }

    public void combinedGenerator()
    {
        long ax, ay, x, y, 
        seed_x, seed_y, mx,
        my, m, w;

        seed_x = seed_y = seed;
        m = 2147483562;
        mx = 2147483563;
        my = 2147483399;
        ax = 40014;
        ay = 40692;
        
        do
        {
            x = (ax * seed_x) % mx;
            seed_x = x;
            y = (ay * seed_y) % my;
            seed_y = y;
            w = (Math.abs(x-y)) % m;
            generated.add((double)(w/m));
            ++p;
        } while(p < n);
        
    }

    public void Fishman()
    {
        long x, a, m;

        a = 48271;
        m = Integer.MAX_VALUE;

        do
        {
            x = (a * seed) % m;
            seed = x;
            ++p;
            generated.add((double)(x/m));
        } while(p < n);
    }

    public void Moore()
    {
        long x, a, m;

        a = 69621;
        m = Integer.MAX_VALUE;

        do 
        {
            x = (a * seed) % m;
            seed = x;
            ++p;
            generated.add((double) (x / m));
        } while (p < n);
    }

    public void RANDU()
    {
        long x, a, m;

        a = (long)Math.pow(2, 16) + 3L;
        m = Integer.MAX_VALUE;

        do 
        {
            x = (a * seed) % m;
            seed = x;
            ++p;
            generated.add((double) (x / m));
        } while (p < n);
    }

    public ArrayList<Double> getGenerated()
    {
        return generated;
    }
}
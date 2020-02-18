import java.math.BigInteger;
import java.util.ArrayList;

/*
* @author Guillermo Girón García 
* Clase que implementa los distintos generadores
* de números pseudoaleatorios estudiados
*/

public class randomGenerator
{
    private ArrayList<BigInteger> generated;
    private BigInteger n, // Integers to generate
    p; // Period

    public randomGenerator(String s, int o) throws Exception
    {
        generated = new ArrayList<BigInteger>();
        n = new BigInteger(s);
        
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
        BigInteger a, // Multiplier
        m, // Modulus
        x,
        prev_X; // Seed
        final int k = 25; // Maximum supported 28

        m = BigInteger.valueOf(2L);
        m = m.pow(k);
        a = BigInteger.valueOf(13L); // 8 * i + 3 (i = 1) General form: (8 * i + o - 3)
        prev_X = BigInteger.ONE; // 1
        p = BigInteger.ZERO;

        x = a.multiply(prev_X).mod(m);
        generated.add(x);
        prev_X = x;

        do
        {
            x = a.multiply(prev_X).mod(m);
            generated.add(x);
            prev_X = x;
            p = p.add(BigInteger.ONE);
        }while(!x.equals(generated.get(0)) && !p.equals(n));
    }

    public void lcg261b()
    {
        BigInteger a, // Multiplier
                m, // Modulus
                x, 
                prev_X; // Seed
        final int k = 25; // Maximum supported 28

        m = BigInteger.valueOf(2L);
        m = m.pow(k);
        a = BigInteger.valueOf(15L); // != 8 * i + 3 (i = 15) General form != (8 * i + o - 3)
        prev_X = BigInteger.ONE; // 1
        p = BigInteger.ZERO;

        x = a.multiply(prev_X).mod(m);
        generated.add(x);
        prev_X = x;

        do 
        {
            x = a.multiply(prev_X).mod(m);
            generated.add(x);
            prev_X = x;
            p = p.add(BigInteger.ONE);
        } while (!x.equals(generated.get(0)) && !p.equals(n));
    }

    public void lcg262()
    {
        BigInteger a, // Multiplier
                m, // Modulus
                x, 
                prev_X; // Seed

        m = BigInteger.valueOf(31L);
        a = BigInteger.valueOf(3L); // != 8 * i + 3 (i = 15) General form != (8 * i + o - 3)
        prev_X = BigInteger.ONE; // 1
        p = BigInteger.ZERO;

        x = a.multiply(prev_X).mod(m);
        generated.add(x);
        prev_X = x;

        do 
        {
            x = a.multiply(prev_X).mod(m);
            generated.add(x);
            prev_X = x;
            p = p.add(BigInteger.ONE);
        } while (!x.equals(generated.get(0)) && !p.equals(n));
    }

    public void lcg263()
    {

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
        randomGenerator r = new randomGenerator("100000000", 2);
        System.out.println(r.getPeriod());
    }
}
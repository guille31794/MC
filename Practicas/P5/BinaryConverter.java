import java.util.ArrayList;

/*
 * @author Guillermo Girón García 
 * Clase que implementa un conversor 
 * de cadenas en binario
 */

 public class BinaryConverter
 {
    private int[] toTransform, cells, transformed;

    public BinaryConverter(String toTransform_, int[] automaton)
    {
        transformed = new int[toTransform_.length()];
        toTransform = toTransform_.chars().toArray();
        cells = automaton;
    }

    public BinaryConverter(String automaton)
    {
        cells = null;
        toTransform = automaton.chars().toArray();
        ArrayList<Integer> al = new ArrayList<Integer>();
        
        for(int i = 0; i < toTransform.length; ++i)
            for(int j = 0; j < Integer.toBinaryString(toTransform[i]).length(); ++j)
                al.add(Integer.toBinaryString(toTransform[i]).charAt(j) - '0');

        Integer[] ar = new Integer[al.size()];
        ar = al.toArray(ar); 
        transformed = new int[ar.length];

        for(int i = 0; i < ar.length; ++i)
            transformed[i] = ar[i];
    }

    public void XOR()
    {
        for (int i = 0; i < toTransform.length; ++i) 
            transformed[i] = toTransform[i] ^ cells[i];
    }

    public String toString()
    {
        String s = "";
        for(int i : transformed)
            s += (char)i;
        
        return s;
    }

    public int[] automaton()
    {
        return transformed;
    }
 }
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

    public void Conversion()
    {
        for (int i = 0; i < toTransform.length; ++i) 
            transformed[i] = toTransform[i] ^ cells[i];
    }
    
    public String toString()
    {
        return transformed.toString();
    }
 }
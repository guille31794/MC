public class test
{
    public static void main(String[] args) {
        String s = new String("Hola, soy pato");
        int[] array = s.chars().toArray();
        
        for (int i : array) 
        {
            System.out.print(i + " ");    
        }

        System.out.println("");

        for (int i : array) 
        {
            String binaryDigit = Integer.toBinaryString(i);
            System.out.print(Integer.parseInt(binaryDigit) + " ");
        }

        System.out.println("");

        int[] v = new int[array.length];

        for(int i = 0; i < array.length; ++i)
        {
            System.out.print((array[i]^1) + " ");
            v[i] = array[i]^1;
        }

        System.out.println("");

        //String s2 = new String(v);
        
        System.out.println(v.toString());
    }
}
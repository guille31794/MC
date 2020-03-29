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

        //String s = "W49QS8M6ugTd5Scn3rQuqLGm4CXbz98N7YY44ZJeZcNTpA6psuS9r8WLe44vER37gf2HkqBd76a236V54yY9LEuq6rb78cC68pHJ6rKbn9y4r74qWf4TS5TaF8Uv7Kp32Ft7Z8Wcw6r28sG7iTv4Rd";
        //System.out.println(s.length());
    }
}
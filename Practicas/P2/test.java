import java.math.*;

public class test{
    public static void main(String[] args) {
        BigInteger a, b;
        a = BigInteger.valueOf(3L);
        b = BigInteger.valueOf(2L);
        BigDecimal d = new BigDecimal(new BigDecimal(a).divide(new BigDecimal(b)));
        System.out.println(d);
    }
}
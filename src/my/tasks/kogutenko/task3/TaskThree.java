package my.tasks.kogutenko.task3;

import java.math.BigInteger;

public class TaskThree {

    public static void main(String[] args) {
        BigInteger factorial = factorial(100);
        System.out.println(factorial);
        System.out.println(countDigitOf(factorial));
    }

    private static BigInteger factorial(long number) {
        BigInteger result = BigInteger.ONE;
        for (long i = 1; i <= number; i++)
            result = result.multiply(BigInteger.valueOf(i));
        return result;
    }

    public static int countDigitOf (BigInteger factorial) {
        int sum = 0;
        String fact_str = factorial.toString();
        char[] chars = fact_str.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            sum += ((((int)chars[i]) - 48) % 10);
        }
        return sum;
    }
}

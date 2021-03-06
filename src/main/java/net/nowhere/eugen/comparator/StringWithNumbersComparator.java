package net.nowhere.eugen.comparator;

import java.math.BigInteger;
import java.util.Comparator;

import static java.math.BigInteger.ZERO;

public class StringWithNumbersComparator implements Comparator<String> {

    private final StringBuilder number1 = new StringBuilder();
    private final StringBuilder number2 = new StringBuilder();

    @Override
    public int compare(String str1, String str2) {
        int i = 0;
        int j = 0;

        while (i < str1.length() && j < str2.length()) {
            // locate number in first string
            for (number1.setLength(0); isDigitAtIndex(str1, i); i++) {
                number1.append(str1.charAt(i));
            }

            // locate number in second string
            for (number2.setLength(0); isDigitAtIndex(str2, j); j++) {
                number2.append(str2.charAt(j));
            }

            if (number1.length() > 0 || number2.length() > 0) {
                BigInteger n1 = parseBigInteger(number1);
                BigInteger n2 = parseBigInteger(number2);
                if (!n1.equals(n2)) return n1.compareTo(n2);
            } else {
                if (str1.charAt(i) != str2.charAt(j)) return str1.charAt(i) - str2.charAt(j);
                i++;
                j++;
            }
        }

        return str1.length() - str2.length();
    }

    private boolean isDigitAtIndex(String str2, int j) {
        return j < str2.length() && Character.isDigit(str2.charAt(j));
    }

    private BigInteger parseBigInteger(StringBuilder numberString) {
        return numberString.length() > 0 ? new BigInteger(numberString.toString()) : ZERO;
    }
}

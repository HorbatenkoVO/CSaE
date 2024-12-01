package helpers;

import static helpers.FormatHelper.formatDot;

public class MathHelper {

    public static String minus(String one, String two) {
        one = formatDot(one);
        two = formatDot(two);
        Double amountOne = Double.parseDouble(one);
        Double amountTwo = Double.parseDouble(two);
        Double result = amountOne - amountTwo;
        return String.format("%.2f", result).replace(",", ".");
    }

    public static String sum(String one, String two) {
        one = formatDot(one);
        two = formatDot(two);
        Double amountOne = Double.parseDouble(one);
        Double amountTwo = Double.parseDouble(two);
        Double result = amountOne + amountTwo;
        return String.format("%.2f", result).replace(",", ".");
    }

    public static String percent(String sum, String of) {
        sum = formatDot(sum);
        of = formatDot(of);
        Double amountOne = Double.parseDouble(sum);
        Double amountTwo = Double.parseDouble(of);
        Double result = (amountOne / amountTwo) * 100;
        return String.format("%.0f", result).replace(",", ".");
    }
}

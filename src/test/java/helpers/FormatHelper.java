package helpers;

public class FormatHelper {
    public static String formatDot(Double sum) {
        return String.format("%.2f", sum).replace(",", ".");
    }

    public static String formatDot(String sum) {
        Double amount = Double.parseDouble(sum.replace(",", "."));
        return String.format("%.2f", amount).replace(",", ".");
    }

    public static String formatComma(String sum) {
        Double amount = Double.parseDouble(sum.replace(",", "."));
        return String.format("%.2f", amount).replace(".", ",");
    }

    public static String formatComma(Double sum) {
        return String.format("%.2f", sum).replace(".", ",");
    }

    public static Double formatDotDouble(String sum) {
        return Double.parseDouble(sum.replace(",", "."));
    }
}

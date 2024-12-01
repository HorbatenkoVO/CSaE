package helpers;

import java.util.Locale;
import java.util.Random;

public class RandomStringUtils {
    private RandomStringUtils() {}

    /**
     * Генерация случайной строки с заданным количеством символов
     * @param symbolsCount количество символов
     * @return сгенерированная строка
     */
    public static String randomAlphabetic(final int symbolsCount) {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder sb = new StringBuilder();

        Random random = new Random();

        for(int i = 0; i < symbolsCount; i++) {

            int index = random.nextInt(alphabet.length());
            char randomChar = alphabet.charAt(index);
            sb.append(randomChar);
        }

        return sb.toString().toLowerCase(Locale.ROOT);
    }

    /**
     * Генерация строки со случайными числами до заданного диапазона
     * @param range диапазон чисел
     * @return сгенерированная строка
     */
    public static String randomNumeric(final int range) {
        return RandomStringUtils.randomNumeric(range);
    }
}

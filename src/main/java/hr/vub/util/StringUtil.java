package hr.vub.util;

public class StringUtil {

    public static boolean isEmpty(String input) {
        return input == null || input.trim().length() == 0;
    }

    public static boolean isNotEmpty(String input) {
        return !isEmpty(input);
    }
}

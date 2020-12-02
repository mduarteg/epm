package epm.util;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TypeUtils {
    public static boolean isInteger(String str) {
        if (str == null) {
            return false;
        }

        try {
            int i = Integer.parseInt(str);
        } catch (NumberFormatException ex) {
            return false;
        }

        return true;
    }

    public static boolean isEnumValue(String str) {
        if (str == null) {
            return false;
        }

        try {
            EventType e = EventType.valueOf(str);
        } catch (IllegalArgumentException ex) {
            return false;
        }

        return true;
    }

    public static boolean isLocalDateTime(String str) {
        if (str == null) {
            return false;
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        try {
            LocalDateTime.parse(str, formatter);
        } catch (RuntimeException ex) {
            return false;
        }

        return true;
    }

    public static boolean isNullOrEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }
}

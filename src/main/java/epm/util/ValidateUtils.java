package epm.util;

import epm.exception.EventFormatException;

public class ValidateUtils {
    public static void validateFormat(String line) throws EventFormatException {
        String[] csv = line.split(",");

        validateLength(csv);
        validateData(csv);
    }

    private static void validateData(String[] csv) throws EventFormatException {
        if (TypeUtils.isNullOrEmpty(csv[1]) || TypeUtils.isNullOrEmpty(csv[4])) {
            throw new EventFormatException("Event could not be parsed - Resources are missing");
        }

        if (!TypeUtils.isEnumValue(csv[0])) {
            throw new EventFormatException("Event could not be parsed - Event type is invalid");
        }

        if (!TypeUtils.isInteger(csv[3])) {
            throw new EventFormatException("Event could not be parsed - Event consumption is invalid");
        }

        if (!TypeUtils.isLocalDateTime(csv[2])) {
            throw new EventFormatException("Event could not be parsed - Event date is invalid");
        }
    }

    private static void validateLength(String[] csv) throws EventFormatException {
        if (csv.length != 5) {
            throw new EventFormatException("Event could not be parsed - The number of values is invalid");
        }
    }
}

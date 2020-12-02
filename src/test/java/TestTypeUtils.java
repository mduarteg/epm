import epm.util.TypeUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestTypeUtils {
    @Test
    public void should_ReturnTrue_When_StringIsAnInteger() {
        String str = "1234";
        boolean res = TypeUtils.isInteger(str);

        assertTrue(res);
    }

    @Test
    public void should_ReturnFalse_When_StringIsNotAnInteger() {
        String str = "notAnInt";
        boolean res = TypeUtils.isInteger(str);

        assertFalse(res);
    }

    @Test
    public void should_ReturnTrue_When_StringIsAnEnumMember() {
        String str = "GSM";
        boolean res = TypeUtils.isEnumValue(str);

        assertTrue(res);
    }

    @Test
    public void should_ReturnFalse_When_StringIsNotAnEnumMember() {
        String str = "AnotherValue";
        boolean res = TypeUtils.isEnumValue(str);

        assertFalse(res);
    }

    @Test
    public void should_ReturnTrue_When_StringIsAValidLocalDateTime() {
        String str = "10/02/2020 10:10:10";
        boolean res = TypeUtils.isLocalDateTime(str);

        assertTrue(res);
    }

    @Test
    public void should_ReturnFalse_When_StringIsNotAValidLocalDateTime() {
        String str = "100/02/2020 00:00:00";
        boolean res = TypeUtils.isLocalDateTime(str);

        assertFalse(res);
    }

    @Test
    public void should_ReturnTrue_When_StringIsNullOrEmpty() {
        String str = "";
        boolean res = TypeUtils.isNullOrEmpty(str);

        assertTrue(res);
    }

    @Test
    public void should_ReturnFalse_When_StringIsNotNullOrEmpty() {
        String str = "I am a String";
        boolean res = TypeUtils.isNullOrEmpty(str);

        assertFalse(res);
    }
}

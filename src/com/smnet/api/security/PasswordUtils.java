package com.smnet.api.security;

import java.util.regex.Pattern;

public class PasswordUtils {

    public static PasswordSecurityStatus isSecure(String password) {

        PasswordSecurityStatus status = isEmpty(password);
        if (status != PasswordSecurityStatus.OK) return status;

        status = containsCapitalLetter(password);
        if (status != PasswordSecurityStatus.OK) return status;

        status = containsLowercaseLetter(password);
        if (status != PasswordSecurityStatus.OK) return status;

        status = containsNumber(password);
        if (status != PasswordSecurityStatus.OK) return status;

        status = containsSpecialChars(password);
        if (status != PasswordSecurityStatus.OK) return status;

        status = isLongEnough(password);
        if (status != PasswordSecurityStatus.OK) return status;

        status = containsSpace(password);
        if (status != PasswordSecurityStatus.OK) return status;

        return PasswordSecurityStatus.OK;
    }

    public static PasswordSecurityStatus isEmpty(String password) {

        if (password.isEmpty())
            return PasswordSecurityStatus.PASSWORD_IS_EMPTY;

        return PasswordSecurityStatus.OK;
    }

    public static PasswordSecurityStatus containsCapitalLetter(String password) {

        if (!Pattern.compile("[A-Z]").matcher(password).find())
            return PasswordSecurityStatus.NO_CAPITAL_LETTER;

        return PasswordSecurityStatus.OK;
    }

    public static PasswordSecurityStatus containsLowercaseLetter(String password) {

        if (!Pattern.compile("[a-z]").matcher(password).find())
            return PasswordSecurityStatus.NO_LOWERCASE_LETTER;

        return PasswordSecurityStatus.OK;
    }

    public static PasswordSecurityStatus containsNumber(String password) {

        if (!Pattern.compile("[0-9]").matcher(password).find())
            return PasswordSecurityStatus.NO_NUMBER;

        return PasswordSecurityStatus.OK;
    }

    public static PasswordSecurityStatus containsSpecialChars(String password) {

        if (!Pattern.compile("[!\"§$%&/()=?*'_:;>{\\[\\]}\\\\~+#\\-.,<|]").matcher(password).find())
            return PasswordSecurityStatus.NO_SPECIAL_CHAR;

        return PasswordSecurityStatus.OK;
    }

    public static PasswordSecurityStatus isLongEnough(String password) {

        if (password.length() < 8)
            return PasswordSecurityStatus.LENGHT_LESS_THAN_8_CHARS;

        return PasswordSecurityStatus.OK;
    }

    public static PasswordSecurityStatus containsSpace(String password) {

        if (password.contains(" "))
            return PasswordSecurityStatus.WITH_SPACE;

        return PasswordSecurityStatus.OK;
    }

    public static PasswordSecurityStatus containsDiacritic(String password) {

        String unicodeAe = "\u00c4";
        String unicodeae = "\u00e4";
        String unicodeOe = "\u00d6";
        String unicodeoe = "\u00f6";
        String unicodeUe = "\u00dc";
        String unicodeue = "\u00fc";
        String unicodeSS = "\u00df";

        String pattern = String.format("[%s%s%s%s%s%s%s]", unicodeAe, unicodeae, unicodeOe, unicodeoe, unicodeUe,
                unicodeue, unicodeSS);

        if (Pattern.compile(pattern).matcher(password).find())
            return PasswordSecurityStatus.WITH_DIACRITIC;

        return PasswordSecurityStatus.OK;
    }

    public enum PasswordSecurityStatus {

        OK,
        PASSWORD_IS_EMPTY,
        NO_CAPITAL_LETTER,
        NO_LOWERCASE_LETTER,
        NO_NUMBER,
        NO_SPECIAL_CHAR,
        LENGHT_LESS_THAN_8_CHARS,
        WITH_SPACE,
        WITH_DIACRITIC;
    }
}

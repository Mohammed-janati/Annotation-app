package com.annotateurproject.security.Utils;

import java.security.SecureRandom;

public class PasswordGenerator {

    private static final String LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
    private static final String UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String DIGITS = "0123456789";
    private static final String SPECIAL = "!@#$%^&*()-_=+[]{}|;:,.<>?/";

    private final boolean useUpper;
    private final boolean useDigits;
    private final boolean useSpecial;
    private final int length;

    public PasswordGenerator(int length, boolean useUpper, boolean useDigits, boolean useSpecial) {
        this.length = length;
        this.useUpper = useUpper;
        this.useDigits = useDigits;
        this.useSpecial = useSpecial;
    }

    public String generate() {
        StringBuilder charPool = new StringBuilder(LOWERCASE);
        if (useUpper) charPool.append(UPPERCASE);
        if (useDigits) charPool.append(DIGITS);
        if (useSpecial) charPool.append(SPECIAL);

        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(charPool.length());
            password.append(charPool.charAt(index));
        }

        return password.toString();
    }
}
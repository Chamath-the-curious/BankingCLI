package org.chamath.util;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public class AccountNumberGenerator {

    private static final Set<String> generatedNumbers = new HashSet<>();

    public static String generate() {
        String number;

        do {
            number = ThreadLocalRandom.current()
                    .ints(10, 0, 10)
                    .collect(StringBuilder::new,
                            StringBuilder::append,
                            StringBuilder::append)
                    .toString();

        } while (!generatedNumbers.add(number));

        return number;
    }
}

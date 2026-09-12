package org.chamath.util;

import java.util.concurrent.atomic.AtomicLong;

public class CustomerIdGenerator {

    private static final AtomicLong counter = new AtomicLong(0);

    public static long generateNextId() {
        return counter.incrementAndGet();
    }
}

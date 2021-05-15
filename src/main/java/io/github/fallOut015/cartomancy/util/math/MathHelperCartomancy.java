package io.github.fallOut015.cartomancy.util.math;

public class MathHelperCartomancy {
    public static long lerp(double pct, long start, long end) {
        return start + ((long) pct * (end - start));
    }
}

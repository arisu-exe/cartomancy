package io.github.fallOut015.cartomancy.util.math;

public class MathHelperCartomancy {
    public static long lerp(double pct, long start, long end) {
        return (long) ((1.0D - pct) * (double) start + pct * (double) end);
    }
}

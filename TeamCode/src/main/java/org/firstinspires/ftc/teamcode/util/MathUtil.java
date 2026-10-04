package org.firstinspires.ftc.teamcode.util;

public final class MathUtil {
    private MathUtil() {
    }

    public static double clamp(double value, double minimum, double maximum) {
        return Math.max(minimum, Math.min(maximum, value));
    }
}

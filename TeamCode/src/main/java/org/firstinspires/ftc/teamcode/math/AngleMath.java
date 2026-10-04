package org.firstinspires.ftc.teamcode.math;

/** Small, unit-agnostic wrapped-angle helpers. Core code uses radians. */
public final class AngleMath {
    private AngleMath() {
    }

    /** Returns an equivalent angle in [-pi, pi). */
    public static double normalizeRadians(double radians) {
        double wrapped = radians % (2.0 * Math.PI);
        if (wrapped >= Math.PI) {
            wrapped -= 2.0 * Math.PI;
        }
        if (wrapped < -Math.PI) {
            wrapped += 2.0 * Math.PI;
        }
        return wrapped;
    }

    /** Returns an equivalent angle in [-180, 180). Useful at telemetry boundaries. */
    public static double normalizeDegrees(double degrees) {
        double wrapped = degrees % 360.0;
        if (wrapped >= 180.0) {
            wrapped -= 360.0;
        }
        if (wrapped < -180.0) {
            wrapped += 360.0;
        }
        return wrapped;
    }
}

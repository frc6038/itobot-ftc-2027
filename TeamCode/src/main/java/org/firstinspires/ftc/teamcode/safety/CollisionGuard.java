package org.firstinspires.ftc.teamcode.safety;

import org.firstinspires.ftc.teamcode.drive.DriveIntent;
import org.firstinspires.ftc.teamcode.util.MathUtil;

/** Conservative forward-only guard. It never steers, commands reverse, or changes turning. */
public final class CollisionGuard {
    private final double hardStopMeters;
    private final double slowdownStartMeters;

    public CollisionGuard(double hardStopMeters, double slowdownStartMeters) {
        if (hardStopMeters < 0.0 || slowdownStartMeters <= hardStopMeters) {
            throw new IllegalArgumentException("Slowdown threshold must exceed hard stop threshold");
        }
        this.hardStopMeters = hardStopMeters;
        this.slowdownStartMeters = slowdownStartMeters;
    }

    public DriveIntent apply(DriveIntent requested, double frontLeftMeters, double frontRightMeters) {
        if (requested.forward <= 0.0) {
            return requested;
        }
        double nearest = nearestValidMeters(frontLeftMeters, frontRightMeters);
        if (!Double.isFinite(nearest)) {
            // Both sensors invalid/stale: do not invent a stop that fights the driver during bring-up.
            return requested;
        }
        double scale = MathUtil.clamp((nearest - hardStopMeters) /
                (slowdownStartMeters - hardStopMeters), 0.0, 1.0);
        return new DriveIntent(requested.strafe, requested.forward * scale, requested.rotation);
    }

    private static double nearestValidMeters(double frontLeftMeters, double frontRightMeters) {
        boolean leftOk = isValidReading(frontLeftMeters);
        boolean rightOk = isValidReading(frontRightMeters);
        if (leftOk && rightOk) {
            return Math.min(frontLeftMeters, frontRightMeters);
        }
        if (leftOk) {
            return frontLeftMeters;
        }
        if (rightOk) {
            return frontRightMeters;
        }
        return Double.NaN;
    }

    private static boolean isValidReading(double meters) {
        // Finite and non-negative only. +Infinity from RevDistanceSensorIO means "no obstacle reading".
        return Double.isFinite(meters) && meters >= 0.0;
    }
}

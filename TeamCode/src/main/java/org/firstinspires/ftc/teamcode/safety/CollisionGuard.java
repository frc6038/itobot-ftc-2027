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
        double nearest = Math.min(frontLeftMeters, frontRightMeters);
        double scale = MathUtil.clamp((nearest - hardStopMeters) /
                (slowdownStartMeters - hardStopMeters), 0.0, 1.0);
        return new DriveIntent(requested.strafe, requested.forward * scale, requested.rotation);
    }
}

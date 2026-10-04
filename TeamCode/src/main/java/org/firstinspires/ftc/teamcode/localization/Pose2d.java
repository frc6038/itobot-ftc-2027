package org.firstinspires.ftc.teamcode.localization;

import org.firstinspires.ftc.teamcode.math.AngleMath;

/** Immutable robot pose in meters and radians, with +x right and +y forward. */
public final class Pose2d {
    public static final Pose2d ORIGIN = new Pose2d(0.0, 0.0, 0.0);

    public final double xMeters;
    public final double yMeters;
    public final double headingRadians;

    public Pose2d(double xMeters, double yMeters, double headingRadians) {
        this.xMeters = xMeters;
        this.yMeters = yMeters;
        this.headingRadians = AngleMath.normalizeRadians(headingRadians);
    }
}

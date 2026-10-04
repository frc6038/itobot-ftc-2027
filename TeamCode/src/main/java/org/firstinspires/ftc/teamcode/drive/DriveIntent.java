package org.firstinspires.ftc.teamcode.drive;

import org.firstinspires.ftc.teamcode.util.MathUtil;

/** Unitless driver or controller demand: strafe right, forward, counter-clockwise rotation. */
public final class DriveIntent {
    public static final DriveIntent STOP = new DriveIntent(0.0, 0.0, 0.0);

    public final double strafe;
    public final double forward;
    public final double rotation;

    public DriveIntent(double strafe, double forward, double rotation) {
        this.strafe = MathUtil.clamp(strafe, -1.0, 1.0);
        this.forward = MathUtil.clamp(forward, -1.0, 1.0);
        this.rotation = MathUtil.clamp(rotation, -1.0, 1.0);
    }

    public DriveIntent withRotation(double newRotation) {
        return new DriveIntent(strafe, forward, newRotation);
    }
}

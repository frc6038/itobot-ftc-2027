package org.firstinspires.ftc.teamcode.drive;

/** Mathematically clean, normalized wheel demands. Hardware polarity is applied by DriveIO. */
public final class WheelSpeeds {
    public static final WheelSpeeds STOP = new WheelSpeeds(0.0, 0.0, 0.0, 0.0);

    public final double frontLeft;
    public final double frontRight;
    public final double rearLeft;
    public final double rearRight;

    public WheelSpeeds(double frontLeft, double frontRight, double rearLeft, double rearRight) {
        this.frontLeft = frontLeft;
        this.frontRight = frontRight;
        this.rearLeft = rearLeft;
        this.rearRight = rearRight;
    }
}

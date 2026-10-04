package org.firstinspires.ftc.teamcode.drive;

/** Converts a field-frame translation command into the robot frame for a CCW-positive heading. */
public final class FieldCentricTransform {
    private FieldCentricTransform() {
    }

    public static DriveIntent toRobotCentric(DriveIntent fieldIntent, double headingRadians) {
        double cosine = Math.cos(headingRadians);
        double sine = Math.sin(headingRadians);
        double robotStrafe = fieldIntent.strafe * cosine + fieldIntent.forward * sine;
        double robotForward = -fieldIntent.strafe * sine + fieldIntent.forward * cosine;
        return new DriveIntent(robotStrafe, robotForward, fieldIntent.rotation);
    }
}

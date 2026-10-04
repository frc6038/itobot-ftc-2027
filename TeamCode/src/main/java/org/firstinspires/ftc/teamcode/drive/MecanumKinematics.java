package org.firstinspires.ftc.teamcode.drive;

/** Pure MEB-compatible mecanum mixing; intentionally contains no motor direction knowledge. */
public final class MecanumKinematics {
    public WheelSpeeds toWheelSpeeds(DriveIntent intent) {
        double frontLeft = intent.forward + intent.strafe + intent.rotation;
        double frontRight = intent.forward - intent.strafe - intent.rotation;
        double rearLeft = intent.forward - intent.strafe + intent.rotation;
        double rearRight = intent.forward + intent.strafe - intent.rotation;
        double maximum = Math.max(1.0, Math.max(Math.abs(frontLeft), Math.max(Math.abs(frontRight),
                Math.max(Math.abs(rearLeft), Math.abs(rearRight)))));
        return new WheelSpeeds(frontLeft / maximum, frontRight / maximum,
                rearLeft / maximum, rearRight / maximum);
    }
}

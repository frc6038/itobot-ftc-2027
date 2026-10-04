package org.firstinspires.ftc.teamcode.drive;

/** Port that is the only normal production path to drivetrain motor output. */
public interface DriveIO {
    void setWheelSpeeds(WheelSpeeds wheelSpeeds);

    void stop();
}

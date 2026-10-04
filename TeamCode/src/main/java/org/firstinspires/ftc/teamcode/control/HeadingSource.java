package org.firstinspires.ftc.teamcode.control;

/** Heading port, keeping FTC's IMU API out of drive control. */
public interface HeadingSource {
    double getHeadingRadians();

    void zeroHeading();
}

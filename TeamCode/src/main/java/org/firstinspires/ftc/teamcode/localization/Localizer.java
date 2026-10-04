package org.firstinspires.ftc.teamcode.localization;

/** Pose source seam. OTOS is the V1 source; a future Pinpoint adapter can implement this unchanged. */
public interface Localizer {
    Pose2d getPose();

    void setPose(Pose2d pose);
}

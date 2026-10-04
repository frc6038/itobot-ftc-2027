package org.firstinspires.ftc.teamcode.localization;

/** Development fallback that preserves a manually supplied pose without claiming sensor localization. */
public final class NoOpLocalizer implements Localizer {
    private Pose2d pose = Pose2d.ORIGIN;

    @Override
    public Pose2d getPose() {
        return pose;
    }

    @Override
    public void setPose(Pose2d pose) {
        this.pose = pose;
    }
}

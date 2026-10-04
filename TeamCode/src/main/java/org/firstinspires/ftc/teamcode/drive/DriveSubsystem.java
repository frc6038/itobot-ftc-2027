package org.firstinspires.ftc.teamcode.drive;

import org.firstinspires.ftc.teamcode.control.HeadingController;
import org.firstinspires.ftc.teamcode.control.HeadingSource;

/** Coordinates shaping, optional field frame, and heading hold before writing a MecanumDrive. */
public final class DriveSubsystem {
    private final MecanumDrive drive;
    private final HeadingSource headingSource;
    private final DriveInputProcessor inputProcessor;
    private final HeadingController headingController;
    private final double manualRotationThreshold;

    public DriveSubsystem(MecanumDrive drive, HeadingSource headingSource, DriveInputProcessor inputProcessor,
                          HeadingController headingController, double manualRotationThreshold) {
        this.drive = drive;
        this.headingSource = headingSource;
        this.inputProcessor = inputProcessor;
        this.headingController = headingController;
        this.manualRotationThreshold = manualRotationThreshold;
        headingController.captureCurrentHeading(headingSource.getHeadingRadians());
    }

    public void drive(double rawStrafe, double rawForward, double rawRotation, boolean fieldCentric,
                      boolean precisionMode, double precisionScale, boolean headingHoldEnabled) {
        double heading = headingSource.getHeadingRadians();
        DriveIntent intent = inputProcessor.process(rawStrafe, rawForward, rawRotation, precisionMode, precisionScale);
        if (Math.abs(intent.rotation) > manualRotationThreshold || !headingHoldEnabled) {
            headingController.captureCurrentHeading(heading);
        } else {
            intent = intent.withRotation(headingController.calculate(heading));
        }
        drive.drive(fieldCentric ? FieldCentricTransform.toRobotCentric(intent, heading) : intent);
    }

    public void zeroHeading() {
        headingSource.zeroHeading();
        headingController.captureCurrentHeading(headingSource.getHeadingRadians());
    }

    public void stop() {
        drive.stop();
    }
}

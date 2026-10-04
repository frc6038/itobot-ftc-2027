package org.firstinspires.ftc.teamcode.control;

import org.firstinspires.ftc.teamcode.drive.DriveIntent;
import org.firstinspires.ftc.teamcode.safety.CollisionGuard;
import org.firstinspires.ftc.teamcode.vision.TargetObservation;

/** Explicitly selects an assist mode; default behavior leaves the driver's intent untouched. */
public final class DriveAssistManager {
    private final AutoAlignController autoAlignController;
    private final CollisionGuard collisionGuard;

    public DriveAssistManager(AutoAlignController autoAlignController, CollisionGuard collisionGuard) {
        this.autoAlignController = autoAlignController;
        this.collisionGuard = collisionGuard;
    }

    public DriveIntent apply(DriveIntent driverIntent, boolean autoAlignEnabled, TargetObservation target,
                             double frontLeftMeters, double frontRightMeters) {
        DriveIntent selected = autoAlignEnabled ? autoAlignController.calculate(target) : driverIntent;
        return collisionGuard == null ? selected : collisionGuard.apply(selected, frontLeftMeters, frontRightMeters);
    }
}

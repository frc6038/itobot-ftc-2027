package org.firstinspires.ftc.teamcode.control;

import org.firstinspires.ftc.teamcode.drive.DriveIntent;
import org.firstinspires.ftc.teamcode.safety.CollisionGuard;
import org.firstinspires.ftc.teamcode.vision.TargetObservation;

/** Explicitly selects an assist mode; default behavior leaves the driver's intent untouched. */
public final class DriveAssistManager {
    private final AutoAlignController autoAlignController;
    private final CollisionGuard collisionGuard;
    private final long maximumTargetAgeNanos;

    public DriveAssistManager(AutoAlignController autoAlignController, CollisionGuard collisionGuard,
                              long maximumTargetAgeNanos) {
        if (maximumTargetAgeNanos <= 0L) {
            throw new IllegalArgumentException("Target age limit must be positive");
        }
        this.autoAlignController = autoAlignController;
        this.collisionGuard = collisionGuard;
        this.maximumTargetAgeNanos = maximumTargetAgeNanos;
    }

    public DriveIntent apply(DriveIntent driverIntent, boolean autoAlignEnabled, TargetObservation target,
                             double frontLeftMeters, double frontRightMeters, long nowNanos) {
        DriveIntent selected;
        if (!autoAlignEnabled) {
            selected = driverIntent;
        } else if (target == null || !target.isFresh(nowNanos, maximumTargetAgeNanos)) {
            // Assist engaged but target lost/stale: fail safe to stop rather than invent motion.
            selected = DriveIntent.STOP;
        } else {
            selected = autoAlignController.calculate(target);
        }
        return collisionGuard == null ? selected : collisionGuard.apply(selected, frontLeftMeters, frontRightMeters);
    }
}

package org.firstinspires.ftc.teamcode.drive;

import org.firstinspires.ftc.teamcode.util.MathUtil;

/** Applies symmetric deadband and an optional cubic blend before control logic sees driver input. */
public final class DriveInputProcessor {
    private final double deadband;
    private final double cubicWeight;

    public DriveInputProcessor(double deadband, double cubicWeight) {
        if (deadband < 0.0 || deadband >= 1.0 || cubicWeight < 0.0 || cubicWeight > 1.0) {
            throw new IllegalArgumentException("Invalid drive input configuration");
        }
        this.deadband = deadband;
        this.cubicWeight = cubicWeight;
    }

    public DriveIntent process(double strafe, double forward, double rotation, boolean precisionMode,
                               double precisionScale) {
        double scale = precisionMode ? MathUtil.clamp(precisionScale, 0.0, 1.0) : 1.0;
        return new DriveIntent(shape(strafe) * scale, shape(forward) * scale, shape(rotation) * scale);
    }

    private double shape(double value) {
        double deadbanded = removeDeadband(MathUtil.clamp(value, -1.0, 1.0));
        return (1.0 - cubicWeight) * deadbanded + cubicWeight * deadbanded * deadbanded * deadbanded;
    }

    private double removeDeadband(double value) {
        if (Math.abs(value) <= deadband) {
            return 0.0;
        }
        return Math.copySign((Math.abs(value) - deadband) / (1.0 - deadband), value);
    }
}

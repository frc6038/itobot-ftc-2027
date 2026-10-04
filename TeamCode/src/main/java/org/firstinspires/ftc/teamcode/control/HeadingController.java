package org.firstinspires.ftc.teamcode.control;

import org.firstinspires.ftc.teamcode.math.AngleMath;
import org.firstinspires.ftc.teamcode.util.MathUtil;

/** Proportional heading hold controller with wrapped radians throughout. */
public final class HeadingController {
    private final double kP;
    private final double maximumCorrection;
    private double targetHeadingRadians;

    public HeadingController(double kP, double maximumCorrection) {
        if (kP < 0.0 || maximumCorrection < 0.0 || maximumCorrection > 1.0) {
            throw new IllegalArgumentException("Invalid heading controller configuration");
        }
        this.kP = kP;
        this.maximumCorrection = maximumCorrection;
    }

    public void setTargetHeadingRadians(double targetHeadingRadians) {
        this.targetHeadingRadians = AngleMath.normalizeRadians(targetHeadingRadians);
    }

    public void captureCurrentHeading(double currentHeadingRadians) {
        setTargetHeadingRadians(currentHeadingRadians);
    }

    public double calculate(double currentHeadingRadians) {
        double error = AngleMath.normalizeRadians(targetHeadingRadians - currentHeadingRadians);
        return MathUtil.clamp(error * kP, -maximumCorrection, maximumCorrection);
    }

    public double getTargetHeadingRadians() {
        return targetHeadingRadians;
    }
}

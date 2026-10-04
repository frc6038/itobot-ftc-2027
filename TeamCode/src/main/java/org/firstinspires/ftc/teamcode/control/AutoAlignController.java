package org.firstinspires.ftc.teamcode.control;

import org.firstinspires.ftc.teamcode.drive.DriveIntent;
import org.firstinspires.ftc.teamcode.util.MathUtil;
import org.firstinspires.ftc.teamcode.vision.TargetObservation;

/** Target-relative assist; it does not infer a field pose from a BIOBUZZ AprilTag. */
public final class AutoAlignController {
    private final double desiredRangeMeters;
    private final double rangeKp;
    private final double bearingKp;
    private final double yawKp;
    private final double maximumOutput;

    public AutoAlignController(double desiredRangeMeters, double rangeKp, double bearingKp,
                               double yawKp, double maximumOutput) {
        this.desiredRangeMeters = desiredRangeMeters;
        this.rangeKp = rangeKp;
        this.bearingKp = bearingKp;
        this.yawKp = yawKp;
        this.maximumOutput = maximumOutput;
    }

    public DriveIntent calculate(TargetObservation target) {
        if (target == null || !target.visible) {
            return DriveIntent.STOP;
        }
        return new DriveIntent(
                clamp(target.bearingRadians * bearingKp),
                clamp((target.rangeMeters - desiredRangeMeters) * rangeKp),
                clamp(target.yawRadians * yawKp));
    }

    private double clamp(double output) {
        return MathUtil.clamp(output, -maximumOutput, maximumOutput);
    }
}

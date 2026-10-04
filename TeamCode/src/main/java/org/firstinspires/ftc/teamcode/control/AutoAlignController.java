package org.firstinspires.ftc.teamcode.control;

import org.firstinspires.ftc.teamcode.drive.DriveIntent;
import org.firstinspires.ftc.teamcode.util.MathUtil;
import org.firstinspires.ftc.teamcode.vision.TargetObservation;

/**
 * Target-relative assist mapped to MEB {@link DriveIntent} axes.
 * Range → forward, bearing → turn, yaw → strafe. Signs follow FTC's omni AprilTag sample
 * after converting its left-positive strafe / sample mixing into our right-positive strafe
 * and CCW-positive rotation convention. Never treats a BIOBUZZ tag as a field pose.
 */
public final class AutoAlignController {
    private final double desiredRangeMeters;
    private final double rangeKp;
    private final double bearingKp;
    private final double yawKp;
    private final double maximumOutput;

    public AutoAlignController(double desiredRangeMeters, double rangeKp, double bearingKp,
                               double yawKp, double maximumOutput) {
        if (desiredRangeMeters < 0.0 || maximumOutput < 0.0 || maximumOutput > 1.0) {
            throw new IllegalArgumentException("Invalid auto-align configuration");
        }
        this.desiredRangeMeters = desiredRangeMeters;
        this.rangeKp = rangeKp;
        this.bearingKp = bearingKp;
        this.yawKp = yawKp;
        this.maximumOutput = maximumOutput;
    }

    public DriveIntent calculate(TargetObservation target) {
        if (target == null || !target.visible
                || !Double.isFinite(target.rangeMeters)
                || !Double.isFinite(target.bearingRadians)
                || !Double.isFinite(target.yawRadians)) {
            return DriveIntent.STOP;
        }
        // FTC sample: drive = (range - desired) * k, turn_cmd = bearing * k, strafe_left = -yaw * k.
        // MEB DriveIntent uses strafe-right and CCW-positive rotation, which converts to:
        // strafe = +yaw * k, rotation = -bearing * k.
        return new DriveIntent(
                clamp(target.yawRadians * yawKp),
                clamp((target.rangeMeters - desiredRangeMeters) * rangeKp),
                clamp(-target.bearingRadians * bearingKp));
    }

    private double clamp(double output) {
        return MathUtil.clamp(output, -maximumOutput, maximumOutput);
    }
}

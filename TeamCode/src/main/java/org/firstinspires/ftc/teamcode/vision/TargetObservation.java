package org.firstinspires.ftc.teamcode.vision;

/** Immutable, target-relative AprilTag information; it is deliberately not a field-pose estimate. */
public final class TargetObservation {
    public static final TargetObservation NONE = new TargetObservation(false, "", Double.NaN,
            Double.NaN, Double.NaN, 0L);

    public final boolean visible;
    public final String identity;
    public final double rangeMeters;
    public final double bearingRadians;
    public final double yawRadians;
    public final long timestampNanos;

    public TargetObservation(boolean visible, String identity, double rangeMeters, double bearingRadians,
                             double yawRadians, long timestampNanos) {
        this.visible = visible;
        this.identity = identity;
        this.rangeMeters = rangeMeters;
        this.bearingRadians = bearingRadians;
        this.yawRadians = yawRadians;
        this.timestampNanos = timestampNanos;
    }

    public boolean isFresh(long nowNanos, long maximumAgeNanos) {
        return visible && nowNanos >= timestampNanos && nowNanos - timestampNanos <= maximumAgeNanos;
    }
}

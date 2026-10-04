package org.firstinspires.ftc.teamcode.robot;

import org.firstinspires.ftc.teamcode.localization.Pose2d;
import org.firstinspires.ftc.teamcode.vision.TargetObservation;

/** Small central read model. Writers publish whole immutable snapshots; consumers never mutate it. */
public final class RobotState {
    private volatile Snapshot snapshot = new Snapshot(Pose2d.ORIGIN, 0.0, TargetObservation.NONE,
            Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, 0L);

    public void publish(Snapshot snapshot) {
        this.snapshot = snapshot;
    }

    public Snapshot snapshot() {
        return snapshot;
    }

    public static final class Snapshot {
        public final Pose2d pose;
        public final double headingRadians;
        public final TargetObservation target;
        public final double frontLeftDistanceMeters;
        public final double frontRightDistanceMeters;
        public final long loopDurationNanos;

        public Snapshot(Pose2d pose, double headingRadians, TargetObservation target,
                        double frontLeftDistanceMeters, double frontRightDistanceMeters, long loopDurationNanos) {
            this.pose = pose;
            this.headingRadians = headingRadians;
            this.target = target;
            this.frontLeftDistanceMeters = frontLeftDistanceMeters;
            this.frontRightDistanceMeters = frontRightDistanceMeters;
            this.loopDurationNanos = loopDurationNanos;
        }
    }
}

package org.firstinspires.ftc.teamcode.vision;

/** Vision port exposing only normalized observations consumed by the rest of the robot. */
public interface VisionSystem extends AutoCloseable {
    TargetObservation getLatestTarget();

    @Override
    void close();
}

package org.firstinspires.ftc.teamcode.vision;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.config.RobotConfig;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagClusterDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;
import org.firstinspires.ftc.vision.apriltag.AprilTagSingleDetection;

import java.util.List;

/** FTC SDK 12 VisionPortal adapter supporting both singleton and cluster BIOBUZZ detections. */
public final class VisionPortalVisionIO implements VisionSystem {
    private static final double INCHES_TO_METERS = 0.0254;
    private final AprilTagProcessor aprilTagProcessor;
    private final VisionPortal visionPortal;

    public VisionPortalVisionIO(HardwareMap hardwareMap) {
        this(hardwareMap, RobotConfig.Vision.CAMERA_NAME);
    }

    public VisionPortalVisionIO(HardwareMap hardwareMap, String cameraName) {
        aprilTagProcessor = new AprilTagProcessor.Builder().build();
        visionPortal = new VisionPortal.Builder()
                .setCamera(hardwareMap.get(WebcamName.class, cameraName))
                .addProcessor(aprilTagProcessor)
                .build();
    }

    @Override
    public TargetObservation getLatestTarget() {
        List<AprilTagDetection> detections = aprilTagProcessor.getDetections();
        if (detections.isEmpty()) {
            return TargetObservation.NONE;
        }
        AprilTagDetection detection = detections.get(0);
        String identity = identityOf(detection);
        return new TargetObservation(true, identity, detection.ftcPose.range * INCHES_TO_METERS,
                Math.toRadians(detection.ftcPose.bearing), Math.toRadians(detection.ftcPose.yaw), System.nanoTime());
    }

    private static String identityOf(AprilTagDetection detection) {
        if (detection instanceof AprilTagSingleDetection) {
            AprilTagSingleDetection single = (AprilTagSingleDetection) detection;
            return single.metadata == null ? "tag-" + single.id : single.metadata.name;
        }
        if (detection instanceof AprilTagClusterDetection) {
            AprilTagClusterDetection cluster = (AprilTagClusterDetection) detection;
            return cluster.metadata == null ? "cluster" : cluster.metadata.name;
        }
        return "unknown";
    }

    @Override
    public void close() {
        visionPortal.close();
    }
}

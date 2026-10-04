package org.firstinspires.ftc.teamcode.opmode.diagnostic;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.vision.TargetObservation;
import org.firstinspires.ftc.teamcode.vision.VisionPortalVisionIO;

@TeleOp(name = "ITOBOT Diag: Vision", group = "ITOBOT Diagnostics")
public final class VisionDiagnostic extends OpMode {
    private VisionPortalVisionIO vision;

    @Override
    public void init() {
        vision = new VisionPortalVisionIO(hardwareMap);
        telemetry.addLine("AprilTag target-relative diagnostic ready.");
    }

    @Override
    public void loop() {
        TargetObservation target = vision.getLatestTarget();
        telemetry.addData("Visible", target.visible);
        if (target.visible) {
            telemetry.addData("Target", target.identity);
            telemetry.addData("Range (m)", "%.2f", target.rangeMeters);
            telemetry.addData("Bearing (deg)", "%.1f", Math.toDegrees(target.bearingRadians));
            telemetry.addData("Yaw (deg)", "%.1f", Math.toDegrees(target.yawRadians));
        }
        telemetry.update();
    }

    @Override
    public void stop() {
        if (vision != null) {
            vision.close();
        }
    }
}

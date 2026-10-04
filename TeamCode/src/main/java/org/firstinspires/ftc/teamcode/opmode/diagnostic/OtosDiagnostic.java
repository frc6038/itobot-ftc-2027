package org.firstinspires.ftc.teamcode.opmode.diagnostic;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.localization.OtosLocalizer;
import org.firstinspires.ftc.teamcode.localization.Pose2d;

@TeleOp(name = "ITOBOT Diag: OTOS", group = "ITOBOT Diagnostics")
public final class OtosDiagnostic extends OpMode {
    private OtosLocalizer localizer;

    @Override
    public void init() {
        localizer = new OtosLocalizer(hardwareMap);
        telemetry.addLine("OTOS ready. A resets its pose to the origin.");
    }

    @Override
    public void loop() {
        if (gamepad1.a) {
            localizer.setPose(Pose2d.ORIGIN);
        }
        Pose2d pose = localizer.getPose();
        telemetry.addData("X (m)", "%.3f", pose.xMeters);
        telemetry.addData("Y (m)", "%.3f", pose.yMeters);
        telemetry.addData("Heading (deg)", "%.1f", Math.toDegrees(pose.headingRadians));
        telemetry.update();
    }
}

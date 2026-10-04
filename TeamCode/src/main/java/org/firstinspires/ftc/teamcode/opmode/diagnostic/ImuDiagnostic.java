package org.firstinspires.ftc.teamcode.opmode.diagnostic;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.control.RevImuHeadingSource;

@TeleOp(name = "ITOBOT Diag: IMU", group = "ITOBOT Diagnostics")
public final class ImuDiagnostic extends OpMode {
    private RevImuHeadingSource heading;

    @Override
    public void init() {
        heading = new RevImuHeadingSource(hardwareMap);
        telemetry.addLine("Press A to zero heading.");
    }

    @Override
    public void loop() {
        if (gamepad1.a) {
            heading.zeroHeading();
        }
        telemetry.addData("Heading (deg)", Math.toDegrees(heading.getHeadingRadians()));
        telemetry.update();
    }
}

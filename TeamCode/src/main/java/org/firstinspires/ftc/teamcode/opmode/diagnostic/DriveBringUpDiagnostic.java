package org.firstinspires.ftc.teamcode.opmode.diagnostic;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.config.RobotConfig;
import org.firstinspires.ftc.teamcode.drive.DriveInputProcessor;
import org.firstinspires.ftc.teamcode.drive.MecanumDrive;
import org.firstinspires.ftc.teamcode.drive.RevMecanumDriveIO;

/** Low-power robot-centric drive check before using the competition TeleOp. */
@TeleOp(name = "ITOBOT Diag: Drive Bring-Up", group = "ITOBOT Diagnostics")
public final class DriveBringUpDiagnostic extends OpMode {
    private static final double MAX_POWER = 0.25;
    private MecanumDrive drive;
    private DriveInputProcessor input;

    @Override
    public void init() {
        drive = new MecanumDrive(new RevMecanumDriveIO(hardwareMap));
        input = new DriveInputProcessor(RobotConfig.Drive.INPUT_DEADBAND, 0.0);
        telemetry.addLine("Robot-centric, capped at 25%. Verify every direction with wheels off ground first.");
    }

    @Override
    public void loop() {
        drive.drive(input.process(gamepad1.left_stick_x, -gamepad1.left_stick_y, gamepad1.right_stick_x,
                true, MAX_POWER));
        telemetry.addData("Input", "strafe %.2f forward %.2f rotate %.2f", gamepad1.left_stick_x,
                -gamepad1.left_stick_y, gamepad1.right_stick_x);
        telemetry.update();
    }

    @Override
    public void stop() {
        if (drive != null) {
            drive.stop();
        }
    }
}

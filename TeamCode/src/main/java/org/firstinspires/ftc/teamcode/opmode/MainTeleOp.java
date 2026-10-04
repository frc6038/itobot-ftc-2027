package org.firstinspires.ftc.teamcode.opmode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.config.RobotConfig;
import org.firstinspires.ftc.teamcode.control.HeadingController;
import org.firstinspires.ftc.teamcode.control.RevImuHeadingSource;
import org.firstinspires.ftc.teamcode.drive.DriveInputProcessor;
import org.firstinspires.ftc.teamcode.drive.DriveSubsystem;
import org.firstinspires.ftc.teamcode.drive.MecanumDrive;
import org.firstinspires.ftc.teamcode.drive.RevMecanumDriveIO;

/** Competition teleop intentionally orchestrates configured subsystems rather than containing drive math. */
@TeleOp(name = "ITOBOT: Main TeleOp", group = "ITOBOT")
public final class MainTeleOp extends OpMode {
    private DriveSubsystem drive;

    @Override
    public void init() {
        RevImuHeadingSource heading = new RevImuHeadingSource(hardwareMap);
        drive = new DriveSubsystem(new MecanumDrive(new RevMecanumDriveIO(hardwareMap)), heading,
                new DriveInputProcessor(RobotConfig.Drive.INPUT_DEADBAND, RobotConfig.Drive.CUBIC_WEIGHT),
                new HeadingController(RobotConfig.Heading.KP, RobotConfig.Heading.MAX_CORRECTION),
                RobotConfig.Drive.MANUAL_ROTATION_THRESHOLD);
        telemetry.addLine("Drive ready: A zeroes heading; LB robot-centric; RB precision; B heading hold");
    }

    @Override
    public void loop() {
        if (gamepad1.a) {
            drive.zeroHeading();
        }
        drive.drive(gamepad1.left_stick_x, -gamepad1.left_stick_y, gamepad1.right_stick_x,
                !gamepad1.left_bumper, gamepad1.right_bumper, RobotConfig.Drive.PRECISION_SCALE, gamepad1.b);
        telemetry.addData("Frame", gamepad1.left_bumper ? "robot" : "field");
        telemetry.addData("Heading hold", gamepad1.b);
        telemetry.update();
    }

    @Override
    public void stop() {
        if (drive != null) {
            drive.stop();
        }
    }
}

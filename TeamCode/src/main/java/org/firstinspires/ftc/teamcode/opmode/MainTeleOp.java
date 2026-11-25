package org.firstinspires.ftc.teamcode.opmode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.config.RobotConfig;
import org.firstinspires.ftc.teamcode.mechanisms.intake.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.mechanisms.intake.RevIntakeIO;
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
    private IntakeSubsystem intake;

    @Override
    public void init() {
        RevImuHeadingSource heading = new RevImuHeadingSource(hardwareMap);
        drive = new DriveSubsystem(new MecanumDrive(new RevMecanumDriveIO(hardwareMap)), heading,
                new DriveInputProcessor(RobotConfig.Drive.INPUT_DEADBAND, RobotConfig.Drive.CUBIC_WEIGHT),
                new HeadingController(RobotConfig.Heading.KP, RobotConfig.Heading.MAX_CORRECTION),
                RobotConfig.Drive.MANUAL_ROTATION_THRESHOLD);
        if (RobotConfig.Intake.ENABLED) {
            intake = new IntakeSubsystem(
                    new RevIntakeIO(hardwareMap),
                    RobotConfig.Intake.INTAKE_POWER,
                    RobotConfig.Intake.OUTTAKE_POWER,
                    RobotConfig.Intake.TRIGGER_DEADBAND
            );
        }

        telemetry.addData("Intake", intake != null ? "Ready" : "Disabled");
        telemetry.addLine("Intake: RT forward; LT reverse");
        telemetry.addLine("Drive ready: A zeroes heading; LB robot-centric; RB precision; B heading hold");
    }

    @Override
    public void loop() {
        if (gamepad1.a) {
            drive.zeroHeading();
        }
        drive.drive(gamepad1.left_stick_x, -gamepad1.left_stick_y, gamepad1.right_stick_x,
                !gamepad1.left_bumper, gamepad1.right_bumper, RobotConfig.Drive.PRECISION_SCALE, gamepad1.b);
        if (intake != null) {
            intake.update(
                    gamepad1.right_trigger,
                    gamepad1.left_trigger
            );
            telemetry.addData(
                    "Intake commanded power",
                    "%.2f",
                    intake.getCommandedPower()
            );
        }

        telemetry.addData("Frame", gamepad1.left_bumper ? "robot" : "field");
        telemetry.addData("Heading hold", gamepad1.b);
        telemetry.update();
    }

    @Override
    public void stop() {
        try {
            if (drive != null) {
                drive.stop();
            }
        } finally {
            if (intake != null) {
                intake.stop();
            }
        }
    }
}

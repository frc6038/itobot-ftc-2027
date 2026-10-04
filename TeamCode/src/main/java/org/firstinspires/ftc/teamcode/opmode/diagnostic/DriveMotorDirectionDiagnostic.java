package org.firstinspires.ftc.teamcode.opmode.diagnostic;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.drive.RevMecanumDriveIO;
import org.firstinspires.ftc.teamcode.drive.WheelSpeeds;

/** Runs exactly one configured drive motor at safe power to establish physical polarity. */
@TeleOp(name = "ITOBOT Diag: Drive Motor Direction", group = "ITOBOT Diagnostics")
public final class DriveMotorDirectionDiagnostic extends OpMode {
    private static final double SAFE_POWER = 0.15;
    private RevMecanumDriveIO driveIO;

    @Override
    public void init() {
        driveIO = new RevMecanumDriveIO(hardwareMap);
        telemetry.addLine("Hold A=FL, B=FR, X=RL, Y=RR. Observe safe wheel direction.");
    }

    @Override
    public void loop() {
        if (gamepad1.a) {
            driveIO.setWheelSpeeds(new WheelSpeeds(SAFE_POWER, 0, 0, 0));
            telemetry.addData("Running", "front left");
        } else if (gamepad1.b) {
            driveIO.setWheelSpeeds(new WheelSpeeds(0, SAFE_POWER, 0, 0));
            telemetry.addData("Running", "front right");
        } else if (gamepad1.x) {
            driveIO.setWheelSpeeds(new WheelSpeeds(0, 0, SAFE_POWER, 0));
            telemetry.addData("Running", "rear left");
        } else if (gamepad1.y) {
            driveIO.setWheelSpeeds(new WheelSpeeds(0, 0, 0, SAFE_POWER));
            telemetry.addData("Running", "rear right");
        } else {
            driveIO.stop();
            telemetry.addData("Running", "none");
        }
        telemetry.update();
    }

    @Override
    public void stop() {
        if (driveIO != null) {
            driveIO.stop();
        }
    }
}

package org.firstinspires.ftc.teamcode.opmode.diagnostic;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.config.RobotConfig;
import org.firstinspires.ftc.teamcode.mechanisms.intake.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.mechanisms.intake.RevIntakeIO;

@TeleOp(
        name = "ITOBOT Diag: Intake",
        group = "ITOBOT Diagnostics"
)
public final class IntakeDiagnostic extends OpMode {
    private static final double MAX_POWER = 0.25;

    private IntakeSubsystem intake;

    @Override
    public void init() {
        intake = new IntakeSubsystem(
                new RevIntakeIO(hardwareMap),
                MAX_POWER,
                MAX_POWER,
                RobotConfig.Intake.TRIGGER_DEADBAND
        );

        telemetry.addLine("Intake diagnostic ready");
        telemetry.addLine("RT: Intake | LT: Outtake");
        telemetry.addLine("Release both triggers to stop");
        telemetry.addLine("Maximum power: 25%");
        telemetry.update();
    }

    @Override
    public void loop() {
        intake.update(
                gamepad1.right_trigger,
                gamepad1.left_trigger
        );

        telemetry.addData("RT", gamepad1.right_trigger);
        telemetry.addData("LT", gamepad1.left_trigger);
        telemetry.addData(
                "Commanded power",
                "%.2f",
                intake.getCommandedPower()
        );
        telemetry.update();
    }

    @Override
    public void stop() {
        if (intake != null) {
            intake.stop();
        }
    }
}

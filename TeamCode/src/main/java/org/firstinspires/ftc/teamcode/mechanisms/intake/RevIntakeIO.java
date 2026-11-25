package org.firstinspires.ftc.teamcode.mechanisms.intake;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.config.RobotConfig;

public final class RevIntakeIO implements IntakeIO {
    private final DcMotorEx motor;

    public RevIntakeIO(HardwareMap hardwareMap) {
        motor = hardwareMap.get(
                DcMotorEx.class,
                RobotConfig.Intake.NAME
        );

        motor.setPower(0.0);
        motor.setDirection(RobotConfig.Intake.DIRECTION);
        motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motor.setZeroPowerBehavior(
                DcMotor.ZeroPowerBehavior.BRAKE
        );
    }

    @Override
    public void setPower(double power) {
        double safePower = Double.isFinite(power)
                ? Math.max(-1.0, Math.min(1.0, power))
                : 0.0;

        motor.setPower(safePower);
    }
}

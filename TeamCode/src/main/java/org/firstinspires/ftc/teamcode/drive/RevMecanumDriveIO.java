package org.firstinspires.ftc.teamcode.drive;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.config.RobotConfig;
import org.firstinspires.ftc.teamcode.util.MathUtil;

/** REV adapter: this is where physical motor polarity belongs, never in mecanum mixing. */
public final class RevMecanumDriveIO implements DriveIO {
    private final DcMotorEx frontLeft;
    private final DcMotorEx frontRight;
    private final DcMotorEx rearLeft;
    private final DcMotorEx rearRight;

    public RevMecanumDriveIO(HardwareMap hardwareMap) {
        this(hardwareMap, RobotConfig.Drive.FRONT_LEFT_NAME, RobotConfig.Drive.FRONT_RIGHT_NAME,
                RobotConfig.Drive.REAR_LEFT_NAME, RobotConfig.Drive.REAR_RIGHT_NAME,
                RobotConfig.Drive.FRONT_LEFT_DIRECTION, RobotConfig.Drive.FRONT_RIGHT_DIRECTION,
                RobotConfig.Drive.REAR_LEFT_DIRECTION, RobotConfig.Drive.REAR_RIGHT_DIRECTION,
                RobotConfig.Drive.USE_ENCODERS);
    }

    public RevMecanumDriveIO(HardwareMap hardwareMap, String frontLeftName, String frontRightName,
                             String rearLeftName, String rearRightName, DcMotor.Direction frontLeftDirection,
                             DcMotor.Direction frontRightDirection, DcMotor.Direction rearLeftDirection,
                             DcMotor.Direction rearRightDirection, boolean useEncoders) {
        frontLeft = hardwareMap.get(DcMotorEx.class, frontLeftName);
        frontRight = hardwareMap.get(DcMotorEx.class, frontRightName);
        rearLeft = hardwareMap.get(DcMotorEx.class, rearLeftName);
        rearRight = hardwareMap.get(DcMotorEx.class, rearRightName);
        configure(frontLeft, frontLeftDirection, useEncoders);
        configure(frontRight, frontRightDirection, useEncoders);
        configure(rearLeft, rearLeftDirection, useEncoders);
        configure(rearRight, rearRightDirection, useEncoders);
    }

    private static void configure(DcMotorEx motor, DcMotor.Direction direction, boolean useEncoders) {
        motor.setDirection(direction);
        motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motor.setMode(useEncoders ? DcMotor.RunMode.RUN_USING_ENCODER : DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    @Override
    public void setWheelSpeeds(WheelSpeeds wheelSpeeds) {
        frontLeft.setPower(MathUtil.clamp(wheelSpeeds.frontLeft, -1.0, 1.0));
        frontRight.setPower(MathUtil.clamp(wheelSpeeds.frontRight, -1.0, 1.0));
        rearLeft.setPower(MathUtil.clamp(wheelSpeeds.rearLeft, -1.0, 1.0));
        rearRight.setPower(MathUtil.clamp(wheelSpeeds.rearRight, -1.0, 1.0));
    }

    @Override
    public void stop() {
        setWheelSpeeds(WheelSpeeds.STOP);
    }
}

package org.firstinspires.ftc.teamcode.control;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.config.RobotConfig;

/** Universal FTC IMU implementation configured once from RobotConfig. */
public final class RevImuHeadingSource implements HeadingSource {
    private final IMU imu;

    public RevImuHeadingSource(HardwareMap hardwareMap) {
        this(hardwareMap, RobotConfig.Imu.NAME, new RevHubOrientationOnRobot(
                RobotConfig.Imu.LOGO_DIRECTION, RobotConfig.Imu.USB_DIRECTION));
    }

    public RevImuHeadingSource(HardwareMap hardwareMap, String name, RevHubOrientationOnRobot orientation) {
        imu = hardwareMap.get(IMU.class, name);
        imu.initialize(new IMU.Parameters(orientation));
    }

    @Override
    public double getHeadingRadians() {
        return imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);
    }

    @Override
    public void zeroHeading() {
        imu.resetYaw();
    }
}

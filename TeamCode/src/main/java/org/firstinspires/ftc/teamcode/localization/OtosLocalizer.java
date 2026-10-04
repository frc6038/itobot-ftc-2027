package org.firstinspires.ftc.teamcode.localization;

import com.qualcomm.hardware.sparkfun.SparkFunOTOS;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.config.RobotConfig;

/** Native FTC SparkFun OTOS adapter. It intentionally performs no vision fusion. */
public final class OtosLocalizer implements Localizer {
    private final SparkFunOTOS otos;

    public OtosLocalizer(HardwareMap hardwareMap) {
        this(hardwareMap, RobotConfig.Otos.NAME);
    }

    public OtosLocalizer(HardwareMap hardwareMap, String deviceName) {
        otos = hardwareMap.get(SparkFunOTOS.class, deviceName);
        otos.setLinearUnit(DistanceUnit.METER);
        otos.setAngularUnit(AngleUnit.RADIANS);
        otos.setOffset(new SparkFunOTOS.Pose2D(RobotConfig.Otos.OFFSET_X_METERS,
                RobotConfig.Otos.OFFSET_Y_METERS, RobotConfig.Otos.OFFSET_HEADING_RADIANS));
        otos.setLinearScalar(RobotConfig.Otos.LINEAR_SCALAR);
        otos.setAngularScalar(RobotConfig.Otos.ANGULAR_SCALAR);
        otos.resetTracking();
    }

    @Override
    public Pose2d getPose() {
        SparkFunOTOS.Pose2D pose = otos.getPosition();
        return new Pose2d(pose.x, pose.y, pose.h);
    }

    @Override
    public void setPose(Pose2d pose) {
        otos.setPosition(new SparkFunOTOS.Pose2D(pose.xMeters, pose.yMeters, pose.headingRadians));
    }
}

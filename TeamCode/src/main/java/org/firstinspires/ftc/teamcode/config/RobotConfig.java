package org.firstinspires.ftc.teamcode.config;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.DcMotor;

/** One deliberately visible place for robot-specific names, polarity, mounting, and tuning. */
public final class RobotConfig {
    private RobotConfig() {
    }

    public static final class Drive {
        public static final String FRONT_LEFT_NAME = "frontLeft";
        public static final String FRONT_RIGHT_NAME = "frontRight";
        public static final String REAR_LEFT_NAME = "rearLeft";
        public static final String REAR_RIGHT_NAME = "rearRight";
        // Verify these with Drive Motor Direction Diagnostic before the first powered drive test.
        public static final DcMotor.Direction FRONT_LEFT_DIRECTION = DcMotor.Direction.FORWARD;
        public static final DcMotor.Direction FRONT_RIGHT_DIRECTION = DcMotor.Direction.REVERSE;
        public static final DcMotor.Direction REAR_LEFT_DIRECTION = DcMotor.Direction.FORWARD;
        public static final DcMotor.Direction REAR_RIGHT_DIRECTION = DcMotor.Direction.REVERSE;
        public static final boolean USE_ENCODERS = true;
        public static final double INPUT_DEADBAND = 0.05;
        public static final double CUBIC_WEIGHT = 0.55;
        public static final double PRECISION_SCALE = 0.35;
        public static final double MANUAL_ROTATION_THRESHOLD = 0.04;
    }

    public static final class Imu {
        public static final String NAME = "imu";
        public static final RevHubOrientationOnRobot.LogoFacingDirection LOGO_DIRECTION =
                RevHubOrientationOnRobot.LogoFacingDirection.UP;
        public static final RevHubOrientationOnRobot.UsbFacingDirection USB_DIRECTION =
                RevHubOrientationOnRobot.UsbFacingDirection.FORWARD;
    }

    public static final class Heading {
        // Equivalent to the former 0.02/degree baseline; tune only after physical testing.
        public static final double KP = 0.02 * 180.0 / Math.PI;
        public static final double MAX_CORRECTION = 0.30;
    }

    public static final class Otos {
        public static final String NAME = "otos";
        public static final double OFFSET_X_METERS = 0.0;
        public static final double OFFSET_Y_METERS = 0.0;
        public static final double OFFSET_HEADING_RADIANS = 0.0;
        public static final double LINEAR_SCALAR = 1.0;
        public static final double ANGULAR_SCALAR = 1.0;
    }

    public static final class Vision {
        public static final String CAMERA_NAME = "Webcam 1";
    }

    public static final class Safety {
        public static final String FRONT_LEFT_TOF_NAME = "frontLeftTof";
        public static final String FRONT_RIGHT_TOF_NAME = "frontRightTof";
        // Placeholders requiring field validation; values are intentionally visible rather than hidden.
        public static final double HARD_STOP_METERS = 0.18;
        public static final double SLOWDOWN_START_METERS = 0.55;
    }
}

# Architecture

The platform uses pragmatic subsystem ports and adapters. Inputs create a `DriveIntent`; shaping, optional field-centric conversion, heading hold, and explicitly enabled assists modify it; `MecanumDrive` converts it to `WheelSpeeds`; only `DriveIO` writes motors.

`MecanumKinematics` is pure math. `RevMecanumDriveIO` owns motor direction and zero-power configuration, so a motor-polarity fix cannot silently change drivetrain math. `HeadingSource`, `Localizer`, `VisionSystem`, and `DistanceSensorIO` isolate FTC hardware APIs. `RobotState` publishes immutable snapshots for reads instead of becoming a mutable global controller.

OTOS supplies the V1 continuous pose. BIOBUZZ AprilTags are moving game elements: vision exports range/bearing/yaw for alignment only and is never used as an absolute field-localization source. Road Runner remains a future `MotionPlanner` adapter, not a dependency.

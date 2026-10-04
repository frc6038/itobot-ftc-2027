# Hardware and calibration

Hardware names and provisional defaults are in `RobotConfig`. Configure matching names in the Robot Controller: `frontLeft`, `frontRight`, `rearLeft`, `rearRight`, `imu`, `otos`, and `Webcam 1`; the two optional ToF names are `frontLeftTof` and `frontRightTof`.

Before first drivetrain operation:

1. Put the robot on blocks and run **ITOBOT Diag: Drive Motor Direction**. Confirm and, if needed, change only the four motor directions in `RobotConfig.Drive`.
2. Run **Drive Bring-Up** at its capped power and verify forward, strafe, and rotation.
3. Set the actual Control Hub logo/USB orientation in `RobotConfig.Imu`, then validate positive CCW heading in **IMU**.
4. Measure OTOS center offset, calibrate angular then linear scalar, and put values in `RobotConfig.Otos`. Check the result with **OTOS**.
5. Set the camera position in its FTC configuration; use **Vision** to validate target range/bearing/yaw. Vision does not localize from BIOBUZZ tags.
6. Measure conservative stop/slowdown distances for the installed ToF sensors before enabling `CollisionGuard` in a competition subsystem.

Motor model, gear ratio, wheel diameter, OTOS offset/scalars, hub orientation, camera mounting, ToF presence, and control tuning are intentionally calibration items, not assumptions in core logic.

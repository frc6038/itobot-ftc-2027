package org.firstinspires.ftc.teamcode.drive;

/** Converts intents to wheel demands and delegates physical output to a hardware adapter. */
public final class MecanumDrive {
    private final DriveIO io;
    private final MecanumKinematics kinematics;

    public MecanumDrive(DriveIO io) {
        this(io, new MecanumKinematics());
    }

    MecanumDrive(DriveIO io, MecanumKinematics kinematics) {
        this.io = io;
        this.kinematics = kinematics;
    }

    public void drive(DriveIntent intent) {
        io.setWheelSpeeds(kinematics.toWheelSpeeds(intent));
    }

    public void stop() {
        io.stop();
    }
}

package org.firstinspires.ftc.teamcode.mechanisms.intake;

import java.util.Objects;

public final class IntakeSubsystem {
    private final IntakeIO io;
    private final double intakePower;
    private final double outtakePower;
    private final double deadband;

    private double commandedPower;

    public IntakeSubsystem(
            IntakeIO io,
            double intakePower,
            double outtakePower,
            double deadband
    ) {
        this.io = Objects.requireNonNull(io);
        this.intakePower = validatePower(intakePower);
        this.outtakePower = validatePower(outtakePower);

        if (!Double.isFinite(deadband)
                || deadband < 0.0 || deadband >= 1.0) {
            throw new IllegalArgumentException("Invalid deadband");
        }

        this.deadband = deadband;
        stop();
    }

    public void update(double intakeTrigger, double outtakeTrigger) {
        if (!Double.isFinite(intakeTrigger)
                || !Double.isFinite(outtakeTrigger)) {
            stop();
            return;
        }

        boolean intake = intakeTrigger > deadband;
        boolean outtake = outtakeTrigger > deadband;

        if (intake == outtake) {
            stop();
        } else if (intake) {
            applyPower(intakePower);
        } else {
            applyPower(-outtakePower);
        }
    }

    public void stop() {
        applyPower(0.0);
    }

    public double getCommandedPower() {
        return commandedPower;
    }

    private void applyPower(double power) {
        io.setPower(power);
        commandedPower = power;
    }

    private static double validatePower(double power) {
        if (!Double.isFinite(power) || power < 0.0) {
            throw new IllegalArgumentException("Invalid motor power");
        }
        return Math.min(1.0, power);
    }
}

package org.firstinspires.ftc.teamcode.mechanisms.intake;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public final class IntakeSubsystemTest {
    private static final double EPSILON = 1e-9;

    private static final class FakeIntakeIO implements IntakeIO {
        double power;

        @Override
        public void setPower(double power) {
            this.power = power;
        }
    }

    private final FakeIntakeIO io = new FakeIntakeIO();

    private final IntakeSubsystem intake =
            new IntakeSubsystem(io, 0.25, 0.25, 0.10);

    @Test
    public void initializationStopsMotor() {
        assertPower(0.0);
    }

    @Test
    public void rightTriggerRunsIntake() {
        intake.update(1.0, 0.0);
        assertPower(0.25);
    }

    @Test
    public void leftTriggerRunsOuttake() {
        intake.update(0.0, 1.0);
        assertPower(-0.25);
    }

    @Test
    public void releasingTriggersStopsMotor() {
        intake.update(1.0, 0.0);
        intake.update(0.0, 0.0);
        assertPower(0.0);
    }

    @Test
    public void simultaneousTriggersStopMotor() {
        intake.update(1.0, 0.0);
        intake.update(1.0, 1.0);
        assertPower(0.0);
    }

    @Test
    public void deadbandPreventsMovement() {
        intake.update(0.09, 0.09);
        assertPower(0.0);
    }

    @Test
    public void invalidInputsStopMotor() {
        intake.update(1.0, 0.0);
        intake.update(Double.NaN, 0.0);
        assertPower(0.0);

        intake.update(1.0, 0.0);
        intake.update(1.0, Double.POSITIVE_INFINITY);
        assertPower(0.0);
    }

    @Test
    public void powerIsClampedAndStopWorks() {
        IntakeSubsystem limited =
                new IntakeSubsystem(io, 2.0, 2.0, 0.10);

        limited.update(1.0, 0.0);
        assertEquals(1.0, io.power, EPSILON);

        limited.update(0.0, 1.0);
        assertEquals(-1.0, io.power, EPSILON);

        limited.stop();
        assertEquals(0.0, io.power, EPSILON);
    }

    private void assertPower(double expected) {
        assertEquals(expected, io.power, EPSILON);
        assertEquals(
                expected,
                intake.getCommandedPower(),
                EPSILON
        );
    }
}

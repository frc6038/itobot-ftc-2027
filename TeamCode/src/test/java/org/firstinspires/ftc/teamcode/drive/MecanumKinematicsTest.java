package org.firstinspires.ftc.teamcode.drive;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MecanumKinematicsTest {
    private static final double EPSILON = 1e-9;
    private final MecanumKinematics kinematics = new MecanumKinematics();

    @Test public void forwardReverseStrafeAndRotateMatchContract() {
        assertWheels(1, 1, 1, 1, kinematics.toWheelSpeeds(new DriveIntent(0, 1, 0)));
        assertWheels(-1, -1, -1, -1, kinematics.toWheelSpeeds(new DriveIntent(0, -1, 0)));
        assertWheels(1, -1, -1, 1, kinematics.toWheelSpeeds(new DriveIntent(1, 0, 0)));
        assertWheels(-1, 1, 1, -1, kinematics.toWheelSpeeds(new DriveIntent(-1, 0, 0)));
        assertWheels(1, -1, 1, -1, kinematics.toWheelSpeeds(new DriveIntent(0, 0, 1)));
        assertWheels(-1, 1, -1, 1, kinematics.toWheelSpeeds(new DriveIntent(0, 0, -1)));
    }

    @Test public void normalizesWithoutChangingRatios() {
        assertWheels(1.0, -1.0 / 3.0, 1.0 / 3.0, 1.0 / 3.0,
                kinematics.toWheelSpeeds(new DriveIntent(1, 1, 1)));
    }

    @Test public void matchesKnownGoodMebFormulaAcrossMixedInputs() {
        double[][] inputs = {{.2, .7, -.1}, {-.5, .3, .8}, {.85, -.4, -.3}, {-.9, -.9, .2}, {0, .4, -.9}};
        for (double[] input : inputs) {
            double x = input[0], y = input[1], z = input[2];
            double fl = y + x + z, fr = y - x - z, rl = y - x + z, rr = y + x - z;
            double max = Math.max(1.0, Math.max(Math.abs(fl), Math.max(Math.abs(fr), Math.max(Math.abs(rl), Math.abs(rr)))));
            assertWheels(fl / max, fr / max, rl / max, rr / max, kinematics.toWheelSpeeds(new DriveIntent(x, y, z)));
        }
    }

    private static void assertWheels(double fl, double fr, double rl, double rr, WheelSpeeds actual) {
        assertEquals(fl, actual.frontLeft, EPSILON); assertEquals(fr, actual.frontRight, EPSILON);
        assertEquals(rl, actual.rearLeft, EPSILON); assertEquals(rr, actual.rearRight, EPSILON);
    }
}

package org.firstinspires.ftc.teamcode.drive;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MecanumKinematicsTest {
    private static final double EPSILON = 1e-9;
    private final MecanumKinematics kinematics = new MecanumKinematics();

    @Test
    public void cardinalCommandsMatchMebContract() {
        // Independent expected values: FL=f+s+r, FR=f-s-r, RL=f-s+r, RR=f+s-r
        assertWheels(1, 1, 1, 1, kinematics.toWheelSpeeds(new DriveIntent(0, 1, 0)));
        assertWheels(-1, -1, -1, -1, kinematics.toWheelSpeeds(new DriveIntent(0, -1, 0)));
        assertWheels(1, -1, -1, 1, kinematics.toWheelSpeeds(new DriveIntent(1, 0, 0)));
        assertWheels(-1, 1, 1, -1, kinematics.toWheelSpeeds(new DriveIntent(-1, 0, 0)));
        assertWheels(1, -1, 1, -1, kinematics.toWheelSpeeds(new DriveIntent(0, 0, 1)));
        assertWheels(-1, 1, -1, 1, kinematics.toWheelSpeeds(new DriveIntent(0, 0, -1)));
    }

    @Test
    public void normalizesCombinedTranslationAndRotation() {
        // s=1,f=1,r=1 → raw FL=3, FR=-1, RL=1, RR=1 → /3
        assertWheels(1.0, -1.0 / 3.0, 1.0 / 3.0, 1.0 / 3.0,
                kinematics.toWheelSpeeds(new DriveIntent(1, 1, 1)));
    }

    @Test
    public void mixedInputsUseHandComputedExpectations() {
        // Table of (strafe, forward, rotation) → expected wheels after normalization.
        // Values precomputed independently of production code.
        Object[][] cases = {
                // s,f,r → FL,FR,RL,RR after /max(1, abs(...))
                {0.2, 0.7, -0.1, 0.8, 0.6, 0.4, 1.0},
                {-0.5, 0.3, 0.8, 0.6 / 1.6, 0.0, 1.0, -1.0 / 1.6},
                {0.85, -0.4, -0.3, 0.15 / 1.55, -0.95 / 1.55, -1.0, 0.75 / 1.55},
                {-0.9, -0.9, 0.2, -0.8, -0.1, 0.1, -1.0},
                {0.0, 0.4, -0.9, -0.5 / 1.3, 1.0, -0.5 / 1.3, 1.0},
        };
        for (Object[] row : cases) {
            double strafe = (Double) row[0];
            double forward = (Double) row[1];
            double rotation = (Double) row[2];
            assertWheels((Double) row[3], (Double) row[4], (Double) row[5], (Double) row[6],
                    kinematics.toWheelSpeeds(new DriveIntent(strafe, forward, rotation)));
        }
    }

    @Test
    public void doesNotEmbedRightMotorInversion() {
        // Pure forward must keep left/right mathematical signs identical; polarity is DriveIO-only.
        WheelSpeeds speeds = kinematics.toWheelSpeeds(new DriveIntent(0, 0.5, 0));
        assertEquals(speeds.frontLeft, speeds.frontRight, EPSILON);
        assertEquals(speeds.rearLeft, speeds.rearRight, EPSILON);
        assertEquals(speeds.frontLeft, speeds.rearLeft, EPSILON);
    }

    private static void assertWheels(double fl, double fr, double rl, double rr, WheelSpeeds actual) {
        assertEquals(fl, actual.frontLeft, EPSILON);
        assertEquals(fr, actual.frontRight, EPSILON);
        assertEquals(rl, actual.rearLeft, EPSILON);
        assertEquals(rr, actual.rearRight, EPSILON);
    }
}

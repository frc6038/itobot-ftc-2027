package org.firstinspires.ftc.teamcode.control;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HeadingControllerTest {
    @Test
    public void correctionSignFollowsTargetMinusCurrent() {
        HeadingController controller = new HeadingController(1, 1);
        controller.setTargetHeadingRadians(0.5);
        assertEquals(0.3, controller.calculate(0.2), 1e-9);
    }

    @Test
    public void clampsOutput() {
        HeadingController controller = new HeadingController(5, 0.3);
        controller.setTargetHeadingRadians(1);
        assertEquals(0.3, controller.calculate(0), 1e-9);
        assertEquals(-0.3, controller.calculate(2), 1e-9);
    }

    @Test
    public void usesShortestWrappedError() {
        HeadingController controller = new HeadingController(1, 1);
        controller.setTargetHeadingRadians(-Math.PI + 0.1);
        assertEquals(0.2, controller.calculate(Math.PI - 0.1), 1e-9);
    }

    @Test
    public void matchesDegreeBaselineGainConversion() {
        // Former baseline: 0.02 per degree, clamp +/-0.30. KP stored as per-radian.
        double kP = 0.02 * 180.0 / Math.PI;
        HeadingController controller = new HeadingController(kP, 0.30);
        controller.setTargetHeadingRadians(Math.toRadians(10.0));
        assertEquals(0.20, controller.calculate(0.0), 1e-9);
    }
}

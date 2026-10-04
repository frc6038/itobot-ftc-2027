package org.firstinspires.ftc.teamcode.control;

import org.firstinspires.ftc.teamcode.vision.TargetObservation;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AutoAlignControllerTest {
    // desiredRange=1m, rangeKp=1, bearingKp=2, yawKp=3, max=0.4
    private final AutoAlignController controller = new AutoAlignController(1.0, 1.0, 2.0, 3.0, 0.4);

    @Test
    public void rangeErrorDrivesTowardFarTarget() {
        // (2 - 1) * 1 = 1 → clamped to 0.4 forward
        assertEquals(0.4, controller.calculate(target(2.0, 0.0, 0.0)).forward, 1e-9);
        assertEquals(0.0, controller.calculate(target(2.0, 0.0, 0.0)).strafe, 1e-9);
        assertEquals(0.0, controller.calculate(target(2.0, 0.0, 0.0)).rotation, 1e-9);
    }

    @Test
    public void tooCloseReverses() {
        // (0.5 - 1) * 1 = -0.5 → clamped magnitude stays -0.4 max... abs 0.5 > 0.4 → -0.4
        assertEquals(-0.4, controller.calculate(target(0.5, 0.0, 0.0)).forward, 1e-9);
    }

    @Test
    public void positiveBearingRequestsClockwiseTurn() {
        // Tag to the right → CW = negative CCW rotation: -0.1 * 2 = -0.2
        assertEquals(-0.2, controller.calculate(target(1.0, 0.1, 0.0)).rotation, 1e-9);
        assertEquals(0.0, controller.calculate(target(1.0, 0.1, 0.0)).strafe, 1e-9);
    }

    @Test
    public void positiveYawRequestsRightStrafeAndClamps() {
        // strafe = yaw * yawKp = 1 * 3 = 3 → clamp 0.4
        assertEquals(0.4, controller.calculate(target(1.0, 0.0, 1.0)).strafe, 1e-9);
        assertEquals(0.0, controller.calculate(target(1.0, 0.0, 1.0)).rotation, 1e-9);
    }

    @Test
    public void lostOrInvalidTargetFailsSafe() {
        assertEquals(0.0, controller.calculate(null).forward, 1e-9);
        assertEquals(0.0, controller.calculate(TargetObservation.NONE).forward, 1e-9);
        assertEquals(0.0, controller.calculate(target(Double.NaN, 0.0, 0.0)).forward, 1e-9);
    }

    private static TargetObservation target(double range, double bearing, double yaw) {
        return new TargetObservation(true, "target", range, bearing, yaw, 1L);
    }
}

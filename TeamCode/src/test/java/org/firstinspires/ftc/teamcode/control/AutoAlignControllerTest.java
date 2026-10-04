package org.firstinspires.ftc.teamcode.control;

import org.firstinspires.ftc.teamcode.vision.TargetObservation;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class AutoAlignControllerTest {
    private final AutoAlignController controller = new AutoAlignController(1.0, 1.0, 2.0, 3.0, .4);
    @Test public void rangeErrorDrivesTowardFarTarget() {
        assertEquals(.4, controller.calculate(target(2, 0, 0)).forward, 1e-9);
    }
    @Test public void positiveBearingRequestsRightStrafe() {
        assertEquals(.2, controller.calculate(target(1, .1, 0)).strafe, 1e-9);
    }
    @Test public void yawRequestsMatchingRotationAndClamps() {
        assertEquals(.4, controller.calculate(target(1, 0, 1)).rotation, 1e-9);
    }
    private static TargetObservation target(double range, double bearing, double yaw) {
        return new TargetObservation(true, "target", range, bearing, yaw, 1);
    }
}

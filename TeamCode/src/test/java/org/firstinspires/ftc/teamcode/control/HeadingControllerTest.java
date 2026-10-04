package org.firstinspires.ftc.teamcode.control;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class HeadingControllerTest {
    @Test public void correctionSignFollowsTargetMinusCurrent() {
        HeadingController controller = new HeadingController(1, 1);
        controller.setTargetHeadingRadians(.5);
        assertEquals(.3, controller.calculate(.2), 1e-9);
    }
    @Test public void clampsOutput() {
        HeadingController controller = new HeadingController(5, .3);
        controller.setTargetHeadingRadians(1);
        assertEquals(.3, controller.calculate(0), 1e-9);
    }
    @Test public void usesShortestWrappedError() {
        HeadingController controller = new HeadingController(1, 1);
        controller.setTargetHeadingRadians(-Math.PI + .1);
        assertEquals(.2, controller.calculate(Math.PI - .1), 1e-9);
    }
}

package org.firstinspires.ftc.teamcode.math;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class AngleMathTest {
    @Test public void wrapsRadiansAtPiBoundary() {
        assertEquals(-Math.PI + .1, AngleMath.normalizeRadians(Math.PI + .1), 1e-9);
        assertEquals(Math.PI - .1, AngleMath.normalizeRadians(-Math.PI - .1), 1e-9);
    }
    @Test public void wrapsDegreesAt360Boundary() {
        assertEquals(-10, AngleMath.normalizeDegrees(350), 1e-9);
        assertEquals(10, AngleMath.normalizeDegrees(-350), 1e-9);
    }
}

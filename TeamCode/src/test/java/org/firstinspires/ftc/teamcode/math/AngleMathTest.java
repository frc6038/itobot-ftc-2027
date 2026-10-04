package org.firstinspires.ftc.teamcode.math;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AngleMathTest {
    @Test
    public void wrapsRadiansAtPiBoundary() {
        assertEquals(-Math.PI + 0.1, AngleMath.normalizeRadians(Math.PI + 0.1), 1e-9);
        assertEquals(Math.PI - 0.1, AngleMath.normalizeRadians(-Math.PI - 0.1), 1e-9);
        assertEquals(0.0, AngleMath.normalizeRadians(2.0 * Math.PI), 1e-9);
        assertEquals(0.0, AngleMath.normalizeRadians(-2.0 * Math.PI), 1e-9);
    }

    @Test
    public void radianResultStaysInPrincipalRange() {
        double wrapped = AngleMath.normalizeRadians(Math.PI);
        assertTrue(wrapped >= -Math.PI && wrapped < Math.PI);
        assertEquals(-Math.PI, wrapped, 1e-9);
    }

    @Test
    public void wrapsDegreesAt360Boundary() {
        assertEquals(-10, AngleMath.normalizeDegrees(350), 1e-9);
        assertEquals(10, AngleMath.normalizeDegrees(-350), 1e-9);
        assertEquals(0, AngleMath.normalizeDegrees(360), 1e-9);
    }
}

package org.firstinspires.ftc.teamcode.safety;

import org.firstinspires.ftc.teamcode.drive.DriveIntent;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CollisionGuardTest {
    private final CollisionGuard guard = new CollisionGuard(0.2, 0.6);

    @Test
    public void clearPathDoesNotChangeCommand() {
        DriveIntent output = guard.apply(new DriveIntent(0.1, 0.8, -0.2), 1.0, 1.0);
        assertEquals(0.8, output.forward, 1e-9);
        assertEquals(0.1, output.strafe, 1e-9);
        assertEquals(-0.2, output.rotation, 1e-9);
    }

    @Test
    public void slowdownRegionScalesOnlyForward() {
        DriveIntent output = guard.apply(new DriveIntent(0.2, 0.8, -0.3), 0.4, 0.7);
        assertEquals(0.4, output.forward, 1e-9);
        assertEquals(0.2, output.strafe, 1e-9);
        assertEquals(-0.3, output.rotation, 1e-9);
    }

    @Test
    public void hardStopBlocksFurtherForwardMotion() {
        assertEquals(0.0, guard.apply(new DriveIntent(0, 0.8, 0), 0.19, 1.0).forward, 1e-9);
    }

    @Test
    public void reverseIsUntouched() {
        assertEquals(-0.7, guard.apply(new DriveIntent(0, -0.7, 0), 0.01, 0.01).forward, 1e-9);
    }

    @Test
    public void usesOnlyValidSensorWhenOtherIsInvalid() {
        // Right invalid (NaN): left at 0.4 → scale = (0.4-0.2)/(0.6-0.2) = 0.5 → forward 0.4
        assertEquals(0.4, guard.apply(new DriveIntent(0, 0.8, 0), 0.4, Double.NaN).forward, 1e-9);
        // Both non-finite: pass through (do not invent a stop).
        assertEquals(0.8, guard.apply(new DriveIntent(0, 0.8, 0), Double.NaN, Double.POSITIVE_INFINITY).forward, 1e-9);
    }
}

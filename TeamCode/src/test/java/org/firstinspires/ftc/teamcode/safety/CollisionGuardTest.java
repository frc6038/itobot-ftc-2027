package org.firstinspires.ftc.teamcode.safety;

import org.firstinspires.ftc.teamcode.drive.DriveIntent;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class CollisionGuardTest {
    private final CollisionGuard guard = new CollisionGuard(.2, .6);
    @Test public void clearPathDoesNotChangeCommand() {
        assertEquals(.8, guard.apply(new DriveIntent(.1, .8, -.2), 1, 1).forward, 1e-9);
    }
    @Test public void slowdownRegionScalesOnlyForward() {
        DriveIntent output = guard.apply(new DriveIntent(.2, .8, -.3), .4, .7);
        assertEquals(.4, output.forward, 1e-9); assertEquals(.2, output.strafe, 1e-9); assertEquals(-.3, output.rotation, 1e-9);
    }
    @Test public void hardStopBlocksFurtherForwardMotion() {
        assertEquals(0, guard.apply(new DriveIntent(0, .8, 0), .19, 1).forward, 1e-9);
    }
    @Test public void reverseIsUntouched() {
        assertEquals(-.7, guard.apply(new DriveIntent(0, -.7, 0), .01, .01).forward, 1e-9);
    }
}

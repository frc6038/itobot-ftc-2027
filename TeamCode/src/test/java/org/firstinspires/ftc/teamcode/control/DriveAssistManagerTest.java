package org.firstinspires.ftc.teamcode.control;

import org.firstinspires.ftc.teamcode.drive.DriveIntent;
import org.firstinspires.ftc.teamcode.safety.CollisionGuard;
import org.firstinspires.ftc.teamcode.vision.TargetObservation;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DriveAssistManagerTest {
    private final AutoAlignController autoAlign = new AutoAlignController(1.0, 1.0, 2.0, 3.0, 0.4);
    private final CollisionGuard guard = new CollisionGuard(0.2, 0.6);
    private final DriveAssistManager manager = new DriveAssistManager(autoAlign, guard, 100_000_000L);

    @Test
    public void disabledAssistLeavesDriverIntentSubjectToCollisionGuard() {
        DriveIntent output = manager.apply(new DriveIntent(0, 0.8, 0), false, TargetObservation.NONE,
                0.4, 0.4, 1_000L);
        assertEquals(0.4, output.forward, 1e-9);
    }

    @Test
    public void enabledAssistWithStaleTargetFailsSafe() {
        TargetObservation stale = new TargetObservation(true, "tag", 2.0, 0.0, 0.0, 1L);
        DriveIntent output = manager.apply(new DriveIntent(0, 0.8, 0), true, stale,
                2.0, 2.0, 200_000_000L);
        assertEquals(0.0, output.forward, 1e-9);
    }

    @Test
    public void enabledAssistWithFreshTargetUsesAutoAlign() {
        TargetObservation fresh = new TargetObservation(true, "tag", 2.0, 0.0, 0.0, 50L);
        DriveIntent output = manager.apply(new DriveIntent(0, 0.8, 0), true, fresh,
                2.0, 2.0, 60L);
        assertEquals(0.4, output.forward, 1e-9);
    }
}

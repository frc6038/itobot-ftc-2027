package org.firstinspires.ftc.teamcode.drive;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class FieldCentricTransformTest {
    @Test public void transformsFieldForwardAtCardinalHeadings() {
        assertIntent(0, 1, FieldCentricTransform.toRobotCentric(new DriveIntent(0, 1, 0), 0));
        assertIntent(1, 0, FieldCentricTransform.toRobotCentric(new DriveIntent(0, 1, 0), Math.PI / 2));
        assertIntent(-1, 0, FieldCentricTransform.toRobotCentric(new DriveIntent(0, 1, 0), -Math.PI / 2));
        assertIntent(0, -1, FieldCentricTransform.toRobotCentric(new DriveIntent(0, 1, 0), Math.PI));
    }
    private static void assertIntent(double strafe, double forward, DriveIntent actual) {
        assertEquals(strafe, actual.strafe, 1e-9); assertEquals(forward, actual.forward, 1e-9);
    }
}

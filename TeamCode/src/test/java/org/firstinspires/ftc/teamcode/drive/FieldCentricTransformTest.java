package org.firstinspires.ftc.teamcode.drive;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FieldCentricTransformTest {
    private static final double EPSILON = 1e-9;

    @Test
    public void fieldForwardAtCardinalHeadings() {
        assertIntent(0, 1, 0, FieldCentricTransform.toRobotCentric(new DriveIntent(0, 1, 0), 0));
        assertIntent(1, 0, 0, FieldCentricTransform.toRobotCentric(new DriveIntent(0, 1, 0), Math.PI / 2));
        assertIntent(-1, 0, 0, FieldCentricTransform.toRobotCentric(new DriveIntent(0, 1, 0), -Math.PI / 2));
        assertIntent(0, -1, 0, FieldCentricTransform.toRobotCentric(new DriveIntent(0, 1, 0), Math.PI));
    }

    @Test
    public void fieldStrafeRightAtCardinalHeadings() {
        assertIntent(1, 0, 0, FieldCentricTransform.toRobotCentric(new DriveIntent(1, 0, 0), 0));
        assertIntent(0, -1, 0, FieldCentricTransform.toRobotCentric(new DriveIntent(1, 0, 0), Math.PI / 2));
        assertIntent(0, 1, 0, FieldCentricTransform.toRobotCentric(new DriveIntent(1, 0, 0), -Math.PI / 2));
        assertIntent(-1, 0, 0, FieldCentricTransform.toRobotCentric(new DriveIntent(1, 0, 0), Math.PI));
    }

    @Test
    public void rotationPassesThroughUnchanged() {
        DriveIntent result = FieldCentricTransform.toRobotCentric(new DriveIntent(0.2, -0.3, 0.4), Math.PI / 3);
        assertEquals(0.4, result.rotation, EPSILON);
    }

    private static void assertIntent(double strafe, double forward, double rotation, DriveIntent actual) {
        assertEquals(strafe, actual.strafe, EPSILON);
        assertEquals(forward, actual.forward, EPSILON);
        assertEquals(rotation, actual.rotation, EPSILON);
    }
}

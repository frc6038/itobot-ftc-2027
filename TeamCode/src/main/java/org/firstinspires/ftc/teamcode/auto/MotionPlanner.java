package org.firstinspires.ftc.teamcode.auto;

import org.firstinspires.ftc.teamcode.localization.Pose2d;

/** Future adapter port, e.g. a Road Runner implementation, without coupling Platform V1 to it. */
public interface MotionPlanner {
    Action moveTo(Pose2d target);
}

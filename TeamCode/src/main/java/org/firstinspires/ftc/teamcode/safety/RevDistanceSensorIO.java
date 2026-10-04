package org.firstinspires.ftc.teamcode.safety;

import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

/** REV/FTC distance sensor adapter with a safe value for invalid readings. */
public final class RevDistanceSensorIO implements DistanceSensorIO {
    private final DistanceSensor sensor;

    public RevDistanceSensorIO(HardwareMap hardwareMap, String name) {
        sensor = hardwareMap.get(DistanceSensor.class, name);
    }

    @Override
    public double getDistanceMeters() {
        double reading = sensor.getDistance(DistanceUnit.METER);
        return Double.isFinite(reading) && reading >= 0.0 ? reading : Double.POSITIVE_INFINITY;
    }
}

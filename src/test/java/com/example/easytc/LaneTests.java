package com.example.easytc;

import com.example.easytc.domain.Lane;
import com.example.easytc.domain.Vehicle;
import com.example.easytc.exception.InvalidVehicleException;
import com.example.easytc.exception.VehicleCollisionInYellowException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class LaneTests {

    @Test
    public void testForwardToYellow_whenNullOBU_throwsInvalidOBUException() {
        Lane lane = new Lane();
        Vehicle vehicle = null;
        assertThrows(InvalidVehicleException.class, () -> {
            lane.forwardToYellow(vehicle);
        });
    }

    @Test
    public void testForwardToYellow_whenYellowAreaNotEmpty_throwsYellowCollisionException() {
        Lane lane = new Lane();
        Vehicle vehicle = Vehicle.randomVehicle();
        Assertions.assertDoesNotThrow(() -> {
            lane.forwardToYellow(vehicle);
        });

        Vehicle anotherVehicle = Vehicle.randomVehicle();

        assertThrows(VehicleCollisionInYellowException.class, () -> {
            lane.forwardToYellow(anotherVehicle);
        });

    }
}

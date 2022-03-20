package com.example.easytc.service;

import com.example.easytc.domain.Lane;
import com.example.easytc.domain.Vehicle;
import com.example.easytc.exception.InvalidVehicleException;
import com.example.easytc.exception.NoVehicleInGreenException;
import com.example.easytc.exception.NoVehicleInYellowException;
import com.example.easytc.exception.VehicleCollisionInYellowException;
import org.springframework.stereotype.Component;

@Component
public class LaneService {

    private Lane lane;

    public LaneService() {
        this.lane = new Lane();
    }

    public void onDetected(Vehicle vehicle) {
        try {
            this.lane.forwardToYellow(vehicle);
        } catch (VehicleCollisionInYellowException e) {
            e.printStackTrace();
        } catch (InvalidVehicleException e) {
            e.printStackTrace();
        }
    }

    public void onForwardInfraredTriggered() {
        try {
            this.lane.yellowForwardToGreen();
        } catch (NoVehicleInYellowException e) {
            e.printStackTrace();
        }
    }

    public void onBackwardInfraredTriggered() {
        try {
            this.lane.greenBackToYellow();
        } catch (VehicleCollisionInYellowException e) {
            e.printStackTrace();
        } catch (NoVehicleInGreenException e) {
            e.printStackTrace();
        }
    }

    public void onCoilTriggered() {
        try {
            this.lane.leaveGreen();
        } catch (NoVehicleInGreenException e) {
            e.printStackTrace();
        }
    }

    public void onCharged(Vehicle vehicle) {
        return;
    }

    public void onCharging(Vehicle vehicle) {
        return;
    }
}

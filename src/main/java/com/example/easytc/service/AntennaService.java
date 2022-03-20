package com.example.easytc.service;

import com.example.easytc.domain.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AntennaService {

    private List<Vehicle> charged = new ArrayList<>();

    private Vehicle current = null;

    private LaneService laneService;

    @Autowired
    public void setLaneService(LaneService laneService) {
        this.laneService = laneService;
    }

    public void detect() {
        Vehicle vehicle = Vehicle.randomVehicle();
        vehicle.setStatus(Vehicle.Status.UNCHARGED);
        laneService.onDetected(vehicle);
        this.current = vehicle;
    }

    public void charging(Vehicle vehicle) {
        vehicle.setStatus(Vehicle.Status.CHARGING);
        this.laneService.onCharging(vehicle);
    }

    public void charge(Vehicle vehicle) {
        vehicle.setStatus(Vehicle.Status.CHARGED);
        this.charged.add(vehicle);
        this.laneService.onCharged(vehicle);
    }
}

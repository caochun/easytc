package com.example.easytc.domain;

import com.example.easytc.exception.InvalidVehicleException;
import com.example.easytc.exception.NoVehicleInGreenException;
import com.example.easytc.exception.NoVehicleInYellowException;
import com.example.easytc.exception.VehicleCollisionInYellowException;

import java.util.ArrayDeque;
import java.util.Optional;
import java.util.UUID;

public class Lane {

    private String id = UUID.randomUUID().toString();

    private String getId() {
        return id;
    }

    private Optional<Vehicle> yellowArea = Optional.empty();
    private final ArrayDeque<Vehicle> greenArea = new ArrayDeque<>();

    public void forwardToYellow(Vehicle vehicle) throws VehicleCollisionInYellowException, InvalidVehicleException {
        if (vehicle == null) throw new InvalidVehicleException();
        if (yellowArea.isPresent())
            throw new VehicleCollisionInYellowException();
        yellowArea = Optional.of(vehicle);
    }

    public void yellowForwardToGreen() throws NoVehicleInYellowException {
        if (yellowArea.isEmpty()) {
            greenArea.offer(Vehicle.noOBUVehicle());
        } else {
            greenArea.offer(yellowArea.get());
            yellowArea = Optional.empty();
        }
    }

    public void greenBackToYellow() throws VehicleCollisionInYellowException, NoVehicleInGreenException {
        if (greenArea.isEmpty()) throw new NoVehicleInGreenException();
        if (yellowArea.isPresent()) throw new VehicleCollisionInYellowException();

        Vehicle vehicle = greenArea.pollLast();
        if (!vehicle.noOBU()) {
            yellowArea = Optional.of(greenArea.pollLast());
        }
    }

    public Vehicle leaveGreen() throws NoVehicleInGreenException {
        if (greenArea.isEmpty()) throw new NoVehicleInGreenException();
        return greenArea.pollFirst();
    }


    public boolean noChargedVehicleAtFirst() {
        return ((this.greenArea.isEmpty()) || (this.greenArea.peekFirst().noOBU()));
    }
}

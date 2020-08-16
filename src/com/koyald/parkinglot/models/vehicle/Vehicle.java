package com.koyald.parkinglot.models.vehicle;

public class Vehicle {
    String ID;
    String model;
    VehicleType vehicleType;

    public Vehicle(VehicleType vt, String ID, String model) {
        this.vehicleType = vt;
        this.ID = ID;
        this.model = model;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public VehicleType getVehicleType(){ return this.vehicleType; };

}

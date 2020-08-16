package com.koyald.parkinglot.models;

import com.koyald.parkinglot.models.lot.Lot;
import com.koyald.parkinglot.models.vehicle.Vehicle;
import com.koyald.parkinglot.models.vehicle.VehicleType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParkingLot {
    String ID;
    String address;

    private List<Area> areas;
    private Map<VehicleType, Integer> costMap;

    public ParkingLot(String ID, String address) {
        this.ID = ID;
        this.address = address;
        areas = new ArrayList<Area>();
        costMap = new HashMap<VehicleType, Integer>();
    }

    public void createArea(Area a){
        areas.add(a);
    }

    public void setCost(VehicleType vehicleType, Integer val){
        costMap.put(vehicleType, val);
    }

    public int getCost(VehicleType vehicleType){
        return costMap.get(vehicleType);
    }

    public Lot parkVehicle(Vehicle vehicle){
        Lot lot = null;
        for(Area area: areas){
            if(area.canPark(vehicle)){
                lot = area.park(vehicle);
                break;
            }
        }
        return lot;
    }

    public Lot exitVehicle(String areaID, Vehicle vehicle){
        Lot exitLot = null;
        for(Area area: areas){
            if(area.getID() == areaID){
                exitLot = area.exit(vehicle);
                break;
            }
        }
        return exitLot;
    }
}

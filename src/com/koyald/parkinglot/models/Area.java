package com.koyald.parkinglot.models;

import com.koyald.parkinglot.models.lot.Lot;
import com.koyald.parkinglot.models.lot.LotType;
import com.koyald.parkinglot.models.vehicle.Vehicle;
import com.koyald.parkinglot.models.vehicle.VehicleType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.koyald.parkinglot.models.lot.LotType.*;
import static com.koyald.parkinglot.models.vehicle.VehicleType.*;

public class Area {
    String ID;
    String position;//North South East West
    String floor;

    Map<LotType, Integer> capacity;
    List<Lot> lotList;

    public Area(String ID, String position) {
        this.ID = ID;
        this.position = position;
        this.floor = "";
        capacity = new HashMap<LotType, Integer>();
        lotList = new ArrayList<Lot>();
    }

    public void addCapacity(LotType lotType, Integer val){
        capacity.put(lotType, val);
        for(int i=0; i<val; i++){
            String listID = this.getID() + Integer.toString(i);
            lotList.add(new Lot(lotType, listID, this.ID));
        }
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public LotType getLotTypeFromVehicleType(VehicleType vehicleType){
        if(vehicleType.equals(TWOWHEELER))
            return TWOWHEELERLOT;
        else if(vehicleType.equals(HATCHBACK))
            return HATCHBACKLOT;
        else
            return SUVLOT;
    }

    public Boolean canPark(Vehicle vehicle){
        LotType lotType= getLotTypeFromVehicleType(vehicle.getVehicleType());
        return capacity.get(lotType) > 0;
    }

    public Lot park(Vehicle vehicle){
        Lot ans = null;
        VehicleType vehicleType = vehicle.getVehicleType();
        LotType lotType = getLotTypeFromVehicleType(vehicleType);

        if(!canPark(vehicle)) return ans;

        int currCapacity = capacity.get(lotType) ;
        capacity.put(lotType, capacity.get(lotType) - 1);

        for(Lot lot: lotList){
            if(lot.getLotType().equals(lotType) && lot.getStatus() == 0){
                lot.setStatus(1);
                lot.setParkedVehicleID(vehicle.getID());
                ans = lot;
                break;
            }
        }
        return ans;
    }

    public Lot exit(Vehicle vehicle){
        Lot exitLot = null;
        VehicleType vehicleType = vehicle.getVehicleType();
        LotType lotType= getLotTypeFromVehicleType(vehicle.getVehicleType());

        for(Lot lot: lotList){
            if(lot.getLotType().equals(lotType) && lot.getStatus() == 1){
                lot.setStatus(1);
                exitLot = lot;
                break;
            }
        }
        capacity.put(lotType, capacity.get(lotType) + 1);
        return exitLot;
    }
}

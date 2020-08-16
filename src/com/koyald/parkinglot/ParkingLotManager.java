package com.koyald.parkinglot;


import com.koyald.parkinglot.models.Area;
import com.koyald.parkinglot.models.ParkingLot;
import com.koyald.parkinglot.models.lot.Lot;
import com.koyald.parkinglot.models.lot.LotType;
import com.koyald.parkinglot.models.vehicle.Vehicle;
import com.koyald.parkinglot.models.vehicle.VehicleType;

import java.util.*;

// all parking lots need to have positions empty in each area
public class ParkingLotManager {
    //only have a list of parking history as get complete history is used infrequently
    //In the List simply store if a vehicle is parked and when its unparked
    Map<String, ParkingLot> parkingLots;
    Map<String, Integer> vehicleStartTimes;
    Map<String, String> vehicleArea;
    List<ParkingHistory> parkingHistoryList;

    public ParkingLotManager() {
        this.parkingLots = new HashMap<String, ParkingLot>();
        this.parkingHistoryList = new ArrayList<ParkingHistory>();
        this.vehicleArea = new HashMap<String, String>();
        this.vehicleStartTimes = new HashMap<String, Integer>();
    }

    public void createParkingLot(String ID, String area){
        parkingLots.put(ID, new ParkingLot(ID, area));
    }

    public void createArea(String parkingLotID, String areaID, String position, int tw, int hb, int suv){
        ParkingLot parkingLot = parkingLots.get(parkingLotID);
        Area area = new Area(areaID, position);
        area.addCapacity(LotType.TWOWHEELERLOT, tw);
        area.addCapacity(LotType.HATCHBACKLOT, hb);
        area.addCapacity(LotType.SUVLOT, suv);

        parkingLot.createArea(area);
    }

    public void setCost(String parkingLotID, VehicleType vehicleType, int cost){
        parkingLots.get(parkingLotID).setCost(vehicleType, cost);
    }

    public void parkVehicle(Vehicle vehicle, String parkingLotID, int starttime){
        Lot lot = parkingLots.get(parkingLotID).parkVehicle(vehicle);
        String vehicleID = vehicle.getID();

        if(lot == null) {
            System.out.println("No lot available");
            return;
        }
        System.out.println(String.format("Parking %s %s at area %s, lot id %s", vehicle.getVehicleType(), vehicle.getID(), lot.getAreaID(), lot.getID()));
        vehicleArea.put(vehicle.getID(), lot.getAreaID());
        vehicleStartTimes.put(vehicleID, starttime);
        parkingHistoryList.add(new ParkingHistory(vehicleID, "test", starttime, -1, -1));
    }

    public int exitVehicle(Vehicle vehicle, String parkingLotID, int endtime){
        ParkingLot parkingLot = parkingLots.get(parkingLotID);
        String areaID = vehicleArea.get(vehicle.getID());
        parkingLot.exitVehicle(areaID, vehicle);

        int starttime = vehicleStartTimes.get(vehicle.getID());
        int amount = (endtime - starttime) *  parkingLot.getCost(vehicle.getVehicleType());

        ParkingHistory vehicleparkingHistory = null;
        for(int i=parkingHistoryList.size()-1; i>=0; i--){
            ParkingHistory parkingHistory = parkingHistoryList.get(i);
            if(parkingHistory.getVehicleID().equalsIgnoreCase(vehicle.getID())){
                vehicleparkingHistory = parkingHistory;
                parkingHistory.setEnd(endtime);
                parkingHistory.setAmount(amount);
                break;
            }
        }

        System.out.println(String.format("%s %s exited area %s lot %s start time: %d end time: %d Amount: %d \n", vehicle.getVehicleType(), vehicle.getID(), vehicleparkingHistory.getLotID(), areaID, starttime, endtime, amount));
        return amount;
    }

    public void getCompleteHistory(Vehicle vehicle){
        String vehicleID = vehicle.getID();
        System.out.println(String.format("Printing parking history for vehicle %s", vehicle.getID()));
        for(ParkingHistory parkingHistory: parkingHistoryList){
            if(parkingHistory.getVehicleID().equalsIgnoreCase(vehicleID)){
                parkingHistory.printHistory();
            }
        }
    }
}
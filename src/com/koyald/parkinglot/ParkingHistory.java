package com.koyald.parkinglot;

import com.koyald.parkinglot.models.lot.Lot;

public class ParkingHistory {
    int start;
    int end;
    String lotID;
    int amount;
    String vehicleID;

    public ParkingHistory(String vehicleID, String lotID, int start, int end, int amount) {
        this.vehicleID = vehicleID;
        this.lotID = lotID;
        this.start = start;
        this.end = end;
        this.amount = amount;
    }

    public void printHistory(){
        if(end != -1)
            System.out.println(String.format("Parked at lotID %s from %d to %d, Total amount was %d", lotID, start, end, amount));
        else
            System.out.println(String.format("Is parked at lotID %s from %d", lotID, start));
    }
    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public String getLotID() {
        return lotID;
    }

    public void setLotID(String lotID) {
        this.lotID = lotID;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getVehicleID() {
        return vehicleID;
    }

    public void setVehicleID(String vehicleID) {
        this.vehicleID = vehicleID;
    }
}

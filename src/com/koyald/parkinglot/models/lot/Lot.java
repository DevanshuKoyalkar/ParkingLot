package com.koyald.parkinglot.models.lot;

public class Lot {
    String ID;
    String areaID;
    LotType lotType;
    int status;//0 free 1 occupied
    String parkedVehicleID;

    public Lot(LotType lotType, String ID, String areaID) {
        this.lotType = lotType;
        this.ID = ID;
        this.areaID = areaID;
        this.status = 0;
    }

    public String getID() {
        return ID;
    }

    public void setParkedVehicleID(String parkedVehicleID) {
        this.parkedVehicleID = parkedVehicleID;
    }

    public String getParkedVehicleID(){
        return this.parkedVehicleID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getAreaID() {
        return areaID;
    }

    public void setAreaID(String areaID) {
        this.areaID = areaID;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public LotType getLotType(){
        return this.lotType;
    }
}

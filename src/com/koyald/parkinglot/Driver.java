package com.koyald.parkinglot;

import com.koyald.parkinglot.models.vehicle.Vehicle;

import java.util.Scanner;

import static com.koyald.parkinglot.models.vehicle.VehicleType.*;

public class Driver {
    public static void main(String [] args) {
        ParkingLotManager manager= new ParkingLotManager();

        manager.createParkingLot("1", "Hyderabad");
        manager.createParkingLot("2", "Bangalore");

        manager.createArea("1", "H1", "North", 2, 1, 1 );
        manager.createArea("1", "B1", "East", 10 , 10 , 10);
        manager.createArea("1", "B2", "West", 10 , 10 , 10);

        manager.setCost("1", TWOWHEELER, 10);
        manager.setCost("1", HATCHBACK, 30);
        manager.setCost("1", SUV, 50);

        Vehicle v1 = new Vehicle(TWOWHEELER, "tw1", "bajaj");
        Vehicle v2 = new Vehicle(HATCHBACK, "hb1", "maruti");
        Vehicle v3 = new Vehicle(SUV, "suv1", "mahindra");

        manager.parkVehicle(v1, "1", 1);
        manager.parkVehicle(v2, "1", 2);

        manager.exitVehicle(v1, "1", 10);
        manager.exitVehicle(v2, "1", 10);

        manager.parkVehicle(v1, "1", 11);
        manager.exitVehicle(v1, "1", 21);

        manager.parkVehicle(v1, "1", 22);
        manager.getCompleteHistory(v1);
    }
}

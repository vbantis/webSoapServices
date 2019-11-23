package com.exerc.ws.tests;

import com.exerc.ws.Entities.Coordinates;
import com.exerc.ws.helpers.ConnectionUtils;
import com.exerc.ws.helpers.DistanceCalculator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class TestClass {

    public static void main(String[] args) {
        ArrayList<Coordinates> coordinates = ConnectionUtils.getGetAllPoint(); //first time called

        if (coordinates == null || coordinates.isEmpty()) { //second time called if its empty or null then hit again the DB otherwise we used the cashed object
            coordinates = ConnectionUtils.getAllrecordfromDB();
        }

        double inplat = 57.173481;
        double inlot = 34.520688;

        HashMap<Integer,Double> listOfPointDistances = new HashMap<Integer, Double>();

        for (Coordinates coordinate : coordinates) {
          DistanceCalculator.distance(inplat,inlot, Double.parseDouble(  coordinate.getLatitude()), Double.parseDouble(  coordinate.getLongitude()));

            listOfPointDistances.put(coordinate.getUserid(), DistanceCalculator.distance(inplat,inlot, Double.parseDouble(  coordinate.getLatitude()), Double.parseDouble(  coordinate.getLongitude())));
        }

        Map<Integer, Double> sortedDistances = DistanceCalculator.sortByValue(listOfPointDistances);

        //get closest distance from point
        Map.Entry<Integer, Double> entry = sortedDistances.entrySet().iterator().next();
        Integer key = entry.getKey();

        for (Coordinates coordinate : coordinates) {
            if (coordinate.getUserid() == key) {
                System.out.println("Nearest point is " + coordinate.getName());
            }
        }

        ConnectionUtils.incrementVersion(key);
    }

}

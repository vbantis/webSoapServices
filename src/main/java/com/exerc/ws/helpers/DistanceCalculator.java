package com.exerc.ws.helpers;

import java.util.*;

public class DistanceCalculator {

    public static HashMap<Integer, Double> sortByValue(HashMap<Integer, Double> hm) {
        // Create a list from elements of HashMap
        List<Map.Entry<Integer, Double>> list =
                new LinkedList<Map.Entry<Integer, Double>>(hm.entrySet());

        // Sort the list
        Collections.sort(list, new Comparator<Map.Entry<Integer, Double>>() {
            public int compare(Map.Entry<Integer, Double> o1,
                               Map.Entry<Integer, Double> o2) {
                return (o1.getValue()).compareTo(o2.getValue());
            }
        });

        // put data from sorted list to hashmap
        HashMap<Integer, Double> temp = new LinkedHashMap<Integer, Double>();
        for (Map.Entry<Integer, Double> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        return temp;
    }


    public static double distance(double inputPointLat, double inputPointlon, double dbPointLat, double dbPointLot) {
        if ((inputPointLat == dbPointLat) && (inputPointlon == dbPointLot)) {
            return 0;
        } else {
            double theta = inputPointlon - dbPointLot;
            double dist = Math.sin(Math.toRadians(inputPointLat)) * Math.sin(Math.toRadians(dbPointLat)) + Math.cos(Math.toRadians(inputPointLat)) * Math.cos(Math.toRadians(dbPointLat)) * Math.cos(Math.toRadians(theta));
            dist = Math.acos(dist);
            dist = Math.toDegrees(dist);
            dist = dist * 60 * 1.1515; //calculate distance of a point

            //convert into kilometers
            dist = dist * 1.609344; //we assume that the default for this exercise is kilometers distance
            return (dist);
        }
    }
}

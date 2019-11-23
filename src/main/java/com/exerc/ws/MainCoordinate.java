package com.exerc.ws;

import com.exerc.ws.Entities.Coordinates;
import com.exerc.ws.helpers.ConnectionUtils;
import com.exerc.ws.helpers.DistanceCalculator;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


@WebService
public class MainCoordinate {


	@WebMethod(operationName="getPointsWithMaxCounter")
	public ArrayList<Coordinates> getMaxCounter(int counter) {
		 return  ConnectionUtils.getAllRecordsWithCounter(counter);
	}

	@WebMethod(operationName="insertCoordinates")
	public void getCoordinates() {
		ConnectionUtils.inserttoDB();
	}

	@WebMethod(operationName="ShowPoints")
	public String getPoints(double inplat, double inplot) {
		ArrayList<Coordinates> coordinates = ConnectionUtils.getGetAllPoint(); //first time called

		if (coordinates == null || coordinates.isEmpty()) { //second time called if its empty or null then hit again the DB otherwise we used the cashed object
			coordinates = ConnectionUtils.getAllrecordfromDB();
		}

		HashMap<Integer,Double> listOfPointDistances = new HashMap<Integer, Double>();

		for (Coordinates coordinate : coordinates) {
			DistanceCalculator.distance(inplat,inplot, Double.parseDouble(  coordinate.getLatitude()), Double.parseDouble(  coordinate.getLongitude()));
			listOfPointDistances.put(coordinate.getUserid(), DistanceCalculator.distance(inplat,inplot, Double.parseDouble(  coordinate.getLatitude()), Double.parseDouble(  coordinate.getLongitude())));
		}

		Map<Integer, Double> sortedDistances = DistanceCalculator.sortByValue(listOfPointDistances);

		//get closest distance from point
		Map.Entry<Integer, Double> entry = sortedDistances.entrySet().iterator().next();
		Integer key = entry.getKey();

		for (Coordinates coordinate : coordinates) {
			if (coordinate.getUserid() == key) {
				System.out.println("Nearest point is " + coordinate.getName());

				ConnectionUtils.incrementVersion(key);

				return coordinate.getName();
			}
		}

		return  null;
	}
 
}
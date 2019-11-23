package com.exerc.ws.helpers;

import com.exerc.ws.Entities.Coordinates;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.util.ArrayList;
import java.util.List;

public class ConnectionUtils {

    static Coordinates coordinates;
    static Session sessionObj;
    static Session sessionObjtemp;
    static SessionFactory sessionFactoryObj;
    static ArrayList<Coordinates> getAllPoint = new ArrayList<Coordinates>();

    private static SessionFactory buildSessionFactory() {
        // Creating Configuration Instance & Passing Hibernate Configuration File
        Configuration configObj = new Configuration();

        configObj.configure("hibernate.cfg.xml");

        ServiceRegistry serviceRegistryObj = new StandardServiceRegistryBuilder().applySettings(configObj.getProperties()).build();

        // Creating Hibernate SessionFactory Instance
        sessionFactoryObj = configObj.buildSessionFactory(serviceRegistryObj);
        return sessionFactoryObj;
    }

    public static void inserttoDB() {

        try {
            sessionObj = buildSessionFactory().openSession();
            sessionObj.beginTransaction();

                //data filling to database with test points
                coordinates = new Coordinates(2001, "9.343382", "3.597790", 0, "Africa");
                sessionObj.save(coordinates);
                coordinates = new Coordinates(2002, "37.772886", "33.642166", 0, "Turkey");
                sessionObj.save(coordinates);
                coordinates = new Coordinates(2003, "37.772886", "22.045676", 0, "Greece");
                sessionObj.save(coordinates);
                coordinates = new Coordinates(2004, "22.006721", "74.932699", 0, "India");
                sessionObj.save(coordinates);
                coordinates = new Coordinates(2005, "37.772886", "59.997825", 0, "Tumerkinstan");
                sessionObj.save(coordinates);
                coordinates = new Coordinates(2006, "41.164182", "13.611865", 0, "Italy");
                sessionObj.save(coordinates);
                coordinates = new Coordinates(2007, "38.739088", "-3.782871", 0, "Spain");
                sessionObj.save(coordinates);
                coordinates = new Coordinates(2008, "51.112144", "10.449186", 0, "Germany");
                sessionObj.save(coordinates);
                coordinates = new Coordinates(2009, "52.631396", "-0.795896", 0, "UK");
                sessionObj.save(coordinates);
                coordinates = new Coordinates(2010, "44.639346", "2.542488", 0, "France");
                sessionObj.save(coordinates);

            System.out.println("\n.......Records Saved Successfully To The Database.......\n");

            // Committing The Transactions To The Database
            sessionObj.getTransaction().commit();
        } catch (Exception sqlException) {
            if (null != sessionObj.getTransaction()) {
                System.out.println("\n.......Transaction Is Being Rolled Back.......");
                sessionObj.getTransaction().rollback();
            }
            sqlException.printStackTrace();
        } finally {
            if (sessionObj != null) {
                sessionObj.close();
            }
        }
    }

    public static ArrayList<Coordinates> getGetAllPoint() {
        return getAllPoint;
    }

    public static void incrementVersion(int id) {
        sessionObj = buildSessionFactory().openSession();
        sessionObj.beginTransaction();

        sessionObjtemp = buildSessionFactory().openSession();
        sessionObjtemp.beginTransaction();

        List<Coordinates> coordinatesList = new ArrayList<Coordinates>();
        List list = sessionObj.createQuery("SELECT c FROM Coordinates c WHERE c.id =:k").setParameter("k",id).list();
        for (Object p : list) {
            Coordinates coordinates = (Coordinates) p;
            coordinatesList.add(coordinates);
            // System.out.println(coordinates.getFirstName());
        }


        int ver =   coordinatesList.get(0).getCounter() + 1;
        coordinatesList.get(0).setCounter(ver);

        sessionObj.persist(coordinatesList.get(0));
        sessionObj.getTransaction().commit();

    }


    public static ArrayList<Coordinates> getAllRecordsWithCounter(int counter) {
        sessionObj = buildSessionFactory().openSession();
        sessionObj.beginTransaction();

        sessionObjtemp = buildSessionFactory().openSession();
        sessionObjtemp.beginTransaction();


        List list = sessionObj.createQuery("SELECT c FROM Coordinates c WHERE c.counter>:counter ").setParameter("counter",counter).list();

        List<Coordinates> coordinatesList = new ArrayList<Coordinates>();
        for (Object p : list) {
            Coordinates coordinates = (Coordinates) p;
            coordinatesList.add(coordinates);
            // System.out.println(coordinates.getFirstName());
        }

        //Print values from database
        for (Coordinates coordinates1 : coordinatesList) {
            System.out.println("Point id " + coordinates1.getUserid() + "Point latitude " + coordinates1.getLatitude() + " Point Longtitude " + coordinates1.getLongitude() + "Counter " + coordinates1.getCounter() + " Name " + coordinates1.getName());
        }

        getAllPoint.addAll(coordinatesList);

        return (ArrayList<Coordinates>) coordinatesList;

    }



    public static ArrayList<Coordinates> getAllrecordfromDB() {
        sessionObj = buildSessionFactory().openSession();
        sessionObj.beginTransaction();

        sessionObjtemp = buildSessionFactory().openSession();
        sessionObjtemp.beginTransaction();


        List list = sessionObj.createQuery("SELECT c FROM Coordinates c").list();

        List<Coordinates> coordinatesList = new ArrayList<Coordinates>();
        for (Object p : list) {
            Coordinates coordinates = (Coordinates) p;
            coordinatesList.add(coordinates);
            // System.out.println(coordinates.getFirstName());
        }

        //Print values from database
        for (Coordinates coordinates1 : coordinatesList) {
            System.out.println("Point id " + coordinates1.getUserid() + "Point latitude " + coordinates1.getLatitude() + " Point Longtitude " + coordinates1.getLongitude() + "Counter " + coordinates1.getCounter() + " Name " + coordinates1.getName());
        }

        getAllPoint.addAll(coordinatesList);

        return (ArrayList<Coordinates>) coordinatesList;

    }

}

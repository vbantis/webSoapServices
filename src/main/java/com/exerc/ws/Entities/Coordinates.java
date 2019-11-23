package com.exerc.ws.Entities;

import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.Random;

@Entity
@Table(name = "coordinates")
public class Coordinates {
    @Id
    @Column(name = "id")
    private int userid;

    @Column(name = "latitude")
    private String latitude;
    @Column(name = "longtitude")
    private String longitude;
    @Column(name = "counter")
    private Integer counter;
    @Column(name = "name")
    private String name;

    public Coordinates(Integer id, String latitude, String longitude, Integer counter, String name) {
        this.userid = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.counter = counter;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Coordinates{" +
                "userid=" + userid +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", counter=" + counter +
                ", name='" + name + '\'' +
                '}';
    }

    public Coordinates() {
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public Integer getCounter() {
        return counter;
    }

    public void setCounter(Integer counter) {
        this.counter = counter;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}

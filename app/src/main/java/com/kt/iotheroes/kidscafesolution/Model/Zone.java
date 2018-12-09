package com.kt.iotheroes.kidscafesolution.Model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by mijeong on 2018. 12. 7..
 */

public class Zone implements Serializable{
    class Coordinate {
        int xCoordinate;
        int yCoordinate;
    }

    private String id;
    private String name;
    private List<Coordinate> coordinates;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Coordinate> getCoordinates() {
        return coordinates;
    }
}

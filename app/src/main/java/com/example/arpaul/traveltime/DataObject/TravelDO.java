package com.example.arpaul.traveltime.DataObject;

/**
 * Created by ARPaul on 10-03-2016.
 */
public class TravelDO {
    public int id;
    public String name;
    public FromCentral fromcentral;
    public Location location;

    public class FromCentral {
        public String car;
        public String train;
    }

    public class Location {
        public Double latitude;
        public Double longitude;
    }

    public String getName() {
        return name;
    }
}

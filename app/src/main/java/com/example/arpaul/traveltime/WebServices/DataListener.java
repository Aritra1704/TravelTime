package com.example.arpaul.traveltime.WebServices;

import com.example.arpaul.traveltime.DataObject.TravelList;

/**
 * Created by ARPaul on 11-03-2016.
 */
public interface DataListener {
    /*public void DataRetrieved(String data, int Status);*/
    public void DataRetrieved(TravelList data, int Status);
}

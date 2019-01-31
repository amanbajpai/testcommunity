package com.gdc.isfacademy.model;

import java.util.ArrayList;

/**
 * Created by ashishthakur on 28/1/19.
 */

public class HouseParentResponse extends CommonResponse {

    ArrayList<HouseList>houses;


    public ArrayList<HouseList> getHouseLists() {
        return houses;
    }

    public void setHouseLists(ArrayList<HouseList> houseLists) {
        this.houses = houseLists;
    }
}

package com.cruise.cruisebooking;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class CruiseService {
    List<CruiseData> listOfCruises = new ArrayList<>();
    int index = 0;

    public List<CruiseData> getCruiseData() {
        return listOfCruises;
    }

    public CruiseData addToCruiseData(CruiseData cruiseData) {
        cruiseData.setCruiseId(index);
        index++;
        listOfCruises.add(cruiseData);
        return cruiseData;
    }

    public CruiseData getCruiseById(int id) {
        for(CruiseData data : listOfCruises){
            if(data.getCruiseId() == id){
                return data;
            }
        }
        return null;
    }

    public CruiseData getCruiseByName(String name) {
        for(CruiseData data : listOfCruises){
            if(data.getCruiseName().equals(name)){
                return data;
            }
        }
        return null;
    }

    public List<CruiseData> deleteCruiseById(int cruiseId) {
        listOfCruises.remove(cruiseId);
        return listOfCruises;
    }

}

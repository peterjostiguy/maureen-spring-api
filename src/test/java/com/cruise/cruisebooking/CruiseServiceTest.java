package com.cruise.cruisebooking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CruiseServiceTest {
    CruiseService cruiseService;

    List<CruiseData> testData;
    @BeforeEach
    void setUp() {
        testData = new ArrayList<>();
        cruiseService = new CruiseService();
        for (int i = 0; i < 10; i++) {
            testData.add(cruiseService.addToCruiseData(new CruiseData("Cruise-"+i)));
        }

    }

    @Test
    void getAllCruiseData() {
        assertEquals(10, cruiseService.getCruiseData().size());
    }

    @Test
    void addToCruiseData() {
        cruiseService.addToCruiseData(new CruiseData("New added cruise"));
        assertEquals(11, cruiseService.getCruiseData().size());
    }

    @Test
    void getCruiseById() {
        CruiseData actual = testData.get(7);
        assertEquals(actual, cruiseService.getCruiseById(actual.getCruiseId()));
    }

    @Test
    void getCruiseByName() {
        CruiseData actual = testData.get(5);
        assertEquals(actual, cruiseService.getCruiseByName(actual.getCruiseName()));
    }

    @Test
    void deleteCruiseById() {
        cruiseService.deleteCruiseById(7);
        assertEquals(9, cruiseService.getCruiseData().size());
    }

}

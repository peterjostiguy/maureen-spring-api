package com.cruise.cruisebooking;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/cruisedata")
public class CruiseController {
    CruiseService cruiseService;

    public CruiseController(CruiseService cruiseService) {
        this.cruiseService = cruiseService;
    }

    @GetMapping
    public List<CruiseData> getMyData(){
        return cruiseService.getCruiseData();
    }

    @PostMapping
    public CruiseData addToCruiseData(@RequestBody CruiseData cruiseData){
        return cruiseService.addToCruiseData(cruiseData);
    }

    @GetMapping("/{cruiseName}")
    public CruiseData getCruiseByName(@PathVariable String cruiseName) {
        return cruiseService.getCruiseByName(cruiseName);
    }

    @DeleteMapping("/{cruiseId}")
    public List<CruiseData> deleteCruiseById(@PathVariable("cruiseId") int cruiseId) {
        return cruiseService.deleteCruiseById(cruiseId);
    }

//    @PutMapping("/cruiseId/newCruiseName")
//    public List<CruiseData> updateCruiseById(@PathVariable("cruiseId") int cruiseId) {
//        return cruiseService.updateCruiseById(cruiseId);
//    }
//



}

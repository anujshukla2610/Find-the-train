package com.anuj.Find.the.train.controller;

import com.anuj.Find.the.train.service.TrainService;
import com.anuj.Find.the.train.enitity.Train;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/train")
public class TrainController {

    private TrainService trainService;
    public TrainController(TrainService trainService)
    {
        this.trainService=trainService;
    }

    @GetMapping
    public List<Train> getAllTrains()
    {
        return trainService.getAllTrains();
    }

    @PostMapping
    public Train addTrain(@RequestBody Train train)
    {
        return trainService.addTrain(train);
    }



}
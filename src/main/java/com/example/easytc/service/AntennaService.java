package com.example.easytc.service;

import com.example.easytc.domain.OBU;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AntennaService {

    private LaneService laneService;

    @Autowired
    public void setLaneService(LaneService laneService) {
        this.laneService = laneService;
    }

    public void onDetect(OBU obu) {
        this.laneService.onDetected(obu);
    }

    public void onCharge(OBU obu){
        this.laneService.onCharged(obu);
    }

}

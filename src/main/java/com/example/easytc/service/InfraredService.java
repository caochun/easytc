package com.example.easytc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InfraredService {

    private LaneService laneService;

    @Autowired
    public void setLaneService(LaneService laneService) {
        this.laneService = laneService;
    }

    public void onTriggered(boolean forward) {
        if (forward)
            this.laneService.onForwardInfraredTriggered();
        else
            this.laneService.onBackwardInfraredTriggered();
    }
}

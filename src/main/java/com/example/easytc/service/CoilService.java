package com.example.easytc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CoilService {
    private LaneService laneService;

    @Autowired
    public void setLaneService(LaneService laneService) {
        this.laneService = laneService;
    }

    public void onTriggered() {
        this.laneService.onCoilTriggered();
    }
}

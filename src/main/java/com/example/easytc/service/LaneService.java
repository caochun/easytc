package com.example.easytc.service;

import com.example.easytc.domain.Lane;
import com.example.easytc.domain.OBU;
import com.example.easytc.exception.GreenEmptyExcpetion;
import com.example.easytc.exception.InvalidOBUException;
import com.example.easytc.exception.YellowCollisionException;
import com.example.easytc.exception.YellowEmptyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class LaneService {

    private Lane lane;

    public LaneService(){
        this.lane = new Lane();
    }

    @Autowired
    public void setLane(Lane lane) {
        this.lane = lane;
    }

    public void onDetected(OBU obu) {
        try {
            this.lane.forwardToYellow(obu);
        } catch (YellowCollisionException e) {
            e.printStackTrace();
        } catch (InvalidOBUException e) {
            e.printStackTrace();
        }

    }

    public void onForwardInfraredTriggered() {
        try {
            this.lane.yellowForwardToGreen();
        } catch (YellowEmptyException e) {
            e.printStackTrace();
        }
    }

    public void onBackwardInfraredTriggered() {
        try {
            this.lane.greenBackToYellow();
        } catch (YellowCollisionException e) {
            e.printStackTrace();
        } catch (GreenEmptyExcpetion e) {
            e.printStackTrace();
        }
    }

    public void onCoilTriggered() {
        try {
            this.lane.yellowForwardToGreen();
        } catch (YellowEmptyException e) {
            e.printStackTrace();
        }
    }

    public void onCharged(OBU obu) {

    }
}

package com.example.easytc.core;

import com.example.easytc.exception.GreenEmptyExcpetion;
import com.example.easytc.exception.InvalidOBUException;
import com.example.easytc.exception.YellowCollisionException;
import com.example.easytc.exception.YellowEmptyException;

import java.util.ArrayDeque;
import java.util.Optional;

public class Lane {

    private Optional<OBU> yellowArea = Optional.empty();
    ;
    private ArrayDeque<OBU> greenArea = new ArrayDeque<>();

    public void forwardToYellow(OBU obu) throws YellowCollisionException, InvalidOBUException {
        if (obu == null) throw new InvalidOBUException();
        if (yellowArea.isPresent())
            throw new YellowCollisionException();
        yellowArea = Optional.of(obu);
    }

    public void yellowForwardToGreen() throws YellowEmptyException {
        if (yellowArea.isEmpty()) throw new YellowEmptyException();
        greenArea.offer(yellowArea.get());
        yellowArea = Optional.empty();
    }

    public void greenBackToYellow() throws YellowCollisionException, GreenEmptyExcpetion {
        if (greenArea.isEmpty()) throw new GreenEmptyExcpetion();
        if (yellowArea.isPresent()) throw new YellowCollisionException();
        yellowArea.of(greenArea.pollLast());
    }

    public OBU leaveGreen() throws GreenEmptyExcpetion {
        if (greenArea.isEmpty()) throw new GreenEmptyExcpetion();
        return greenArea.pollFirst();
    }


}

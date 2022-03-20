package com.example.easytc.domain;

import com.example.easytc.exception.GreenEmptyExcpetion;
import com.example.easytc.exception.InvalidOBUException;
import com.example.easytc.exception.YellowCollisionException;
import com.example.easytc.exception.YellowEmptyException;

import java.util.ArrayDeque;
import java.util.Optional;
import java.util.UUID;

public class Lane {

    private String id = UUID.randomUUID().toString();

    private String getId() {
        return id;
    }

    private Optional<OBU> yellowArea = Optional.empty();
    private final ArrayDeque<OBU> greenArea = new ArrayDeque<>();

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
        yellowArea = Optional.of(greenArea.pollLast());
    }

    public OBU leaveGreen() throws GreenEmptyExcpetion {
        if (greenArea.isEmpty()) throw new GreenEmptyExcpetion();
        return greenArea.pollFirst();
    }
}

package com.example.easytc;

import com.example.easytc.domain.Lane;
import com.example.easytc.domain.OBU;
import com.example.easytc.exception.InvalidOBUException;
import com.example.easytc.exception.YellowCollisionException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class LaneTests {

    @Test
    public void testForwardToYellow_whenNullOBU_throwsInvalidOBUException() {

        Lane lane = new Lane();
        OBU obu = null;
        assertThrows(InvalidOBUException.class, () -> {
            lane.forwardToYellow(obu);
        });

    }

    @Test
    public void testForwardToYellow_whenYellowAreaNotEmpty_throwsYellowCollisionException() {

        Lane lane = new Lane();
        OBU obu = new OBU();
        Assertions.assertDoesNotThrow(()->{
            lane.forwardToYellow(obu);
        });

        OBU anotherObu = new OBU();

        assertThrows(YellowCollisionException.class, () -> {
            lane.forwardToYellow(anotherObu);
        });

    }
}

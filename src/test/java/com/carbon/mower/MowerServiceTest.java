package com.carbon.mower;

import com.carbon.mower.pojo.MowerBO;
import com.carbon.mower.pojo.OrientationType;
import com.carbon.mower.pojo.PositionBO;
import com.carbon.mower.service.MowerService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MowerServiceTest {

    //@Test
    //@DisplayName("The mower run straight ahead")
    void runMowersWithOneSimpleMower() {

        var mowerService = new MowerService();

        var lawn = new PositionBO(5, 5);
        var mowerBOs = List.of(new MowerBO(new PositionBO(1, 2), OrientationType.N, "A"));
        var mowersSolution = List.of(new MowerBO(new PositionBO(1, 3), OrientationType.N, ""));

        assertEquals(mowersSolution, mowerService.runMowers(mowerBOs, lawn));
    }

    //@Test
    //@DisplayName("The mower turn on himself at max lawn range")
    void runMowersTurnOnHimself() {

        var mowerService = new MowerService();

        var lawn = new PositionBO(5, 5);
        var mowerBOs = List.of(new MowerBO(new PositionBO(5, 5), OrientationType.N, "GGDDGDGDDGDG"));
        var mowersSolution = List.of(new MowerBO(new PositionBO(5, 5), OrientationType.N, ""));

        assertEquals(mowersSolution, mowerService.runMowers(mowerBOs, lawn));
    }

    //@Test
    //@DisplayName("The mower try to come out the field straight ahead")
    void runMowersWithOneMowerBehindMapLimit() {

        var mowerService = new MowerService();

        var lawn = new PositionBO(5, 5);
        var mowerBOs = List.of(new MowerBO(new PositionBO(1, 2), OrientationType.N, "AAAAAAAA"));
        var mowersSolution = List.of(new MowerBO(new PositionBO(1, 5), OrientationType.N, ""));

        assertEquals(mowersSolution, mowerService.runMowers(mowerBOs, lawn));
    }

    //@Test
    //@DisplayName("Two mower run the field")
    void runMowersWithTwoMower() {

        var mowerService = new MowerService();

        var lawn = new PositionBO(5, 5);
        var mowerBOs = List.of(
                new MowerBO(new PositionBO(1, 2), OrientationType.N, "GAGAGAGAA"),
                new MowerBO(new PositionBO(3, 3), OrientationType.E, "AADAADADDA")
        );
        var mowersSolution = List.of(
                new MowerBO(new PositionBO(1, 3), OrientationType.N, ""),
                new MowerBO(new PositionBO(5, 1), OrientationType.E, "")
                );

        assertEquals(mowersSolution, mowerService.runMowers(mowerBOs, lawn));
    }

}
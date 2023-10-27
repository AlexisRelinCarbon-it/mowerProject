package com.carbon.mower.service;

import com.carbon.mower.pojo.MowerBO;
import com.carbon.mower.pojo.OrientationType;
import com.carbon.mower.pojo.PositionBO;

import java.util.List;
import java.util.stream.Collectors;

public class MowerService {

    private static final String RIGHT_INSTRUCTION = "D";
    private static final String LEFT_INSTRUCTION = "G";
    private static final String MOVE_FORWARD_INSTRUCTION = "A";

    public List<MowerBO> runMowers(List<MowerBO> mowers, PositionBO lawn) {
        return mowers
                .stream()
                .map(mower -> runMower(mower, lawn))
                .collect(Collectors.toList());
    }

    private static MowerBO runMower(MowerBO mower, PositionBO lawn) {

        var position = mower.position();
        var orientation = mower.orientation();
        var instructions = mower.instructions().split("");

        for (String instruction : instructions) {
            position = getPositionIfMowerMoveForward(
                    instruction, position, orientation, lawn);

            orientation = switch (instruction) {
                case LEFT_INSTRUCTION -> orientation.getRotateLeft();
                case RIGHT_INSTRUCTION -> orientation.getRotateRight();
                default -> orientation;
            };
        }
        return new MowerBO(position, orientation, "");
    }

    private static PositionBO getPositionIfMowerMoveForward(
            String instruction, PositionBO position, OrientationType orientation, PositionBO lawn) {

        if (!instruction.equals(MOVE_FORWARD_INSTRUCTION))
            return position;

        return switch (orientation) {
            case N -> new PositionBO(
                    position.x(),
                    position.y() + 1 > lawn.y() ? position.y() : position.y() + 1);

            case S -> new PositionBO(
                    position.x(),
                    position.y() - 1 < 0 ? position.y() : position.y() - 1);

            case E -> new PositionBO(
                    position.x() + 1 > lawn.x() ? position.x() : position.x() + 1,
                    position.y());

            case W -> new PositionBO(
                    position.x() - 1 < 0 ? position.x() : position.x() - 1,
                    position.y());
        };
    }

}

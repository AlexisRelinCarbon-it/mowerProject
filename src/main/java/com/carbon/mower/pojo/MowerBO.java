package com.carbon.mower.pojo;

public record MowerBO(
        PositionBO position,
        OrientationType orientation,
        String instructions) {

    public static String toStringForFile(MowerBO mower) {
        return String.format("%d %d %s",
                mower.position.x(),
                mower.position.y(),
                mower.orientation
        );
    }
}

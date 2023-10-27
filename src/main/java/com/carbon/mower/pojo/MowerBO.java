package com.carbon.mower.pojo;

public record MowerBO(
        PositionBO position,
        OrientationType orientation,
        String instructions) {
}

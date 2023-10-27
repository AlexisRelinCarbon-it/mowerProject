package com.carbon.mower.pojo;

public enum OrientationType {
    N,
    W,
    S,
    E;

    public OrientationType getRotateLeft() {
        return switch (this) {
            case N -> OrientationType.W;
            case S -> OrientationType.E;
            case E -> OrientationType.N;
            case W -> OrientationType.S;
        };
    }

    public OrientationType getRotateRight() {
        return switch (this) {
            case N -> OrientationType.E;
            case S -> OrientationType.W;
            case E -> OrientationType.S;
            case W -> OrientationType.N;
        };
    }
}

package com.kauruck.coastEngine.core.input;

public enum MouseButton {
    Left(0),
    Right(1),
    Middle(2);

    private final int number;

    private MouseButton(int number){
        this.number = number;
    }

    public int getNumber() {
        return number;
    }
}

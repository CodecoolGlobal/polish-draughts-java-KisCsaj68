package com.codecool.polishDraughts;

public class Pawn {
    Color color;
    Coordinates coordinates;

    public Pawn(String color, int x, int y) {
        this.color = new Color(color);
        this.coordinates = new Coordinates(x, y);

    }

    public boolean isCrowned() {
        return false;
    }

    @Override
    public String toString() {
        return color.getColor().equals("white") ? "☻" : "☺";

    }

    public String getColor() {
        return this.color.getColor();
    }
}

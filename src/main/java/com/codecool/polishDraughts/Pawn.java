package com.codecool.polishDraughts;

public class Pawn {
     private Color color;
    private Coordinate coordinate;
    private boolean isCrowned;

    public Pawn(String color, int x, int y, boolean isCrowned) {
        this.color = new Color(color);
        this.coordinate = new Coordinate(x, y);
        this.isCrowned = isCrowned;

    }

    public void setCrowned(boolean crowned) {
        isCrowned = crowned;
    }



    public boolean isCrowned() {
        return this.isCrowned;
    }

    @Override
    public String toString() {
        if(!isCrowned) {
            return color.getColor().equals("white") ? "☻" : "☺";
        }
        return color.getColor().equals("white") ? "■" : "⌂";

    }

    public String getColor() {
        return this.color.getColor();
    }

    public Coordinate getCoordinates() {
        return this.coordinate;
    }
}

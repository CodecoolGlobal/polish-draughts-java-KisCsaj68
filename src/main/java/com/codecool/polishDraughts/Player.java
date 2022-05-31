package com.codecool.polishDraughts;

import java.util.Scanner;

public class Player {
    private Color color;


    public Player(String color) {
        this.color = new Color(color);
    }

    public String getColor() {
        return this.color.getColor();
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getUserInput(String question) {
        System.out.println(question);
        Scanner userInput = new Scanner(System.in);
        int move = userInput.nextInt();

        return move;
    }


}

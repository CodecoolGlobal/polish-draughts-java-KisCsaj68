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


    public Coordinate getUserInput(String question) throws InterruptPlayRound, InvalidUserInputLength{
        Coordinate result = new Coordinate(0,0);
        System.out.println(question);
        Scanner userInput = new Scanner(System.in);
        String input = userInput.next();
        if (input.equals("Q") || input.equals("q") || input.equals("QUIT") || input.equals("quit")) {
            System.exit(0);
        }
        if (input.equals("c")){
            throw new InterruptPlayRound();
        }

        if (input.length() > 3) {
            throw new InvalidUserInputLength();
        }
        char row = input.substring(0,1).toLowerCase().charAt(0);
        result.setX((int)row);
        try {
            int column = Integer.parseInt(input.substring(1));
            result.setY(column-1);
        }catch (Exception e) {
            result.setY(-1);
        }

    return result;
    }



}

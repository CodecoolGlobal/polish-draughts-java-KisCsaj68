package com.codecool.polishDraughts;

import java.io.CharConversionException;
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

    public int getUserInputRow(String question) throws Exception{
        System.out.println(question);
        Scanner userInput = new Scanner(System.in);
        String row = userInput.next().toLowerCase();
        if (row.length() != 1) {
            throw new Exception();
        }
        char result = row.charAt(0);
        return (int)result;
    }

    public int getUserInputColumn(String question) {
        System.out.println(question);
        Scanner userInput = new Scanner(System.in);
        try{
            int column = userInput.nextInt();
            return column -1;
        }
        catch (Exception e) {
            return -1;
        }
    }


}

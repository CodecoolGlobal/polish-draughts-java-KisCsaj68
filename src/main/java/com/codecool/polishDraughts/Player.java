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

    public int[] getUserInput(String question) throws Exception{
        int[] result = new int[2];
        System.out.println(question);
        Scanner userInput = new Scanner(System.in);
        String input = userInput.next();
        if (input.equals("Q") || input.equals("q") || input.equals("QUIT") || input.equals("quit")) {
            System.exit(0);
        }
        if (input.equals("c")){
            return null;
        }

        if (input.length() != 2) {
            throw new Exception();
        }
        char row = input.substring(0,1).toLowerCase().charAt(0);
        result[0] = (int)row;
        try {
            int column = Integer.parseInt(input.substring(1));
            result[1] = column - 1;
        }catch (Exception e) {
            result[1] = -1;
        }

    return result;
    }



}

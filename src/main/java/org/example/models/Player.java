package org.example.models;

import java.util.Scanner;

public class Player {

    private String name;
    private char symbol;
    private PlayerType playerType;

    public Player(String name, String symbol, PlayerType playerType) {
        this.name = name;
        this.symbol = symbol.charAt(0);
        this.playerType = playerType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public char getSymbol() {
        return symbol;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }

    public PlayerType getPlayerType() {
        return playerType;
    }

    public void setPlayerType(PlayerType playerType) {
        this.playerType = playerType;
    }

    public Move decideMove(Board board) {
        // implement decide move
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the row number : ");
        int row = sc.nextInt();
        System.out.println("Enter the column number : ");
        int column = sc.nextInt();
        return new Move(this,new Cell(row,column));
    }
}

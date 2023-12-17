package org.example;

import org.example.controllers.GameController;
import org.example.exceptions.InvalidGameException;
import org.example.models.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TicTacToeGame {

    public static void main(String[] args) throws InvalidGameException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the dimension of the board : ");
        int dimension = sc.nextInt();

        System.out.println("Do you want to have the bot player in the game ? y/n");
        String isBot = sc.next();

        List<Player> players = new ArrayList<>();

        int numberOfPlayers = dimension - 1;
        if(isBot.charAt(0) == 'y'){
            numberOfPlayers = dimension - 2;
        }

        for(int i=0;i<numberOfPlayers;i++){
            System.out.println("Enter the name of player " + (i+1) + " : ");
            String name = sc.next();
            System.out.println("What is the symbol for player " + (i+1) + " : ");
            String symbol = sc.next();
            players.add(new Player(name,symbol, PlayerType.HUMAN));
        }
        if(isBot.equals("y")){
            System.out.println("Enter the name of Bot  : ");
            String name = sc.next();
            System.out.println("What is the symbol for Bot : ");
            String symbol = sc.next();
            players.add(new Bot(name,symbol, PlayerType.BOT, BotPlayingDifficulty.EASY));
        }

        System.out.println(players);

        // start the game

        GameController gameController = new GameController();
        Game game = gameController.createGame(dimension,players);
        gameController.addPlayersToGame(players,game);

        while(gameController.getGameStatus(game).equals(GameStatus.IN_PROGRESS)){
            System.out.println("Board");
            gameController.displayBoard(game);

            System.out.println("Do you want to undo the last move ? y/n");
            String undo = sc.next();
            if(undo.charAt(0) == 'y'){
                gameController.undo();
            }else{
                gameController.executeNextMove(game);
            }


        }

        System.out.println("The game has ended");
        System.out.println("*******************************");
        if(game.getGameStatus().equals(GameStatus.ENDED)) {
            System.out.println("The winner of the game is " + game.getWinner().getName());
        }


    }
}

package org.example.controllers;

import org.example.exceptions.InvalidGameException;
import org.example.models.Game;
import org.example.models.GameStatus;
import org.example.models.Player;

import java.util.List;

public class GameController {

    //functionality of the game required to play the game

    public void undo() {
        // TODO implement undo feature
    }

    public Game createGame(int dimension, List<Player> players) throws InvalidGameException {
        Game game = Game.getBuilder()
                .setPlayers(players)
                .setDimension(dimension)
                .build();
        return game;
    }

    public void displayBoard(Game game){
        game.getBoard().displayBoard();
    }

    public GameStatus getGameStatus(Game game){
        return game.getGameStatus();
    }

    public  void executeNextMove(Game game){
        // TODO implement execute next move
        game.makeNextMove();

    }

    public void addPlayersToGame(List<Player> players,Game game){
        game.setPlayers(players);
    }
}

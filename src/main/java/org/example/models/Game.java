package org.example.models;

import org.example.exceptions.InvalidGameException;
import org.example.strategies.gameWinningStrategy.GameWinningStrategy;
import org.example.strategies.gameWinningStrategy.OrderOneWinnigStrategy;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private Board board;
    private List<Player> players;
    private GameStatus gameStatus;
    private List<Move> moves;
    private int nextPlayerIndex;
    private GameWinningStrategy gameWinningStrategy;
    private Player winner;


    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }


    public GameWinningStrategy getGameWinningStrategy() {
        return gameWinningStrategy;
    }

    public void setGameWinningStrategy(GameWinningStrategy gameWinningStrategy) {
        this.gameWinningStrategy = gameWinningStrategy;
    }



    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }

    public int getNextPlayerIndex() {
        return nextPlayerIndex;
    }

    public void setNextPlayerIndex(int nextPlayerIndex) {
        this.nextPlayerIndex = nextPlayerIndex;
    }

    public void displayBoard() {
        // implement display board
        this.displayBoard();
    }
    public void makeNextMove() {
        Player playerToMove = this.players.get(this.nextPlayerIndex);

        System.out.println("It is " + playerToMove.getName() + "'s turn to play");
        Move move = playerToMove.decideMove(this.board);
        Cell cell = move.getCell();


        // validate the move
        int row = move.getCell().getRow();
        int col = move.getCell().getCol();
        System.out.println("Player is making a move at row: " + row + " & col: " + col);

        // Game will validate the move

        cell.setPlayer(playerToMove);
        board.getBoard().get(row).get(col).setPlayer(playerToMove);
        board.getBoard().get(row).get(col).setCellState(CellState.FILLED);

        if(gameWinningStrategy.checkWinner(board,playerToMove,cell)){
            System.out.println("Player " + playerToMove.getName() + " won the game");
            this.setGameStatus(GameStatus.ENDED);
            winner = playerToMove;
            return;
        }

        nextPlayerIndex = (nextPlayerIndex+ 1) % players.size();


        // Winnig strategy
    }


    public static Builder getBuilder() {
        return new Builder();
    }



    public static class Builder {
        private int dimension;
        private List<Player> players;


        public List<Player> getPlayers() {
            return players;
        }

        public Builder setPlayers(List<Player> players) {
            this.players = players;
            return this;
        }

        public int getDimension() {
            return dimension;
        }

        public Builder setDimension(int dimension) {
            this.dimension = dimension;
            return this;
        }

        public Game build() throws InvalidGameException {
            if (!isValid()) {
                throw new InvalidGameException("Someting went wrong while creating game object");
            }

            Game game = new Game();
            game.setGameStatus(GameStatus.IN_PROGRESS);
            game.setBoard(new Board(this.dimension));
            game.setPlayers(new ArrayList<>());
            game.setMoves(new ArrayList<>());
            game.setNextPlayerIndex(0);
            game.setGameWinningStrategy(new OrderOneWinnigStrategy(dimension));
            game.setWinner(null);
            return game;
        }

        private boolean isValid() throws InvalidGameException {
            if (this.dimension < 1) {
                return false;
            }
            return true;
        }


    }


}

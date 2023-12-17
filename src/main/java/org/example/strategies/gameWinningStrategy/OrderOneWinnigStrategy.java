package org.example.strategies.gameWinningStrategy;

import org.example.models.Board;
import org.example.models.Cell;
import org.example.models.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OrderOneWinnigStrategy implements GameWinningStrategy{

    private List<HashMap<Character,Integer>> rowSymbolCounts = new ArrayList<>();
    private List<HashMap<Character,Integer>> colSymbolCounts = new ArrayList<>();

    private HashMap<Character,Integer> leftDiagonalSymbolCounts = new HashMap<>();

    private  HashMap<Character,Integer> rightDiagonalSymbolCounts = new HashMap<>();

    public OrderOneWinnigStrategy(int dimension) {
        for (int i = 0; i < dimension; i++) {
            rowSymbolCounts.add(new HashMap<>());
            colSymbolCounts.add(new HashMap<>());
        }

    }


       @Override
       public boolean checkWinner(Board board,
                                  Player player,
                                  Cell cell) {
        char symbol = cell.getPlayer().getSymbol();
        int row = cell.getRow();
        int col = cell.getCol();
        int dimension = board.getBoard().size();

            // check rows
           if (!rowSymbolCounts.get(row).containsKey(symbol)){
               rowSymbolCounts.get(row).put(symbol, 0);
           }
           rowSymbolCounts.get(row).put(symbol, rowSymbolCounts.get(row).get(symbol) + 1);
           if (!colSymbolCounts.get(col).containsKey(symbol)){
               colSymbolCounts.get(col).put(symbol, 0);
           }
           colSymbolCounts.get(col).put(symbol, colSymbolCounts.get(col).get(symbol) + 1);

           // check left diagonal
           if(row == col){
               if(!leftDiagonalSymbolCounts.containsKey(symbol)) {
                   leftDiagonalSymbolCounts.put(symbol, 0);
               }
               leftDiagonalSymbolCounts.put(symbol,leftDiagonalSymbolCounts.get(symbol)+1);
           }

           // check right diagonal
           if(row + col == dimension -1){
               if(!rightDiagonalSymbolCounts.containsKey(symbol)) {
                   rightDiagonalSymbolCounts.put(symbol, 0);
               }
               rightDiagonalSymbolCounts.put(symbol,rightDiagonalSymbolCounts.get(symbol)+1);
           }

           // check if the player has won after this move either in rows or columns
           if(rowSymbolCounts.get(row).get(symbol) == dimension ||
                   colSymbolCounts.get(col).get(symbol) == dimension){
               return true;
           }
           // check if left diagonal count for current move symbol is dimension
           if(row == col) {
                if (leftDiagonalSymbolCounts.get(symbol) == dimension) {
                     return true;
                }
           }
           // check if right diagonal count for current move symbol is dimension
           if(row + col == dimension - 1 ){
               if(rightDiagonalSymbolCounts.get(symbol) == dimension){
                   return true;
               }
           }
           return false;
   }
}

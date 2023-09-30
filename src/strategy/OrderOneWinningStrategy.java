package strategy;

import model.Board;
import model.Move;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OrderOneWinningStrategy implements GameWinningStrategy{

    List<HashMap<Character, Integer>> rowSymbolCounts = new ArrayList<>();
    List<HashMap<Character, Integer>> colSymbolCounts = new ArrayList<>();
    HashMap<Character, Integer> topLeftDiagonalSymbolCounts = new HashMap<>();
    HashMap<Character, Integer> topRightDiagonalSymbolCounts = new HashMap<>();

    public OrderOneWinningStrategy(int dimension) {
        for(int i=0; i<dimension; i++) {
            rowSymbolCounts.add(new HashMap<>());
            colSymbolCounts.add(new HashMap<>());
        }
    }

    @Override
    public boolean checkWinner(Board board, Move move) {
        char symbol = move.getPlayer().getSymbol();
        int row = move.getCell().getRow();
        int col = move.getCell().getCol();
        int dimension = board.getBoard().size();

        //Updating the row values
        if(!rowSymbolCounts.get(row).containsKey(symbol)) {
            System.out.println("Row hashmap is called...");
            rowSymbolCounts.get(row).put(symbol, 0);
        }
        // update the value by one if the same row having the character...
        rowSymbolCounts.get(row).put(symbol, rowSymbolCounts.get(row).get(symbol)+1);

        //Updating the columns values
        if(!colSymbolCounts.get(row).containsKey(symbol)) {
            System.out.println("Col hashmap is called...");
            colSymbolCounts.get(row).put(symbol, 0);
        }
        // update the value by one if the same col having the character...
        colSymbolCounts.get(row).put(symbol, colSymbolCounts.get(row).get(symbol)+1);

        //Updating the top left diagonal values
        if(row == col) {
            if(!topLeftDiagonalSymbolCounts.containsKey(symbol)) {
                System.out.println("TopLeft hashmap is called...");
                topLeftDiagonalSymbolCounts.put(symbol, 0);
            }
            // update the value by one if the same col having the character...
            topLeftDiagonalSymbolCounts.put(symbol, topLeftDiagonalSymbolCounts.get(symbol)+1);
        }

        //Updating the bottomRight values
        if(row+col == dimension-1) {
            if(!topRightDiagonalSymbolCounts.containsKey(symbol)) {
                System.out.println("TopRight hashmap is called...");
                topRightDiagonalSymbolCounts.put(symbol, 0);
            }
            // update the value by one if the same col having the character...
            topRightDiagonalSymbolCounts.put(symbol, topRightDiagonalSymbolCounts.get(symbol)+1);
        }

        //check winner
        if(rowSymbolCounts.get(row).get(symbol) == dimension || colSymbolCounts.get(col).get(symbol) == dimension) {
            return true;
        }

        if(row == col && topLeftDiagonalSymbolCounts.get(symbol) == dimension) {
            return true;
        }
        if(row+col == dimension-1 && topRightDiagonalSymbolCounts.get(symbol) == dimension) {
            return true;
        }

        return false;
    }
}

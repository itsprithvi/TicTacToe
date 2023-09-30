import controller.GameController;
import model.*;

import java.util.*;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class TicTacToeGame {
    public static void main(String[] args) {
        System.out.println("Hello World, welcome to tic tac toe game....");

        Scanner sc = new Scanner(System.in);

        System.out.println("What is the dimension of the game");
        int dimension = sc.nextInt();

        System.out.println("No of players");
        int noOfPlayers = sc.nextInt();

        List<Player> players = new LinkedList<>();

        System.out.println("Will there be any bot or not ? y/n");
        String isBot = sc.next();

        if(isBot.equals("y")) {
            noOfPlayers = noOfPlayers - 1;

            System.out.println("Enter the name of the bot");
            String name = sc.next();

            System.out.println("Enter the symbol of the bot");
            String botSymbol = sc.next();

            System.out.println("Enter bot Difficulty level: 1-Easy 2-Medium 3-Hard");
            int difficultyLevel = sc.nextInt();

            //creating a bot type
            players.add(new Bot(botSymbol.charAt(0), name, BotDifficultyLevel.EASY));
        }

        for(int i=0; i<noOfPlayers; i++) {
            System.out.println("What is the name of the player: " +(i+1));
            String name = sc.next();

            System.out.println("What is the symbol of the player: " + i+1);
            String symbol = sc.next();  // Assumption: Single Character

            Player player = new Player(symbol.charAt(0), name, PlayerType.HUMAN);
            players.add(player);
        }

//        Game game = Game.getBuilder()
//                .setDimension(dimension)
//                .setPlayers(players)
//                .build();

        GameController gameController = new GameController();

        Game game = gameController.createGame(dimension, players);

        while(gameController.getGameStatus(game) == GameStatus.INPROGRESS) {
            // TODO play the game

            break;
        }
        if(gameController.getGameStatus(game) == GameStatus.DRAW) {
            System.out.println("Game has drawn");
        } else {
            System.out.println("Game has won by : " +gameController.getWinningPlayer(game));
        }
    }
}
package MiniProject;

import java.util.ArrayList;

public class RunGame implements Runnable {

    private Game game;

    public RunGame(int noOfPlayers, int noOfSnakes, int noOfLadders, ArrayList<String> names, int numberOfColumns, int numberOfRows) {
        this.game = new Game(noOfPlayers, noOfSnakes, noOfLadders, names, numberOfColumns, numberOfRows);
    }

    @Override
    public void run() {
        game.start();
    }
}
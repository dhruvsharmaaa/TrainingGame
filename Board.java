package MiniProject;

import jdk.nashorn.internal.objects.annotations.Getter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


public class Board {

    private int numberOfRows;
    private int numberOfColumns;
    private int boxSize;
    private int numberOfSnakes;
    private int numberOfLadders;
    private int range;

    private ArrayList<Pair> positionOfSnakes = new ArrayList<>();
    private ArrayList<Pair> positionOfLadders = new ArrayList<>();

    Board(int numberOfColumns, int numberOfRows, int numberOfSnakes, int numberOfLadders) {
        this.numberOfColumns = numberOfColumns;
        this.numberOfLadders = numberOfLadders;
        this.numberOfSnakes = numberOfSnakes;
        this.numberOfRows  = numberOfRows;
        range = numberOfColumns * numberOfRows;
        int boxesTaken = 0;
        Set <Integer> boxesLeft = new HashSet<Integer>();
        for(int i=1;i<=range;i++) {
            boxesLeft.add(i);
        }
        for(int i=1;i<=numberOfSnakes; i++) {
            int snakeStart = (int)(Math.floor(Math.random() * (range-boxesTaken)) + 1);
            if(boxesLeft.contains(snakeStart)){
                boxesLeft.remove(snakeStart);
                boxesTaken += 1;
                int snakeLength = (int)(Math.floor(Math.random() * (range-snakeStart)) + 1);
                boxesLeft.remove(snakeStart+snakeLength);
                positionOfSnakes.add(new Pair(snakeStart, snakeStart+snakeLength));
            }
            else {
                i--;
            }
        }
        for(int i=1;i<=numberOfLadders; i++) {
            int ladderStart = (int)(Math.floor(Math.random() * (range-boxesTaken)) + 1);
            if(boxesLeft.contains(ladderStart)){
                boxesLeft.remove(ladderStart);
                boxesTaken += 1;
                int ladderLength = (int)(Math.floor(Math.random() * (range-ladderStart)) + 1);
                boxesLeft.remove(ladderStart+ladderLength);
                positionOfLadders.add(new Pair(ladderStart, ladderStart+ladderLength));
            }
            else {
                i--;
            }
        }

    }


    public int getRows() {
        return this.numberOfRows;
    }

    public int getColumns() {
        return this.numberOfColumns;
    }

    public ArrayList<Pair> getPositionOfSnakes() {
        return positionOfSnakes;
    }


    public ArrayList<Pair> getPositionOfLadders() {
        return positionOfLadders;
    }

}

class Pair{
    int from;
    int to;
    Pair(int from, int to){
        this.from = from;
        this.to = to;
    }
}

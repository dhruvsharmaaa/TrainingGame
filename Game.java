package MiniProject;

import sun.lwawt.macosx.CSystemTray;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    private int gameSize = 100; //fixed for now
    private int noOfPlayers;
    private int noOfSnakes;
    private int noOfLadders;

    private ArrayList <Player> players = new ArrayList<>();
    private int turn;

    Dice dice;

    Board board;

    private ArrayList<Pair> positionOfSnakes;
    private ArrayList<Pair> positionOfLadders;

    private int boxSize = 5;
    private int exit = 0;

    Game(int noOfPlayers,
         int noOfSnakes,
         int noOfLadders,
         ArrayList<String> names,
         int numberOfColumns,
         int numberOfRows) {
        this.noOfPlayers = noOfPlayers;
        this.noOfSnakes = noOfSnakes;
        this.noOfLadders = noOfLadders;
        for (int i=0; i<noOfPlayers; i++) {
            players.add(new Player(names.get(i), 0, i+1));
        }
        this.turn = 0;
        this.dice = new Dice();
        this.board = new Board(numberOfColumns, numberOfRows, noOfSnakes, noOfLadders);
        this.positionOfSnakes = board.getPositionOfSnakes();
        this.positionOfLadders = board.getPositionOfLadders();
//        print();
//        start();
    }

    public void playerTurn() {
        System.out.println("Press any key to Move for Player " + (turn + 1));
        Scanner sc = new Scanner(System.in);
        char c = sc.next().charAt(0);
        Player pl = players.get(turn);
        int position = pl.getPosition();
        int diceValue = pl.rollDice(dice);
        System.out.println("Printing DICE ROLL" + diceValue);
        int newPosition = position + diceValue;

        for(int i=0;i<positionOfSnakes.size();i++) {
            if(positionOfSnakes.get(i).to == newPosition) {
                newPosition = positionOfSnakes.get(i).from;
                System.out.println("Took a bite from Snake");
            }
        }
        for(int i=0;i<positionOfLadders.size();i++) {
            if(positionOfLadders.get(i).from == newPosition) {
                boolean takeLadder = pl.takeladder(positionOfLadders.get(i).from, positionOfLadders.get(i).to);
                if(takeLadder) {
                    newPosition = positionOfLadders.get(i).to;
                    System.out.println("Took a ladder");
                }
            }
        }
        pl.setPosition(newPosition);
        turn = (turn + 1)%(noOfPlayers);
        if(newPosition == 100) {
            exit = 1;
        }
    }

    public void print() {
        System.out.flush();
        int rows = board.getRows();
        int columns = board.getColumns();
        for(int i=0;i<=rows*boxSize;i++)
        {
            for(int j=0;j<=columns*boxSize;j++)
            {
                if((i%boxSize==0) || (j%boxSize==0))
                {
                    System.out.printf("*");
                }
                else
                    System.out.printf(" ");
            }
            System.out.println();
        }
    }

    public void start() {

        System.out.println("Printing LADDERS");
        for(int i=0;i<positionOfLadders.size();i++){
            System.out.println("From: " + positionOfLadders.get(i).from + " To: " + positionOfLadders.get(i).to);
        }
        System.out.println("Printing SNAKES");
        for(int i=0;i<positionOfSnakes.size();i++){
            System.out.println("From: " + positionOfSnakes.get(i).to + " To: " + positionOfSnakes.get(i).from);
        }
        while(true){
            playerTurn();
            if(exit==1) {
                System.out.println("Game Over!! Player " + (turn - 1 + noOfPlayers)%noOfPlayers + " Wins!!");
                break;
            }

            for(int i=0;i<noOfPlayers;i++) {
                System.out.println("Position of Player " + (i+1) + " : " + players.get(i).getPosition());
            }

//            print();
        }
    }

}
package MiniProject;


import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Play {

    public static void main(String[] args) {
// write your code here
        System.out.println("Please enter the number of players, number of snakes, number of ladders, names of players, number of columns, number of rows");
        Scanner scanner = new Scanner(System.in);
        int noOfPlayers = scanner.nextInt();
        int noOfSnakes = scanner.nextInt();
        int noOfLadders = scanner.nextInt();
        ArrayList<String> names = new ArrayList<>();
        for(int i = 0; i < noOfPlayers; i++) {
            String name = scanner.nextLine();
            names.add(name);
        }
        int numberOfColumns = scanner.nextInt();
        int numberOfRows = scanner.nextInt();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(new RunGame(noOfPlayers, noOfSnakes, noOfLadders, names, numberOfColumns, numberOfRows));
    }
}

//3
//        10 10 dhruv
//        agam
//        shuvam
//        10
//        10
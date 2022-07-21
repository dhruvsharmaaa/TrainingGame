package MiniProject;

import java.util.Scanner;

public class Player {
    public String name;
    private Integer position;

    public Integer id;
    public Player(String name, Integer initialPosition, Integer id){
        this.name = name;
        this.position = initialPosition;
        this.id = id;
    }
    public void setPosition(Integer v){
        this.position = v;
    }
    public Integer getPosition(){
        return this.position;
    }
    public Boolean takeladder(Integer from,Integer to) {
        System.out.println("Do You Wish To Take LADDER? Press Y for YES");
        Scanner sc = new Scanner(System.in);
        char c = sc.next().charAt(0);
        if(c=='Y' || c=='y'){
            System.out.println("Taking LADDER");
            return Boolean.TRUE;
        }else return Boolean.FALSE;
    }
    public Integer rollDice(Dice dice) {
        int mn = dice.getDiceMin();
        int mx = dice.getDiceMax();
        int range = mx - mn + 1;
        return (int) (Math.floor(Math.random() * range) + mn);
    }
}


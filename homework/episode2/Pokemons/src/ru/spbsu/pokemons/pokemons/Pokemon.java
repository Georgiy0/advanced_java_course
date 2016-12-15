package ru.spbsu.pokemons.pokemons;
import ru.spbsu.pokemons.Moves.*;

import java.util.Scanner;

/**
 * Created by Egor on 10.11.2016.
 */
public class Pokemon {
    public String name;
    private int HP;
    private int attack;
    private int deffence;
    private int moveNum;

    Move moves[];

    public Pokemon(String name, int HP, int attack, int deffence){
        this.moveNum = 0;
        this.name = name;
        this.HP = HP;
        this.attack = attack;
        this.deffence = deffence;
        moves = new Move[4];
    }

    public int getHP() {
        return HP;
    }

    public int getMoveNum() {
        return moveNum;
    }

    public int getAttack() {
        return attack;
    }

    public int getDeffence() {
        return deffence;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public void addMove(MoveType type, String moveName, int damage){
        Move newMove = new Move(type, moveName, damage);
        if(moveNum < 4) {
            this.moves[moveNum] = newMove;
            moveNum++;
        }
        else{
            System.out.println("This Pokemon has already learnt 4 moves!");
        }
    }

    public Move selectMove(){
        if(moveNum == 0){
            System.out.println("This pokemon do not have any moves!");
            return null;
        }

        Scanner sc = new Scanner(System.in);
        System.out.println(this.name.toUpperCase() + " KNOWS THE FOLLOWING MOVES");
        for(int i = 0; i < moveNum; i++){
            System.out.println(i+1 + ") " + moves[i].name.toUpperCase());
        }

        System.out.println("Select a move to use (1, 2, etc.)".toUpperCase());
        int input = sc.nextInt();

        while(input < 1 || input > moveNum){
            input = sc.nextInt();
        }
        return moves[input - 1];
    }

    public int makeMove(Move move){
        if(move == null)
            return 0;
        if(move.isAttack())
            return (int)(move.getDamage() + move.getDamage()*(0.01 * this.getAttack()));
        else
            return 0;
    }

    public boolean takeDamage(int damage){
        damage = (int)(damage - damage*(0.01 * getDeffence()));
        System.out.println(this.name.toUpperCase() + " TAKES " + damage + " DAMAGE");
        this.setHP(getHP() - damage);
        if(getHP() < 0) {
            System.out.println(this.name.toUpperCase() + " FAINTS");
            return true;
        }
        System.out.println(this.name.toUpperCase() + "'S NOW HAS " + this.getHP() + " LEFT");
        return false;
    }
}

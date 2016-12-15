package ru.spbsu.pokemons.Moves;

public class Move {
    private MoveType type;
    public String name;
    private int damage;

    public Move(MoveType type, String name, int damage) {
        this.type = type;
        this.name = name;
        this.damage = damage;
    }

    public boolean isAttack(){
        if(type == MoveType.Attack)
            return true;
        return false;
    }

    public int getDamage() {
        return damage;
    }

    public void printOnPerform(String nameIn){
        System.out.println(nameIn.toUpperCase() + " USES " + this.name.toUpperCase());
    }
}


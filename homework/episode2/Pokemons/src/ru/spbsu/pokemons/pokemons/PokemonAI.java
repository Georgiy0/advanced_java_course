package ru.spbsu.pokemons.pokemons;

import ru.spbsu.pokemons.Moves.*;
/**
 * Created by Egor on 11.11.2016.
 */
public class PokemonAI extends Pokemon {

    public PokemonAI(String name, int HP, int attack, int deffence) {
        super(name, HP, attack, deffence);
    }

    @Override
    public Move selectMove(){

        if(getMoveNum() == 0){
            System.out.println("This pokemon do not have any moves!");
            return null;
        }

        int maxDamage = 0;
        int MaxI = 0;

        for(int i = 0; i<getMoveNum(); i++){
            if(moves[i].getDamage() > maxDamage){
                maxDamage = moves[i].getDamage();
                MaxI = i;
            }
        }

        return moves[MaxI];
    }
}

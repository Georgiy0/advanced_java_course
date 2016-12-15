package ru.spbsu.pokemons.trainer;

import ru.spbsu.pokemons.pokemons.Pokemon;

/**
 * Created by Egor on 10.11.2016.
 */

// Класс, наследуемый от Тренера, представляет треннеров-оппонентов, поэтому функции, которые
// основывалися на вводе данных пользователем, переопределены в этом классе
public class TrainerAI extends Trainer{

    public TrainerAI(String name) {
        super(name);
    }

    // Возвращает покемона с максимальным HP
    @Override
    public Pokemon selectPokemon(){
        Pokemon selected = null;
        int MaxHP = 0;
        for(Pokemon pok : this.pokemons) {
            if(pok.getHP() > MaxHP){
                MaxHP = pok.getHP();
                selected = pok;
            }
        }
        return selected;
    }

    @Override
    public void choosePokemonForBattle(){
        if(this.pokemons.isEmpty()){
            System.out.println("Your opponent don't have any Pokemons for the battle");
            return;
        }

        this.pokemonForBattle = selectPokemon();
    }
}

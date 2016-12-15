package ru.spbsu.pokemons.Battle;

import ru.spbsu.pokemons.Moves.Move;
import ru.spbsu.pokemons.pokemons.Pokemon;
import ru.spbsu.pokemons.trainer.Trainer;

/**
 * Created by Egor on 10.11.2016.
 */
public class Battle {

    public static Trainer startBattle(Trainer trainer, Pokemon pokemon1,
                               Trainer opponent, Pokemon pokemon2){
        System.out.println(trainer.name.toUpperCase() + " chooses " + pokemon1.name.toUpperCase());
        System.out.println(opponent.name.toUpperCase() + " chooses " + pokemon2.name.toUpperCase());

        boolean end;
        Move move;
        int roundNum = 0;
        while(true){
            roundNum++;
            System.out.println();
            System.out.println("---------------- ROUND " + roundNum + " ----------------");
            System.out.println("---------------- IT'S " + trainer.name.toUpperCase() + " TURN ----------------");
            move = trainer.battleDesicion();
            end = pokemon2.takeDamage(pokemon1.makeMove(move));
            if(end) {
                return trainer;
            }
            System.out.println();
            System.out.println("---------------- IT'S " + opponent.name.toUpperCase() + " TURN ----------------");
            move = opponent.battleDesicion();
            end = pokemon1.takeDamage(pokemon2.makeMove(move));
            if(end) {
                return opponent;
            }
        }
    }

}

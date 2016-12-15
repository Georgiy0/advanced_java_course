package ru.spbsu.pokemons.trainer;

import ru.spbsu.pokemons.Battle.Battle;
import ru.spbsu.pokemons.Moves.Move;
import ru.spbsu.pokemons.pokemons.*;
import java.util.*;
/**
 * Created by Egor on 10.11.2016.
 */

// Этот класс представляет тренера, который находиться под управлением игрока
public class Trainer {
    public String name;
    LinkedList<Pokemon> pokemons; // список покемонов тренера

    protected Pokemon pokemonForBattle; // покемон выбранный для сражения

    public Trainer(String name) {
        this.name = name;
        this.pokemons = new LinkedList<>();
        this.pokemonForBattle = null;
    }

    public void addPokemon(Pokemon newPokemon){
        this.pokemons.add(newPokemon);
    } // добавляет нового покемона в список

    // Реализует пользовательский интерфейс для выбора покемона
    public Pokemon selectPokemon(){
        Pokemon selected = null;
        Scanner sc = new Scanner(System.in);
        for(Pokemon pok : this.pokemons){
            System.out.println(pok.name);
            System.out.println("Do you want to choose " + pok.name + " Yes or No");
            String input = sc.nextLine();
            while (!input.equals("Yes") && !input.equals("No"))
                input = sc.nextLine();
            if(input.equals("Yes")) {
                selected = pok;
                break;
            }
        }
        return selected; // возвращает выбранного покемона
    }

    // функция для начала поединка с тренером "opponent"
    public void battleOpponent(Trainer opponent){
        this.choosePokemonForBattle();
        opponent.choosePokemonForBattle();
        Trainer winner = Battle.startBattle(this, this.pokemonForBattle, opponent, opponent.pokemonForBattle);
        System.out.println(winner.name.toUpperCase() + " WINS");
    }

    // Функция инициализирующая поле pokemonForBattle
    public void choosePokemonForBattle(){
        Pokemon pok = null;
        if(this.pokemons.isEmpty()){
            System.out.println("You don't have any Pokemons for the battle");
            return;
        }

        while((pok = this.selectPokemon()) == null)
            System.out.println("You have to select a Pokemon for the battle");

        pokemonForBattle = pok;
    }

    // Функция используется для "управления" покемоном во время боя
    public Move battleDesicion(){
        Move move;
        move = this.pokemonForBattle.selectMove();
        move.printOnPerform(this.pokemonForBattle.name);
        return move;
    }
}


import ru.spbsu.pokemons.Moves.MoveType;
import ru.spbsu.pokemons.pokemons.Pokemon;
import ru.spbsu.pokemons.pokemons.PokemonAI;
import ru.spbsu.pokemons.trainer.Trainer;
import ru.spbsu.pokemons.trainer.TrainerAI;

import java.util.Scanner;

/**
 * Created by Egor on 10.11.2016.
 */
public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Trainer player;
        String nameIn;
        System.out.println("Enter a name for your trainer:");
        nameIn = sc.nextLine();
        player = new Trainer(nameIn);
        System.out.println("Add some Pokemons to your trainer");
        addPokemons(player, true);
        TrainerAI opponent;
        System.out.println("Enter a name for your opponent:");
        nameIn = sc.nextLine();
        opponent = new TrainerAI(nameIn);
        System.out.println("Add some Pokemons to your opponent");
        addPokemons(opponent, false);

        // Вызов функции, отвечающей за бой покемонов
        player.battleOpponent(opponent);
    }

    // Функция для добавления покемонов вручную
    public static void addPokemons(Trainer trainer, boolean player){
        Pokemon pok;
        String nameIn;
        Scanner sc = new Scanner(System.in);

        // Цикл добавления покемонов
        while(true){
            System.out.println("Enter a name for the new Pokemon");
            nameIn = sc.nextLine();
            System.out.println("Enter the HP amount of the new Pokemon");
            int HP = sc.nextInt();
            System.out.println("Enter the ATTACK stat of the new Pokemon");
            int attack = sc.nextInt();
            System.out.println("Enter the DEFENSE stat of the new Pokemon");
            int defense = sc.nextInt();
            if(player)
                pok = new Pokemon(nameIn, HP, attack, defense);
            else
                pok = new PokemonAI(nameIn, HP, attack, defense);
            nameIn = sc.nextLine();
            System.out.println("Add some moves to your Pokemon");

            // Цикл добавления ударов для покемона
            while (true){
                System.out.println("Enter a name for the new move");
                nameIn = sc.nextLine();
                System.out.println("Enter the DAMAGE stat of the new move");
                int damage = sc.nextInt();
                pok.addMove(MoveType.Attack, nameIn, damage);
                System.out.println("Do you want to add more moves tp your Pokemon (max 4 moves)? Yes or No");
                nameIn = sc.nextLine();
                while (!nameIn.equals("Yes") && !nameIn.equals("No"))
                    nameIn = sc.nextLine();
                if(nameIn.equals("No")) {
                    break;
                }
            }

            trainer.addPokemon(pok);
            System.out.println("Do you want to add one more Pokemon? Yes or No");
            nameIn = sc.nextLine();
            while (!nameIn.equals("Yes") && !nameIn.equals("No"))
                nameIn = sc.nextLine();
            if(nameIn.equals("No")) {
                break;
            }
        }
    }
}

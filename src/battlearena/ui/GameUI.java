package battlearena.ui;

import battlearena.model.Fighter;
import battlearena.model.FighterType;
import battlearena.service.GameService;
import java.util.Scanner;

public class GameUI {
    final String ACTION_REQUEST = "Please, make your choice by entering one of provided numbers...";
    private final GameService game;

    public GameUI(GameService game) {
        this.game = game;
    }

    public void run(){
        boolean running = true;
        Scanner scanner = new Scanner(System.in);

        //game.initialize();

        while(running){
            for(MenuOptions options : MenuOptions.values()){
                System.out.println(options.getCode() + " - " + options.getDisplayName());
            }
            System.out.println(ACTION_REQUEST);

            int input = scanner.nextInt();
            MenuOptions option = MenuOptions.getByCode(input);

            switch (option){
                case null -> {}
                case QUIT -> running = false;
                case CREATE_PLAYER -> createPlayerMenu(scanner);
                case CHOOSE_PLAYER -> choosePlayerMenu(scanner);
                case CHOOSE_FIGHTER -> chooseFighterMenu(scanner);
                case START_BATTLE -> game.startBattle();
                case SHOW_BATTLE_HISTORY -> {}
            }

        }

    }

    private void chooseFighterMenu(Scanner scanner) {
        for(FighterType fighter : FighterType.values()){
            System.out.println(fighter.getCode() + " - " + fighter.getDisplayName());
            //For display fighters paramethers changed in FighterType enurm needed. Refactoring of fighter's classes
            //will be needed after updating enurm
            //System.out.println("Health = " + );


        }
        System.out.println(ACTION_REQUEST);

        int input = scanner.nextInt();
        //
    }

    private void choosePlayerMenu(Scanner scanner) {
    }

    private void createPlayerMenu(Scanner scanner) {
    }


}

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

        while(running){
            for(MenuOptions options : MenuOptions.values()){
                System.out.println(options.getCode() + " - " + options.getDisplayName());
            }
            System.out.println(ACTION_REQUEST);

            int input = scanner.nextInt();
            MenuOptions option = MenuOptions.getByCode(input);

            switch (option){
                case QUIT -> running = false;
                case GAME -> {
                    createPlayerMenu(scanner);
                    String playersFighter = chooseFighterMenu(scanner);
                    battleLoop(scanner, playersFighter);
                }
                case SHOW_BATTLE_HISTORY -> {}
                case null -> System.out.println("Invalid option, please try again.");
            }
        }
    }

    private void battleLoop(Scanner scanner, final String playersFighter) {
        game.startBattle();
        String playersName = game.getPlayer().getName();
        int action = 1;
        Fighter computerFighterF = game.getComputer().getFighter();
        String computersFighter = computerFighterF.getClass().getSimpleName();

        System.out.println("=====" + playersFighter + " " + playersName +  " VS " + computersFighter + "=====");
        System.out.println("=====BATTLE IS STARTED=====");

        while(game.isBattleActive()){
            System.out.println("\nChoose your action:");
            System.out.println("1 - ATTACK");
            System.out.println("2 - SPECIAL - "  );
            action = scanner.nextInt();

            //game.executeTurn(action);

        }

    }

    private String chooseFighterMenu(Scanner scanner) {
        for(FighterType fighter : FighterType.values()){
            System.out.println(fighter.getCode() + " - " + fighter.getDisplayName());
            System.out.println("Health = " + fighter.getBaseHealth());
            System.out.println("Special Action - " + fighter.getSpecialAction());
        }
        System.out.println(ACTION_REQUEST);

        int input = scanner.nextInt();
        FighterType type = FighterType.fromCode(input);
        if(type == null){
            System.out.println("Invalid input. Please try again.");
        }else{
            game.selectFighter(type);
            System.out.println("Fighter " + type.getDisplayName() + " selected!");
            return type.getDisplayName();
        }
        return null;
    }

    private void createPlayerMenu(Scanner scanner) {
        System.out.println("Please enter the name of the player you would like to create.");
        game.setPlayerName(scanner.next());
        System.out.println("Player is created!.");
    }

}

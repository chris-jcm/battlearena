package battlearena.ui;

import battlearena.model.Fighter;
import battlearena.model.FighterType;
import battlearena.model.PlayerAction;
import battlearena.model.TurnResult;
import battlearena.service.GameService;

import java.util.List;
import java.util.Scanner;

public class GameUI {
    final String ACTION_REQUEST = "Please, make your choice by entering one of provided numbers...";
    final String INVALID_INPUT = "Invalid input. Please try again.";
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
                    int playersFighter = chooseFighterMenu(scanner);
                    battleLoop(scanner, playersFighter);
                }
                case SHOW_BATTLE_HISTORY -> {}
                case null -> System.out.println("Invalid option, please try again.");
            }
        }
    }

    private void battleLoop(Scanner scanner, final int playersFighterCode) {
        game.startBattle();
        String playersName = game.getPlayer().getName();
        int actionCode = 1;
        Fighter computerFighterF = game.getComputer().getFighter();
        String computersFighter = computerFighterF.getClass().getSimpleName();

        System.out.println("=====" + FighterType.getByCode(playersFighterCode).getDisplayName() + " "
                + playersName +  " VS " + computersFighter + "=====");
        System.out.println("=====BATTLE IS STARTED=====");

        while(game.isBattleActive()){
            System.out.println("\nChoose your action:");
            System.out.println("1 - ATTACK");
            System.out.println("2 - SPECIAL - " + FighterType.getByCode(playersFighterCode).getSpecialAction());
            actionCode = scanner.nextInt();
            //game.executeTurn(PlayerAction.getByCode(actionCode));

            try{
                List<TurnResult> result = game.executeTurn(PlayerAction.getByCode(actionCode));
                for(TurnResult turnResult : result){
                    System.out.println(turnResult.toString());
                }
            } catch(IllegalStateException e){
                System.out.println(e.getMessage());
            }

        }

    }

    private int chooseFighterMenu(Scanner scanner) {
        for (FighterType fighter : FighterType.values()) {
            System.out.println(fighter.getCode() + " - " + fighter.getDisplayName());
            System.out.println("Health = " + fighter.getBaseHealth());
            System.out.println("Special Action - " + fighter.getSpecialAction());
        }

        FighterType type = null;

        do{
            System.out.println(ACTION_REQUEST);

            while(!scanner.hasNextInt()){
                System.out.println(INVALID_INPUT);
                scanner.next();
            }

            type = FighterType.getByCode(scanner.nextInt());

            if (type == null) {
                System.out.println(INVALID_INPUT);
            } else {
                game.selectFighter(type);
                System.out.println("Fighter " + type.getDisplayName() + " selected!");
                return type.getCode();
            }
        } while (true);

    }

    private void createPlayerMenu(Scanner scanner) {
        System.out.println("Please enter the name of the player you would like to create.");
        game.setPlayerName(scanner.next());
        System.out.println("Player is created!.");
    }

}

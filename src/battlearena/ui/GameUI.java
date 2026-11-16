package battlearena.ui;

import battlearena.model.Fighter;
import battlearena.model.FighterType;
import battlearena.model.PlayerAction;
import battlearena.model.TurnResult;
import battlearena.service.GameService;

import java.util.List;
import java.util.Scanner;
import java.util.function.Function;

public class GameUI {
    static final String ACTION_REQUEST = "Please, make your choice by entering one of provided numbers...";
    static final String INVALID_INPUT = "Invalid input. Please try again.";

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

            int input = scanInt(scanner);
            MenuOptions option = MenuOptions.getByCode(input);

            switch (option){
                case QUIT -> running = false;
                case GAME -> {
                    createPlayerMenu(scanner);
                    FighterType playersFighter = getFighterChoice(scanner);
                    battleLoop(scanner, playersFighter);
                }
                case SHOW_BATTLE_HISTORY -> displayBattleHistory();
                case null -> System.out.println(INVALID_INPUT);
            }
        }
    }

    private void displayBattleHistory() {

    }

    private void battleLoop(Scanner scanner, FighterType playersFighter) {
        game.startBattle();

        String playersName = game.getPlayer().getName();
        
        Fighter computerFighterF = game.getComputer().getFighter();
        String computersFighter = computerFighterF.getClass().getSimpleName();

        System.out.println("=====" + playersFighter.getDisplayName() + " " + playersName +  " VS " + computersFighter + "=====");
        System.out.println("=====BATTLE IS STARTED=====");

        while(game.isBattleActive()){
            System.out.println("\nChoose your action:");
            System.out.println("1 - ATTACK");
            System.out.println("2 - SPECIAL - " + playersFighter.getSpecialAction());

            playTurn(scanner);
        }

        System.out.println("=====BATTLE IS FINISHED=====");
        System.out.println("=========IT'S A " + game.getMatchResult() + "=========");

    }

    private void playTurn(Scanner scanner) {
        try{
            List<TurnResult> result = game.executeTurn(getActionChoice(scanner));
            for(TurnResult turnResult : result){
                System.out.println(turnResult.toString());
            }
        } catch(IllegalStateException e){
            System.out.println(e.getMessage());
        }
    }

    private PlayerAction getActionChoice(Scanner scanner) {

        return readChoice(scanner, PlayerAction::getByCode);
    }

    private <T> T readChoice(Scanner scanner, Function<Integer, T> getByCode) {
        T choice = null;

        do {
            choice = getByCode.apply(scanInt(scanner));

            if (choice == null) {
                System.out.println(INVALID_INPUT);
            }
        } while (choice == null);

        return choice;
    }

    private FighterType getFighterChoice(Scanner scanner) {
        listFighters();

        FighterType type = readChoice(scanner, FighterType::getByCode);

        game.selectFighter(type);
        System.out.println("Fighter " + type.getDisplayName() + " selected!");

        return type;
    }

    private int scanInt(Scanner scanner) {
        while(true) {
            String input = scanner.nextLine().trim();

            try {
                return Integer.parseInt(input);
            }catch (IllegalArgumentException e){
                System.out.println(INVALID_INPUT);
            }
        }
    }

    private void listFighters() {
        for (FighterType fighter : FighterType.values()) {
            System.out.println(fighter.getCode() + " - " + fighter.getDisplayName());
            System.out.println("Health = " + fighter.getBaseHealth());
            System.out.println("Special Action - " + fighter.getSpecialAction());
        }
        System.out.println(ACTION_REQUEST);
    }

    private void createPlayerMenu(Scanner scanner) {
        System.out.println("Please enter the name of the player you would like to create.");
        game.setPlayerName(scanner.nextLine());
        System.out.println("Player is created!.");
    }

}


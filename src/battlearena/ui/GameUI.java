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
    private final Scanner scanner = new Scanner(System.in);

    static final String ACTION_REQUEST = "Please, make your choice by entering one of provided numbers...";
    static final String INVALID_INPUT = "Invalid input. Please try again.";

    private final GameService game;

    public GameUI(GameService game) {
        this.game = game;
    }

    public void run() {
        boolean running = true;

        while (running) {
            displayOptions(MenuOptions.values());

            switch (readChoice(MenuOptions::getByCode)) {
                case QUIT -> running = false;
                case GAME -> startGame();
                case SHOW_BATTLE_HISTORY -> displayBattleHistory();
            }
        }
    }

    private void startGame() {
        createPlayerMenu();
        getFighterChoice();

        boolean playing = true;

        while (playing) {
            battleLoop();
            displayOptions(AfterBattleOptions.values());

            switch (readChoice(AfterBattleOptions::getByCode)){
                case MAIN_MENU -> playing = false;
                case PLAY_AGAIN -> {}
                case CHANGE_FIGHTER ->
                    getFighterChoice();
            }
        }

    }

    //TODO
    private void displayBattleHistory() {

    }

    //TODO SPECIAL DISPLAY
    private void battleLoop() {
        game.startBattle();

        String playersName = game.getPlayer().getName();
        Fighter computerFighterF = game.getComputer().getFighter();
        String computersFighter = computerFighterF.getClass().getSimpleName();

        //System.out.println("=====" + playersFighter.getDisplayName() + " " + playersName + " VS " + computersFighter + "=====");
        System.out.println("=====BATTLE IS STARTED=====");

        while (game.isBattleActive()) {
            System.out.println("\nChoose your action:");
            System.out.println("1 - ATTACK");
            //System.out.println("2 - SPECIAL - " + playersFighter.getSpecialAction());

            playTurn();
        }

        System.out.println("=====BATTLE IS FINISHED=====");
        System.out.println("=========IT'S A " + game.getMatchResult() + "=========");

    }


    private void playTurn() {
        try {
            List<TurnResult> result = game.executeTurn(getActionChoice());
            for (TurnResult turnResult : result) {
                System.out.println(turnResult.toString());
            }
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        }
    }

    private PlayerAction getActionChoice() {
        return readChoice(PlayerAction::getByCode);
    }

    private  void displayOptions(MenuItem[] items){
        for (MenuItem item : items) {
            System.out.println(item.getCode() + " - " + item.getDisplayName());
        }
        System.out.println(ACTION_REQUEST);
    }

    private <T> T readChoice(Function<Integer, T> getByCode) {
        T choice = null;

        do {
            choice = getByCode.apply(scanInt());
            if (choice == null) {
                System.out.println(INVALID_INPUT);
            }
        } while (choice == null);

        return choice;
    }

    private void getFighterChoice() {
        listFighters();

        FighterType type = readChoice(FighterType::getByCode);

        game.selectFighter(type);
        System.out.println("Fighter " + type.getDisplayName() + " selected!");

    }

    private int scanInt() {
        while (true) {
            String input = scanner.nextLine().trim();

            try {
                return Integer.parseInt(input);
            } catch (IllegalArgumentException e) {
                System.out.println(INVALID_INPUT);
            }
        }
    }

    //TODO Health and special action
    private void listFighters() {
        for (FighterType fighter : FighterType.values()) {
            System.out.println(fighter.getCode() + " - " + fighter.getDisplayName());
            System.out.println();
            //System.out.println("Health = " + fighter.getBaseHealth());
            //System.out.println("Special Action - " + fighter.getSpecialAction());
        }
        System.out.println(ACTION_REQUEST);
    }

    private void createPlayerMenu() {
        System.out.println("Please enter the name of the player you would like to create.");
        game.setPlayerName(scanner.nextLine());
        System.out.println("Player is created!.");
    }

}


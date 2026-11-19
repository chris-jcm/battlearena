package battlearena.ui;

import battlearena.model.*;
import battlearena.service.GameService;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;

public class GameUI {
    FighterType playersFighterType;
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
                case CHANGE_FIGHTER -> getFighterChoice();
            }
        }

    }

    //TODO
    private void displayBattleHistory() {

        try {
        List<BattleResult> battleHistoryResult = game.getMatchHistory();

        if (battleHistoryResult.isEmpty()) {
            System.out.println("No battle history saved yet.");
            return;
        }

        for (BattleResult battleHistory : battleHistoryResult) {
            System.out.println(battleHistory);
        }

        } catch (IOException e) {
            System.out.println("Could not get battle history." + e.getMessage());
        }
    }

    private void battleLoop() {
        game.startBattle();

        Fighter playersFighter = game.getPlayer().getFighter();
        Fighter computerFighter = game.getComputer().getFighter();

        System.out.println("===== " + playersFighterType.getDisplayName() + " " + game.getPlayer().getName() +
                " VS " + computerFighter.getClass().getSimpleName() + " =====");
        System.out.println("===== BATTLE IS STARTED =====");

        while (game.isBattleActive()) {
            System.out.println("\nChoose your action:");
            System.out.println("1 - ATTACK");
            System.out.println("2 - SPECIAL - " + playersFighter.getSpecialAction().getDisplayName());

            playTurn();
        }

        System.out.println("===== BATTLE IS FINISHED =====");
        System.out.println("========= IT'S A " + game.getMatchResult() + " =========");

        try {
            game.saveMatchHistory();
            System.out.println("Match result saved.");
        } catch (IOException e) {
            System.out.println("Could not save match result." + e.getMessage());
        }
    }

    //TODO Nice output
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

        playersFighterType = type;
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

    private void listFighters() {
        for (FighterType fighter : FighterType.values()) {
            Fighter temp = game.previewFighter(fighter);
            System.out.print(fighter.getCode() + " - " + fighter.getDisplayName());
            System.out.print("  === Special: " + temp.getSpecialAction().getDisplayName());
            System.out.println("  === Health: " + temp.getMaxHealthPoints());

        }
        System.out.println(ACTION_REQUEST);
    }

    private void createPlayerMenu() {
        System.out.println("Please enter the name of the player you would like to create.");
        String playerName;
        do {
            playerName = scanner.nextLine().trim();
            if (playerName.isEmpty()) {
                System.out.println(INVALID_INPUT);
            }
        } while (playerName.isEmpty());
        game.setPlayerName(playerName);
        System.out.println("Player is created!");
    }

}


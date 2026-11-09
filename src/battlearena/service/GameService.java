package battlearena.service;

import battlearena.model.*;

import java.util.ArrayList;
import java.util.List;

public class GameService {
    public static final String BATTLE_STILL_ACTIVE = "The battle is still active.";
    public static final String NO_ACTIVE_BATTLE = "No active battle.";
    public static final String NO_FIGHTER_SELECTED = "Player has no fighter selected.";
    public static final String COMPUTER_TURN = "It is the computer's turn.";
    public static final String PLAYER_TURN = "It is the player's turn.";
    private final BattleService battleService;
    private final FighterFactory fighterFactory;
    private final Player player;
    private final Player computer;
    private boolean isBattleActive;
    private boolean isPlayerTurn;

    public GameService(FighterFactory fighterFactory, BattleService battleService) {
        this.fighterFactory = fighterFactory;
        this.battleService = battleService;
        player = new Player("Player", false);
        computer = new Player("Computer", true);
        isBattleActive = false;
        isPlayerTurn = false;
    }

    public Player getPlayer() {
        return player;
    }

    public Player getComputer() {
        return computer;
    }

    public boolean isBattleActive() {
        return isBattleActive;
    }

    public boolean isPlayerTurn() {
        return isPlayerTurn;
    }

    public void setPlayerName(String name) {
        player.setName(name);
    }

    public void selectFighter(FighterType fighterType) {
        Fighter fighter = fighterFactory.createFighter(fighterType);
        player.setFighter(fighter);
    }

    public void startBattle() {
        if (player.getFighter() == null) {
            throw new IllegalStateException(NO_FIGHTER_SELECTED);
        }
        // Reset player fighter
        var maxHP = player.getFighter().getMaxHealthPoints();
        player.getFighter().setHealthPoints(maxHP);

        computer.setFighter(fighterFactory.createRandomFighter());
        // Decide who starts
        isPlayerTurn = Math.random() < 0.5;
        isBattleActive = true;
    }

    // Execute only player turn
    public TurnResult executePlayerTurn(PlayerAction action) {
        if (!isBattleActive) {
            throw new IllegalStateException(NO_ACTIVE_BATTLE);
        }

        if (!isPlayerTurn) {
            throw new IllegalStateException(COMPUTER_TURN);
        }

        TurnResult result = executeAction(player, computer, action);

        if (isBattleActive) {
            isPlayerTurn = false;
        }

        return result;
    }

    // Execute only computer turn
    public TurnResult executeComputerTurn() {
        if (!isBattleActive) {
            throw new IllegalStateException(NO_ACTIVE_BATTLE);
        }

        if (isPlayerTurn) {
            throw new IllegalStateException(PLAYER_TURN);
        }

        TurnResult result = executeComputerAction();

        if (isBattleActive) {
            isPlayerTurn = true;
        }

        return result;
    }

    // Execute both Player and Computer turn.
    public List<TurnResult> executeTurn(PlayerAction action) {
        if (!isBattleActive) {
            throw new IllegalStateException(NO_ACTIVE_BATTLE);
        }

        var results = new ArrayList<TurnResult>();

        if (isPlayerTurn) {
            results.add(executeAction(player, computer, action));

            if (isBattleActive) {
                results.add(executeComputerAction());
            }
        } else {
            results.add(executeComputerAction());

            if (isBattleActive) {
                results.add(executeAction(player, computer, action));
            }
        }

        return results;
    }

    public GameResult getMatchResult() {
        if (isBattleActive) {
            throw new IllegalStateException(BATTLE_STILL_ACTIVE);
        }

        if (player.getFighter().getHealthPoints() > 0) {
            return GameResult.WIN;
        } else {
            return GameResult.LOSS;
        }
    }

    private TurnResult executeComputerAction() {
        var action = Math.random() < 0.6 ? PlayerAction.ATTACK : PlayerAction.SPECIAL;
        return executeAction(computer, player, action);
    }

    private TurnResult executeAction(Player attacker, Player defender, PlayerAction action) {
        var battleLog = battleService.executeAction(attacker.getFighter(), defender.getFighter(), action);

        checkMatchStatus();

        return new TurnResult(attacker.getName(), defender.getName(), battleLog);
    }

    private void checkMatchStatus() {
        if (player.getFighter().getHealthPoints() <= 0 || computer.getFighter().getHealthPoints() <= 0) {
            isBattleActive = false;
        }
    }
}

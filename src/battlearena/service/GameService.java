package battlearena.service;

import battlearena.model.*;

public class GameService {
    private final BattleService battleService;
    private final FighterFactory fighterFactory;
    private final Player player;
    private final Player computer;
    private boolean isBattleActive;

    public GameService(FighterFactory fighterFactory, BattleService battleService) {
        this.fighterFactory = fighterFactory;
        this.battleService = battleService;
        player = new Player("Player", false);
        computer = new Player("Computer", true);
        isBattleActive = false;
    }

    public void initialize() {
        // TBDeleted
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

    public void createPlayer(String name) {
        player.setName(name);
    }

    public void selectFighter(FighterType fighterType) {
        Fighter fighter = fighterFactory.createFighter(fighterType);
        player.setFighter(fighter);
    }

    public void startBattle() {
        computer.setFighter(fighterFactory.createRandomFighter());
        isBattleActive = true;
    }

    public TurnResult executePlayerAction(PlayerAction action) {
        return executeAction(player, computer, action);
    }

    public TurnResult executeComputerAction() {
        var action = generateComputerAction();
        return executeAction(computer, player, action);
    }

    private TurnResult executeAction(Player attacker, Player defender, PlayerAction action) {
        if (!isBattleActive) {
            throw new IllegalStateException("No active battle.");
        }

        var battleLog = battleService.executeAction(attacker.getFighter(), defender.getFighter(), action);

        checkMatchStatus();

        return new TurnResult(attacker.getName(), defender.getName(), battleLog);
    }

    public GameResult getMatchResult() {
        // Add validation
        if (player.getFighter().getHealthPoints() > 0) {
            return GameResult.WIN;
        } else {
            return GameResult.LOSS;
        }
    }

    private void checkMatchStatus() {
        if (player.getFighter().getHealthPoints() <= 0 ||
                computer.getFighter().getHealthPoints() <= 0) {
            isBattleActive = false;
        }
    }

    private PlayerAction generateComputerAction() {
        // Simple random action
        return Math.random() < 0.6 ? PlayerAction.ATTACK : PlayerAction.SPECIAL;
    }
}

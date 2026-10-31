package battlearena.service;

import battlearena.model.*;


public class GameService {

    private final BattleService battleService;
    private Player player;
    private Player computer;

    public GameService(BattleService battleservice) {
        this.battleService = battleservice;
    }

    public Player getPlayer() {
        return player;
    }

    public void initialize() {
        createPlayer("Computer", true);
    }

    public void createPlayer(String name, Boolean isComputer) {

    }

    public void createFighter(FighterType fighterType) {

    }

    public void StartBattle(Player player, Player computer) {

    }
}

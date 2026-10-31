import battlearena.service.BattleService;
import battlearena.service.GameService;
import battlearena.ui.GameUI;

public class Main {
    public static void main(String[] args) {

        var battleService = new BattleService();
        var gameService = new GameService(battleService);
        var game = new GameUI(gameService);

        game.run();
    }
}


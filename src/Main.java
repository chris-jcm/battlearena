import battlearena.service.BattleService;
import battlearena.service.FighterFactory;
import battlearena.service.GameService;
import battlearena.ui.GameUI;

public class Main {
    public static void main(String[] args) {

        var fighterFactory = new FighterFactory();
        var battleService = new BattleService();
        var gameService = new GameService(fighterFactory, battleService);
        var game = new GameUI(gameService);

        game.run();
    }
}


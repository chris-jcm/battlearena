import battlearena.client.ArenaClient;
import battlearena.service.BattleService;
import battlearena.service.FighterFactory;
import battlearena.service.GameService;
import battlearena.ui.GameUI;

public class Main {
    public static void main(String[] args) {

        var arenaClient = new ArenaClient();
        var fighterFactory = new FighterFactory();
        var battleService = new BattleService();
        var gameService = new GameService(fighterFactory, battleService, arenaClient);
        var game = new GameUI(gameService);

        game.run();
    }
}


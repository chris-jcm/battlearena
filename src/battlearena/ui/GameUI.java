package battlearena.ui;

import battlearena.service.GameService;

public class GameUI {
    private final GameService game;

    public GameUI(GameService game) {
        this.game = game;
    }

    public void run(){
        System.out.println("battle arena");

        game.initialize();
    }
}

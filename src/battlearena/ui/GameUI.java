package battlearena.ui;

public class GameUI {
    private final String title;

    public GameUI(String title) {
        this.title = title;
    }

    public void run(){
        System.out.println(title);
    }
}

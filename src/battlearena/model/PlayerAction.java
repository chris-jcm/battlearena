package battlearena.model;

public enum PlayerAction implements BattleAction {
    ATTACK("Attack"),
    SPECIAL("Special");

    private final String displayName;

    PlayerAction(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String getDisplayName() {
        return displayName;
    }
}

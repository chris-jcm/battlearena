package battlearena.model;

public enum PlayerAction implements BattleAction {
    ATTACK("Attack", 1),
    SPECIAL("Special", 2);

    private final String displayName;
    private final int code;

    PlayerAction(String displayName, int code) {
        this.displayName = displayName;
        this.code = code;
    }

    public static PlayerAction getByCode(int code) {
        for (PlayerAction playerAction : PlayerAction.values()) {
            if (playerAction.code == code) {
                return playerAction;
            }
        }
        return null;
    }

    @Override
    public String getDisplayName() {
        return displayName;
    }
}

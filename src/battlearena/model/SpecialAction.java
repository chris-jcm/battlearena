package battlearena.model;

public enum SpecialAction implements BattleAction {
    LIFE_STEAL("Life steal", 10 ),
    DEFEND("Defend", 5),
    HEAL("Heal", 12);

    private final String displayName;
    private final int value;

    SpecialAction(String displayName, int value) {
        this.displayName = displayName;
        this.value = value;
    }

    @Override
    public String getDisplayName() {
        return displayName;
    }

    public int getValue() {
        return value;
    }

}
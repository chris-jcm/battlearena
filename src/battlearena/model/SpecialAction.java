package battlearena.model;

public enum SpecialAction {

    DRAINANDHEAL("Drainandheal", 10 ),
    DEFEND("Defend", 1),
    HEAL("Heal", 12);

    private final String displayName;
    private final int value;

    SpecialAction(String displayName, int value) {
        this.displayName = displayName;
        this.value = value;
    }

    public String getDisplayName() {
        return displayName;
    }

    public int getValue() {
        return value;
    }

}

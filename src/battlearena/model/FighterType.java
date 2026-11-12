package battlearena.model;

public enum FighterType {
    ROGUE(1, "Rogue"),
    WARRIOR(2, "Warrior"),
    MAGE(3, "Mage");

    private final int code;
    private final String displayName;

    FighterType(int code, String displayName) {
        this.code = code;
        this.displayName = displayName;
    }

    public int getCode() {
        return code;
    }
    public String getDisplayName() {
        return displayName;
    }
}

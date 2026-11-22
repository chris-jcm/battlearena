package battlearena.model.fighter;

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

    public static FighterType getByCode(int code) {
        for (FighterType fighterType : FighterType.values()) {
            if (fighterType.getCode() == code) {
                return fighterType;
            }
        }
        return null;
    }
}

package battlearena.model;

public enum FighterType {
    ROGUE(1, "Rogue", 100, SpecialAction.DEFEND.getDisplayName()),
    WARRIOR(2, "Warrior", 110, SpecialAction.LIFE_STEAL.getDisplayName()),
    MAGE(3, "Mage", 80, SpecialAction.HEAL.getDisplayName()),;

    private final int code;
    private final String displayName;
    private final int baseHealth;
    private final String specialAction;

    FighterType(int code, String displayName, int baseHealth, String specialAction) {
        this.code = code;
        this.displayName = displayName;
        this.baseHealth = baseHealth;
        this.specialAction = specialAction;
    }

    public int getCode() {
        return code;
    }
    public String getDisplayName() {
        return displayName;
    }

    public int getBaseHealth() {
        return baseHealth;
    }

    public String getSpecialAction() {
        return specialAction;
    }

    public static FighterType fromCode(int code) {
        for (FighterType fighterType : FighterType.values()) {
            if (fighterType.getCode() == code) {
                return fighterType;
            }
        }
        return null;
    }
}

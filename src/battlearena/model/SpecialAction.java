package battlearena.model;

public enum SpecialAction {

    LIFE_STEAL("Drainandheal", 10 ),
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



















/*package battlearena.model;

public enum SpecialAction {

    DRAINANDHEAL("Drainandheal", 2, 10 ),
    DEFEND("Defend", 7, 7),
    HEAL("Heal", 12, 12);

    private final String displayName;
    private final int minValue;
    private final int maxValue;

    SpecialAction(String displayName, int minValue, int maxValue) {
        this.displayName = displayName;
        this.minValue = minValue;
        this.maxValue = maxValue;
    }

    public String getDisplayName() {
        return displayName;
    }
    public int getMinValue() {
        return minValue;
    }
    public int getMaxValue() {
        return maxValue;
    }
}*/

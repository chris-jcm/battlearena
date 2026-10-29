package battlearena.model;

public enum SpecialAction {

    // Drainandheal special skadar motståndaren med slumpvårde
    // och ger användaren tillbaka hp som skadevärdet
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
}

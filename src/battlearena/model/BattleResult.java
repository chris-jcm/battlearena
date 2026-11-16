package battlearena.model;

public record BattleResult(
        String playerName,
        FighterType playerFighter,
        FighterType computerFighter,
        GameResult gameResult) {

    @Override
    public String toString() {
        return playerName + ": " + playerFighter +
                " vs " + computerFighter + " - Result: " + gameResult;
    }
}

package battlearena.model;

import battlearena.model.fighter.FighterType;

public record BattleResult(
        String playerName,
        FighterType playerFighter,
        FighterType computerFighter,
        GameResult gameResult) {

    public String serialize() {
        return String.join("|",
                playerName,
                playerFighter.name(),
                computerFighter.name(),
                gameResult.name()
        );
    }

    public static BattleResult deserialize(String data) {
        String[] fields = data.split("\\|", -1);

        if (fields.length != 4) {
            throw new IllegalArgumentException();
        }

        return new BattleResult(
                fields[0],
                FighterType.valueOf(fields[1]),
                FighterType.valueOf(fields[2]),
                GameResult.valueOf(fields[3])
        );
    }
}

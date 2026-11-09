package battlearena.model;

public record TurnResult(
        String attackerName,
        String defenderName,
        BattleLog battleLog) {
}

package battlearena.model;

public record BattleLog(
        BattleAction action,
        int value,
        int attackerHP,
        int defenderHP) {
}

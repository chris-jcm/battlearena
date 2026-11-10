package battlearena.model;

public record BattleLog(
        BattleAction action,
        int damageDealt,
        int healingDone,
        int attackerHP,
        int defenderHP) {
}

package battlearena.service;

import battlearena.model.BattleLog;
import battlearena.model.Combatant;
import battlearena.model.PlayerAction;
import battlearena.model.SpecialAction;

public class BattleService {

    public BattleLog executeAction(Combatant attacker, Combatant defender, PlayerAction action) {
        if (action == PlayerAction.ATTACK) {
            return attack(attacker, defender);
        }

        return specialAction(attacker, defender);
    }

    private BattleLog attack(Combatant attacker, Combatant defender) {
        int damageDealt = dealDamage(defender, attacker.getAttackDamage());

        return new BattleLog(PlayerAction.ATTACK, damageDealt, 0, attacker.getHealthPoints(), defender.getHealthPoints());
    }

    private BattleLog specialAction(Combatant attacker, Combatant defender) {
        var special = attacker.getSpecialAction();
        int value = special.getValue();
        int damageDealt = 0;
        int healingDone = 0;

        switch (special) {
            case LIFE_STEAL -> {
                damageDealt = dealDamage(defender, value);
                healingDone = attacker.heal(damageDealt);
            }
            case DEFEND -> {
                attacker.setIsDefending(true);
            }
            case HEAL -> {
                healingDone = attacker.heal(value);
            }
        }

        return new BattleLog(special, damageDealt, healingDone, attacker.getHealthPoints(), defender.getHealthPoints());
    }

    private int dealDamage(Combatant defender, int damage) {
        if (defender.isDefending()) {
            damage = Math.max(0, damage - SpecialAction.DEFEND.getValue());
            defender.setIsDefending(false);
        }

        return defender.takeDamage(damage);
    }
}

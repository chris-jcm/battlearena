package battlearena.service;

import battlearena.model.BattleLog;
import battlearena.model.Fighter;
import battlearena.model.PlayerAction;

public class BattleService {

    public BattleLog executeAction(Fighter attacker, Fighter defender, PlayerAction action) {
        if (action == PlayerAction.ATTACK) {
            return attack(attacker, defender, action);
        }

        return specialAction(attacker, defender);
    }

    private BattleLog attack(Fighter attacker, Fighter defender, PlayerAction action) {
        var damage = attacker.getAttackDamage();
        var defenderHP = defender.getHealthPoints();

        // Move to takeDamage method and check if defending
        defender.setHealthPoints(defenderHP - damage);

        return new BattleLog(action.toString(), damage, attacker.getHealthPoints(), defender.getHealthPoints());
    }

    private BattleLog specialAction(Fighter attacker, Fighter defender) {
        var special = attacker.getSpecialAction();
        var value = special.getValue();

        switch (special) {
            case LIFE_STEAL -> {
                defender.setHealthPoints(defender.getHealthPoints() - value);
                attacker.setHealthPoints(attacker.getMaxHealthPoints() + value);
            }
            case DEFEND -> {
                // Add defend logic to fighter
            }
            case HEAL -> {
                attacker.setHealthPoints(attacker.getMaxHealthPoints() + value);
            }
        }

        return new BattleLog(special.toString(), value, attacker.getHealthPoints(), defender.getHealthPoints());
    }
}

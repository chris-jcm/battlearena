package battlearena.service;

import battlearena.model.BattleLog;
import battlearena.model.Fighter;
import battlearena.model.PlayerAction;

public class BattleService {
    public BattleService() {

    }

    public BattleLog executeAction(Fighter attacker, Fighter defender, PlayerAction action) {
        // Add battle logic
        return new BattleLog(action.toString(), attacker.getAttackDamage(), attacker.getHealthPoints(), defender.getHealthPoints());
    }
}

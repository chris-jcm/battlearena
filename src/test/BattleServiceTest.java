package test;

import battlearena.model.*;
import battlearena.service.BattleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BattleServiceTest {
    private BattleService battleService;
    private Fighter defender;

    @BeforeEach
    void setUp() {
        battleService = new BattleService();
        defender = new Warrior();
    }

    @Test
    void shouldExecuteAttack() {
        var attacker = new Assaassin();
        int initialHealth = defender.getHealthPoints();

        battleService.executeAction(attacker, defender, PlayerAction.ATTACK);

        assertTrue(initialHealth > defender.getHealthPoints());
    }

    @Test
    void shouldExecuteDrainAndHeal() {
        var attacker = new Warrior();
        int initialHealth = defender.getHealthPoints();
        int value = attacker.getSpecialAction().getValue();

        battleService.executeAction(attacker, defender, PlayerAction.SPECIAL);

        assertEquals(initialHealth - value, defender.getHealthPoints());
    }

    @Test
    void shouldExecuteHeal() {
        var attacker = new Mage();
        attacker.setHealthPoints(attacker.getMaxHealthPoints() - 20);
        int initialHealth = attacker.getHealthPoints();

        battleService.executeAction(attacker, defender, PlayerAction.SPECIAL);

        assertTrue(attacker.getHealthPoints() > initialHealth);
    }
}

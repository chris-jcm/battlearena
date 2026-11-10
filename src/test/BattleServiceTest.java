package test;

import battlearena.model.*;
import battlearena.service.BattleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BattleServiceTest {
    private BattleService battleService;

    private static class TestFighter extends Fighter {
        private final SpecialAction specialAction;

        TestFighter(SpecialAction specialAction) {
            super(100, 5, 10);
            this.specialAction = specialAction;
        }

        @Override
        public SpecialAction getSpecialAction() {
            return specialAction;
        }
    }

    @BeforeEach
    void setUp() {
        battleService = new BattleService();
    }

    @Test
    void shouldExecuteAttack() {
        var attacker = new TestFighter(SpecialAction.DEFEND);
        var defender = new TestFighter(SpecialAction.HEAL);

        int initialHealth = defender.getHealthPoints();

        battleService.executeAction(attacker, defender, PlayerAction.ATTACK);

        assertTrue(initialHealth > defender.getHealthPoints());
    }

    @Test
    void shouldExecuteLifeSteal() {
        var attacker = new TestFighter(SpecialAction.LIFE_STEAL);
        var defender = new TestFighter(SpecialAction.HEAL);
        attacker.setHealthPoints(50);

        int initialAttackerHealth = attacker.getHealthPoints();
        int initialDefenderHealth = defender.getHealthPoints();
        int value = attacker.getSpecialAction().getValue();

        battleService.executeAction(attacker, defender, PlayerAction.SPECIAL);

        assertEquals(initialAttackerHealth + value, attacker.getHealthPoints());
        assertEquals(initialDefenderHealth - value, defender.getHealthPoints());
    }

    @Test
    void shouldExecuteHeal() {
        var attacker = new TestFighter(SpecialAction.HEAL);
        var defender = new TestFighter(SpecialAction.HEAL);

        attacker.setHealthPoints(50);
        int initialHealth = attacker.getHealthPoints();

        battleService.executeAction(attacker, defender, PlayerAction.SPECIAL);

        assertEquals(initialHealth + attacker.getSpecialAction().getValue(), attacker.getHealthPoints());
    }

    @Test
    void shouldSetIsDefend() {
        var attacker = new TestFighter(SpecialAction.DEFEND);
        var defender = new TestFighter(SpecialAction.HEAL);

        battleService.executeAction(attacker, defender, PlayerAction.SPECIAL);

        assertTrue(attacker.isDefending());
    }

    @Test
    void shouldDefendAgainstDamage() {
        var attacker = new TestFighter(SpecialAction.HEAL);
        var defender = new TestFighter(SpecialAction.DEFEND);
        defender.setIsDefending(true);

        int initialHealth = defender.getHealthPoints();

        battleService.executeAction(attacker, defender, PlayerAction.ATTACK);

        assertTrue(defender.getHealthPoints() > initialHealth - attacker.getAttackDamage());
        assertFalse(defender.isDefending());
    }
}

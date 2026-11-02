package test;

import battlearena.model.Fighter;
import battlearena.model.Mage;
import battlearena.model.SpecialAction;
import battlearena.model.Warrior;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FighterTest {

    private Fighter fighter;

    // Arrange
    @BeforeEach
    void  setup() {
        fighter = new Mage();

    }

    @Test
    void getActionDamageShouldReturnDamageWithinRange() {

        //Act
        int result = fighter.getAttackDamage();

        // Assert
        assertTrue(result >= 5 && result <= 11);
    }

    @Test
    void setHealthPointsShouldReturnNormalHealthWhenHealthIsBetweenZeroAndMaxHealth() {

         fighter.setHealthPoints(50);

        assertEquals(50, fighter.getHealthPoints());
    }

    @Test
    void setHealthPointsShouldReturnZeroHealthWhenHealthIsNegativ() {

        fighter.setHealthPoints(-10);

        assertEquals(0, fighter.getHealthPoints());
    }

    @Test
    void setHealthPointsShouldReturnMaxHealthPointsWhenHealthIsOverMaxHealthPoints() {

        fighter.setHealthPoints(110);

        assertEquals(100, fighter.getHealthPoints());
    }

}

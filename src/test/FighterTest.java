package test;

import battlearena.model.Fighter;
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
        fighter = new Warrior("Warrior", 100, 100, 7, 13);

    }

    @Test
    void getDamageShouldReturnDamageWithinRange() {

        //Act
        int result = fighter.getAttackDamage();

        assertTrue(result >= 7 && result <= 13);
    }
}

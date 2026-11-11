package test;

import battlearena.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FighterTest {

    private Fighter fighter;

    // Arrange
    @BeforeEach
    void  setup() {
        fighter = new Mage();
        fighter = new Warrior();
        fighter = new Rogue();
    }

    static Stream<Arguments> fighters(){
        return Stream.of(
                Arguments.of(new Mage(), 4, 11),
                Arguments.of(new Warrior(), 7, 13),
                Arguments.of(new Rogue(), 4, 10)
        );
    }

    @ParameterizedTest
    @MethodSource("fighters")
    void getActionDamageShouldReturnDamageWithinRange(Fighter fighter, int min, int max) {

       int result = fighter.getAttackDamage();

       assertTrue(result >= min && result <= max);
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

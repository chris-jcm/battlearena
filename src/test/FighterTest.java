package test;

import battlearena.model.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FighterTest {

    static Stream<Arguments> fightersForAttackDamage(){
        return Stream.of(
                Arguments.of(new Mage(), 33, 40),
                Arguments.of(new Warrior(), 36, 43),
                Arguments.of(new Rogue(), 30, 37)
        );
    }

    @ParameterizedTest
    @MethodSource("fightersForAttackDamage")
    void getActionDamageShouldReturnDamageWithinRange(Fighter fighter, int min, int max) {

       int result = fighter.getAttackDamage();

       assertTrue(result >= min && result <= max);
    }

    static Stream<Fighter> fightersHealth() {
        return Stream.of(
                new Mage(),
                new Warrior(),
                new Rogue()
        );
    }

    @ParameterizedTest
    @MethodSource("fightersHealth")
    void setHealthPointsShouldReturnNormalHealthWhenHealthIsBetweenZeroAndMaxHealth(Fighter fighter) {

        fighter.setHealthPoints(50);

        assertEquals(50, fighter.getHealthPoints());
    }

    @ParameterizedTest
    @MethodSource("fightersHealth")
    void setHealthPointsShouldReturnZeroHealthWhenHealthIsNegativ(Fighter fighter) {

        fighter.setHealthPoints(-10);

        assertEquals(0, fighter.getHealthPoints());
    }

    @ParameterizedTest
    @MethodSource("fightersHealth")
    void setHealthPointsShouldReturnMaxHealthPointsWhenHealthIsOverMaxHealthPoints(Fighter fighter) {

        fighter.setHealthPoints(110);

        assertEquals(fighter.getMaxHealthPoints(), fighter.getHealthPoints());
    }
}

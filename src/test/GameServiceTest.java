package test;

import battlearena.model.FighterType;
import battlearena.model.Warrior;
import battlearena.service.BattleService;
import battlearena.service.FighterFactory;
import battlearena.service.GameService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GameServiceTest {
    private GameService service;

    @BeforeEach
    void setUp() {
        service = new GameService(new FighterFactory(), new BattleService());
    }

    @Test
    void selectFighterShouldSetPlayersFighter() {
        service.selectFighter(FighterType.WARRIOR);

        assertInstanceOf(Warrior.class, service.getPlayer().getFighter());
    }

    @Test
    void startBattleShouldActivateBattleAndSetComputerFighter() {
        service.selectFighter(FighterType.WARRIOR);

        service.startBattle();

        assertTrue(service.isBattleActive());
        assertNotNull(service.getComputer().getFighter());
    }
}

package battlearena.service;

import battlearena.model.*;

import java.util.concurrent.ThreadLocalRandom;

public class FighterFactory {

    public Fighter createFighter(FighterType fighterType) {
        return switch(fighterType) {
            case ROGUE -> new Rogue();
            case WARRIOR -> new Warrior();
            case MAGE -> new Mage();
        };
    }

    public Fighter createRandomFighter() {
        FighterType[] types = FighterType.values();
        FighterType randomType = types[ThreadLocalRandom.current().nextInt(types.length)];
        return createFighter(randomType);
    }
}

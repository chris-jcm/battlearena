package battlearena.service;

import battlearena.model.fighter.*;

import java.util.concurrent.ThreadLocalRandom;

public class FighterFactory {

    public Fighter createFighter(FighterType fighterType) {
        return switch(fighterType) {
            case ROGUE -> new Rogue();
            case WARRIOR -> new Warrior();
            case MAGE -> new Mage();
        };
    }


    public FighterType createRandomFighterType() {
        FighterType[] types = FighterType.values();
        return types[ThreadLocalRandom.current().nextInt(types.length)];
    }

    public Fighter createRandomFighter(FighterType fighterType) {
        return createFighter(fighterType);
    }
}

package battlearena.model.fighter;

import battlearena.model.SpecialAction;

public class Rogue extends Fighter {
    private static final int MIN_ATTACK = 30;
    private static final int MAX_ATTACK = 37;
    private static final int BASE_HEALTH = 100;

    public Rogue() {
        super(BASE_HEALTH, MIN_ATTACK, MAX_ATTACK);
    }

    @Override
    public SpecialAction getSpecialAction() {
        return SpecialAction.LIFE_STEAL;
    }

    @Override
    public FighterType getType() {
        return FighterType.ROGUE;
    }
}

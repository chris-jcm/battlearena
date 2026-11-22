package battlearena.model.fighter;

import battlearena.model.SpecialAction;

public class Mage extends Fighter {

    private static final int MIN_ATTACK = 33;
    private static final int MAX_ATTACK = 40;
    private static final int BASE_HEALTH = 80;

    public Mage() {
        super(BASE_HEALTH, MIN_ATTACK, MAX_ATTACK);
    }

    @Override
    public SpecialAction getSpecialAction() {
        return SpecialAction.HEAL;
    }
}

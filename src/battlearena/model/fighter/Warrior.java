package battlearena.model.fighter;

import battlearena.model.SpecialAction;

public class Warrior extends Fighter {

    private static final int MIN_ATTACK = 36;
    private static final int MAX_ATTACK = 43;
    private static final int BASE_HEALTH = 110;

    public Warrior() {
        super(BASE_HEALTH, MIN_ATTACK, MAX_ATTACK);
    }

    @Override
    public SpecialAction getSpecialAction() {
        return SpecialAction.LIFE_STEAL;
 }

}

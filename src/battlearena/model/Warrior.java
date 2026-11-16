package battlearena.model;

public class Warrior extends Fighter {

    private static final int MIN_ATTACK = 36;
    private static final int MAX_ATTACK = 43;

    public Warrior() {
        super(FighterType.WARRIOR.getBaseHealth(), MIN_ATTACK, MAX_ATTACK);
    }

    @Override
    public SpecialAction getSpecialAction() {
        return SpecialAction.LIFE_STEAL;
 }

}

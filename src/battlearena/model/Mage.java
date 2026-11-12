package battlearena.model;

public class Mage extends Fighter {

    private static final int MIN_ATTACK = 5;
    private static final int MAX_ATTACK = 11;

    public Mage() {
        super(FighterType.MAGE.getBaseHealth(), MIN_ATTACK, MAX_ATTACK);
    }

    @Override
    public SpecialAction getSpecialAction() {
        return SpecialAction.HEAL;
    }
}

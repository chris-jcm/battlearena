package battlearena.model;

public class Mage extends Fighter {

    private static final int BASE_HEALTH = 80;
    private static final int MIN_ATTACK = 5;
    private static final int MAX_ATTACK = 11;

    public Mage() {
        super(BASE_HEALTH, MIN_ATTACK, MAX_ATTACK);
    }

    @Override
    public SpecialAction getSpecialAction() {
        return SpecialAction.HEAL;
    }
}

package battlearena.model;

public class Rogue extends Fighter {

    private static final int BASE_HEALTH = 100;
    private static final int MIN_ATTACK = 4;
    private static final int MAX_ATTACK = 10;

    public Rogue() {
        super(BASE_HEALTH, MIN_ATTACK, MAX_ATTACK);
    }

    @Override
    public SpecialAction getSpecialAction() {
        return SpecialAction.DEFEND;
    }
}

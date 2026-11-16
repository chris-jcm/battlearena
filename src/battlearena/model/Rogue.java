package battlearena.model;

public class Rogue extends Fighter {

    private static final int MIN_ATTACK = 30;
    private static final int MAX_ATTACK = 37;

    public Rogue() {
        super(FighterType.ROGUE.getBaseHealth(), MIN_ATTACK, MAX_ATTACK);
    }

    @Override
    public SpecialAction getSpecialAction() {
        return SpecialAction.DEFEND;
    }
}

package battlearena.model;

public class Rogue extends Fighter {

    private static final int MIN_ATTACK = 4;
    private static final int MAX_ATTACK = 10;

    public Rogue() {
        super(FighterType.ROGUE.getBaseHealth(), MIN_ATTACK, MAX_ATTACK);
    }

    @Override
    public SpecialAction getSpecialAction() {
        return SpecialAction.DEFEND;
    }
}

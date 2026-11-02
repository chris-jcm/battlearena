package battlearena.model;

public class Warrior extends Fighter {

    private static final int BASE_HEALTH = 110;
    private static final int MIN_ATTACK = 7;
    private static final int MAX_ATTACK = 13;

    public Warrior() {
        super(BASE_HEALTH, MIN_ATTACK, MAX_ATTACK);
    }

    @Override
    public SpecialAction getSpecialAction() {
        return SpecialAction.DRAINANDHEAL;
 }

}

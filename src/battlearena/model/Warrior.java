package battlearena.model;

public class Warrior extends Fighter {

    public Warrior(String name, int healthPoints, int maxHealthPoints, int minDamage, int maxDamage) {
        super(name, healthPoints, maxHealthPoints, 7, 13);
    }

    @Override
    public SpecialAction getSpecialAction() {
        return SpecialAction.DRAINANDHEAL;
 }

}

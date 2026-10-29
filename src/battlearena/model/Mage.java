package battlearena.model;

public class Mage extends Fighter {

    public Mage(String name, int healthPoints, int maxHealthPoints, int minDamage, int maxDamage) {
        super(name, healthPoints, maxHealthPoints, 5, 11);
    }

    @Override
    public SpecialAction getSpecialAction() {
        return SpecialAction.HEAL;
    }
}

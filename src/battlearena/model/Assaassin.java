package battlearena.model;

public class Assaassin extends Fighter {

    public Assaassin(String name, int healthPoints, int maxHealthPoints, int minDamage, int maxDamage) {
        super(name, healthPoints, maxHealthPoints, 4, 10);
    }

    @Override
    public SpecialAction getSpecialAction() {
        return SpecialAction.DEFEND;
    }
}

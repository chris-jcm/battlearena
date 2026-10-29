package battlearena.model;

public class Assaassin extends Fighter {

    public Assaassin(String name, int healthPoints, int maxHealthPoints, int minDamage, int maxDamage) {
        super(name, healthPoints, maxHealthPoints, minDamage, maxDamage);
    }

    @Override
    public SpecialAction getSpecialAction() {
        return SpecialAction.DEFEND;
    }
}

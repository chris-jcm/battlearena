package battlearena.model;

import java.util.Random;

public abstract class Fighter implements Combatant {

    private final String name;
    private int healthPoints;
    private final int maxHealthPoints;

    private final int minAttackDamage;
    private final int maxAttackDamage;

    private final Random random = new Random();

    public Fighter(String name, int healthPoints, int maxHealthPoints, int minAttackDamage, int maxAttackDamage) {
        this.name = name;
        this.healthPoints = healthPoints;
        this.maxHealthPoints = maxHealthPoints;
        this.minAttackDamage = minAttackDamage;
        this.maxAttackDamage = maxAttackDamage;
    }

    public String getName() {
        return name;
    }

    public int getHealthPoints() {
        return healthPoints;
    }

    public int getMaxHealthPoints() {
        return maxHealthPoints;
    }


    //public abstract SpecialAction getSpecialAction();

    // hindrar att HP blir negativ och över maxHP
    public void setHealthPoints(int healthPoints) {
        this.healthPoints = Math.clamp(healthPoints,0, maxHealthPoints);
    }

    // genererar ett slummässigt skadevärde varje ny attack beroende på karaktärtyp
    @Override
    public int getAttackDamage() {
        return random.nextInt(maxAttackDamage - minAttackDamage + 1 ) + minAttackDamage;
    }
}

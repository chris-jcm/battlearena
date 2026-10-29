package battlearena.model;

import java.util.Random;

public abstract class Fighter implements Combatant {

    private final String name;
    private int healthPoints;
    private final int maxHealthPoints;

    private final int minDamage;
    private final int maxDamage;

    private final Random random = new Random();

    public Fighter(String name, int healthPoints, int maxHealthPoints, int minDamage, int maxDamage) {
        this.name = name;
        this.healthPoints = healthPoints;
        this.maxHealthPoints = maxHealthPoints;
        this.minDamage = minDamage;
        this.maxDamage = maxDamage;
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


    public abstract SpecialAction getSpecialAction();

    // hindrar att HP blir negativ och över maxHP
    public void setHealthPoints(int healthPoints) {
        this.healthPoints = Math.max(0, Math.min(healthPoints, maxHealthPoints));
    }

    // genererar ett slummässigt skadevärde varje ny attack beroende på karaktärtyp
    @Override
    public int getDamage() {
        return random.nextInt(maxDamage - minDamage + 1 ) + minDamage;
    }
}

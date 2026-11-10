package battlearena.model;

import java.util.Random;

public abstract class Fighter implements Combatant {

    private int healthPoints;
    private final int maxHealthPoints;

    private final int minAttackDamage;
    private final int maxAttackDamage;

    private boolean defending;

    private final Random random = new Random();

    public Fighter(int healthPoints, int minAttackDamage, int maxAttackDamage) {

        this.healthPoints = healthPoints;
        this.maxHealthPoints = healthPoints;
        this.minAttackDamage = minAttackDamage;
        this.maxAttackDamage = maxAttackDamage;
    }

    public int getHealthPoints() {
        return healthPoints;
    }
    public int getMaxHealthPoints() {return maxHealthPoints;}

    public void setHealthPoints(int healthPoints) {
        this.healthPoints = Math.clamp(healthPoints,0, maxHealthPoints);
    }

    @Override
    public int getAttackDamage() {
        return random.nextInt(minAttackDamage, maxAttackDamage + 1);
    }
    public void setIsDefending(boolean defending) {
        this.defending = defending;
    }

    public boolean isDefending() {
        return defending;
    }
}

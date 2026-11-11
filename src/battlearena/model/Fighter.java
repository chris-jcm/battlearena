package battlearena.model;

import java.util.concurrent.ThreadLocalRandom;

public abstract class Fighter implements Combatant {
    private int healthPoints;
    private final int maxHealthPoints;
    private final int minAttackDamage;
    private final int maxAttackDamage;
    private boolean defending;

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

    public int takeDamage(int damage) {
        int damageTaken = Math.clamp(damage, 0, healthPoints);
        healthPoints -= damageTaken;
        return damageTaken;
    }

    public int heal(int amount) {
        int healthBefore = healthPoints;
        healthPoints = Math.clamp(healthPoints + Math.max(0, amount), 0, maxHealthPoints);
        return healthPoints - healthBefore;
    }

    public int getAttackDamage() {
        return ThreadLocalRandom.current().nextInt(minAttackDamage, maxAttackDamage + 1);
    }

    public boolean isDefending() {
        return defending;
    }

    public void setIsDefending(boolean defending) {
        this.defending = defending;
    }
}

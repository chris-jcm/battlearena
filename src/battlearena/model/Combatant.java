package battlearena.model;

public interface Combatant {
   int getAttackDamage();
   SpecialAction getSpecialAction();
   int getHealthPoints();
   int takeDamage(int damage);
   int heal(int amount);
   void setIsDefending(boolean defending);
   boolean isDefending();
}

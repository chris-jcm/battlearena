package battlearena.model.fighter;

import battlearena.model.SpecialAction;

public interface FighterInfo {
    FighterType getType();
    SpecialAction getSpecialAction();
    int getMaxHealthPoints();
}

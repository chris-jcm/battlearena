package battlearena.model;

public class Player {
    private final String name;
    private final Boolean isComputer;
    private Fighter fighter;

    public Player(String name, Boolean isComputer) {
        this.name = name;
        this.isComputer = isComputer;
    }

    public String getName() {
        return name;
    }

    public Boolean getIsComputer() {
        return isComputer;
    }

    public Fighter getFighter() {
        return fighter;
    }

    public void setFighter(Fighter fighter) {
        this.fighter = fighter;
    }
}

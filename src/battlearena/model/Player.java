package battlearena.model;

public class Player {
    private String name;
    private final Boolean isComputer;
    private Fighter fighter;

    public Player(String name, Boolean isComputer) {
        this.name = name;
        this.isComputer = isComputer;
    }

    public String getName() {
        return name;
    }

    public Boolean isComputer() {
        return isComputer;
    }

    public Fighter getFighter() {
        return fighter;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFighter(Fighter fighter) {
        this.fighter = fighter;
    }
}

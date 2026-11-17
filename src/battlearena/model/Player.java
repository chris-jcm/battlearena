package battlearena.model;

public class Player {
    private String name;
    private final Boolean isComputer;
    private Fighter fighter;
    private FighterType fighterType;

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

    public FighterType getFighterType() {
        return fighterType;
    }

    public void setFighterType(FighterType fighterType) {
        this.fighterType = fighterType;
    }
}

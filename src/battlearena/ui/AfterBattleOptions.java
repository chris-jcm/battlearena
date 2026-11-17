package battlearena.ui;

public enum AfterBattleOptions implements MenuItem {
    PLAY_AGAIN(1, "Play again"),
    CHANGE_FIGHTER(2, "Change fighter"),
    MAIN_MENU(3, "Main menu");

    private final int code;
    private final String display_name;

    private AfterBattleOptions(int code, String display_name) {
        this.code = code;
        this.display_name = display_name;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getDisplayName() {
        return display_name;
    }

    public static AfterBattleOptions getByCode(int code) {
        for (AfterBattleOptions option : AfterBattleOptions.values()) {
            if (option.getCode() == code) {
                return option;
            }
        }
        return null;
    }
}

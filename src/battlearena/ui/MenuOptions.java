package battlearena.ui;

enum MenuOptions implements MenuItem {
    GAME(1, "Play"),
    SHOW_BATTLE_HISTORY(2, "Show battle history"),
    QUIT(3, "Quit");

    private final int code;
    private final String displayName;

    MenuOptions(int code, String description) {
        this.code = code;
        this.displayName = description;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getDisplayName() {
        return displayName;
    }

    public static MenuOptions getByCode(int code) {
        for (MenuOptions option : MenuOptions.values()) {
            if (option.getCode() == code) {
                return option;
            }
        }
        return null;
    }
}

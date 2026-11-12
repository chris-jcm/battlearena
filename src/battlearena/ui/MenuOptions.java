package battlearena.ui;

enum MenuOptions {
    GAME(1, "Play"),
    SHOW_BATTLE_HISTORY(4, "Show battle history"),
    QUIT(5, "Quit"),
    ;

    private final int code;
    private final String displayName;

    MenuOptions(int code, String description) {
        this.code = code;
        this.displayName = description;
    }

    public int getCode() {
        return code;
    }

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

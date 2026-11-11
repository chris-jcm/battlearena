package battlearena.ui;

enum MenuOptions {
    CREATE_PLAYER(1, "Create player"),
    CHOOSE_PLAYER(2, "Choose player"),
    CHOOSE_FIGHTER(3, "Choose fighter"),
    START_BATTLE(4, "Start battle"),
    SHOW_BATTLE_HISTORY(5, "Show battle history"),
    QUIT(6, "Quit"),
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

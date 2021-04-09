package app;

import java.util.*;

public class NavBar {
    private List<Button> buttons;

    NavBar() {
        initButtons();
    }

    private void initButtons() {
        this.buttons.add(new HomeButton());
    }
}

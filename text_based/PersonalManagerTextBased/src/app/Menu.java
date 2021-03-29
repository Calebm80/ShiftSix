package app;

import java.util.*;

public abstract class Menu {
    List<Button> buttons;

    Menu() {
        displayMenu();
    }

    abstract void displayMenu();

    public List<Button> getButtons() {
        
        return buttons;
    };

    protected void addButton(Button button) {
        buttons.add(button);
    }
}

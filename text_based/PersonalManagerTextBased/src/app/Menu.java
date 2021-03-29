package app;

import java.util.*;

public abstract class Menu implements MenuChangeListener {
    List<Button> buttons;

    Menu() {
        displayMenu();
        onMenuChangeEvent(this);
    }

    abstract void displayMenu();

    public List<Button> getButtons() {
        
        return buttons;
    };

    protected void addButton(Button button) {
        buttons.add(button);
    }
}

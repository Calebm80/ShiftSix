package app;

import java.util.*;

public abstract class Menu {
    protected List<Button> buttons;
    protected Scanner scanner;

    Menu() {
        this.buttons = new ArrayList<Button>();
    }

    protected abstract void displayMenu();

    protected void initScanner() {
        this.scanner = new Scanner(System.in);
    }

    public List<Button> getButtons() {
        return buttons;
    };

    protected void addButton(Button button) {
        this.buttons.add(button);
    }
}

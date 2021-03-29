package app;

import java.util.*;

public class MainMenu extends Menu {
    private List<Button> buttons;

    MainMenu() {
        displayMenu();
    }

    public void displayMenu() {
        System.out.println("Main Menu");
    }

    public List<Button> getButtons() {
        return this.buttons;
    }
}

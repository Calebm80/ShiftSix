package app;

import java.util.*;

public class Menu {
    protected List<Button> buttons;
    protected String menuID;

    Menu(String menuID) {
        this.buttons = new ArrayList<Button>();
        this.menuID = menuID;
    }

    public List<Button> getButtons() {
        return buttons;
    };

    protected void addButton(Button button) {
        this.buttons.add(button);
    }

    protected void handleInput() { // currently input handling has issues when finishing one action and beginning another - fix later
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Input button choice: ");
            String choice = scanner.nextLine();

            for (Button button : buttons) {
                String id = button.getID();
                if (id.equals(choice)) {
                    button.onClick();
                } else {
                    System.out.println("button does not exist");
                }
            }
        }
    }

    protected void displayMenu() {
        System.out.println("---" + menuID + "---");
        System.out.println("Button choices, type button id to simulate click:");

        for (Button button : buttons) {
            System.out.println(button.getID());
        }
    }

}

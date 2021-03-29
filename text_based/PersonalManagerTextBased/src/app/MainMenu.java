package app;

public class MainMenu extends Menu {
    MainMenu() {
        initMenu();
        displayMenu();
        handleInput();
    }

    private void initMenu() {
        addButton(new TestButton());
        initScanner();
    }

    protected void displayMenu() {
        System.out.println("Main Menu");
        System.out.println("Button choices, type button id to simulate click:");

        for (Button button : buttons) {
            System.out.println(button.getID());
        }
    }

    private void handleInput() {
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
}

package app;

public class MainMenu extends Menu {
    MainMenu() {
        super("MainMenu");
        initMenu();
        displayMenu();
        handleInput();
    }

    private void initMenu() {
        addButton(new TestButton());
        addButton(new ExpenseModuleMenuButton());
    }    
}

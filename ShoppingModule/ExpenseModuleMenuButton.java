package app;

public class ExpenseModuleMenuButton extends Button {
    ExpenseModuleMenuButton() {
        super("ExpenseModuleMenuButton");
    }

    public void onClick() {
        new ExpenseModuleMenu();
    }
}

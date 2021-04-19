package app;

public class ExpenseModuleMenu extends Menu {
    ExpenseList expenseList;

    ExpenseModuleMenu() {
        super("ExpenseModule");
        initMenu();
        displayMenu();
        handleInput();
    }

    private void initMenu() {
        this.expenseList = new ExpenseList();
        addButton(new AddExpenseButton(expenseList));
        addButton(new DisplayExpenseListButton(expenseList));
    }
}

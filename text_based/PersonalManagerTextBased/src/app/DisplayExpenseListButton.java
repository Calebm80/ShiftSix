package app;

public class DisplayExpenseListButton extends Button {
    ExpenseList expenseList; // this expenselist would be serialized to a common file which it would load
                             // from - containing it in button is a temp fix for testing

    DisplayExpenseListButton(ExpenseList list) {
        super("DisplayExpenseListButton");
        this.expenseList = list;
    }

    public void onClick() {
        expenseList.display();
    }
}

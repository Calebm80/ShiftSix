package app;

public class DisplayExpenseListButton extends Button {
    ExpenseList expenseList;

    DisplayExpenseListButton(ExpenseList list) {
        super("DisplayExpenseListButton");
        this.expenseList = list;
    }

    public void onClick() {
        expenseList.display();
    }
}

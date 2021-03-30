package app;

import java.util.Scanner;

public class AddExpenseButton extends Button {
    ExpenseList expenseList;

    AddExpenseButton(ExpenseList list) {
        super("AddExpenseButton");
        this.expenseList = list;
    }

    public void onClick() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input the note for the expense you'd like to add: ");
        String note = scanner.nextLine();
        System.out.println("Input the cost of the expense: ");
        Double cost = Double.parseDouble(scanner.nextLine());
        System.out.println("Input the type of the expense.");
        String type = scanner.nextLine();

        ExpenseItem.ExpenseType expenseType = ExpenseItem.ExpenseType.PERSONAL;
        try {
            expenseType = ExpenseItem.ExpenseType.valueOf(type);
        }
        catch (Exception e) {
            System.out.print("Invalid expense type, defaulting to Personal");
        }

        expenseList.addExpense(cost, note, expenseType);
    }
}

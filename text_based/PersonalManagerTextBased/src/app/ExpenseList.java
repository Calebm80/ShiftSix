package app;

import java.util.*;

public class ExpenseList {
    private List<ExpenseItem> expenseList;
    private double budget;

    ExpenseList() {
        initializeList();
        this.budget = 0;
    }

    private void initializeList() { // works as reset too, just re-call initialization
        this.expenseList = new ArrayList<ExpenseItem>();
    }

    // add expense function
    public void addExpense(Double cost, String note, ExpenseItem.ExpenseType type) {
        expenseList.add(new ExpenseItem(note, cost, type));
    }

    // remove expense at point function
    public void removeExpense(int i) {
        expenseList.remove(i);
        expenseList.remove(i);
    }

    public void display() {
        for (ExpenseItem expenseItem : expenseList) {
            System.out.println(expenseItem);
        }
    }

    // organize list function, broke this fix later
    /*public void organizeExpense() {
        bubbleSort();
    }*/

<<<<<<< HEAD
    void bubbleSort(ArrayList<Double> arr) {
=======
    void bubbleSort(ArrayList<Double> arr) { // ?
>>>>>>> develop
        int n = arr.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr.get(j) > arr.get(j + 1)) {
                    // swap arr[j+1] and arr[j]
                    double temp = arr.get(j);
                    arr.set(j, arr.get(j + 1));
                    arr.set(j + 1, temp);
                }
            }
        }
    }

    void changeBudget(double i) {
        this.budget = i;
    }
}
package app;

public class ExpenseItem {
    String note;
    double cost;
    ExpenseType type;

    public enum ExpenseType {
        FOOD, BILLS, MEDICAL, PERSONAL, PTP // what is PtP?
    }
    
    ExpenseItem(String note, double cost, ExpenseType type) {
        this.note = note;
        this.cost = cost;
        this.type = type;
    }
}

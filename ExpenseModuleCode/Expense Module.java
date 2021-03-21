// add an expense
    //add note to expense
    //list of basic expenditure
        //food
        //bills
        //medical
        //personal
        //person to person transfer





// remove expense
    // shows a list of added expenses to remove one


// reset lists



// show top/bottom/etc expense



// expected monthly expenditure


// add budget (compare with expected)


public class ExpenseModule {
    public static void main(String[] args) {

            Lists theList;
            theList.initializeList();
            theList.changeBudget(350.50);
            print(theList.budget);
            theList.addExpense(theList,23.20,"Tissues");
            theList.addExpense(theList,44.30,"Tuxedo");
            theList.addExpense(theList,1.50,"Gum");
            theList.organizeExpense();

    }

}
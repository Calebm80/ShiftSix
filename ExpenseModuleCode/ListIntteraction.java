
import java.io.*;
import java.util.*;

public class ListIntteraction{

public int budget = 0;


public void initializeList(){
    Lists food = new Lists(); // food list
    Lists bills = new Lists();
    Lists medical = new Lists();
    Lists personal = new Lists();
    Lists PtP = new Lists();

    Lists[] listTracker = new Lists[5];
    listTracker[0] = food;
    listTracker[1] = bills;
    listTracker[2] = medical;
    listTracker[3] = personal;
    listTracker[4] = PtP;

}

public Lists[] resetList(Lists[] list){

    for(int i = 0; i < list.length ; i++){
        list[i] = null;
    }
    list = null;

    Lists food = new Lists(); // food list
    Lists bills = new Lists();
    Lists medical = new Lists();
    Lists personal = new Lists();
    Lists PtP = new Lists();

    Lists[] listTracker = new Lists[5];
    listTracker[0] = food;
    listTracker[1] = bills;
    listTracker[2] = medical;
    listTracker[3] = personal;
    listTracker[4] = PtP;

    return listTracker;

}

//add expense function

public void addExpense(Lists list, Double i, String note){

    list.myNotes.add(note);
    list.myNumbers.add(i);

}

//remove expense at point function

public void removeExpense(Lists list, int i){

    list.myNotes.remove(i);
    list.myNumbers.remove(i);

}

//organize list function
public void organizeExpense(Lists list){

    bubbleSort(list.organizeNumbers, list.organizeNotes);

}

void bubbleSort(ArrayList<Double> arr, ArrayList<String> note){ 
        int n = arr.size(); 
        for (int i = 0; i < n-1; i++) 
            for (int j = 0; j < n-i-1; j++) 
                if (arr.get(j) > arr.get(j+1)) 
                { 
                    // swap arr[j+1] and arr[j] 
                    double temp = arr.get(j); 
                    arr.set(j,arr.get(j+1));
                    arr.set(j+1, temp);

                    // swap notes in same way
                    String tempNote = note.get(j); 
                    note.set(j,note.get(j+1));
                    note.set(j+1, tempNote);

                } 
    }

//show top expense
    //simply give someone the organized lsits


// expected monthly expenditure
    //do on the first of every month

//change budget
void changeBudget (int i){

    budget = i;

}

//check budget
void checkBudget{
    
}
//


}
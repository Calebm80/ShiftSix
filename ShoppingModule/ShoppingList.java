package app;

import java.util.*;
import java.io.*;

public class ShoppingList {

    private List<ShoppingItem> shoppingList;
    private double totalCost;
    private double salesTax;
    private double finalCost;
    
    ShoppingList() {
        initializeList();
        this.totalCost = 0;
    }

    private void initializeList() { // works as reset too, just re-call initialization
        this.shoppingList = new ArrayList<ShoppingItem>();
    }

    // add expense function
    public void addItem(Double cost, String name) {
        shoppingList.add(new ShoppingItem(name, cost));
    }

    // remove expense at point function
    public void removeItem(int i) {
        shoppingList.remove(i);
        //shoppingList.remove(i); idk why you put this in twice
    }

    public void display() {
        for (ShoppingItem shoppingItem : shoppingList) {
            System.out.println(shoppingItem);
        }
    }

    public void checkItem(int i){
        //sets check to true
        shoppingList.get(i).setTrue;
    }

    public void addCost(){
        for (ShoppingItem shoppingItem : shoppingList) {
            this.totalCost += shoppingItem.cost;
        }
    }

    public void addSalesTax(double i){
        thi.salesTax = i;
    }

    public calcTotal(){
        this.finalCost = (this.salesTax * this.totalCost) + this.totalCost;
    }

    //idk how to do this quite, but just add final cost to expenses
    // public void addToExpense(){
        
    // }

}

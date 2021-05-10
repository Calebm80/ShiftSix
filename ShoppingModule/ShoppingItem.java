package app;

import java.util.*;
import java.io.*;

public class ShoppingItem {
    String name;
    double cost;
    boolean check;

    ExpenseItem(String name, double cost) {
        this.name = name;
        this.cost = cost;
        this.check = false;
    }

    void setTrue(){
        this.check = true;
    }
}

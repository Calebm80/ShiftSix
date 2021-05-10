public class ShoppingModuleMenu {
    
}
package app;

public class ShoppingModuleMenu extends Menu {
    ShoppingList ShoppingList;

    ShoppingModuleMenu() {
        super("ShoppingModule");
        initMenu();
        displayMenu();
        handleInput();
    }

    private void initMenu() {
        this.shoppingList = new ShoppingList();
        addButton(new AddShoppingButton(shoppingList));
        addButton(new DisplayShoppingListButton(shoppingList));
    }
}

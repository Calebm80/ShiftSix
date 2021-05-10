package app;

public class ShoppingModuleMenuButton extends Button {
    ShoppingModuleMenuButton() {
        super("ShoppingModuleMenuButton");
    }

    public void onClick() {
        new ShoppingModuleMenu();
    }
}

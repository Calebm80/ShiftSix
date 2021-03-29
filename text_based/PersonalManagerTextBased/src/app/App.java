package app;

public class App {
    private Menu currentMenu;
    private MenuChangeListener mListener;

    public static void main(String[] args) {
        App app = new App();
    }
    
    App() {
        this.mListener = new MenuChangeListener(){
            public void onMenuChangeEvent(Menu menu) {
                currentMenu = menu;
            }
        };
        new MainMenu();
    }
}

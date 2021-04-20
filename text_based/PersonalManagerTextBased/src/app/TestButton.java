package app;

public class TestButton extends Button { // temp button added for testing - remove later
    TestButton() {
        super("TestButton");
    }

    public void onClick() {
        System.out.println("test button clicked");
    }
}

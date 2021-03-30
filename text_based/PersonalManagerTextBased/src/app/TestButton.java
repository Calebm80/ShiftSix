package app;

public class TestButton extends Button {
    TestButton() {
        super("TestButton");
    }

    public void onClick() {
        System.out.println("test button clicked");
    }
}

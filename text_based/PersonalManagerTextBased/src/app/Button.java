package app;

public abstract class Button implements iClickable {
    private String id; // all buttons and UI elements MUST have an id to interact with android studio

    Button(String id) {
        this.id = id;
    }

    public String getID() {
        return this.id;
    }

    public abstract void onClick();
}

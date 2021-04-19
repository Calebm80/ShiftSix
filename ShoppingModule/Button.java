package app;

public abstract class Button implements iClickable {
    private String id;

    Button(String id) {
        this.id = id;
    }

    public String getID() {
        return this.id;
    }

    public abstract void onClick();
}

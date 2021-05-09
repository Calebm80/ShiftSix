package com.example.shiftsix.containers;

/*
/   This class is being used as a wrapper for preferences to avoid some of the absolute mess created when
/   dynamically adding settings to the settings menu, rather than manually putting them all in. This is
/   probably overkill, but the default method of doing it felt super janky.
 */
public class Preference {
    private String name; // the name to be shown in settings
    private int id; // id as shown in the reserved_ids.xml file - MUST be manually added there and will be used as key when saving/loading preferences
    private int value; // the value of the preference

    public Preference(String name, int id, int value) {
        this.name = name;
        this.id = id;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public String getIdAsString() {
        return String.valueOf(id);
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}

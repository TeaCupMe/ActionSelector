package space.crtech.utils;

public abstract class UserAction {
    final String name;

    public UserAction(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    abstract public void act();
}

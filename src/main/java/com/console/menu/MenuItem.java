package com.console.menu;

public class MenuItem {
    private int id;
    private String name;
    private Runnable action;
    private Menu subMenu;

    public MenuItem(int id, String name, Runnable action) {
        this.id = id;
        this.name = name;
        this.action = action;
    }

    public MenuItem(int id, String name, Menu subMenu) {
        this.id = id;
        this.name = name;
        this.subMenu = subMenu;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Runnable getAction() {
        return action;
    }

    public Menu getSubMenu() {
        return subMenu;
    }

    public boolean hasSubMenu() {
        return subMenu != null;
    }
}

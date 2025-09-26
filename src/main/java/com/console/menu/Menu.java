package com.console.menu;

import java.util.ArrayList;
import java.util.List;

public class Menu {
    private String title;
    private List<MenuItem> items;

    public Menu(String title) {
        this.title = title;
        this.items = new ArrayList<>();
    }

    public void addItem(MenuItem item) {
        items.add(item);
    }

    public String getTitle() {
        return title;
    }

    public List<MenuItem> getItems() {
        return items;
    }

    public MenuItem getItem(int id) {
        return items.stream()
                .filter(item -> item.getId() == id)
                .findFirst()
                .orElse(null);
    }
}

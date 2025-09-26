package com.console;

import com.console.config.AppConfig;
import com.console.engine.Engine;
import com.console.menu.Menu;
import com.console.menu.MenuItem;

public class Main {
    public static void main(String[] args) {
        AppConfig config = new AppConfig(
            "Console Menu Framework",
            "1.0.0",
            "Jeyor1337",
            "https://github.com/Jeyor1337/console-menu"
        );

        Engine engine = new Engine(config);

        Menu mainMenu = new Menu("Main Menu");
        Menu userMenu = new Menu("User Management");
        Menu settingsMenu = new Menu("Settings");

        userMenu.addItem(new MenuItem(1, "Add User", () -> System.out.println("Adding user...")));
        userMenu.addItem(new MenuItem(2, "Delete User", () -> System.out.println("Deleting user...")));
        userMenu.addItem(new MenuItem(3, "List Users", () -> System.out.println("Listing users...")));

        settingsMenu.addItem(new MenuItem(1, "General Settings", () -> System.out.println("Showing general settings...")));
        settingsMenu.addItem(new MenuItem(2, "Security Settings", () -> System.out.println("Showing security settings...")));

        mainMenu.addItem(new MenuItem(1, "User Management", userMenu));
        mainMenu.addItem(new MenuItem(2, "Settings", settingsMenu));
        mainMenu.addItem(new MenuItem(3, "About", () -> {
            System.out.println("About this application:");
            System.out.println("Name: " + config.getAppName());
            System.out.println("Version: " + config.getVersion());
            System.out.println("Author: " + config.getAuthor());
            System.out.println("Repository: " + config.getRepoUrl());
        }));

        engine.setMainMenu(mainMenu);
        engine.start();
    }
}

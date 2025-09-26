package com.console.engine;

import com.console.config.AppConfig;
import com.console.menu.Menu;
import com.console.menu.MenuItem;

import java.util.Scanner;
import java.util.Stack;

public class Engine {
    private AppConfig config;
    private Menu mainMenu;
    private Stack<Menu> menuStack;
    private Scanner scanner;
    private boolean running;

    public Engine(AppConfig config) {
        this.config = config;
        this.menuStack = new Stack<>();
        this.scanner = new Scanner(System.in);
        this.running = true;
    }

    public void setMainMenu(Menu menu) {
        this.mainMenu = menu;
    }

    public void showWelcome() {
        clearScreen();
        System.out.println("====================================");
        System.out.println("Welcome to " + config.getAppName());
        System.out.println("Version: " + config.getVersion());
        System.out.println("Author: " + config.getAuthor());
        System.out.println("Repository: " + config.getRepoUrl());
        System.out.println("====================================");
        System.out.println("\nPress Enter to continue...");
        scanner.nextLine();
    }

    public void start() {
        if (mainMenu == null) {
            throw new IllegalStateException("Main menu not set!");
        }

        showWelcome();
        menuStack.push(mainMenu);

        while (running) {
            showCurrentMenu();
            handleMenuSelection();
        }
    }

    private void showCurrentMenu() {
        clearScreen();
        Menu currentMenu = menuStack.peek();
        System.out.println("\n=== " + currentMenu.getTitle() + " ===");
        
        for (MenuItem item : currentMenu.getItems()) {
            System.out.println(item.getId() + ". " + item.getName());
        }
        System.out.println("0. " + (menuStack.size() > 1 ? "Return" : "Exit"));
        System.out.print("\nPlease select an option: ");
    }

    private void handleMenuSelection() {
        try {
            int choice = Integer.parseInt(scanner.nextLine().trim());
            Menu currentMenu = menuStack.peek();

            if (choice == 0) {
                if (menuStack.size() > 1) {
                    menuStack.pop();
                } else {
                    running = false;
                    System.out.println("Thank you for using " + config.getAppName());
                }
                return;
            }

            MenuItem selectedItem = currentMenu.getItem(choice);
            if (selectedItem == null) {
                System.out.println("Invalid option! Press Enter to continue...");
                scanner.nextLine();
                return;
            }

            if (selectedItem.hasSubMenu()) {
                menuStack.push(selectedItem.getSubMenu());
            } else if (selectedItem.getAction() != null) {
                selectedItem.getAction().run();
                System.out.println("\nPress Enter to continue...");
                scanner.nextLine();
            }
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number! Press Enter to continue...");
            scanner.nextLine();
        }
    }

    private void clearScreen() {
        try {
            final String os = System.getProperty("os.name");
            if (os.contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            // Fallback for clearing screen
            for (int i = 0; i < 50; i++) {
                System.out.println();
            }
        }
    }
}

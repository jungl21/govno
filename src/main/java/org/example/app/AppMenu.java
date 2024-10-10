package org.example.app;


import org.example.data.DbConnectionImpl;
import org.example.data.Stuff;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class AppMenu {
    public static void start() {
        System.out.println("System started");
        showMenu();
    }

    private static void showMenu() {
        System.out.println("1 - Sign up");
        System.out.println("2 - Login");
        System.out.println("3 - Exit");

        checkUserChoice(getUserChoice());
    }

    private static void checkUserChoice(int userChoice) {
        switch (userChoice) {
            case 1 -> showSingMenu();
            case 2 -> showLoginMenu();
            case 3 -> System.exit(0);
            default -> {
                System.out.println("An incorrect selection was entered." + " Please, try again later.");
                showMenu();
            }
        }
    }

    private static void showLoginMenu() {
        var name = "";
        var surname = "";
        var patroymic = "";
        var password = "";

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter your Surname:");
        surname = sc.nextLine();

        System.out.println("Enter your Name: ");
        name = sc.nextLine();

        System.out.println("Enter your Patroymic:");
        patroymic = sc.nextLine();

        System.out.println("Enter password");
        password = sc.nextLine();

        System.out.println("Enter job_title");
        Stuff.checkJob();


        try {
            DbConnectionImpl impl = new DbConnectionImpl();
            impl.insertStuff(new Stuff(surname,name, patroymic, password));
            impl.connect().close();
        }
        catch (SQLException e) {
            System.out.println("Failure");
        }
        showMenu();
    }

    private static void showSingMenu() {
        var login = "";
        var password = "";

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter your login:");
        login = sc.nextLine();
        System.out.println("Enter your password:");
        password = sc.nextLine();

        try {
            DbConnectionImpl impl = new DbConnectionImpl();
            impl.select();
            impl.connect().close();
        } catch (SQLException e) {
            System.out.println("Failure");
        }
        showMenu();
    }

    private static int getUserChoice() {
        var choice = 0;
        try {
            Scanner sc = new Scanner(System.in);
            choice = sc.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Incorrect menu entry type. Try again.");
            getUserChoice();
        }
        return choice;
    }















}









package org.example.app;


import org.example.data.CheckJob;
import org.example.data.DbConnectionImpl;
import org.example.data.Stuff;
import org.example.workers.Administrator;
import org.example.workers.Engineer;
import org.example.workers.Manager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.InputMismatchException;
import java.util.Scanner;

import static org.example.data.Encryption.encrypt;

public class AppMenu {
    public static void start() {
        System.out.println("System started");
        showMenu();
    }

    public static void showMenu() {
        System.out.println("1 - Log in");
        System.out.println("2 - Sign up");
        System.out.println("3 - Exit");

        checkUserChoice(getUserChoice());
    }

    private static void checkUserChoice(int userChoice) {
        switch (userChoice) {
            case 1 -> showSignMenu();
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
        var patronymic = "";
        var password = "";

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter your Surname:");
        surname = sc.nextLine();

        System.out.print("Enter your Name: ");
        name = sc.nextLine();

        System.out.print("Enter your Patronymic:");
        patronymic = sc.nextLine();

        System.out.print("Enter Password: ");
        password = sc.nextLine();


        System.out.print("Enter Job title: ");

        var job = CheckJob.checkJob(sc.nextLine());
        try {
            DbConnectionImpl impl = new DbConnectionImpl();
            impl.insertStuff(new Stuff(surname, name, patronymic, password, job));
            impl.connect().close();
        } catch (SQLException e) {
            System.out.println("Failure");
        }
        showMenu();
    }

    private static void showSignMenu() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter your login: ");
        String login = sc.nextLine();
        System.out.print("Enter your password: ");
        String password = sc.nextLine();

        checkData(login, password);
    }

    private static void checkData(String login, String password) {
        DbConnectionImpl dbConnection = new DbConnectionImpl();
        try (PreparedStatement stmt = dbConnection.connect().prepareStatement(
                "SELECT * FROM name_stuff WHERE login = ? AND password = ?")) {
            stmt.setString(1, login);
            stmt.setString(2, encrypt(password));
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                Stuff user= new Stuff(rs.getString("name"),
                        rs.getString("surname"),
                        rs.getString("patronymic"),
                        rs.getString("job_title"),
                        rs.getDouble("salary"),
                        rs.getString("id")
                        );
                greetUser(user);
                showAppMenu(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void showAppMenu(Stuff user) {
        switch (user.getJob_title().toUpperCase()) {
            case "MANAGER" -> Manager.showMenuManager(user);
            case "ENGINEER" -> Engineer.showMenuEngineer();
            case "ADMINISTRATOR" -> Administrator.showMenuAdministrator();
        }
    }

    private static void greetUser(Stuff user) throws SQLException {
        int currentHour = LocalTime.now().getHour();
        String greeting;
        if (currentHour >= 4 && currentHour < 12) {
            greeting = "Good morning";
        } else if (currentHour >= 12 && currentHour < 17) {
            greeting = "Good day";
        } else if (currentHour >= 17 && currentHour < 22) {
            greeting = "Good evening";
        } else {
            greeting = "Good night";
        }
        System.out.println(greeting + ", " + user.getName() + ".");
        System.out.println(user.getId() + " " + user.getSurname() + " " + user.getName() + " "
                + user.getPatronymic() + ", " + user.getJob_title());

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









package org.example.app;


//import org.example.data.CheckJob;
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
        System.out.println("Система работает");
        showMenu();
    }

    public static void showMenu() {
        System.out.println("1 - Войти: ");
        System.out.println("2 - Регистрация: ");
        System.out.println("3 - Выйти: ");

        checkUserChoice(getUserChoice());
    }

    private static void checkUserChoice(int userChoice) {
        switch (userChoice) {
            case 1 -> showSignMenu();
            case 2 -> showLoginMenu();
            case 3 -> System.exit(0);
            default -> {
                System.out.println("Был введен неправильный выбор." + " Пожалуйста, попробуйте ещё раз.");
                showMenu();
            }
        }
    }

    private static void showLoginMenu() {
        var name = "";
        var surname = "";
        var middle_name = "";
        var password = "";
        var job = "";

        Scanner sc = new Scanner(System.in);

        System.out.print("Введите вашу фамилию:");
        surname = sc.nextLine();

        System.out.print("Введите ваше имя: ");
        name = sc.nextLine();

        System.out.print("Введите ваше отчество:");
        middle_name = sc.nextLine();

        System.out.print("Введите пароль: ");
        password = sc.nextLine();


        System.out.print("Введите вашу должность: ");
        job = sc.nextLine();
        try {
            DbConnectionImpl impl = new DbConnectionImpl();
            impl.insertStuff(new Stuff(surname, name, middle_name, password, job));
            impl.connect().close();
        } catch (SQLException e) {
            System.out.println("Ошибка");
        }
        showMenu();
    }

    private static void showSignMenu() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Введите ваш логин: ");
        String login = sc.nextLine();
        System.out.print("Введите ваш пароль: ");
        String password = sc.nextLine();

        checkData(login, password);
    }

    private static void checkData(String login, String password) {
        DbConnectionImpl dbConnection = new DbConnectionImpl();
        try (PreparedStatement stmt = dbConnection.connect().prepareStatement(
                "SELECT * FROM test_tb WHERE login = ? AND password = ?")) {
            stmt.setString(1, login);
            stmt.setString(2, encrypt(password));
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                Stuff user= new Stuff(rs.getString("name"),
                        rs.getString("surname"),
                        rs.getString("middle_name"),
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
            greeting = "Доброе утро";
        } else if (currentHour >= 12 && currentHour < 17) {
            greeting = "Добрый день";
        } else if (currentHour >= 17 && currentHour < 22) {
            greeting = "Доброе вечер";
        } else {
            greeting = "Доброй ночи";
        }
        System.out.println(greeting + ", " + user.getName() + ".");
        System.out.println(user.getId() + " " + user.getSurname() + " " + user.getName() + " "
                + user.middle_name() + ", " + user.getJob_title());

    }

    private static int getUserChoice() {
        var choice = 0;
        try {
            Scanner sc = new Scanner(System.in);
            choice = sc.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Неправильный тип пункта меню. Попробуйте еще раз.");
            getUserChoice();
        }
        return choice;
    }


    private static class CheckJob {
    }
}









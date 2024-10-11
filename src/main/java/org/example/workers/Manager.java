package org.example.workers;

import org.example.app.AppMenu;
import org.example.data.DbConnectionImpl;
import org.example.data.Stuff;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Manager {
    public static void showMenuManager(Stuff user) {
        System.out.println("The following rights are available to you to work");
        System.out.println("1 - You can see your salary");
        System.out.println("2 - You can view your name, surname and patronymic in db.");
        System.out.println("3 - You can view salary everyone stuff");
        System.out.println("4 - Log out");

        getUserMenuChoice(user);
    }

    private static void getUserMenuChoice(Stuff user) {
        Scanner sc = new Scanner(System.in);
        switch (sc.nextLine()) {
            case "1" -> showFirst(user);
            case "2" -> showSecond(user);
            case "3" -> showThird();
            case "4" -> showFourth();
            default -> {
                System.out.println("Incorrect choice. PLease, try again.");
                showMenuManager(user);
            }
        }
    }

    private static void showFourth() {
        AppMenu.showMenu();
    }

    private static void showThird() {
        selectUsersData();
    }

    private static void selectUsersData() {
        DbConnectionImpl dbConnection = new DbConnectionImpl();
        try {
            String  request = "SELECT * FROM public.name_stuff";
            Statement statement = dbConnection.connect().createStatement();
            ResultSet resultSet = statement.executeQuery(request);

            while (resultSet.next()) {
                var id = resultSet.getString("id");
                var name = resultSet.getString("name");
                var surname = resultSet.getString("surname");
                var patronymic = resultSet.getString("patronymic");
                var job_title = resultSet.getString("job_title");
                var salary = resultSet.getDouble("salary");

                System.out.println(id + " " + name + " " + surname + " " + salary);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    private static void showSecond(Stuff stuff) {
        System.out.println("Your data in db: " + "Your name - " + stuff.getName() + ", your surname - " +
                stuff.getSurname() + ", your patronymic - " + stuff.getPatronymic());
    }

    private static void showFirst(Stuff stuff) {
        //TODO() - result of first menu
        System.out.println("Your salary:  " + stuff.getSalary());
    }




}

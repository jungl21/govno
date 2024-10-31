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
        System.out.println("Вам доступны следующие права для работы");
        System.out.println("1 - Вы можете видеть свою зарплату");
        System.out.println("2 - Посмотреть свое имя, фамилию и отчество вы можете в БД.");
        System.out.println("3 - Вы можете просмотреть информацию о зарплате всех");
        System.out.println("4 - Выйти");

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
                System.out.println("Неправильный выбор. Пожалуйста, попробуйте еще раз.");
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
            String  request = "SELECT * FROM public.test_tb";
            Statement statement = dbConnection.connect().createStatement();
            ResultSet resultSet = statement.executeQuery(request);

            while (resultSet.next()) {
                var id = resultSet.getString("id");
                var name = resultSet.getString("name");
                var surname = resultSet.getString("surname");
                var middle_name = resultSet.getString("middle_name");
                var job_title = resultSet.getString("job_title");
                var salary = resultSet.getDouble("salary");

                System.out.println(id + " " + name + " " + surname + " " + salary);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    private static void showSecond(Stuff stuff) {
        System.out.println("Ваши данные в БД:" + "Ваше имя " + stuff.getName() + ", твоя фамилия - " +
                stuff.getSurname() + ", твое отчество - " + stuff.middle_name());
    }

    private static void showFirst(Stuff stuff) {
        System.out.println("Ваша зарплата:" + stuff.getSalary());
    }




}

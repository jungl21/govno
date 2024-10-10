package org.example.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbConnectionImpl implements DbConnection {
    @Override
    public void select() {
        try {
            String  request = "SELECT * FROM public.name_stuff";
            Statement statement = connect().createStatement();
            ResultSet resultSet = statement.executeQuery(request);

            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                String patronymic = resultSet.getString("patronymic");
                String job_title = resultSet.getString("job_title");
                String login = resultSet.getString("login");
                String password = resultSet.getString("password");
                var salary = resultSet.getDouble("salary");

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public boolean insertStuff(Stuff stuff) {
        try {
            var request = "INSERT INTO public.name_stuff(id, name, surname, patronymic, login, password, job_title, salary) " +
                    "VALUES(?, ?, ?, ?, ?, ?, ?, ?)";

            var connection = connect();

            var prepareStatement = connection.prepareStatement(request, Statement.RETURN_GENERATED_KEYS);


            prepareStatement.setString(1, IdGenerator.generatedId(stuff));
            prepareStatement.setString(2, stuff.getName());
            prepareStatement.setString(3, stuff.getSurname());
            prepareStatement.setString(4, stuff.getPatronymic());
            prepareStatement.setString(5, stuff.getLogin());
            prepareStatement.setString(6, stuff.getPassword());
            prepareStatement.setString(7, stuff.getJob_title());
            prepareStatement.setDouble(8, stuff.getSalary());


            prepareStatement.executeUpdate();
            System.out.println("Data inserted successfully!");
        }
        catch (SQLException e) {
            System.out.println("Data insertion failed!");
            System.out.println(e);
        }
        return false;
    }

    @Override
    public void update() {

    }

    @Override
    public void delete() {

    }

    public int countObjects() {
        var size = 0;
        try {
            String request = "SELECT * FROM public.name_stuff";
            var statement = connect().createStatement();
            ResultSet resultSet = statement.executeQuery(request);

            while (resultSet.next()) {
                size++;
            }
        } catch (SQLException e) {
            System.out.println("Cannot load data from db. Please try again.");
            throw new RuntimeException(e);
        }
        return size;
    }

}

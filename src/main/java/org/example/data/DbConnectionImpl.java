package org.example.data;

import java.sql.*;

public class DbConnectionImpl implements DbConnection {
    @Override
    public void select() {
        try {
            String  request = "SELECT * FROM public.test_tb";
            Statement statement = connect().createStatement();
            ResultSet resultSet = statement.executeQuery(request);

            while (resultSet.next()) {
                long id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                String middle_name = resultSet.getString("middle_name");
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
            var request = "INSERT INTO public.test_tb(id, name, surname," +
                    " middle_name, login, password, job_title, salary) " +
                    "VALUES(?, ?, ?, ?, ?, ?, ?, ?)";

            var connection = connect();

            var prepareStatement = connection.prepareStatement(request, Statement.RETURN_GENERATED_KEYS);


            prepareStatement.setString(1, IdGenerator.generatedId(stuff));
            prepareStatement.setString(2, stuff.getName());
            prepareStatement.setString(3, stuff.getSurname());
            prepareStatement.setString(4, stuff.middle_name());
            prepareStatement.setString(5, stuff.getLogin());
            prepareStatement.setString(6, Encryption.encrypt(stuff.getPassword()));
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
            String request = "SELECT * FROM public.test_tb";
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

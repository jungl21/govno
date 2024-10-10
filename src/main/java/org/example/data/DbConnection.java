package org.example.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public interface DbConnection {
    default Connection connect() {
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/employee_authorization",
                    "postgres",
                    "admin"
            );
           // System.out.println("Connection succesed!");
            return connection;
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Connection failed. Please, try again later.");
            return null;
        }
    }

    void select();
    boolean insertStuff(Stuff stuff);
    void update();
    void delete();
}


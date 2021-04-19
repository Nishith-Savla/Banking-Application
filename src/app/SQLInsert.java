package app;

//libraries

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

class SQLInsert {

    Connection connection = null;
    PreparedStatement preparedStatement = null;

    public SQLInsert(String name, String username, String password, short age, String gender, String email) {
        try {
            connection = DriverManager.getConnection(
                    System.getenv("BANKING_APP_DATABASE_URL"),
                    System.getenv("MYSQL_USERNAME"),
                    System.getenv("MYSQL_PASSWORD")
            );
            preparedStatement = connection.prepareStatement(
                    "INSERT INTO 'bank_account' ('Name', 'AccNo', 'Password', 'Age', 'Gender', 'Email') VALUES (?, ?, ?, ?, ?, ?)");
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, username);
            preparedStatement.setString(3, password);
            preparedStatement.setShort(4, age);
            preparedStatement.setString(5, gender);
            preparedStatement.setString(6, email);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}

package app;

import java.sql.*;

public class GetBalance {
    public double getBalance(String accNo, double balance) {
        Connection connection;
        Statement statement;
        ResultSet resultSet;
        String query = "Select * from bank_account";
        try {
            connection = DriverManager.getConnection(
                    System.getenv("BANKING_APP_DATABASE_URL"),
                    System.getenv("MYSQL_USERNAME"),
                    System.getenv("MYSQL_PASSWORD")
            ); // Establishing connection
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                if (accNo.equals(resultSet.getString("AccNo"))) {
                    balance = resultSet.getDouble("Balance");
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return balance;

    }

}

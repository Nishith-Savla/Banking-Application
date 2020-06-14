package app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

// @author Nishith
public class Get_balance
{

    public double get_bal(String accNo, double balance)
    {
        Connection mycon = null;
        Statement mystm = null;
        ResultSet myres = null;
        String query = "Select * from bank_account";
        try {
            mycon = DriverManager.getConnection("Database Url", "Username", "Your Password");
            mystm = mycon.createStatement();
            myres = mystm.executeQuery(query);
            while (myres.next()) {
                if (accNo.equals(myres.getString("AccNo"))) {
                    balance = myres.getDouble("Balance");
                }
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
        return balance;

    }

}

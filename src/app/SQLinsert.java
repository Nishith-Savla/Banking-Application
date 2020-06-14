package app;

//libraries
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

class SQLinsert
{

    Connection conn = null;
    PreparedStatement pstat = null;

    public SQLinsert(String name, String username, String password, short age, String gender, String email)
    {
        try {
            conn = DriverManager.getConnection("Database Url", "Username", "Your Password");
            pstat = conn.prepareStatement("INSERT INTO bank_account (Name, AccNo, Password, Age, Gender, Email) VALUES (?, ?, ?, ?, ?, ?)");
            pstat.setString(1, name);
            pstat.setString(2, username);
            pstat.setString(3, password);
            pstat.setShort(4, age);
            pstat.setString(5, gender);
            pstat.setString(6, email);

            pstat.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e);
        }
    }

}

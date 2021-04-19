package app;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

class Login extends JFrame {

    // <editor-fold defaultstate="collapsed" desc="Variables' Declaration">
    JLabel accNoLabel, passwordLabel;
    JTextField accNoTextField;
    JPasswordField passwordField;
    JButton loginButton, registerButton;
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    //</editor-fold>

    public Login() {
        super("LOG IN PAGE "); // Title

        // Label for accNo
        accNoLabel = new JLabel("ACCOUNT NO:");
        accNoLabel.setBounds(20, 60, 130, 20);
        accNoLabel.setFont(new FontUIResource("Castellar", FontUIResource.BOLD, 18));
        add(accNoLabel);

        // TextField for accNo
        accNoTextField = new JTextField("BKAC");
        accNoTextField.setBounds(165, 60, 160, 20);
        accNoTextField.setFont(new FontUIResource("Verdana", FontUIResource.PLAIN, 13));
        add(accNoTextField);

        // Label for password
        passwordLabel = new JLabel(" PASSWORD  :");
        passwordLabel.setBounds(20, 100, 130, 20);
        passwordLabel.setFont(new FontUIResource("Castellar", FontUIResource.BOLD, 18));
        add(passwordLabel);

        // Password field for password
        passwordField = new JPasswordField();
        passwordField.setBounds(165, 100, 160, 20);
        add(passwordField);

        // Button for login
        loginButton = new JButton("LOG IN");
        loginButton.setBounds(135, 150, 100, 30);
        loginButton.setFont(new FontUIResource("Engravers MT", FontUIResource.BOLD, 13));
        loginButton.setForeground(ColorUIResource.WHITE);
        loginButton.setBackground(ColorUIResource.black);
        // <editor-fold defaultstate="collapsed" desc="Login Button Action and Key Listener(Adapter)">
        loginButton.addActionListener(ae -> {
            String password = new String(passwordField.getPassword());
            verify(accNoTextField.getText(), password); // Verify credentials
        });
        loginButton.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent ke) {
                if (ke.getKeyCode() == KeyEvent.VK_ENTER) // Triggered on pressing enter
                {
                    String password = new String(passwordField.getPassword());
                    verify(accNoTextField.getText(), password); // Verify credentials
                }
            }

        });
//</editor-fold>
        add(loginButton);

        // Register button
        registerButton = new JButton("New here... First register yourself");
        registerButton.setBounds(50, 300, 250, 30);
        registerButton.setFont(new FontUIResource("Engravers MT", FontUIResource.BOLD, 13));
        registerButton.setBorder(null);
        registerButton.setBackground(ColorUIResource.cyan);
        // <editor-fold defaultstate="collapsed" desc="Register Button Action and Key Listener(Adapter)">
        // ACTION LISTENER
        registerButton.addActionListener(ae -> {
            dispose();
            new Register();
        });
        registerButton.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent ke) {
                if (ke.getKeyCode() == KeyEvent.VK_ENTER) {
                    dispose();
                    new Register();
                }
            }

        });
//</editor-fold>
        add(registerButton);

        // Frame Constraints
        getContentPane().setBackground(ColorUIResource.cyan);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setLayout(null);
        setBounds(((int) screenSize.getWidth() / 2 - 200), ((int) screenSize.getHeight() / 2 - 200), 400, 400);
        setVisible(true);

    }

    // Function to verify the credentials
    public void verify(String username, String password) {
        // Creating variables of classes from java.sql.__
        Connection connection;
        Statement statement;
        ResultSet resultSet;
        String query = "Select * from bank_account"; // query for the database
        try {
            connection = DriverManager.getConnection(
                    System.getenv("BANKING_APP_DATABASE_URL"),
                    System.getenv("MYSQL_USERNAME"),
                    System.getenv("MYSQL_PASSWORD")
            ); // Establishing connection
            statement = connection.createStatement(); // Creating query statement
            resultSet = statement.executeQuery(query); // Executing query
            boolean access = false;
            String name = null;
            while (resultSet.next()) {
                if (username.equals(resultSet.getString("AccNo")) && password.equals(resultSet.getString("Password"))) // Checking if credentials match
                {
                    access = true;
                    name = resultSet.getString("Name");
                }
            }

            if (access) {
                JOptionPane.showMessageDialog(rootPane, "Welcome " + name + " Login Successful");
                dispose();
                new Services(name, username); // Calling constructor of class Services

            } else {
                JOptionPane.showMessageDialog(rootPane, "Access Denied"); // Error dialog
                accNoTextField.setText("");
                passwordField.setText("");
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}

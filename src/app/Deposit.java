package app;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.FontUIResource;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Timer;
import java.util.TimerTask;

public class Deposit {

    // <editor-fold defaultstate="collapsed" desc="Variables' Declaration">
    JLabel usernameLabel, accNoLabel, depositLabel, iconLabel, lineSeparator;
    JTextField depositTextField;
    JButton depositButton;
    ImageIcon depositImage, tickImage;
    Timer timer = new Timer();
//</editor-fold>

    public Deposit(String username, String accountNo, JPanel depositPanel) {
        // Label to display Account Name
        usernameLabel = new JLabel("Account Name: " + username);
        usernameLabel.setBounds(10, 10, 200, 30);
        usernameLabel.setFont(new FontUIResource("Comic Sans MS", FontUIResource.CENTER_BASELINE, 16));
        usernameLabel.setForeground(ColorUIResource.white);
        depositPanel.add(usernameLabel);

        // Label to display Account No
        accNoLabel = new JLabel("Account No: " + accountNo);
        accNoLabel.setBounds(10, 40, 200, 30);
        accNoLabel.setFont(new FontUIResource("Comic Sans MS", FontUIResource.CENTER_BASELINE, 16));
        accNoLabel.setForeground(ColorUIResource.white);
        depositPanel.add(accNoLabel);

        // Separating Line
        lineSeparator = new JLabel("__________________________________________________");
        lineSeparator.setBounds(0, 60, 400, 30);
        lineSeparator.setFont(new FontUIResource("Arial", FontUIResource.PLAIN, 14));
        lineSeparator.setForeground(ColorUIResource.white);
        depositPanel.add(lineSeparator);

        // Label for deposit amount
        depositLabel = new JLabel("Enter the amount to deposit:");
        depositLabel.setBounds(10, 120, 200, 20);
        depositLabel.setFont(new FontUIResource("Comic Sans MS", FontUIResource.PLAIN, 14));
        depositLabel.setForeground(ColorUIResource.white);
        depositPanel.add(depositLabel);

        // TextField for deposit amount
        depositTextField = new JTextField("0");
        depositTextField.setBounds(215, 120, 170, 25);
        depositTextField.setFont(new FontUIResource("MV Boli", FontUIResource.PLAIN, 12));
        depositPanel.add(depositTextField);

        // ImageIcons
        depositImage = new ImageIcon(getClass().getResource("/media/deposit-image.png"));
        tickImage = new ImageIcon(getClass().getResource("/media/tick.gif"));
        // Done label
        iconLabel = new JLabel();
        iconLabel.setBounds(175, 300, 85, 50);
        iconLabel.setForeground(ColorUIResource.white);
        depositPanel.add(iconLabel);

        // Button to Deposit money
        depositButton = new JButton("Deposit");
        depositButton.setBounds(100, 170, 200, 30);
        depositButton.setFont(new FontUIResource("Ink Free", FontUIResource.BOLD, 14));
        depositButton.setIcon(depositImage);
        // <editor-fold defaultstate="collapsed" desc="Deposit button Action and Key Listeners">
        depositButton.addActionListener(ae -> {
            double balance = 0;
            GetBalance user1 = new GetBalance();
            balance = user1.getBalance(accountNo, balance);
            depositBalance(accountNo, (balance + Integer.parseInt(depositTextField.getText())));
            iconLabel.setIcon(tickImage);
            iconLabel.setText("Done");
            depositTextField.setText("0"); // Clear the TextField
            timer.schedule(new TimerTask() { // To hide the done icon and label after 10 secs
                @Override
                public void run() {
                    iconLabel.setIcon(null);
                    iconLabel.setText("");
                }
            }, 10000);

        });
        depositButton.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent ke) {
                if (ke.getKeyCode() == KeyEvent.VK_ENTER) {
                    double balance = 0;
                    GetBalance user1 = new GetBalance();
                    balance = user1.getBalance(accountNo, balance);
                    depositBalance(accountNo, (balance + Integer.parseInt(depositTextField.getText())));
                    iconLabel.setIcon(tickImage);
                    iconLabel.setText("Done");
                    depositTextField.setText("0"); // Clear the TextField
                    timer.schedule(new TimerTask() // To hide the done icon and label after 10 secs
                    {
                        @Override
                        public void run() {
                            iconLabel.setIcon(null);
                            iconLabel.setText("");
                        }
                    }, 10000);
                }
            }
        });
//</editor-fold>
        depositPanel.add(depositButton);
    }

    // Method to deposit money and update the balance
    public void depositBalance(String accNo, double balance) {
        Connection connection;
        PreparedStatement preparedStatement;
        try {
            connection = DriverManager.getConnection(
                    System.getenv("BANKING_APP_DATABASE_URL"),
                    System.getenv("MYSQL_USERNAME"),
                    System.getenv("MYSQL_PASSWORD")
            ); // Establishing connection
            preparedStatement = connection.prepareStatement("UPDATE bank_account SET balance = ? where AccNo = ?");
            preparedStatement.setDouble(1, balance);
            preparedStatement.setString(2, accNo);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}

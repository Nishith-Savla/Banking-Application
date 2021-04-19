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

public class Withdraw {

    // <editor-fold defaultstate="collapsed" desc="Variables' Declaration">
    JLabel usernameLabel, accNoLabel, withdrawLabel, iconLabel, lineSeparator;
    JTextField withdrawTextField;
    JButton withdrawButton;
    ImageIcon withdrawImage, tickImage;
    Timer timer = new Timer();
    //</editor-fold>

    public Withdraw(String username, String accountNo, JPanel withdraw) {
        // Label to display Account Name
        usernameLabel = new JLabel("Account Name: " + username);
        usernameLabel.setBounds(10, 10, 200, 30);
        usernameLabel.setFont(new FontUIResource("Comic Sans MS", FontUIResource.CENTER_BASELINE, 16));
        usernameLabel.setForeground(ColorUIResource.white);
        withdraw.add(usernameLabel);

        // Label to display Account No
        accNoLabel = new JLabel("Account No: " + accountNo);
        accNoLabel.setBounds(10, 40, 200, 30);
        accNoLabel.setFont(new FontUIResource("Comic Sans MS", FontUIResource.CENTER_BASELINE, 16));
        accNoLabel.setForeground(ColorUIResource.white);
        withdraw.add(accNoLabel);

        // Separating Line
        lineSeparator = new JLabel("__________________________________________________");
        lineSeparator.setBounds(0, 60, 400, 30);
        lineSeparator.setFont(new FontUIResource("Arial", FontUIResource.PLAIN, 14));
        lineSeparator.setForeground(ColorUIResource.white);
        withdraw.add(lineSeparator);

        // Label for withdraw amount
        withdrawLabel = new JLabel("Enter the amount to withdraw:");
        withdrawLabel.setBounds(10, 120, 200, 20);
        withdrawLabel.setFont(new FontUIResource("Comic Sans MS", FontUIResource.PLAIN, 14));
        withdrawLabel.setForeground(ColorUIResource.white);
        withdraw.add(withdrawLabel);

        // TextField for withdraw amount
        withdrawTextField = new JTextField("0");
        withdrawTextField.setBounds(215, 120, 170, 25);
        withdrawTextField.setFont(new FontUIResource("MV Boli", FontUIResource.PLAIN, 12));
        withdraw.add(withdrawTextField);

        // ImageIcons
        withdrawImage = new ImageIcon(getClass().getResource("/media/withdraw-image.png"));
        tickImage = new ImageIcon(getClass().getResource("/media/tick.gif"));
        // Done label
        iconLabel = new JLabel();
        iconLabel.setBounds(175, 300, 85, 50);
        iconLabel.setForeground(ColorUIResource.white);
        withdraw.add(iconLabel);

        // Withdraw button
        withdrawButton = new JButton("Withdraw");
        withdrawButton.setBounds(100, 170, 200, 30);
        withdrawButton.setFont(new FontUIResource("Ink Free", FontUIResource.BOLD, 14));
        withdrawButton.setIcon(withdrawImage);
        // <editor-fold defaultstate="collapsed" desc="Withdraw button Action and Key Listeners">
        withdrawButton.addActionListener(ae -> {
            double balance = 0;
            GetBalance userBalance = new GetBalance();
            balance = userBalance.getBalance(accountNo, balance);

            if (balance >= Integer.parseInt(withdrawTextField.getText())) {
                withdrawBalance(accountNo, (balance - Integer.parseInt(withdrawTextField.getText())));

                iconLabel.setIcon(tickImage);
                iconLabel.setText("Done");
                withdrawTextField.setText("0");
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        iconLabel.setIcon(null);
                        iconLabel.setText("");
                    }
                }, 10000);
            } else {
                JOptionPane.showMessageDialog(withdraw, "Not enough balance! :(");
            }
        });
        withdrawButton.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent ke) {
                if (ke.getKeyCode() == KeyEvent.VK_ENTER) {
                    double balance = 0;
                    GetBalance user1 = new GetBalance();
                    balance = user1.getBalance(accountNo, balance);

                    if (balance >= Integer.parseInt(withdrawTextField.getText())) {
                        withdrawBalance(accountNo, (balance - Integer.parseInt(withdrawTextField.getText())));

                        iconLabel.setIcon(tickImage);
                        iconLabel.setText("Done");
                        withdrawTextField.setText("0");
                        timer.schedule(new TimerTask() {
                            @Override
                            public void run() {
                                iconLabel.setIcon(null);
                                iconLabel.setText("");
                            }
                        }, 10000);
                    } else {
                        JOptionPane.showMessageDialog(withdraw, "Not enough balance! :(");
                    }
                }
            }
        });
//</editor-fold>
        withdraw.add(withdrawButton);
    }

    // Method to withdraw money and update the balance
    private void withdrawBalance(String accNo, double balance) {
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

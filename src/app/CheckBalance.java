package app;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.FontUIResource;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

// @author Nishith
public class CheckBalance {

    // <editor-fold defaultstate="collapsed" desc="Variables' Declaration">
    JLabel usernameLabel, accNoLabel, lineSeparator, balanceLabel;
    JButton balanceButton;
    ImageIcon checkBalanceImage;
    //</editor-fold>

    public CheckBalance(String username, String accountNo, JPanel checkBalancePanel) {
        // Label to display Account Name
        usernameLabel = new JLabel("Account Name: " + username);
        usernameLabel.setBounds(10, 10, 200, 30);
        usernameLabel.setFont(new FontUIResource("Comic Sans MS", FontUIResource.CENTER_BASELINE, 16));
        usernameLabel.setForeground(ColorUIResource.white);
        checkBalancePanel.add(usernameLabel);

        // Label to display Account No
        accNoLabel = new JLabel("Account No: " + accountNo);
        accNoLabel.setBounds(10, 40, 200, 30);
        accNoLabel.setFont(new FontUIResource("Comic Sans MS", FontUIResource.CENTER_BASELINE, 16));
        accNoLabel.setForeground(ColorUIResource.white);
        checkBalancePanel.add(accNoLabel);

        // Separating Line
        lineSeparator = new JLabel("__________________________________________________");
        lineSeparator.setBounds(0, 60, 400, 30);
        lineSeparator.setFont(new FontUIResource("Arial", FontUIResource.PLAIN, 14));
        lineSeparator.setForeground(ColorUIResource.white);
        checkBalancePanel.add(lineSeparator);

        // Label to display Account No
        balanceLabel = new JLabel("", JLabel.CENTER);
        balanceLabel.setBounds(100, 120, 200, 20);
        balanceLabel.setFont(new FontUIResource("Comic Sans MS", FontUIResource.CENTER_BASELINE, 14));
        balanceLabel.setForeground(ColorUIResource.white);
        checkBalancePanel.add(balanceLabel);

        // ImageIcon
        checkBalanceImage = new ImageIcon(getClass().getResource("/media/check-balance.png"));

        // Check balance button
        balanceButton = new JButton("Check Balance");
        balanceButton.setBounds(100, 170, 200, 30);
        balanceButton.setFont(new FontUIResource("Ink Free", FontUIResource.BOLD, 14));
        balanceButton.setIcon(checkBalanceImage);

        // <editor-fold defaultstate="collapsed" desc="Check Balance button Action and Key Listener">
        balanceButton.addActionListener(ae -> {
            double balance = 0;
            GetBalance user1 = new GetBalance();
            balance = user1.getBalance(accountNo, balance);
            balanceLabel.setText("Balance = " + balance);
        });

        balanceButton.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent ke) {
                if (ke.getKeyCode() == KeyEvent.VK_ENTER) {
                    double balance = 0;
                    GetBalance user1 = new GetBalance();
                    balance = user1.getBalance(accountNo, balance);
                    balanceLabel.setText("Balance = " + balance);
                }
            }
        });
//</editor-fold>

        checkBalancePanel.add(balanceButton);
    }

}

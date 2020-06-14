package app;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

// @author Nishith
public class Deposit
{

    // <editor-fold defaultstate="collapsed" desc="Variables' Declaration">
    JLabel l_username, l_accNo, l_deposit, l_icon, line;
    JTextField t_deposit;
    JButton b_deposit;
    ImageIcon deposit_img, tick;
    Timer timer = new Timer();
//</editor-fold>

    public Deposit(String username, String accountNo, JPanel deposit)
    {
        // Label to display Account Name
        l_username = new JLabel("Account Name: " + username);
        l_username.setBounds(10, 10, 200, 30);
        l_username.setFont(new Font("Comic Sans MS", Font.CENTER_BASELINE, 16));
        l_username.setForeground(Color.white);
        deposit.add(l_username);

        // Label to display Account No
        l_accNo = new JLabel("Account No: " + accountNo);
        l_accNo.setBounds(10, 40, 200, 30);
        l_accNo.setFont(new Font("Comic Sans MS", Font.CENTER_BASELINE, 16));
        l_accNo.setForeground(Color.white);
        deposit.add(l_accNo);

        // Seperating Line
        line = new JLabel("__________________________________________________");
        line.setBounds(0, 60, 400, 30);
        line.setFont(new Font("Arial", Font.PLAIN, 14));
        line.setForeground(Color.white);
        deposit.add(line);

        // Label for deposit amount
        l_deposit = new JLabel("Enter the amount to deposit:");
        l_deposit.setBounds(10, 120, 200, 20);
        l_deposit.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
        l_deposit.setForeground(Color.white);
        deposit.add(l_deposit);

        // Textfield for deposit amount
        t_deposit = new JTextField("0");
        t_deposit.setBounds(215, 120, 170, 25);
        t_deposit.setFont(new Font("MV Boli", Font.PLAIN, 12));
        deposit.add(t_deposit);

        // ImageIcons
        deposit_img = new ImageIcon(getClass().getResource("/media/deposit-image.png"));
        tick = new ImageIcon(getClass().getResource("/media/tick.gif"));
        // Done label
        l_icon = new JLabel();
        l_icon.setBounds(175, 300, 85, 50);
        l_icon.setForeground(Color.white);
        deposit.add(l_icon);

        // Button to Deposit money
        b_deposit = new JButton("Deposit");
        b_deposit.setBounds(100, 170, 200, 30);
        b_deposit.setFont(new Font("Ink Free", Font.BOLD, 14));
        b_deposit.setIcon(deposit_img);
        // <editor-fold defaultstate="collapsed" desc="Deposit button Action and Key Listeners">
        b_deposit.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                double balance = 0;
                Get_balance user1 = new Get_balance();
                balance = user1.get_bal(accountNo, balance);
                deposit_bal(accountNo, (balance + Integer.parseInt(t_deposit.getText())));
                l_icon.setIcon(tick);
                l_icon.setText("Done");
                t_deposit.setText("0"); // Clear the textfield
                timer.schedule(new TimerTask() // To hide the done icon and label after 10 secs
                {
                    @Override
                    public void run()
                    {
                        l_icon.setIcon(null);
                        l_icon.setText("");
                    }
                }, 10000);

            }
        });
        b_deposit.addKeyListener(new KeyAdapter()
        {
            @Override
            public void keyPressed(KeyEvent ke)
            {
                if (ke.getKeyCode() == KeyEvent.VK_ENTER) {
                    double balance = 0;
                    Get_balance user1 = new Get_balance();
                    balance = user1.get_bal(accountNo, balance);
                    deposit_bal(accountNo, (balance + Integer.parseInt(t_deposit.getText())));
                    l_icon.setIcon(tick);
                    l_icon.setText("Done");
                    t_deposit.setText("0"); // Clear the textfield
                    timer.schedule(new TimerTask() // To hide the done icon and label after 10 secs
                    {
                        @Override
                        public void run()
                        {
                            l_icon.setIcon(null);
                            l_icon.setText("");
                        }
                    }, 10000);
                }
            }
        });
//</editor-fold>
        deposit.add(b_deposit);
    }

    // Method to deposit money and update the balance
    public void deposit_bal(String accNo, double balance)
    {
        Connection conn = null;
        PreparedStatement pstat = null;
        try {
            conn = DriverManager.getConnection("Database Url", "Username", "Your Password");
            pstat = conn.prepareStatement("UPDATE bank_account SET balance = ? where AccNo = ?");
            pstat.setDouble(1, balance);
            pstat.setString(2, accNo);

            pstat.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

}

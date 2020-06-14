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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

// @author Nishith
public class Withdraw
{

    // <editor-fold defaultstate="collapsed" desc="Variables' Declaration">
    JLabel l_username, l_accNo, l_withdraw, l_icon, line;
    JTextField t_withdraw;
    JButton b_withdraw;
    ImageIcon withdraw_img, tick;
    Timer timer = new Timer();
//</editor-fold>

    public Withdraw(String username, String accountNo, JPanel withdraw)
    {
        // Label to display Account Name
        l_username = new JLabel("Account Name: " + username);
        l_username.setBounds(10, 10, 200, 30);
        l_username.setFont(new Font("Comic Sans MS", Font.CENTER_BASELINE, 16));
        l_username.setForeground(Color.white);
        withdraw.add(l_username);

        // Label to display Account No
        l_accNo = new JLabel("Account No: " + accountNo);
        l_accNo.setBounds(10, 40, 200, 30);
        l_accNo.setFont(new Font("Comic Sans MS", Font.CENTER_BASELINE, 16));
        l_accNo.setForeground(Color.white);
        withdraw.add(l_accNo);

        // Seperating Line
        line = new JLabel("__________________________________________________");
        line.setBounds(0, 60, 400, 30);
        line.setFont(new Font("Arial", Font.PLAIN, 14));
        line.setForeground(Color.white);
        withdraw.add(line);

        // Label for withdraw amount
        l_withdraw = new JLabel("Enter the amount to withdraw:");
        l_withdraw.setBounds(10, 120, 200, 20);
        l_withdraw.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
        l_withdraw.setForeground(Color.white);
        withdraw.add(l_withdraw);

        // Textfield for withdraw amount
        t_withdraw = new JTextField("0");
        t_withdraw.setBounds(215, 120, 170, 25);
        t_withdraw.setFont(new Font("MV Boli", Font.PLAIN, 12));
        withdraw.add(t_withdraw);

        // ImageIcons
        withdraw_img = new ImageIcon(getClass().getResource("/media/withdraw-image.png"));
        tick = new ImageIcon(getClass().getResource("/media/tick.gif"));
        // Done label
        l_icon = new JLabel();
        l_icon.setBounds(175, 300, 85, 50);
        l_icon.setForeground(Color.white);
        withdraw.add(l_icon);

        // Withdraw button
        b_withdraw = new JButton("Withdraw");
        b_withdraw.setBounds(100, 170, 200, 30);
        b_withdraw.setFont(new Font("Ink Free", Font.BOLD, 14));
        b_withdraw.setIcon(withdraw_img);
        // <editor-fold defaultstate="collapsed" desc="Withdraw button Action and Key Listeners">
        b_withdraw.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                double balance = 0;
                Get_balance user1 = new Get_balance();
                balance = user1.get_bal(accountNo, balance);

                if (balance >= Integer.parseInt(t_withdraw.getText())) {
                    withdraw_bal(accountNo, (balance - Integer.parseInt(t_withdraw.getText())));

                    l_icon.setIcon(tick);
                    l_icon.setText("Done");
                    t_withdraw.setText("0");
                    timer.schedule(new TimerTask()
                    {
                        @Override
                        public void run()
                        {
                            l_icon.setIcon(null);
                            l_icon.setText("");
                        }
                    }, 10000);
                } else {
                    JOptionPane.showMessageDialog(withdraw, "Not enough balance! :(");
                }
            }
        });
        b_withdraw.addKeyListener(new KeyAdapter()
        {
            @Override
            public void keyPressed(KeyEvent ke)
            {
                if (ke.getKeyCode() == KeyEvent.VK_ENTER) {
                    double balance = 0;
                    Get_balance user1 = new Get_balance();
                    balance = user1.get_bal(accountNo, balance);

                    if (balance >= Integer.parseInt(t_withdraw.getText())) {
                        withdraw_bal(accountNo, (balance - Integer.parseInt(t_withdraw.getText())));

                        l_icon.setIcon(tick);
                        l_icon.setText("Done");
                        t_withdraw.setText("0");
                        timer.schedule(new TimerTask()
                        {
                            @Override
                            public void run()
                            {
                                l_icon.setIcon(null);
                                l_icon.setText("");
                            }
                        }, 10000);
                    } else {
                        JOptionPane.showMessageDialog(withdraw, "Not enough balance! :(");
                    }
                }
            }
        });
//</editor-fold>
        withdraw.add(b_withdraw);
    }

    // Method to withdraw money and update the balance
    private void withdraw_bal(String accNo, double balance)
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

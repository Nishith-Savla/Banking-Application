package app;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

// @author Nishith
public class CheckBalance
{

    // <editor-fold defaultstate="collapsed" desc="Variables' Declaration">
    JLabel l_username, l_accNo, line, l_bal;
    JButton b_bal;
    ImageIcon checkbal_img;
//</editor-fold>

    public CheckBalance(String username, String accountNo, JPanel checkbal)
    {
        // Label to display Account Name
        l_username = new JLabel("Account Name: " + username);
        l_username.setBounds(10, 10, 200, 30);
        l_username.setFont(new Font("Comic Sans MS", Font.CENTER_BASELINE, 16));
        l_username.setForeground(Color.white);
        checkbal.add(l_username);

        // Label to display Account No
        l_accNo = new JLabel("Account No: " + accountNo);
        l_accNo.setBounds(10, 40, 200, 30);
        l_accNo.setFont(new Font("Comic Sans MS", Font.CENTER_BASELINE, 16));
        l_accNo.setForeground(Color.white);
        checkbal.add(l_accNo);

        // Seperating Line
        line = new JLabel("__________________________________________________");
        line.setBounds(0, 60, 400, 30);
        line.setFont(new Font("Arial", Font.PLAIN, 14));
        line.setForeground(Color.white);
        checkbal.add(line);

        // Label to display Account No
        l_bal = new JLabel("", JLabel.CENTER);
        l_bal.setBounds(100, 120, 200, 20);
        l_bal.setFont(new Font("Comic Sans MS", Font.CENTER_BASELINE, 14));
        l_bal.setForeground(Color.white);
        checkbal.add(l_bal);

        // ImageIcon
        checkbal_img = new ImageIcon(getClass().getResource("/media/check-balance.png"));

        // Check balance button
        b_bal = new JButton("Check Balance");
        b_bal.setBounds(100, 170, 200, 30);
        b_bal.setFont(new Font("Ink Free", Font.BOLD, 14));
        b_bal.setIcon(checkbal_img);
        // <editor-fold defaultstate="collapsed" desc="Check Balance button Action and Key Listener">
        b_bal.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                double balance = 0;
                Get_balance user1 = new Get_balance();
                balance = user1.get_bal(accountNo, balance);
                l_bal.setText("Balance = " + balance);
            }
        });
        b_bal.addKeyListener(new KeyAdapter()
        {
            @Override
            public void keyPressed(KeyEvent ke)
            {
                if (ke.getKeyCode() == KeyEvent.VK_ENTER) {
                    double balance = 0;
                    Get_balance user1 = new Get_balance();
                    balance = user1.get_bal(accountNo, balance);
                    l_bal.setText("Balance = " + balance);
                }
            }

        });
//</editor-fold>
        checkbal.add(b_bal);
    }

}

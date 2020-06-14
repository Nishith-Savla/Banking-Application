package app;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

class Login extends JFrame
{

    // <editor-fold defaultstate="collapsed" desc="Variables' Declaration">
    JLabel l_accNo, l_pass;
    JTextField t_accNo;
    JPasswordField pass;
    JButton login, register;
    Dimension screenSize;
//</editor-fold>

    public Login()
    {
        super("LOG IN PAGE "); // Title

        // Label for accno
        l_accNo = new JLabel("ACCOUNT NO:");
        l_accNo.setBounds(20, 60, 130, 20);
        l_accNo.setFont(new Font("Castellar", Font.BOLD, 18));
        add(l_accNo);

        // Textfield for accno
        t_accNo = new JTextField("BKAC");
        t_accNo.setBounds(165, 60, 160, 20);
        t_accNo.setFont(new Font("Verdana", Font.PLAIN, 13));
        add(t_accNo);

        // Label for password
        l_pass = new JLabel(" PASSWORD  :");
        l_pass.setBounds(20, 100, 130, 20);
        l_pass.setFont(new Font("Castellar", Font.BOLD, 18));
        add(l_pass);

        // Password field for password
        pass = new JPasswordField();
        pass.setBounds(165, 100, 160, 20);
        add(pass);

        // Button for login
        login = new JButton("LOG IN");
        login.setBounds(135, 150, 100, 30);
        login.setFont(new Font("Engravers MT", Font.BOLD, 13));
        login.setForeground(Color.white);
        login.setBackground(Color.black);
        // <editor-fold defaultstate="collapsed" desc="Login Button Action and Key Listener(Adapter)">
        login.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
                String password = new String(pass.getPassword());
                verify(t_accNo.getText(), password); // Verify credentials
            }
        });
        login.addKeyListener(new KeyAdapter()
        {
            @Override
            public void keyPressed(KeyEvent ke)
            {
                if (ke.getKeyCode() == KeyEvent.VK_ENTER) // Triggered on pressing enter
                {
                    String password = new String(pass.getPassword());
                    verify(t_accNo.getText(), password); // Verify credentials
                }
            }

        });
//</editor-fold>
        add(login);

        // Register button
        register = new JButton("New here... First register yourself");
        register.setBounds(50, 300, 250, 30);
        register.setFont(new Font("Engravers MT", Font.BOLD, 13));
        register.setBorder(null);
        register.setBackground(Color.cyan);
        // <editor-fold defaultstate="collapsed" desc="Register Button Action and Key Listener(Adapter)">
        register.addActionListener(new ActionListener()
        {
            // ACTIONLISTENER
            public void actionPerformed(ActionEvent ae)
            {
                dispose();
                new Register();
            }
        });
        register.addKeyListener(new KeyAdapter()
        {
            @Override
            public void keyPressed(KeyEvent ke)
            {
                if (ke.getKeyCode() == KeyEvent.VK_ENTER) {
                    dispose();
                    new Register();
                }
            }

        });
//</editor-fold>
        add(register);

        // Frame Constraints
        screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        getContentPane().setBackground(Color.cyan);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setLayout(null);
        setBounds(((int) screenSize.getWidth() / 2 - 200), ((int) screenSize.getHeight() / 2 - 200), 400, 400);
        setVisible(true);

    }

    // Functiom to verify the credentials
    public void verify(String username, String password)
    {
        // Creating variables of classes from java.sql.__
        Connection mycon = null;
        Statement mystm = null;
        ResultSet myres = null;
        String query = "Select * from bank_account"; // query for the database
        try {
            mycon = DriverManager.getConnection("jdbc:mysql://localhost:3306/java_login?zeroDateTimeBehaviour=convertToNull", "root", "NoPassword"); // Establishing onnection
            mystm = mycon.createStatement(); // Creating query statement
            myres = mystm.executeQuery(query); // Executing query
            boolean access = false;
            String name = null;
            while (myres.next()) {
                if (username.equals(myres.getString("AccNo")) && password.equals(myres.getString("Password"))) // Checking if credentials match
                {
                    access = true;
                    name = myres.getString("Name");
                }
            }

            if (access) {
                JOptionPane.showMessageDialog(rootPane, "Welcome " + name + " Login Successful");
                dispose();
                new Services(name, username); // Calling constructor of class Services

            } else {
                JOptionPane.showMessageDialog(rootPane, "Access Denied"); // Error dialog
                t_accNo.setText("");
                pass.setText("");
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }

}

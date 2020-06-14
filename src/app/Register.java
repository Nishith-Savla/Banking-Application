package app;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Arrays;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

class Register extends JFrame
{

    // <editor-fold defaultstate="collapsed" desc="Variables' Declaration">
    private final JLabel l_name, heading, l_accNo, l_pass, l_cpass, l_age, l_gender, l_email;
    private final String[] ages;
    private final JButton button;
    private JLabel error;
    private JTextField t_name, t_accNo, t_email;
    private JPasswordField p_pass, p_cpass;
    private String gender, passText;
    private JComboBox dropdown;
    private JRadioButton male, female, other;
    private ButtonGroup bg;
    Dimension screenSize;
//</editor-fold>

    public Register()
    {
        super("Form");

        // Title
        heading = new JLabel("Registration Form");
        heading.setFont(new Font("Arial", Font.PLAIN, 24));
        heading.setBounds(100, 20, 200, 50);
        add(heading);

        // Label for name
        l_name = new JLabel("Enter your name: ");
        l_name.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
        l_name.setBounds(10, 100, 145, 20);
        add(l_name);

        // Textfield to get name
        t_name = new JTextField();
        t_name.setFont(new Font("Verdana", Font.PLAIN, 12));
        t_name.setBounds(175, 100, 200, 25);
        add(t_name);

        // Label for username
        l_accNo = new JLabel("Enter account no: ");
        l_accNo.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
        l_accNo.setBounds(10, 145, 145, 20);
        add(l_accNo);

        // Textfield to get username
        t_accNo = new JTextField("BKAC");
        t_accNo.setFont(new Font("Verdana", Font.PLAIN, 12));
        t_accNo.setBounds(175, 145, 200, 25);
        add(t_accNo);

        // Label for password
        l_pass = new JLabel("Create Password: ");
        l_pass.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
        l_pass.setBounds(10, 190, 145, 20);
        add(l_pass);

        // Passwordfield to get password
        p_pass = new JPasswordField();
        p_pass.setBounds(175, 190, 200, 25);
        add(p_pass);

        // Label for password confirmation
        error = new JLabel("");
        error.setBounds(100, 265, 200, 20);
        error.setFont(new Font("Courier New", Font.LAYOUT_NO_START_CONTEXT, 14));
        error.setForeground(Color.red);
        add(error);

        // Label for confirm password
        l_cpass = new JLabel("Confirm Password: ");
        l_cpass.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
        l_cpass.setBounds(10, 235, 145, 20);
        add(l_cpass);

        // Passwordfield to get confirmed password
        p_cpass = new JPasswordField();
        p_cpass.setBounds(175, 235, 200, 25);

        // <editor-fold defaultstate="collapsed" desc="Key listeners (adapters) for real time password validation ">
        p_pass.addKeyListener(new KeyAdapter()
        {
            @Override
            public void keyReleased(KeyEvent ke)
            {
                for (int i = 0; i < p_pass.getPassword().length; i++) {
                    if (Arrays.equals(p_pass.getPassword(), p_cpass.getPassword())) {
                        error.setText("");
                    } else {
                        error.setText("Passwords Don't Match !");
                    }
                }
            }

        });
        p_cpass.addKeyListener(new KeyAdapter()
        {
            @Override
            public void keyReleased(KeyEvent ke)
            {
                for (int i = 0; i < p_pass.getPassword().length; i++) {
                    if (Arrays.equals(p_pass.getPassword(), p_cpass.getPassword())) {
                        error.setText("");
                    } else {
                        error.setText("Passwords Don't Match !");
                    }
                }
            }

        });
        add(p_cpass);
//</editor-fold>

        // Label for age
        l_age = new JLabel("Enter your age: ");
        l_age.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
        l_age.setBounds(10, 300, 145, 20);
        add(l_age);

        // Array of ages
        ages = new String[100];
        for (int i = 0; i < 100; i++) {
            ages[i] = Integer.toString(i + 1); // Values for JCombobox
        }

        // Combobox (Dropdown) for age
        dropdown = new JComboBox(ages);
        dropdown.setBounds(175, 300, 200, 25);
        add(dropdown);

        // Label for gender
        l_gender = new JLabel("Gender: ");
        l_gender.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
        l_gender.setBounds(10, 345, 90, 20);
        add(l_gender);

        // Radio Buttons for gender
        male = new JRadioButton("Male", true);
        male.setBounds(110, 345, 80, 25);
        male.setBackground(Color.decode("#00ffb6"));
        add(male);

        female = new JRadioButton("Female", false);
        female.setBounds(210, 345, 80, 25);
        female.setBackground(Color.decode("#00ffb6"));
        add(female);

        other = new JRadioButton("Other", false);
        other.setBounds(310, 345, 80, 25);
        other.setBackground(Color.decode("#00ffb6"));
        add(other);

        // Button group of radio buttons
        bg = new ButtonGroup();
        bg.add(male);
        bg.add(female);
        bg.add(other);

        // Label for email
        l_email = new JLabel("Enter your email-id: ");
        l_email.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
        l_email.setBounds(10, 390, 145, 20);
        add(l_email);

        // Textfield to get email
        t_email = new JTextField();
        t_email.setFont(new Font("Verdana", Font.PLAIN, 12));
        t_email.setBounds(175, 390, 200, 25);
        add(t_email);

        // SUBMIT Button
        button = new JButton("SUBMIT");
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setBounds(140, 460, 100, 40);
        button.setForeground(Color.black);
        button.setBackground(Color.white);

        // <editor-fold defaultstate="collapsed" desc="Button Action and key listeners">
        button.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                if (t_name.getText().equals("")) {
                    JOptionPane.showMessageDialog(rootPane, "Name cannot be empty");
                }

                if (t_accNo.getText().equals("BKAC")) {
                    JOptionPane.showMessageDialog(rootPane, "Username cannot be empty");
                }

                if (p_cpass.getPassword().length == 0) {
                    JOptionPane.showMessageDialog(rootPane, "Password cannot be empty");
                }

                if (t_email.getText().equals("")) {
                    JOptionPane.showMessageDialog(rootPane, "Email-id cannot be empty");
                }

                if (!(t_name.getText().equals("") && t_accNo.getText().equals("BKAC") && t_email.getText().equals("")
                        && p_cpass.getPassword().length == 0) && error.getText().equals("")) {
                    int confirm = JOptionPane.showConfirmDialog(rootPane, "Confirm Details", "Confirmation Dialog",
                            JOptionPane.YES_NO_OPTION);
                    if (confirm == 0) {
                        JOptionPane.showMessageDialog(rootPane, "Registration Successfull");
                        dispose();
                    }

                    if (male.isSelected() == true) {
                        gender = "Male";
                    } else if (female.isSelected() == true) {
                        gender = "Female";
                    } else if (other.isSelected() == true) {
                        gender = "Other";
                    }

                    passText = new String(p_cpass.getPassword());
                    short age = Short.parseShort((String) dropdown.getItemAt(dropdown.getSelectedIndex()));
                    new SQLinsert(t_name.getText(), t_accNo.getText(), passText, age, gender, t_email.getText());
                    new Login();

                }

            }
        });
        button.addKeyListener(new KeyAdapter()
        {
            @Override
            public void keyPressed(KeyEvent ke)
            {
                if (ke.getKeyCode() == KeyEvent.VK_ENTER) {
                    {
                        if (t_name.getText().equals("")) {
                            JOptionPane.showMessageDialog(rootPane, "Name cannot be empty");
                        }

                        if (t_accNo.getText().equals("BKAC")) {
                            JOptionPane.showMessageDialog(rootPane, "Username cannot be empty");
                        }

                        if (p_cpass.getPassword().length == 0) {
                            JOptionPane.showMessageDialog(rootPane, "Password cannot be empty");
                        }

                        if (t_email.getText().equals("")) {
                            JOptionPane.showMessageDialog(rootPane, "Email-id cannot be empty");
                        }

                        if (!(t_name.getText().equals("") && t_accNo.getText().equals("BKAC")
                                && t_email.getText().equals("") && p_cpass.getPassword().length == 0)
                                && error.getText().equals("")) {
                            int confirm = JOptionPane.showConfirmDialog(rootPane, "Confirm Details",
                                    "Confirmation Dialog", JOptionPane.YES_NO_OPTION);
                            if (confirm == 0) {
                                JOptionPane.showMessageDialog(rootPane, "Registration Successfull");
                                dispose();
                            }

                            if (male.isSelected() == true) {
                                gender = "Male";
                            } else if (female.isSelected() == true) {
                                gender = "Female";
                            } else if (other.isSelected() == true) {
                                gender = "Other";
                            }
                            String passText = new String(p_cpass.getPassword());
                            short age = Short.parseShort((String) dropdown.getItemAt(dropdown.getSelectedIndex()));
                            new SQLinsert(t_name.getText(), t_accNo.getText(), passText, age, gender,
                                    t_email.getText());
                            new Login();
                        }

                    }
                }
            }

        });
        add(button);
//</editor-fold>

        // Frame settings
        screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        getContentPane().setBackground(Color.decode("#00ffb6"));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setLayout(null);
        setBounds(((int) screenSize.getWidth() / 2 - 200), ((int) screenSize.getHeight() / 2 - 300), 400, 600);
        setVisible(true);
    }

}

package app;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Arrays;

class Register extends JFrame {

    // <editor-fold defaultstate="collapsed" desc="Variables' Declaration">
    private final JLabel errorLabel;
    private final JTextField nameTextField, accNoTextField, emailTextField;
    private final JPasswordField passwordField, confirmPasswordField;
    private final JComboBox<Short> dropdown;
    private final JRadioButton maleRadioButton;
    private final JRadioButton femaleRadioButton;
    private final JRadioButton otherRadioButton;
    Dimension screenSize;
    private String gender, passText;
    //</editor-fold>

    public Register() {
        super("Form");

        // Title
        JLabel headingLabel = new JLabel("Registration Form");
        headingLabel.setFont(new FontUIResource("Arial", FontUIResource.PLAIN, 24));
        headingLabel.setBounds(100, 20, 200, 50);
        add(headingLabel);

        // Label for name
        JLabel nameLabel = new JLabel("Enter your name: ");
        nameLabel.setFont(new FontUIResource("Comic Sans MS", FontUIResource.PLAIN, 14));
        nameLabel.setBounds(10, 100, 145, 20);
        add(nameLabel);

        // TextField to get name
        nameTextField = new JTextField();
        nameTextField.setFont(new FontUIResource("Verdana", FontUIResource.PLAIN, 12));
        nameTextField.setBounds(175, 100, 200, 25);
        add(nameTextField);

        // Label for username
        JLabel accNoLabel = new JLabel("Enter account no: ");
        accNoLabel.setFont(new FontUIResource("Comic Sans MS", FontUIResource.PLAIN, 14));
        accNoLabel.setBounds(10, 145, 145, 20);
        add(accNoLabel);

        // TextField to get username
        accNoTextField = new JTextField("BKAC");
        accNoTextField.setFont(new FontUIResource("Verdana", FontUIResource.PLAIN, 12));
        accNoTextField.setBounds(175, 145, 200, 25);
        add(accNoTextField);

        // Label for password
        JLabel passwordLabel = new JLabel("Create Password: ");
        passwordLabel.setFont(new FontUIResource("Comic Sans MS", FontUIResource.PLAIN, 14));
        passwordLabel.setBounds(10, 190, 145, 20);
        add(passwordLabel);

        // PasswordField to get password
        passwordField = new JPasswordField();
        passwordField.setBounds(175, 190, 200, 25);
        add(passwordField);

        // Label for password confirmation
        errorLabel = new JLabel("");
        errorLabel.setBounds(100, 265, 200, 20);
        errorLabel.setFont(new FontUIResource("Courier New", FontUIResource.PLAIN, 14));
        errorLabel.setForeground(ColorUIResource.red);
        add(errorLabel);

        // Label for confirm password
        JLabel confirmPasswordLabel = new JLabel("Confirm Password: ");
        confirmPasswordLabel.setFont(new FontUIResource("Comic Sans MS", FontUIResource.PLAIN, 14));
        confirmPasswordLabel.setBounds(10, 235, 145, 20);
        add(confirmPasswordLabel);

        // PasswordField to get confirmed password
        confirmPasswordField = new JPasswordField();
        confirmPasswordField.setBounds(175, 235, 200, 25);

        // <editor-fold defaultstate="collapsed" desc="Key listeners (adapters) for real time password validation ">
        passwordField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent ke) {
                for (int i = 0; i < passwordField.getPassword().length; i++) {
                    if (Arrays.equals(passwordField.getPassword(), confirmPasswordField.getPassword())) {
                        errorLabel.setText("");
                    } else {
                        errorLabel.setText("Passwords Don't Match !");
                    }
                }
            }

        });

        confirmPasswordField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent ke) {
                for (int i = 0; i < passwordField.getPassword().length; i++) {
                    if (Arrays.equals(passwordField.getPassword(), confirmPasswordField.getPassword())) {
                        errorLabel.setText("");
                    } else {
                        errorLabel.setText("Passwords Don't Match !");
                    }
                }
            }

        });
        add(confirmPasswordField);
//</editor-fold>

        // Label for age
        JLabel ageLabel = new JLabel("Enter your age: ");
        ageLabel.setFont(new FontUIResource("Comic Sans MS", FontUIResource.PLAIN, 14));
        ageLabel.setBounds(10, 300, 145, 20);
        add(ageLabel);

        // Array of ages
        Short[] ages = new Short[100];
        for (short i = 0; i < 100; ++i) ages[i] = (short) (i + 1); // Values for JCombobox

        // Combobox (Dropdown) for age
        dropdown = new JComboBox<>(ages);
        dropdown.setBounds(175, 300, 200, 25);
        add(dropdown);

        // Label for gender
        JLabel genderLabel = new JLabel("Gender: ");
        genderLabel.setFont(new FontUIResource("Comic Sans MS", FontUIResource.PLAIN, 14));
        genderLabel.setBounds(10, 345, 90, 20);
        add(genderLabel);

        // Radio Buttons for gender
        maleRadioButton = new JRadioButton("Male", true);
        maleRadioButton.setBounds(110, 345, 80, 25);
        maleRadioButton.setBackground(ColorUIResource.decode("#00ffb6"));
        add(maleRadioButton);

        femaleRadioButton = new JRadioButton("Female", false);
        femaleRadioButton.setBounds(210, 345, 80, 25);
        femaleRadioButton.setBackground(ColorUIResource.decode("#00ffb6"));
        add(femaleRadioButton);

        otherRadioButton = new JRadioButton("Other", false);
        otherRadioButton.setBounds(310, 345, 80, 25);
        otherRadioButton.setBackground(ColorUIResource.decode("#00ffb6"));
        add(otherRadioButton);

        // Button group of radio buttons
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(maleRadioButton);
        buttonGroup.add(femaleRadioButton);
        buttonGroup.add(otherRadioButton);

        // Label for email
        JLabel emailLabel = new JLabel("Enter your email-id: ");
        emailLabel.setFont(new FontUIResource("Comic Sans MS", FontUIResource.PLAIN, 14));
        emailLabel.setBounds(10, 390, 145, 20);
        add(emailLabel);

        // TextField to get email
        emailTextField = new JTextField();
        emailTextField.setFont(new FontUIResource("Verdana", FontUIResource.PLAIN, 12));
        emailTextField.setBounds(175, 390, 200, 25);
        add(emailTextField);

        // Submit Button
        JButton button = new JButton("SUBMIT");
        button.setFont(new FontUIResource("Arial", FontUIResource.BOLD, 16));
        button.setBounds(140, 460, 100, 40);
        button.setForeground(ColorUIResource.black);
        button.setBackground(ColorUIResource.white);

        // <editor-fold defaultstate="collapsed" desc="Button Action and key listeners">
        button.addActionListener(ae -> {
            if (nameTextField.getText().equals("")) {
                JOptionPane.showMessageDialog(rootPane, "Name cannot be empty");
            }

            if (accNoTextField.getText().equals("BKAC")) {
                JOptionPane.showMessageDialog(rootPane, "Username cannot be empty");
            }

            if (confirmPasswordField.getPassword().length == 0) {
                JOptionPane.showMessageDialog(rootPane, "Password cannot be empty");
            }

            if (emailTextField.getText().equals("")) {
                JOptionPane.showMessageDialog(rootPane, "Email-id cannot be empty");
            }

            if (!(nameTextField.getText().equals("") && accNoTextField.getText().equals("BKAC") && emailTextField.getText().equals("")
                    && confirmPasswordField.getPassword().length == 0) && errorLabel.getText().equals("")) {
                int confirm = JOptionPane.showConfirmDialog(rootPane, "Confirm Details", "Confirmation Dialog",
                        JOptionPane.YES_NO_OPTION);
                if (confirm == 0) {
                    JOptionPane.showMessageDialog(rootPane, "Registration Successful");
                    dispose();
                }

                if (maleRadioButton.isSelected()) {
                    gender = "Male";
                } else if (femaleRadioButton.isSelected()) {
                    gender = "Female";
                } else if (otherRadioButton.isSelected()) {
                    gender = "Other";
                }

                passText = new String(confirmPasswordField.getPassword());
                short age = dropdown.getItemAt(dropdown.getSelectedIndex());
                new SQLInsert(nameTextField.getText(), accNoTextField.getText(), passText, age, gender, emailTextField.getText());
                new Login();

            }

        });
        button.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent ke) {
                if (ke.getKeyCode() == KeyEvent.VK_ENTER) {
                    {
                        if (nameTextField.getText().equals("")) {
                            JOptionPane.showMessageDialog(rootPane, "Name cannot be empty");
                        }

                        if (accNoTextField.getText().equals("BKAC")) {
                            JOptionPane.showMessageDialog(rootPane, "Username cannot be empty");
                        }

                        if (confirmPasswordField.getPassword().length == 0) {
                            JOptionPane.showMessageDialog(rootPane, "Password cannot be empty");
                        }

                        if (emailTextField.getText().equals("")) {
                            JOptionPane.showMessageDialog(rootPane, "Email-id cannot be empty");
                        }

                        if (!(nameTextField.getText().equals("") && accNoTextField.getText().equals("BKAC")
                                && emailTextField.getText().equals("") && confirmPasswordField.getPassword().length == 0)
                                && errorLabel.getText().equals("")) {
                            int confirm = JOptionPane.showConfirmDialog(rootPane, "Confirm Details",
                                    "Confirmation Dialog", JOptionPane.YES_NO_OPTION);
                            if (confirm == 0) {
                                JOptionPane.showMessageDialog(rootPane, "Registration Successful");
                                dispose();
                            }

                            if (maleRadioButton.isSelected()) {
                                gender = "Male";
                            } else if (femaleRadioButton.isSelected()) {
                                gender = "Female";
                            } else if (otherRadioButton.isSelected()) {
                                gender = "Other";
                            }
                            String passText = new String(confirmPasswordField.getPassword());
                            short age = dropdown.getItemAt(dropdown.getSelectedIndex());
                            new SQLInsert(nameTextField.getText(), accNoTextField.getText(), passText, age, gender,
                                    emailTextField.getText());
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
        getContentPane().setBackground(ColorUIResource.decode("#00ffb6"));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setLayout(null);
        setBounds(((int) screenSize.getWidth() / 2 - 200), ((int) screenSize.getHeight() / 2 - 300), 400, 600);
        setVisible(true);
    }

}

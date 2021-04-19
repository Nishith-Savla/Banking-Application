package app;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.InsetsUIResource;
import java.awt.*;

public class Services extends JFrame {

    // <editor-fold defaultstate="collapsed" desc="Variables' Declaration">
    JTabbedPane tabbedPane;
    JPanel depositPanel, withdrawPanel, checkBalancePanel;
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    //</editor-fold>

    public Services(String username, String accountNo) {
        super("Services");

        // Panel for deposit
        depositPanel = new JPanel();
        new Deposit(username, accountNo, depositPanel); // Calling constructor of class Deposit to add the components
        depositPanel.setLayout(null); // Changing layout from flowlayout to null
        depositPanel.setBackground(ColorUIResource.decode("#521a90"));

        // Panel for withdraw
        withdrawPanel = new JPanel();
        new Withdraw(username, accountNo, withdrawPanel); // Calling constructor of class Withdraw to add the components
        withdrawPanel.setLayout(null); // Changing layout from flowlayout to null
        withdrawPanel.setBackground(ColorUIResource.decode("#521a90"));

        // Panel for withdraw
        checkBalancePanel = new JPanel();
        new CheckBalance(username, accountNo, checkBalancePanel); // Calling constructor of class CheckBalance to add the
        // components
        checkBalancePanel.setLayout(null); // Changing layout from flowlayout to null
        checkBalancePanel.setBackground(ColorUIResource.decode("#521a90"));
        checkBalancePanel.setBorder(null);

        // Tabbed Pane to create tabs & add Panels to tabs
        UIManager.put("TabbedPane.selected", ColorUIResource.decode("#521a90"));
        tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Deposit", depositPanel);
        tabbedPane.addTab("Withdraw", withdrawPanel);
        tabbedPane.addTab("Check Balance", checkBalancePanel);
        tabbedPane.setForeground(ColorUIResource.orange);

        InsetsUIResource insets = (InsetsUIResource) UIManager.getInsets("TabbedPane.contentBorderInsets");
        insets.set(-1, -1, -1, -1); // Removing Insets
        UIManager.put("TabbedPane.contentBorderInsets", insets);
        add(tabbedPane);

        // Frame Constraints
        getContentPane().setBackground(ColorUIResource.orange);
        setDefaultCloseOperation(EXIT_ON_CLOSE); // To stop the program after the window is closed
        setResizable(false); // Setting to set frame as non-resizeable
        setBounds(((int) screenSize.getWidth() / 2 - 200), ((int) screenSize.getHeight() / 2 - 200), 400, 400);
        setVisible(true);

    }
}

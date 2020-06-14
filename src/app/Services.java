package app;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;

// @author Nishith
public class Services extends JFrame
{

    // <editor-fold defaultstate="collapsed" desc="Variables' Declaration">
    JTabbedPane tabpane;
    JPanel deposit, withdraw, checkbal;
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
//</editor-fold>

    @SuppressWarnings("ResultOfObjectAllocationIgnored")
    public Services(String username, String accountNo)
    {
        super("Services");

        // Panel for deposit
        deposit = new JPanel();
        new Deposit(username, accountNo, deposit); // Calling constructor of class Deposit to add the components
        deposit.setLayout(null); // Changing layout from flowlayout to null
        deposit.setBackground(Color.decode("#521a90"));

        // Panel for withdraw
        withdraw = new JPanel();
        new Withdraw(username, accountNo, withdraw); // Calling constructor of class Withdraw to add the components
        withdraw.setLayout(null); // Changing layout from flowlayout to null
        withdraw.setBackground(Color.decode("#521a90"));

        // Panel for withdraw
        checkbal = new JPanel();
        new CheckBalance(username, accountNo, checkbal); // Calling constructor of class CheckBalance to add the
        // components
        checkbal.setLayout(null); // Changing layout from flowlayout to null
        checkbal.setBackground(Color.decode("#521a90"));
        checkbal.setBorder(null);

        // Tabbed Pane to create tabs & add Panels to tabs
        UIManager.put("TabbedPane.selected", Color.decode("#521a90"));
        tabpane = new JTabbedPane();
        tabpane.addTab("Deposit", deposit);
        tabpane.addTab("Withdraw", withdraw);
        tabpane.addTab("Check Balance", checkbal);
        tabpane.setForeground(Color.orange);

        Insets insets = UIManager.getInsets("TabbedPane.contentBorderInsets");
        insets.set(-1, -1, -1, -1); // Removing Insets
        UIManager.put("TabbedPane.contentBorderInsets", insets);
        add(tabpane);

        // Frame Constaints
        getContentPane().setBackground(Color.orange);
        setDefaultCloseOperation(EXIT_ON_CLOSE); // To stop the program after the window is closed
        setResizable(false); // Setting to set frame as unresizeable
        setBounds(((int) screenSize.getWidth() / 2 - 200), ((int) screenSize.getHeight() / 2 - 200), 400, 400);
        setVisible(true);

    }
}

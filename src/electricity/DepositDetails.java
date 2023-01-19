package electricity;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Logger;

public class DepositDetails extends JFrame implements ActionListener {

    private static final Logger LOGGER = Logger.getLogger(String.valueOf(DepositDetails.class));

    JTable billTable;

    JButton searchButton, printButton, cancelButton;

    JLabel lSortByMeterNo, lSortMonth;

    Choice cSortByMeterNo, cSortMonth;

    String[] x = {"Meter Number", "Month", "Units", "Total Bill", "Status"};

    String[][] y = new String[40][8];

    int i = 0;

    int j = 0;

    DepositDetails () {

        //============================================================================================================//

        /*----Header----*/
        super("Deposit Details");

        //============================================================================================================//

        LOGGER.info("==:DepositDetails:: Inside DepositDetails Constructor:==");

        //============================================================================================================//


        /*----Create Frame----*/
        setSize(700, 750);
        setLocation(600, 150);
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);

        //============================================================================================================//

        /*----Label Sort by Meter Number:----*/
        lSortByMeterNo = new JLabel("Sort by Meter Number");
        lSortByMeterNo.setBounds(20, 20, 150, 20);
        lSortByMeterNo.setFont(new Font("Tahoma", Font.BOLD, 12));
        // add lSortByMeterNo into frame:
        add(lSortByMeterNo);

        /*----Choice Sort by Meter Number:----*/
        cSortByMeterNo = new Choice();
        cSortByMeterNo.setBounds(180, 20, 150, 20);
        cSortByMeterNo.setForeground(new Color(0, 102, 102));
        cSortByMeterNo.setFont(new Font("Tahoma", Font.BOLD, 12));

        try {

            // Connection With Database:
            Connection c = ConnectionProvider.getConnection();
            Statement s = c.createStatement();

            String customerQuery = "select * from customer";
            LOGGER.info("customerQuery: " + customerQuery);

            // Fetch the all records from customer table:
            ResultSet rs = s.executeQuery(customerQuery);


            while (rs.next()) {
                // Fetch all the meter from records, add into choice cSortByMeterNo:
                cSortByMeterNo.add(rs.getString("meter"));
            }


        } catch (Exception e) {

            e.printStackTrace();
            StringWriter errors = new StringWriter();
            e.printStackTrace(new PrintWriter(errors));
            LOGGER.info("----DepositDetails:: Getting Exception DepositDetails Constructor----" + errors);

        }
        // add cSortByMeterNo into frame:
        add(cSortByMeterNo);


        //============================================================================================================//

        /*----Label Sort by Month:----*/
        lSortMonth = new JLabel("Sort By Month");
        lSortMonth.setBounds(380, 20, 100, 20);
        lSortMonth.setFont(new Font("Tahoma", Font.BOLD, 12));
        // add lSortMonth into frame:
        add(lSortMonth);

        /*----Choice Sort by Month:----*/
        cSortMonth = new Choice();
        cSortMonth.setBounds(500, 20, 150, 20);
        cSortMonth.setForeground(new Color(0, 102, 102));
        cSortMonth.setFont(new Font("Tahoma", Font.BOLD, 12));
        cSortMonth.add("January");
        cSortMonth.add("February");
        cSortMonth.add("March");
        cSortMonth.add("April");
        cSortMonth.add("May");
        cSortMonth.add("June");
        cSortMonth.add("July");
        cSortMonth.add("August");
        cSortMonth.add("September");
        cSortMonth.add("October");
        cSortMonth.add("November");
        cSortMonth.add("December");
        // add cSortMonth into frame:
        add(cSortMonth);

        //============================================================================================================//

        /*----Create table:----*/
        billTable = new JTable(y, x);

        try {

            // Connection With Database:
            Connection c = ConnectionProvider.getConnection();
            Statement s = c.createStatement();

            String billQuery = "select * from bill";
            LOGGER.info("billQuery: " + billQuery);

            // Fetch the all records from bill table:
            ResultSet rs = s.executeQuery(billQuery);

            // set the  model to JTable billTable:
            billTable.setModel(DbUtils.resultSetToTableModel(rs));


        } catch (Exception e) {

            e.printStackTrace();
            StringWriter errors = new StringWriter();
            e.printStackTrace(new PrintWriter(errors));
            LOGGER.info("----DepositDetails:: Getting Exception DepositDetails Constructor----" + errors);

        }

        /*----For ScrollPane:----*/
        JScrollPane sp = new JScrollPane(billTable);
        sp.setBounds(0, 100, 700, 650);
        // add scroll pane into frame:
        add(sp);

        //============================================================================================================//

        /*----create Search Button and add properties:----*/
        ImageIcon searchIcon = new ImageIcon(ClassLoader.getSystemResource("icon/Search.jpg"));
        Image searchImage = searchIcon.getImage().getScaledInstance(16, 16, Image.SCALE_DEFAULT);
        searchButton = new JButton("Search", new ImageIcon(searchImage));
        searchButton.setBounds(160, 70, 110, 20);
        searchButton.setFont(new Font("Tahoma", Font.BOLD, 12));
        searchButton.addActionListener(this);
        // add searchButton into frame:
        add(searchButton);

        //============================================================================================================//

        /*----create Print Button and add properties:----*/
        ImageIcon printIcon = new ImageIcon(ClassLoader.getSystemResource("icon/print.png"));
        Image printImage = printIcon.getImage().getScaledInstance(16, 16, Image.SCALE_DEFAULT);
        printButton = new JButton("Print", new ImageIcon(printImage));
        printButton.setBounds(290, 70, 110, 20);
        printButton.setFont(new Font("Tahoma", Font.BOLD, 12));
        printButton.addActionListener(this);
        // add printButton into frame:
        add(printButton);

        //============================================================================================================//


        /*----create Cancel Button and add properties:----*/
        ImageIcon cancelIcon = new ImageIcon(ClassLoader.getSystemResource("icon/cancel.jpg"));
        Image cancelImage = cancelIcon.getImage().getScaledInstance(16, 16, Image.SCALE_DEFAULT);
        cancelButton = new JButton("Cancel", new ImageIcon(cancelImage));
        cancelButton.setBounds(420, 70, 110, 20);
        cancelButton.setFont(new Font("Tahoma", Font.BOLD, 12));
        cancelButton.addActionListener(this);
        // add cancelButton into frame:
        add(cancelButton);

    }

    public static void main (String[] args) {
        LOGGER.info("==: DepositDetails:: Inside main Method :==");
        new DepositDetails().setVisible(true);
    }

    public void actionPerformed (ActionEvent ae) {

        LOGGER.info("==: DepositDetails:: Inside actionPerformed Method :==");

        // If click the Button Search:
        if (ae.getSource() == searchButton) {

            String billQuery =
                    "select * from bill where meter = '" + cSortByMeterNo.getSelectedItem() + "' AND month = '" + cSortMonth.getSelectedItem() + "'";

            LOGGER.info("billQuery: " + billQuery);

            try {

                // Connection with database:
                Connection c = ConnectionProvider.getConnection();
                Statement s = c.createStatement();

                // Fetch the all records from bill where meter = cSortByMeterNo.getSelectedItem() AND month =
                // cSortMonth.getSelectedItem();
                ResultSet rs = s.executeQuery(billQuery);

                // added into billTable table:
                billTable.setModel(DbUtils.resultSetToTableModel(rs));

            } catch (Exception e) {

                e.printStackTrace();
                StringWriter errors = new StringWriter();
                e.printStackTrace(new PrintWriter(errors));
                LOGGER.info("----DepositDetails:: Getting Exception actionPerformed Method----" + e);

            }

            // If click on Button Print:
        } else if (ae.getSource() == printButton) {

            try {

                billTable.print();

            } catch (Exception e) {

                e.printStackTrace();
                StringWriter errors = new StringWriter();
                e.printStackTrace(new PrintWriter(errors));
                LOGGER.info("----DepositDetails:: Getting Exception actionPerformed Method----" + e);

            }
            // If click on Cancel Print:
        } else if (ae.getSource() == cancelButton) {
            // simply close the current frame:
            this.setVisible(false);
        }
    }

}

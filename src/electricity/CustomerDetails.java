package electricity;

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

public class CustomerDetails extends JFrame implements ActionListener {

    private static final Logger LOGGER = Logger.getLogger(String.valueOf(CustomerDetails.class));

    JTable customerTable;

    JButton printButton;

    String[] x = {"Customer Name", "Meter Number", "Address", "City", "State", "Email", "Phone"};

    String[][] y = new String[40][8];

    int i = 0;
    int j = 0;

    // Default Constructor:
    CustomerDetails () {

        //============================================================================================================//

        /*-----------Header---------*/
        super("Customer Details");

        //============================================================================================================//

        LOGGER.info("==: CustomerDetails:: Inside CustomerDetails Constructor :==");

        //============================================================================================================//

        /*----Create Frame----*/
        setSize(1200, 650);
        setLocation(400, 150);

        //============================================================================================================//

        try {

            /*-------------Connection Database-------------*/
            Connection c1 = ConnectionProvider.getConnection();
            Statement s = c1.createStatement();

            // Fetch the all record from the customer table:
            String customerQuery = "select * from customer";
            LOGGER.info("customerQuery: " + customerQuery);

            // Fetch all the record from customerQuery:
            ResultSet rs = s.executeQuery(customerQuery);

            while (rs.next()) {

                // Fetch the name from records and add into 1st column Customer Name:
                y[i][j++] = rs.getString("name");

                // Fetch the meter from records and add into 2nd column Meter Number:
                y[i][j++] = rs.getString("meter");

                // Fetch the address from records and add into 3rd column Address:
                y[i][j++] = rs.getString("address");

                // Fetch the city from records and add into 4th column City:
                y[i][j++] = rs.getString("city");

                // Fetch the state from records and add into 5th column State:
                y[i][j++] = rs.getString("state");

                // Fetch the email from records and add into 6th column Email:
                y[i][j++] = rs.getString("email");

                // Fetch the phone from records and add into 7th column Phone:
                y[i][j++] = rs.getString("phone");

                i++;
                // Every time column start from 0:
                j = 0;

            }

            /*----All the records represent into table:----*/
            customerTable = new JTable(y, x);

        } catch (Exception e) {

            e.printStackTrace();
            StringWriter errors = new StringWriter();
            e.printStackTrace(new PrintWriter(errors));
            LOGGER.info("----CustomerDetails::Getting Default Constructor Exception----" + e.getMessage());

        }

        //============================================================================================================//

        /*----create Print Button and add properties:----*/
        ImageIcon printIcon = new ImageIcon(ClassLoader.getSystemResource("icon/print.png"));
        Image printImage = printIcon.getImage().getScaledInstance(16, 16, Image.SCALE_DEFAULT);
        printButton = new JButton("Print", new ImageIcon(printImage));
        printButton.setFont(new Font("Tahoma", Font.BOLD, 12));
        add(printButton, "South");
        JScrollPane sp = new JScrollPane(customerTable);
        // add scroll pane into frame:
        add(sp);

        // adding action listener to print button:
        printButton.addActionListener(this);

    }

    public static void main (String[] args) {
        LOGGER.info("==: CustomerDetails:: Inside main method :==");
        new CustomerDetails().setVisible(true);
    }

    @Override
    public void actionPerformed (ActionEvent ae) {

        LOGGER.info("==: CustomerDetails:: Inside actionPerformed method:==");

        try {

            customerTable.print();

        } catch (Exception ignored) {

            ignored.printStackTrace();
            StringWriter errors = new StringWriter();
            ignored.printStackTrace(new PrintWriter(errors));
            LOGGER.info("----CustomerDetails::Getting actionPerformed Method Exception----" + ignored.getMessage());

        }
    }

}

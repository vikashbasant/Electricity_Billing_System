package electricity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Logger;

public class PayBill extends JFrame implements ActionListener {

    private static final Logger LOGGER = Logger.getLogger(String.valueOf(PayBill.class));

    JLabel lMeterNo, lName, lMonth, lUnits, lTotalBill, lStatus;

    JLabel lMeterValue, lNameValue, lUnitsValue, lTotalBillValue, lStatusValue;

    JTextField t1;

    Choice cMonth, c2;

    JButton payButton, backButton;

    String meter;

    PayBill (String meter) {

        //============================================================================================================//

        LOGGER.info("==:PayBill:: Inside PayBill Constructor:==");

        //============================================================================================================//

        /*----set the meter:----*/
        this.meter = meter;

        //============================================================================================================//

        /*----Create Frame----*/
        setLayout(null);
        setBounds(550, 220, 900, 600);
        // set the contentPane color to white:
        getContentPane().setBackground(Color.WHITE);

        //============================================================================================================//

        /*----Label "Electricity Bill":----*/
        JLabel title = new JLabel("Electricity Bill");
        title.setFont(new Font("Tahoma", Font.BOLD, 24));
        title.setBounds(120, 5, 400, 30);
        // add title into frame:
        add(title);

        //============================================================================================================//

        /*----Label Meter No----*/
        lMeterNo = new JLabel("Meter No");
        lMeterNo.setBounds(35, 80, 200, 20);
        lMeterNo.setFont(new Font("Tahoma", Font.BOLD, 14));
        // add lMeterNo into frame:
        add(lMeterNo);

        /*----Label Meter No Value:----*/
        lMeterValue = new JLabel();
        lMeterValue.setBounds(300, 80, 200, 20);
        lMeterValue.setForeground(new Color(0, 102, 102));
        lMeterValue.setFont(new Font("Tahoma", Font.BOLD, 14));
        // add lMeterValue into frame:
        add(lMeterValue);

        //============================================================================================================//

        /*----Label Name:----*/
        lName = new JLabel("Name");
        lName.setBounds(35, 140, 200, 20);
        lName.setFont(new Font("Tahoma", Font.BOLD, 14));
        // add lName into frame:
        add(lName);

        /*----Label Name Value:----*/
        lNameValue = new JLabel();
        lNameValue.setBounds(300, 140, 200, 20);
        lNameValue.setForeground(new Color(0, 102, 102));
        lNameValue.setFont(new Font("Tahoma", Font.BOLD, 14));
        // add lNameValue into frame:
        add(lNameValue);


        //============================================================================================================//

        /*----Label Month:----*/
        lMonth = new JLabel("Month");
        lMonth.setBounds(35, 200, 200, 20);
        lMonth.setFont(new Font("Tahoma", Font.BOLD, 14));
        // add lMonth into frame:
        add(lMonth);

        /*----choice Month----*/
        cMonth = new Choice();
        cMonth.setBounds(300, 200, 200, 20);
        cMonth.setFont(new Font("Tahoma", Font.BOLD, 14));
        cMonth.setForeground(new Color(0, 102, 102));
        cMonth.add("January");
        cMonth.add("February");
        cMonth.add("March");
        cMonth.add("April");
        cMonth.add("May");
        cMonth.add("June");
        cMonth.add("July");
        cMonth.add("August");
        cMonth.add("September");
        cMonth.add("October");
        cMonth.add("November");
        cMonth.add("December");
        // add cMonth into frame:
        add(cMonth);


        //============================================================================================================//


        /*----Label Units:----*/
        lUnits = new JLabel("Units");
        lUnits.setBounds(35, 260, 200, 20);
        lUnits.setFont(new Font("Tahoma", Font.BOLD, 14));
        // add lUnits into frame:
        add(lUnits);

        /*----Label Units Value:----*/
        lUnitsValue = new JLabel();
        lUnitsValue.setBounds(300, 260, 200, 20);
        lUnitsValue.setFont(new Font("Tahoma", Font.BOLD, 14));
        lUnitsValue.setForeground(new Color(0, 102, 102));
        // add lUnitsValue into frame:
        add(lUnitsValue);

        //============================================================================================================//

        /*----Label Total Bill:----*/
        lTotalBill = new JLabel("Total Bill");
        lTotalBill.setBounds(35, 320, 200, 20);
        lTotalBill.setFont(new Font("Tahoma", Font.BOLD, 14));
        // add lTotalBill into frame:
        add(lTotalBill);

        /*----Label Total Bill Value:----*/
        lTotalBillValue = new JLabel();
        lTotalBillValue.setBounds(300, 320, 200, 20);
        lTotalBillValue.setFont(new Font("Tahoma", Font.BOLD, 14));
        lTotalBillValue.setForeground(new Color(0, 102, 102));
        // add lTotalBillValue into frame:
        add(lTotalBillValue);

        //============================================================================================================//

        /*----Label Status:----*/
        lStatus = new JLabel("Status");
        lStatus.setBounds(35, 380, 200, 20);
        lStatus.setFont(new Font("Tahoma", Font.BOLD, 14));
        // add lStatus into frame:
        add(lStatus);

        /*----Label Status Value:----*/
        lStatusValue = new JLabel();
        lStatusValue.setBounds(300, 380, 200, 20);
        lStatusValue.setFont(new Font("Tahoma", Font.BOLD, 14));
        lStatusValue.setForeground(Color.RED);
        // add lStatusValue into frame:
        add(lStatusValue);

        //============================================================================================================//

        /*
            1. Fetch meter and name from customer table and put into meterValue and nameValue:
            2. Fetch units, total Bill and status from bill table and put into units and totalBill and status value:
         */

        try {

            // Connection with database:
            Connection c = ConnectionProvider.getConnection();
            Statement s = c.createStatement();

            String customerQuery = "select * from customer where meter = '" + meter + "'";
            LOGGER.info("customerQuery: " + customerQuery);

            // Fetch all the records from customer table where meter = ? :
            ResultSet rs = s.executeQuery(customerQuery);

            while (rs.next()) {
                // Fetch the meter no and name from resultSet and set into lMeterValue and lNameValue:
                lMeterValue.setText(rs.getString("meter"));
                lNameValue.setText(rs.getString("name"));

            }

            String billQuery = "select * from bill where meter = '" + meter + "' AND month = 'January'";
            LOGGER.info("billQuery: " + billQuery);

            // Fetch all the records from bill table where meter = ?:
            rs = s.executeQuery(billQuery);

            while (rs.next()) {

                // Fetch all the information and set into related label:
                lUnitsValue.setText(rs.getString("units"));
                lTotalBillValue.setText(rs.getString("total_bill"));
                lStatusValue.setText(rs.getString("status"));

            }

        } catch (Exception e) {

            e.printStackTrace();
            StringWriter errors = new StringWriter();
            e.printStackTrace(new PrintWriter(errors));
            LOGGER.info("----PayBill:: Getting Exception PayBill Constructor----" + e.getMessage());

        }

        //============================================================================================================//

        /*
            1. adding itemListener on choice of month then simply all the information like units, totalBill and
            status from bill table and put into unitsValue and totalBillValue and statusValue:
         */
        cMonth.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged (ItemEvent ae) {

                try {

                    // Connection with database:
                    Connection c = ConnectionProvider.getConnection();
                    Statement s = c.createStatement();

                    String billQuery =
                            "select * from bill where meter = '" + meter + "' AND month = '" + cMonth.getSelectedItem() + "'";

                    LOGGER.info("billQuery: " + billQuery);

                    // Fetch all the records from bill table where meter = ? and month = ?:
                    ResultSet rs = s.executeQuery(billQuery);


                    while (rs.next()) {
                        // Fetch all the information and set label:
                        lUnitsValue.setText(rs.getString("units"));
                        lTotalBillValue.setText(rs.getString("total_bill"));
                        lStatusValue.setText(rs.getString("status"));

                    }


                } catch (Exception e) {

                    e.printStackTrace();
                    StringWriter errors = new StringWriter();
                    e.printStackTrace(new PrintWriter(errors));
                    LOGGER.info("----PayBill:: Getting Exception PayBill Constructor----" + e.getMessage());

                }
            }
        });


        //============================================================================================================//

        /*----Create Button Pay and Back:----*/
        ImageIcon payIcon = new ImageIcon(ClassLoader.getSystemResource("icon/pay.png"));
        Image payImage = payIcon.getImage().getScaledInstance(16, 16, Image.SCALE_DEFAULT);
        payButton = new JButton("Pay", new ImageIcon(payImage));
        payButton.setBackground(Color.WHITE);
        payButton.setForeground(Color.BLACK);
        payButton.setBounds(100, 460, 100, 25);
        payButton.setFont(new Font("Tahoma", Font.BOLD, 14));
        // add payButton into frame:
        add(payButton);


        ImageIcon backIcon = new ImageIcon(ClassLoader.getSystemResource("icon/back3.png"));
        Image backImage = backIcon.getImage().getScaledInstance(16, 16, Image.SCALE_DEFAULT);
        backButton = new JButton("Back", new ImageIcon(backImage));
        backButton.setBackground(Color.WHITE);
        backButton.setForeground(Color.BLACK);
        backButton.setBounds(230, 460, 100, 25);
        backButton.setFont(new Font("Tahoma", Font.BOLD, 14));
        backButton.addActionListener(this);
        // add backButton into frame:
        add(backButton);


        //============================================================================================================//

        /*----Label Image Icon:----*/
        ImageIcon billIcon = new ImageIcon(ClassLoader.getSystemResource("icon/bill.png"));
        Image billImage = billIcon.getImage().getScaledInstance(600, 300, Image.SCALE_DEFAULT);
        JLabel lBillIcon = new JLabel(new ImageIcon(billImage));
        lBillIcon.setBounds(400, 120, 600, 300);
        add(lBillIcon);

        // adding action listener to Pay and Back Button:
        payButton.addActionListener(this);
        backButton.addActionListener(this);


    }

    public static void main (String[] args) {
        LOGGER.info("==: PayBill:: Inside main Method:==");
        new PayBill("").setVisible(true);
    }

    @Override
    public void actionPerformed (ActionEvent ae) {

        LOGGER.info("==: PayBill:: Inside actionPerformed Method:==");

        // If Click on Pay Button:
        if (ae.getSource() == payButton) {

            try {

                // Connection with database:
                Connection c = ConnectionProvider.getConnection();
                Statement s = c.createStatement();

                String billQuery =
                        "update bill set status = 'Paid' where meter = '" + meter + "' AND month = '" + cMonth.getSelectedItem() + "'";
                LOGGER.info("billQuery: " + billQuery);

                // update the bill table status = 'Paid' where meter = ? and month = ?:
                s.executeUpdate(billQuery);

                // simply close the current Frame:
                this.setVisible(false);

                // open the Paytm frame:
                new Paytm(meter).setVisible(true);


            } catch (Exception e) {

                e.printStackTrace();
                StringWriter errors = new StringWriter();
                e.printStackTrace(new PrintWriter(errors));
                LOGGER.info("----PayBill:: Getting Exception actionPerformed Method----" + e.getMessage());

            }


            // If click on Back Button:
        } else if (ae.getSource() == backButton) {

            // Simply close the current window:
            this.setVisible(false);

        }
    }
}

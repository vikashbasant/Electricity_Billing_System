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

public class CalculateBill extends JFrame implements ActionListener {

    private static final Logger LOGGER = Logger.getLogger(String.valueOf(CalculateBill.class));

    JLabel lCalculateBill, lMeterNo, lUnitsConsumed, lHiIcon, lMonth;

    JTextField tUnitsConsumed;

    Choice cMeterNo, cMonth;

    JButton submitButton, cancelButton;

    JPanel panel;

    CalculateBill () {

        //============================================================================================================//

        LOGGER.info("==: CalculateBill:: Inside CalculateBill Constructor:==");

        //============================================================================================================//

        /*----Create panel:----*/
        panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(173, 216, 230));


        //============================================================================================================//

        /*----Label Calculate Electricity Bill:----*/
        lCalculateBill = new JLabel("Calculate Electricity Bill");
        lCalculateBill.setBounds(30, 10, 400, 30);
        lCalculateBill.setFont(new Font("Senserif", Font.PLAIN, 26));
        /*----Move the label to center----*/
        lCalculateBill.setHorizontalAlignment(JLabel.CENTER);


        /*----Added lCalculateBill into panel----*/
        panel.add(lCalculateBill);

        //============================================================================================================//

        /*----Label Meter No:----*/
        lMeterNo = new JLabel("Meter No");
        lMeterNo.setBounds(60, 70, 100, 30);
        lMeterNo.setFont(new Font("Tahoma", Font.BOLD, 14));

        /*----Choice Meter No.:----*/
        cMeterNo = new Choice();
        cMeterNo.setBounds(200, 70, 180, 20);
        cMeterNo.setFont(new Font("Tahoma", Font.BOLD, 14));
        cMeterNo.setForeground(new Color(0, 102, 102));

        try {

            // Connection with database:
            Connection c = ConnectionProvider.getConnection();
            Statement s = c.createStatement();

            String query = "select * from customer";
            LOGGER.info("query For Meter No: " + query);

            // Fetch the all records from customer table:
            ResultSet rs = s.executeQuery(query);


            while (rs.next()) {

                // Fetch the meter no adding into choice cMeterNo:
                cMeterNo.add(rs.getString("meter"));

            }

        } catch (Exception e) {

            e.printStackTrace();
            StringWriter errors = new StringWriter();
            e.printStackTrace(new PrintWriter(errors));
            LOGGER.info("----CalculateBill:: Exception CalculateBill Constructor----" + errors);

        }


        /*----Added lMeterNo and cMeterNo into panel:----*/
        panel.add(lMeterNo);
        panel.add(cMeterNo);

        //============================================================================================================//

        /*----Label Name:----*/
        JLabel lName = new JLabel("Name");
        lName.setBounds(60, 120, 100, 30);
        lName.setFont(new Font("Tahoma", Font.BOLD, 14));
        /*----Added lName into panel----*/
        panel.add(lName);

        /*----Label Address:----*/
        JLabel lAddress = new JLabel("Address");
        lAddress.setBounds(60, 170, 100, 30);
        lAddress.setFont(new Font("Tahoma", Font.BOLD, 14));
        /*----Added lAddress into panel----*/
        panel.add(lAddress);

        /*----Label Value for Name and Address:----*/
        JLabel lNameValue = new JLabel();
        lNameValue.setBounds(200, 120, 180, 20);
        lNameValue.setFont(new Font("Tahoma", Font.BOLD, 14));
        /*----Added lNameValue into panel----*/
        panel.add(lNameValue);

        JLabel lAddressValue = new JLabel();
        lAddressValue.setBounds(200, 170, 180, 20);
        lAddressValue.setFont(new Font("Tahoma", Font.BOLD, 14));
        /*----Added lAddressValue into panel----*/
        panel.add(lAddressValue);

        try {

            // Connection with database:
            Connection c = ConnectionProvider.getConnection();
            Statement s = c.createStatement();

            String customerQuery = "select * from customer where meter = '" + cMeterNo.getSelectedItem() + "'";
            LOGGER.info("customerQuery: " + customerQuery);

            // Fetch the records from customer table where meter no:
            ResultSet rs = s.executeQuery(customerQuery);

            while (rs.next()) {

                // Fetch the name and address from records and adding lNameValue and lAddressValue label:
                lNameValue.setText(rs.getString("name"));
                lAddressValue.setText(rs.getString("address"));

            }

        } catch (Exception e) {

            e.printStackTrace();
            StringWriter errors = new StringWriter();
            e.printStackTrace(new PrintWriter(errors));
            LOGGER.info("----CalculateBill:: Exception CalculateBill Constructor----" + errors);

        }

        //============================================================================================================//

        /*----On the change of meter no in choice. specific name and address are show----*/
        cMeterNo.addItemListener(new ItemListener() {
            public void itemStateChanged (ItemEvent ae) {

                try {

                    // Connection with database:
                    Connection c = ConnectionProvider.getConnection();
                    Statement s = c.createStatement();

                    String customerQuery = "select * from customer where meter = '" + cMeterNo.getSelectedItem() + "'";
                    LOGGER.info("customerQuery: " + customerQuery);


                    // Fetch the records from customer table where meter no:
                    ResultSet rs = s.executeQuery(customerQuery);

                    while (rs.next()) {

                        // Fetch the name and address from records and adding l11 and l12 label:
                        lNameValue.setText(rs.getString("name"));
                        lAddressValue.setText(rs.getString("address"));

                    }

                } catch (Exception e) {

                    e.printStackTrace();
                    StringWriter errors = new StringWriter();
                    e.printStackTrace(new PrintWriter(errors));
                    LOGGER.info("----CalculateBill:: Exception CalculateBill Constructor----" + errors);

                }

            }
        });

        //============================================================================================================//

        /*----Label Units Consumed:----*/
        lUnitsConsumed = new JLabel("Units Consumed");
        lUnitsConsumed.setBounds(60, 220, 130, 30);
        lUnitsConsumed.setFont(new Font("Tahoma", Font.BOLD, 14));

        /*----TextField Units Consumed:----*/
        tUnitsConsumed = new JTextField();
        tUnitsConsumed.setBounds(200, 220, 180, 20);
        tUnitsConsumed.setFont(new Font("Tahoma", Font.BOLD, 14));

        /*----Added lUnitsConsumed and tUnitsConsumed into panel:----*/
        panel.add(lUnitsConsumed);
        panel.add(tUnitsConsumed);


        //============================================================================================================//


        /*----Label Month:----*/
        lMonth = new JLabel("Month");
        lMonth.setBounds(60, 270, 100, 30);
        lMonth.setFont(new Font("Tahoma", Font.BOLD, 14));

        /*----Choice Month:----*/
        cMonth = new Choice();
        cMonth.setBounds(200, 270, 180, 20);
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

        /*----Added lMonth and cMonth into panel:----*/
        panel.add(lMonth);
        panel.add(cMonth);


        //============================================================================================================//

        /*----Create Submit and Cancel Button and also set background and foreground color to it:----*/
        ImageIcon iSave = new ImageIcon(ClassLoader.getSystemResource("icon/save.png"));
        Image imgSave = iSave.getImage().getScaledInstance(16, 16, Image.SCALE_DEFAULT);
        submitButton = new JButton("Submit", new ImageIcon(imgSave));
        submitButton.setBounds(100, 350, 100, 25);
        submitButton.setFont(new Font("Tahoma", Font.BOLD, 13));

        submitButton.setBackground(Color.WHITE);
        submitButton.setForeground(Color.BLACK);


        ImageIcon iCancel = new ImageIcon(ClassLoader.getSystemResource("icon/cancel.jpg"));
        Image imgCancel = iCancel.getImage().getScaledInstance(16, 16, Image.SCALE_DEFAULT);
        cancelButton = new JButton("Cancel", new ImageIcon(imgCancel));
        cancelButton.setBounds(230, 350, 100, 25);
        cancelButton.setFont(new Font("Tahoma", Font.BOLD, 13));

        cancelButton.setBackground(Color.WHITE);
        cancelButton.setForeground(Color.BLACK);

        /*----Added Both Submit and Cancel Button into panel:----*/
        panel.add(submitButton);
        panel.add(cancelButton);

        /*----adding action listener to Submit and cancel button:----*/
        submitButton.addActionListener(this);
        cancelButton.addActionListener(this);

        //============================================================================================================//


        // Label ImageIcon
        ImageIcon iHicon = new ImageIcon(ClassLoader.getSystemResource("icon/hicon2.jpg"));
        Image imgHicon = iHicon.getImage().getScaledInstance(180, 270, Image.SCALE_DEFAULT);
        lHiIcon = new JLabel(new ImageIcon(imgHicon));

        //============================================================================================================//

        setLayout(new BorderLayout(30, 30));
        /*----Added panel and lJiIcon into frame:----*/
        add(panel, "Center");
        add(lHiIcon, "West");

        //============================================================================================================//

        /*----Create frame with properties----:*/

        getContentPane().setBackground(Color.WHITE);
        setSize(750, 500);
        setLocation(550, 220);

    }

    public static void main (String[] args) {
        LOGGER.info("==: CalculateBill:: Inside main Method:==");
        new CalculateBill().setVisible(true);
    }

    @Override
    public void actionPerformed (ActionEvent ae) {

        LOGGER.info("==:CalculateBill:: Inside actionPerformed Method:==");

        // If click on Submit button:
        if (ae.getSource() == submitButton) {

            // Fetch meterNo from cMeterNo:
            String meterNo = cMeterNo.getSelectedItem();
            LOGGER.info("meterNo: " + meterNo);

            // Fetch units from tUnitsConsumed
            String units = tUnitsConsumed.getText();
            LOGGER.info("units: " + units);

            // Fetch units from cMonth:
            String month = cMonth.getSelectedItem();
            LOGGER.info("month: " + month);

            // String to Integer:
            int unitsConsumed = Integer.parseInt(units);

            int totalBill = 0;
            try {

                // Connection with database:
                Connection c = ConnectionProvider.getConnection();
                Statement s = c.createStatement();

                String taxQuery = "select * from tax";
                LOGGER.info("taxQuery: " + taxQuery);


                // Fetch the all record from tax table:
                ResultSet rs = s.executeQuery(taxQuery);

                while (rs.next()) {

                    /*---- Calculate Bill:----*/
                    totalBill = unitsConsumed * Integer.parseInt(rs.getString("cost_per_unit")); // 120 * 9
                    totalBill += Integer.parseInt(rs.getString("meter_rent"));
                    totalBill += Integer.parseInt(rs.getString("service_charge"));
                    totalBill += Integer.parseInt(rs.getString("service_tax"));
                    totalBill += Integer.parseInt(rs.getString("swachh_bharat_cess"));
                    totalBill += Integer.parseInt(rs.getString("fixed_tax"));

                    LOGGER.info("totalBill: " + totalBill);

                }

            } catch (Exception e) {

                e.printStackTrace();
                StringWriter errors = new StringWriter();
                e.printStackTrace(new PrintWriter(errors));
                LOGGER.info("----CalculateBill::Exception actionPerformed----" + errors);

            }

            // after calculating the bill then update into bill table:
            String billQuery =
                    "insert into bill values('" + meterNo + "','" + month + "','" + units + "','" + totalBill + "', 'Not Paid')";

            try {

                // Connection with database:
                Connection c = ConnectionProvider.getConnection();
                Statement s = c.createStatement();

                // Now update into bill table:
                s.executeUpdate(billQuery);

                // after update the bill table, pop-up message is showing "Customer Bill Updated Successfully":
                JOptionPane.showMessageDialog(null, "Customer Bill Updated Successfully");

                // Then simply close the current frame:
                this.setVisible(false);

            } catch (Exception aee) {

                aee.printStackTrace();
                StringWriter errors = new StringWriter();
                aee.printStackTrace(new PrintWriter(errors));
                LOGGER.info("----CalculateBill:: Exception actionPerformed Method----" + errors);

            }

            // If click on Cancel Button:
        } else if (ae.getSource() == cancelButton) {

            // then simply close the current window:
            this.setVisible(false);

        }
    }
}

package electricity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.Statement;
import java.util.Random;
import java.util.logging.Logger;

public class NewCustomer extends JFrame implements ActionListener {

    private static final Logger LOGGER = Logger.getLogger(String.valueOf(NewCustomer.class));

    JLabel lCustomerName, lMeterNo, lAddress, lState, lCity, lEmail, lPhoneNo, l8, tMeterValue;
    JTextField tCustomerName, tAddress, tState, tCity, tEmail, tPhoneNo;
    JButton nextButton, cancelButton;


    NewCustomer () {

        //============================================================================================================//

        LOGGER.info("==: NewCustomer:: Inside NewCustomer Constructor :=");

        //============================================================================================================//

        // set the location and size:
        setLocation(600, 200);
        setSize(700, 500);

        //============================================================================================================//


        /*----Create Panel----*/
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.WHITE);
        panel.setBackground(new Color(173, 216, 230));

        //============================================================================================================//

        /*----Title New Customer----*/
        JLabel title = new JLabel("New Customer");
        title.setBounds(180, 10, 200, 26);
        title.setFont(new Font("Tahoma", Font.BOLD, 24));
        panel.add(title);

        //============================================================================================================//

        /*----JLabel For Customer Name:----*/
        lCustomerName = new JLabel("Customer Name");
        lCustomerName.setBounds(100, 80, 100, 20);
        lCustomerName.setFont(new Font("Tahoma", Font.BOLD, 12));

        /*----TextField for Customer Name:----*/
        tCustomerName = new JTextField();
        tCustomerName.setBounds(240, 80, 200, 20);
        tCustomerName.setFont(new Font("Tahoma", Font.BOLD, 12));

        /*----Adding label and textField into panel:----*/
        panel.add(lCustomerName);
        panel.add(tCustomerName);


        //============================================================================================================//


        /*----Label Meter No:----*/
        lMeterNo = new JLabel("Meter No");
        lMeterNo.setBounds(100, 120, 100, 20);
        lMeterNo.setFont(new Font("Tahoma", Font.BOLD, 12));

        /*----Label For Meter No value: which is non-editable:----*/
        tMeterValue = new JLabel();
        tMeterValue.setBounds(240, 120, 200, 20);
        tMeterValue.setFont(new Font("Tahoma", Font.BOLD, 12));

        /*----Generating Random Meter No. of 6 Digits:----*/
        Random ran = new Random();
        long first = (ran.nextLong() % 1000000);
        tMeterValue.setText("" + Math.abs(first));

        /*----Adding label into panel----*/
        panel.add(lMeterNo);
        panel.add(tMeterValue);


        //============================================================================================================//


        /*----Label Address:----*/
        lAddress = new JLabel("Address");
        lAddress.setBounds(100, 160, 100, 20);
        lAddress.setFont(new Font("Tahoma", Font.BOLD, 12));

        /*----TextField Address:----*/
        tAddress = new JTextField();
        tAddress.setBounds(240, 160, 200, 20);
        tAddress.setFont(new Font("Tahoma", Font.BOLD, 12));

        /*----Adding label and textField into panel:----*/
        panel.add(lAddress);
        panel.add(tAddress);


        //============================================================================================================//


        /*----Label City:----*/
        lCity = new JLabel("City");
        lCity.setBounds(100, 200, 100, 20);
        lCity.setFont(new Font("Tahoma", Font.BOLD, 12));

        /*----TextField City:----*/
        tCity = new JTextField();
        tCity.setBounds(240, 200, 200, 20);
        tCity.setFont(new Font("Tahoma", Font.BOLD, 12));

        /*----Adding label and textField into panel:----*/
        panel.add(lCity);
        panel.add(tCity);

        //============================================================================================================//


        /*----Label State:----*/
        lState = new JLabel("State");
        lState.setBounds(100, 240, 100, 20);
        lState.setFont(new Font("Tahoma", Font.BOLD, 12));

        /*----TextField State:----*/
        tState = new JTextField();
        tState.setBounds(240, 240, 200, 20);
        tState.setFont(new Font("Tahoma", Font.BOLD, 12));

        /*----Adding label and textField into panel:----*/
        panel.add(lState);
        panel.add(tState);

        //============================================================================================================//


        /*----Label Email:----*/
        lEmail = new JLabel("Email");
        lEmail.setBounds(100, 280, 100, 20);
        lEmail.setFont(new Font("Tahoma", Font.BOLD, 12));

        /*----TextField Email:----*/
        tEmail = new JTextField();
        tEmail.setBounds(240, 280, 200, 20);
        tEmail.setFont(new Font("Tahoma", Font.BOLD, 12));

        /*----Adding label and textField into panel:----*/
        panel.add(lEmail);
        panel.add(tEmail);


        //============================================================================================================//


        /*----Label Phone:----*/
        lPhoneNo = new JLabel("Phone Number");
        lPhoneNo.setBounds(100, 320, 100, 20);
        lPhoneNo.setFont(new Font("Tahoma", Font.BOLD, 12));

        /*----TextField Phone:----*/
        tPhoneNo = new JTextField();
        tPhoneNo.setBounds(240, 320, 200, 20);
        tPhoneNo.setFont(new Font("Tahoma", Font.BOLD, 12));

        /*----Adding label and textField into panel:----*/
        panel.add(lPhoneNo);
        panel.add(tPhoneNo);

        //============================================================================================================//


        /*----Creating Next and Cancel Button and also set the background and foreground color to it:----*/

        ImageIcon iIcon = new ImageIcon(ClassLoader.getSystemResource("icon/next.png"));
        Image nextImage = iIcon.getImage().getScaledInstance(16, 16, Image.SCALE_DEFAULT);
        nextButton = new JButton("Next", new ImageIcon(nextImage));
        nextButton.setBounds(140, 390, 100, 25);
        nextButton.setFont(new Font("Tahoma", Font.BOLD, 14));

        nextButton.setBackground(Color.WHITE);
        nextButton.setForeground(Color.BLACK);


        ImageIcon cIcon = new ImageIcon(ClassLoader.getSystemResource("icon/cancel.jpg"));
        Image cancelImage = cIcon.getImage().getScaledInstance(16, 16, Image.SCALE_DEFAULT);
        cancelButton = new JButton("Cancel", new ImageIcon(cancelImage));
        cancelButton.setBounds(290, 390, 100, 25);
        cancelButton.setFont(new Font("Tahoma", Font.BOLD, 14));

        cancelButton.setBackground(Color.WHITE);
        cancelButton.setForeground(Color.BLACK);

        // Adding Next and Cancel Button to panel:
        panel.add(nextButton);
        panel.add(cancelButton);

        // adding action listener to Next and Cancel Button:
        nextButton.addActionListener(this);
        cancelButton.addActionListener(this);


        //============================================================================================================//

        setLayout(new BorderLayout());

        // adjust the panel to center:
        add(panel, "Center");

        //============================================================================================================//

        // New Customer Image:
        ImageIcon ic1 = new ImageIcon(ClassLoader.getSystemResource("icon/hicon1.jpg"));
        Image i3 = ic1.getImage().getScaledInstance(150, 300, Image.SCALE_DEFAULT);
        ImageIcon ic2 = new ImageIcon(i3);
        l8 = new JLabel(ic2);
        // New Customer adding into West side:
        add(l8, "West");


        //============================================================================================================//
        //for changing the color of the whole Frame
        getContentPane().setBackground(Color.WHITE);


    }

    public static void main (String[] args) {

        LOGGER.info("==: NewCustomer:: Inside main Method:==");
        new NewCustomer().setVisible(true);
    }

    public void actionPerformed (ActionEvent ae) {

        LOGGER.info("==: NewCustomer:: Inside actionPerformed Method :==");

        // If click on Next Button:
        if (ae.getSource() == nextButton) {

            // Fetch name from tCustomerName:
            String name = tCustomerName.getText();
            LOGGER.info("name: " + name);

            // Fetch meter from tMeterValue:
            String meter = tMeterValue.getText();
            LOGGER.info("meter: " + meter);

            // Fetch address from tAddress:
            String address = tAddress.getText();
            LOGGER.info("address: " + address);

            // Fetch state from tState:
            String state = tState.getText();
            LOGGER.info("state: " + state);

            // Fetch city from tCity:
            String city = tCity.getText();
            LOGGER.info("city: " + city);

            // Fetch email from tEmail:
            String email = tEmail.getText();
            LOGGER.info("email: " + email);

            // Fetch phone from tPhoneNo:
            String phone = tPhoneNo.getText();
            LOGGER.info("phone: " + phone);


            String query1 = "insert into customer values('" + name + "','" + meter + "','" + address + "','" + city +
                    "','" + state + "','" + email + "','" + phone + "')";
            LOGGER.info("query1: " + query1);


            String query2 = "insert into login values('" + meter + "', '', '" + name + "', '', '')";
            LOGGER.info("query2: " + query2);


            try {

                // Connection with Database:
                Connection c1 = ConnectionProvider.getConnection();
                Statement s = c1.createStatement();

                // update information into both customer and login table:
                s.executeUpdate(query1);
                s.executeUpdate(query2);

                // after the both the table just show the pop-up message
                // Customer Details Added Successfully
                JOptionPane.showMessageDialog(null, "Customer Details Added Successfully");

                // Then simply close the current frame and open MeterInfo frame:
                this.setVisible(false);
                new MeterInfo(meter).setVisible(true);

            } catch (Exception ex) {


                ex.printStackTrace();
                StringWriter errors = new StringWriter();
                ex.printStackTrace(new PrintWriter(errors));
                LOGGER.info("----NewCustomer:: Getting actionPerformed----" + ex);

            }

            // If click on Cancel Button:
        } else if (ae.getSource() == cancelButton) {

            // then simply close the current windows:
            this.setVisible(false);

        }
    }
}

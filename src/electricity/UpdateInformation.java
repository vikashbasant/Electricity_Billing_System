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

public class UpdateInformation extends JFrame implements ActionListener {

    private static final Logger LOGGER = Logger.getLogger(String.valueOf(UpdateInformation.class));

    JTextField tAddress, tCity, tState, tEmail, tPhone;

    JLabel lNameValue, lMeterValue;

    JButton updateButton, backButton;

    String meter;

    UpdateInformation (String meter) {

        //============================================================================================================//

        LOGGER.info("==: UpdateInformation:: Inside UpdateInformation Constructor :==");

        //============================================================================================================//

        /*----Set the meter value:----*/
        this.meter = meter;

        //============================================================================================================//

        /*----Create Frame----*/
        setBounds(500, 220, 1050, 500);
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);

        //============================================================================================================//

        /*----Label UPDATE CUSTOMER INFORMATION:----*/
        JLabel title = new JLabel("UPDATE CUSTOMER INFORMATION");
        title.setBounds(110, 0, 400, 30);
        title.setFont(new Font("Tahoma", Font.BOLD, 20));
        // add title into frame:
        add(title);

        //============================================================================================================//

        /*----Label Name:----*/
        JLabel lName = new JLabel("Name");
        lName.setBounds(30, 70, 100, 20);
        lName.setFont(new Font("Tahoma", Font.BOLD, 14));
        lName.setForeground(new Color(0, 102, 102));
        // add lName into frame:
        add(lName);

        /*----Label Name Value:----*/
        lNameValue = new JLabel();
        lNameValue.setBounds(230, 70, 200, 20);
        lNameValue.setFont(new Font("Tahoma", Font.BOLD, 14));
        // add lNameValue into frame:
        add(lNameValue);

        //============================================================================================================//

        /*----Label Meter Number:----*/
        JLabel lMeterNo = new JLabel("Meter Number");
        lMeterNo.setBounds(30, 110, 100, 20);
        lMeterNo.setFont(new Font("Tahoma", Font.BOLD, 14));
        // add lMeterNo into frame:
        add(lMeterNo);

        /*----Label Meter Value----*/
        lMeterValue = new JLabel();
        lMeterValue.setBounds(230, 110, 200, 20);
        lMeterValue.setFont(new Font("Tahoma", Font.BOLD, 14));
        lMeterValue.setForeground(new Color(0, 102, 102));
        // add lMeterValue into frame:
        add(lMeterValue);

        //============================================================================================================//

        /*----Label Address:----*/
        JLabel lAddress = new JLabel("Address");
        lAddress.setBounds(30, 150, 100, 20);
        lAddress.setFont(new Font("Tahoma", Font.BOLD, 14));
        // add lAddress into frame:
        add(lAddress);

        /*----TextFiled Address:----*/
        tAddress = new JTextField();
        tAddress.setBounds(230, 150, 200, 20);
        tAddress.setFont(new Font("Tahoma", Font.BOLD, 14));
        // add tAddress into frame:
        add(tAddress);

        //============================================================================================================//

        /*----Label City:----*/
        JLabel lCity = new JLabel("City");
        lCity.setBounds(30, 190, 100, 20);
        lCity.setFont(new Font("Tahoma", Font.BOLD, 14));
        // add lCity into frame:
        add(lCity);

        /*----TextFiled City:----*/
        tCity = new JTextField();
        tCity.setBounds(230, 190, 200, 20);
        tCity.setFont(new Font("Tahoma", Font.BOLD, 14));
        // add tCity into frame:
        add(tCity);

        //============================================================================================================//

        /*----Label State:----*/
        JLabel lState = new JLabel("State");
        lState.setBounds(30, 230, 100, 20);
        lState.setFont(new Font("Tahoma", Font.BOLD, 14));
        // add tState into frame:
        add(lState);

        /*----TextFiled State:----*/
        tState = new JTextField();
        tState.setBounds(230, 230, 200, 20);
        tState.setFont(new Font("Tahoma", Font.BOLD, 14));
        // add tState into frame:
        add(tState);

        //============================================================================================================//

        /*----Label Email:----*/
        JLabel lEmail = new JLabel("Email");
        lEmail.setBounds(30, 270, 100, 20);
        lEmail.setFont(new Font("Tahoma", Font.BOLD, 14));
        // add lEmail into frame:
        add(lEmail);

        /*----TextField Email:----*/
        tEmail = new JTextField();
        tEmail.setBounds(230, 270, 200, 20);
        tEmail.setFont(new Font("Tahoma", Font.BOLD, 14));
        // add tEmail into frame:
        add(tEmail);

        //============================================================================================================//

        /*----Label Phone:----*/
        JLabel lPhone = new JLabel("Phone");
        lPhone.setBounds(30, 310, 100, 20);
        lPhone.setFont(new Font("Tahoma", Font.BOLD, 14));
        // add lPhone into frame:
        add(lPhone);

        /*----TextField Phone:----*/
        tPhone = new JTextField();
        tPhone.setBounds(230, 310, 200, 20);
        tPhone.setFont(new Font("Tahoma", Font.BOLD, 14));
        // add tPhone into frame:
        add(tPhone);


        //============================================================================================================//


        try {
            // Connection with database:
            Connection c = ConnectionProvider.getConnection();
            Statement s = c.createStatement();

            String customerQuery = "select * from customer where meter = '" + meter + "'";
            LOGGER.info("Customer query: " + customerQuery);

            // Fetch the records from customer where meter = ?:
            ResultSet rs = s.executeQuery(customerQuery);

            while (rs.next()) {

                // Fetch customer name and set into lNameValue:
                lNameValue.setText(rs.getString(1));

                // Fetch customer meter number set into lMeterValue:
                lMeterValue.setText(rs.getString(2));

                // Fetch customer address set into billTable:
                tAddress.setText(rs.getString(3));

                // Fetch customer city set into tCity:
                tCity.setText(rs.getString(4));

                // Fetch customer state set into tState:
                tState.setText(rs.getString(5));

                // Fetch customer Email set into tEmail:
                tEmail.setText(rs.getString(6));

                // Fetch customer Phone set into tPhone:
                tPhone.setText(rs.getString(7));

            }

        } catch (Exception e) {

            e.printStackTrace();
            StringWriter errors = new StringWriter();
            e.printStackTrace(new PrintWriter(errors));
            LOGGER.info("----UpdateInformation:: Getting Exception----" + errors);

        }

        //============================================================================================================//

        /*----Create Update and Back Button and set properties to that button:----*/
        ImageIcon updateIcon = new ImageIcon(ClassLoader.getSystemResource("icon/update.jpg"));
        Image updateImage = updateIcon.getImage().getScaledInstance(16, 16, Image.SCALE_DEFAULT);
        updateButton = new JButton("Update", new ImageIcon(updateImage));
        updateButton.setBackground(Color.WHITE);
        updateButton.setForeground(Color.BLACK);
        updateButton.setBounds(70, 360, 130, 25);
        updateButton.setFont(new Font("Tahoma", Font.BOLD, 14));
        updateButton.addActionListener(this);
        // add updateButton into frame:
        add(updateButton);

        ImageIcon backIcon = new ImageIcon(ClassLoader.getSystemResource("icon/back3.png"));
        Image backImage = backIcon.getImage().getScaledInstance(16, 16, Image.SCALE_DEFAULT);
        backButton = new JButton("Back", new ImageIcon(backImage));
        backButton.setBackground(Color.WHITE);
        backButton.setForeground(Color.BLACK);
        backButton.setBounds(230, 360, 130, 25);
        backButton.setFont(new Font("Tahoma", Font.BOLD, 14));
        backButton.addActionListener(this);
        // add backButton into frame:
        add(backButton);


        //============================================================================================================//


        /*----Label ImageIcon----*/
        ImageIcon iconUpdate = new ImageIcon(ClassLoader.getSystemResource("icon/update.jpg"));
        Image imageUpdate = iconUpdate.getImage().getScaledInstance(400, 300, Image.SCALE_DEFAULT);
        JLabel lUpdate = new JLabel(new ImageIcon(imageUpdate));
        lUpdate.setBounds(550, 50, 400, 300);
        // add lUpdate into frame:
        add(lUpdate);
    }

    public static void main (String[] args) {
        LOGGER.info("==: UpdateInformation:: Inside main Method :==");
        new UpdateInformation("").setVisible(true);

    }

    @Override
    public void actionPerformed (ActionEvent ae) {

        LOGGER.info("==: UpdateInformation:: Inside actionPerformed Method:==");

        // If click on Update Button: Simply update the all information for selected meter no:
        if (ae.getSource() == updateButton) {

            // Fetch text value of address:
            String addressValue = tAddress.getText();
            LOGGER.info("addressValue: " + addressValue);

            // Fetch text value of City:
            String cityValue = tCity.getText();
            LOGGER.info("cityValue: " + cityValue);

            // Fetch text value of state:
            String stateValue = tState.getText();
            LOGGER.info("stateValue: " + stateValue);

            // Fetch text value of Email:
            String emailValue = tEmail.getText();
            LOGGER.info("emailValue: " + emailValue);

            // Fetch text value of phone:
            String phoneValue = tPhone.getText();
            LOGGER.info("phoneValue: " + phoneValue);


            try {

                // Connection with database:
                Connection c = ConnectionProvider.getConnection();
                Statement s = c.createStatement();

                // update into customer table with meter = ?:
                String customerQuery = "update customer set address = '" + addressValue + "', city = '" + cityValue +
                        "', state " +
                        "= " +
                        "'" + stateValue + "', email = '" + emailValue + "', phone = '" + phoneValue + "' where meter = '" + meter +
                        "'";
                LOGGER.info("customerQuery: " + customerQuery);

                s.executeUpdate(customerQuery);

                // after successfully update into customer table just shows pop-up:
                // "Details Updated Successfully"
                JOptionPane.showMessageDialog(null, "Details Updated Successfully");

                // Now simply close the current window:
                this.setVisible(false);

            } catch (Exception e) {

                e.printStackTrace();
                StringWriter errors = new StringWriter();
                e.printStackTrace(new PrintWriter(errors));
                LOGGER.info("----UpdateInformation:: Getting Exception actionPerformed Method----" + e);

            }

            // If click Back Button:
        } else if (ae.getSource() == backButton) {

            // then simply close the current window:
            this.setVisible(false);

        }
    }
}

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

public class ViewInformation extends JFrame implements ActionListener {

    private static final Logger LOGGER = Logger.getLogger(String.valueOf(ViewInformation.class));

    JButton backButton;

    ViewInformation (String meter) {

        //============================================================================================================//

        /*----Create Frame----*/
        setBounds(600, 250, 850, 665);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        //============================================================================================================//

        /*----Label VIEW CUSTOMER INFORMATION----*/
        JLabel title = new JLabel("VIEW CUSTOMER INFORMATION");
        title.setBounds(250, 0, 500, 40);
        title.setFont(new Font("Tahoma", Font.BOLD, 20));
        // add title into frame:
        add(title);


        //============================================================================================================//

        /*----Label Name----*/
        JLabel lName = new JLabel("Name");
        lName.setBounds(70, 80, 100, 20);
        lName.setFont(new Font("Tahoma", Font.BOLD, 14));
        // add lName into frame:
        add(lName);

        /*----Label Name Value:----*/
        JLabel lNameVale = new JLabel();
        lNameVale.setBounds(250, 80, 100, 20);
        lNameVale.setFont(new Font("Tahoma", Font.BOLD, 14));
        // add lNameValue into frame:
        add(lNameVale);

        //============================================================================================================//

        /*----Label Meter Number:----*/
        JLabel lMeterNo = new JLabel("Meter Number");
        lMeterNo.setBounds(70, 140, 100, 20);
        lMeterNo.setFont(new Font("Tahoma", Font.BOLD, 14));
        // add lMeterNo into frame:
        add(lMeterNo);

        /*----Label Meter Number Value:----*/
        JLabel lMeterValue = new JLabel();
        lMeterValue.setBounds(250, 140, 100, 20);
        lMeterValue.setFont(new Font("Tahoma", Font.BOLD, 14));
        // add lMeterValue into frame:
        add(lMeterValue);

        //============================================================================================================//

        /*----Label Address:----*/
        JLabel lAddress = new JLabel("Address");
        lAddress.setBounds(70, 200, 100, 20);
        lAddress.setFont(new Font("Tahoma", Font.BOLD, 14));
        // add lAddress into frame:
        add(lAddress);

        /*----Label Address Value:----*/
        JLabel lAddressValue = new JLabel();
        lAddressValue.setBounds(250, 200, 100, 20);
        lAddressValue.setFont(new Font("Tahoma", Font.BOLD, 14));
        // add lAddressValue into frame:
        add(lAddressValue);

        //============================================================================================================//

        /*----Label City:----*/
        JLabel lCity = new JLabel("City");
        lCity.setBounds(70, 260, 100, 20);
        lCity.setFont(new Font("Tahoma", Font.BOLD, 14));
        // add lCity into frame:
        add(lCity);

        /*----Label City Value:----*/
        JLabel lCityValue = new JLabel();
        lCityValue.setBounds(250, 260, 100, 20);
        lCityValue.setFont(new Font("Tahoma", Font.BOLD, 14));
        // add lCityValue into frame:
        add(lCityValue);

        //============================================================================================================//

        /*----Label State:----*/
        JLabel lState = new JLabel("State");
        lState.setBounds(500, 80, 100, 20);
        lState.setFont(new Font("Tahoma", Font.BOLD, 14));
        // add lState into frame
        add(lState);

        /*----Label State Value:----*/
        JLabel lStateValue = new JLabel();
        lStateValue.setBounds(650, 80, 100, 20);
        lStateValue.setFont(new Font("Tahoma", Font.BOLD, 14));
        // add lStateValue into frame
        add(lStateValue);

        //============================================================================================================//

        /*----Label Email:----*/
        JLabel lEmail = new JLabel("Email");
        lEmail.setBounds(500, 140, 100, 20);
        lEmail.setFont(new Font("Tahoma", Font.BOLD, 14));
        // add lEmail into frame
        add(lEmail);

        /*----Label Email Value:----*/
        JLabel lEmailValue = new JLabel();
        lEmailValue.setBounds(650, 140, 150, 20);
        lEmailValue.setFont(new Font("Tahoma", Font.BOLD, 14));
        // add lEmailValue into frame
        add(lEmailValue);

        //============================================================================================================//

        /*----Label Phone:----*/
        JLabel lPhone = new JLabel("Phone");
        lPhone.setBounds(500, 200, 100, 20);
        lPhone.setFont(new Font("Tahoma", Font.BOLD, 14));
        // add lPhone into frame
        add(lPhone);

        /*----Label Phone Value:----*/
        JLabel lPhoneValue = new JLabel();
        lPhoneValue.setBounds(650, 200, 100, 20);
        lPhoneValue.setFont(new Font("Tahoma", Font.BOLD, 14));
        // add lPhoneValue into frame:
        add(lPhoneValue);

        //============================================================================================================//

        /*----Try to fetch all information of the specific meter no. and add into all values----*/

        try {

            // Connection with database:
            Connection c = ConnectionProvider.getConnection();
            Statement s = c.createStatement();

            String customerQuery = "select * from customer where meter = '" + meter + "'";
            LOGGER.info("customerQuery: " + customerQuery);

            // Fetch the all records from customer table where meter = ?:
            ResultSet rs = s.executeQuery(customerQuery);

            while (rs.next()) {

                // Fetch customer name from resultSet, set into lNameVale
                lNameVale.setText(rs.getString(1));

                // Fetch customer Meter Number from resultSet, set into lMeterValue
                lMeterValue.setText(rs.getString(2));

                // Fetch customer Address from resultSet, set into lAddressValue
                lAddressValue.setText(rs.getString(3));

                // Fetch customer city from resultSet, set into lCityValue
                lCityValue.setText(rs.getString(4));

                // Fetch customer state from resultSet, set into lStateValue
                lStateValue.setText(rs.getString(5));

                // Fetch customer Email from resultSet, set into lEmailValue
                lEmailValue.setText(rs.getString(6));

                // Fetch customer Phone from resultSet, set into lPhoneValue
                lPhoneValue.setText(rs.getString(7));

            }

        } catch (Exception e) {

            e.printStackTrace();
            StringWriter errors = new StringWriter();
            e.printStackTrace(new PrintWriter(errors));
            LOGGER.info("----ViewInformation:: Getting Exception ViewInformation Constructor----" + errors);
        }

        //============================================================================================================//

        /*----Create Back Button----*/
        ImageIcon backIcon = new ImageIcon(ClassLoader.getSystemResource("icon/back3.png"));
        Image backImage = backIcon.getImage().getScaledInstance(16, 16, Image.SCALE_DEFAULT);

        backButton = new JButton("Back", new ImageIcon(backImage));
        // set properties of Back Button:
        backButton.setBackground(Color.WHITE);
        backButton.setForeground(Color.BLACK);
        backButton.setBounds(350, 340, 120, 30);
        backButton.setFont(new Font("Tahoma", Font.BOLD, 20));
        // addActionListener when click on this button then they trigger the action listener:
        backButton.addActionListener(this);
        // added Back Button to frame:
        add(backButton);

        //============================================================================================================//

        /*----Label ImageIcon:----*/
        ImageIcon iViewCustomer = new ImageIcon(ClassLoader.getSystemResource("icon/viewcustomer.jpg"));
        Image imgViewCustomer = iViewCustomer.getImage().getScaledInstance(600, 300, Image.SCALE_DEFAULT);
        JLabel viewCustomer = new JLabel(new ImageIcon(imgViewCustomer));
        viewCustomer.setBounds(20, 350, 600, 300);
        // add viewCustomer to frame:
        add(viewCustomer);

    }

    public static void main (String[] args) {

        LOGGER.info("==: ViewInformation:: Inside main Method :==");
        new ViewInformation("").setVisible(true);
    }

    @Override
    public void actionPerformed (ActionEvent ae) {

        LOGGER.info("==: ViewInformation:: Inside actionPerformed Method:==");

        // simply close the current window:
        this.setVisible(false);

    }
}

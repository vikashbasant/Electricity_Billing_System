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

    JTextField t1, t2, t3, t4, t5, t6, t7;

    JLabel l11, l12;

    JButton b1, b2;

    String meter;

    UpdateInformation (String meter) {

        LOGGER.info("==: UpdateInformation:: Inside UpdateInformation Consturctor :==");

        // Set the meter value:
        this.meter = meter;

        // Set the Bounds:
        setBounds(500, 220, 1050, 450);
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);

        // Label UPDATE CUSTOMER INFORMATION:
        JLabel title = new JLabel("UPDATE CUSTOMER INFORMATION");
        title.setBounds(110, 0, 400, 30);
        title.setFont(new Font("Tahoma", Font.PLAIN, 20));
        add(title);

        // Label Name:
        JLabel l1 = new JLabel("Name");
        l1.setBounds(30, 70, 100, 20);
        add(l1);

        l11 = new JLabel();
        l11.setBounds(230, 70, 200, 20);
        add(l11);

        // Label Meter Number:
        JLabel l2 = new JLabel("Meter Number");
        l2.setBounds(30, 110, 100, 20);
        add(l2);

        l12 = new JLabel();
        l12.setBounds(230, 110, 200, 20);
        add(l12);

        // Label Address:
        JLabel l3 = new JLabel("Address");
        l3.setBounds(30, 150, 100, 20);
        add(l3);

        // TextFiled Address:
        t1 = new JTextField();
        t1.setBounds(230, 150, 200, 20);
        add(t1);

        // Label City:
        JLabel l4 = new JLabel("City");
        l4.setBounds(30, 190, 100, 20);
        add(l4);

        // TextFiled City:
        t2 = new JTextField();
        t2.setBounds(230, 190, 200, 20);
        add(t2);

        // Label State:
        JLabel l5 = new JLabel("State");
        l5.setBounds(30, 230, 100, 20);
        add(l5);

        // TextFiled State:
        t3 = new JTextField();
        t3.setBounds(230, 230, 200, 20);
        add(t3);

        // Label Email:
        JLabel l6 = new JLabel("Email");
        l6.setBounds(30, 270, 100, 20);
        add(l6);

        // TextField Email:
        t4 = new JTextField();
        t4.setBounds(230, 270, 200, 20);
        add(t4);

        // Label Phone:
        JLabel l7 = new JLabel("Phone");
        l7.setBounds(30, 310, 100, 20);
        add(l7);

        // TextField Phone:
        t5 = new JTextField();
        t5.setBounds(230, 310, 200, 20);
        add(t5);


        // Create Update and Back Button and set properties to that button:
        b1 = new JButton("Update");
        b1.setBackground(Color.BLACK);
        b1.setForeground(Color.WHITE);
        b1.setBounds(70, 360, 100, 25);
        b1.addActionListener(this);
        add(b1);

        b2 = new JButton("Back");
        b2.setBackground(Color.BLACK);
        b2.setForeground(Color.WHITE);
        b2.setBounds(230, 360, 100, 25);
        b2.addActionListener(this);
        add(b2);

        try {
            // Connection with database:
            Connection c = ConnectionProvider.getConnection();
            Statement s = c.createStatement();

            // Fetch the records from customer where meter = ?:
            ResultSet rs = s.executeQuery("select * from customer where meter = '" + meter + "'");

            while (rs.next()) {

                // Fetch customer name and set into l11:
                l11.setText(rs.getString(1));

                // Fetch customer meter number set into l12:
                l12.setText(rs.getString(2));

                // Fetch customer address set into billTable:
                t1.setText(rs.getString(3));

                // Fetch customer city set into t2:
                t2.setText(rs.getString(4));

                // Fetch customer state set into t3:
                t3.setText(rs.getString(5));

                // Fetch customer Email set into t4:
                t4.setText(rs.getString(6));

                // Fetch customer Phone set into t5:
                t5.setText(rs.getString(7));

            }

        } catch (Exception e) {

            e.printStackTrace();
            StringWriter errors = new StringWriter();
            e.printStackTrace(new PrintWriter(errors));
            LOGGER.info("==: UpdateInformation:: Inside UpdateInformation Constructor :==");

        }


        // Label ImageIcon
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/update.jpg"));
        Image i2 = i1.getImage().getScaledInstance(400, 300, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel l8 = new JLabel(i3);
        l8.setBounds(550, 50, 400, 300);
        add(l8);
    }

    public static void main (String[] args) {
        LOGGER.info("==: UpdateInformation:: Inside main Method :==");
        new UpdateInformation("").setVisible(true);

    }

    @Override
    public void actionPerformed (ActionEvent ae) {

        LOGGER.info("==: UpdateInformation:: Inside actionPerformed Method:==");

        // If click on Update Button:
        if (ae.getSource() == b1) {

            // Fetch all the textfield value and update into customer table:
            String s1 = l11.getText();
            String s2 = l12.getText();
            String s3 = t1.getText();
            String s4 = t2.getText();
            String s5 = t3.getText();
            String s6 = t4.getText();
            String s7 = t5.getText();

            try {

                // Connection with database:
                Connection c = ConnectionProvider.getConnection();
                Statement s = c.createStatement();

                // update into customer table with meter = ?:
                s.executeUpdate("update customer set address = '" + s3 + "', city = '" + s4 + "', state = '" + s5 + "', email = '" + s6 + "', phone = '" + s7 + "' where meter = '" + meter + "'");

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
        } else if (ae.getSource() == b2) {

            // then simply close the current window:
            this.setVisible(false);

        }
    }
}

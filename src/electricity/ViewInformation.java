
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

public class ViewInformation extends JFrame implements ActionListener{

    private static final Logger LOGGER = Logger.getLogger(String.valueOf(ViewInformation.class));

    JButton b1;

    ViewInformation(String meter){

        // set the bound:
        setBounds(600,250, 850, 650);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        // Label VIEW CUSTOMER INFORMATION
        JLabel title = new JLabel("VIEW CUSTOMER INFORMATION");
        title.setBounds(250, 0, 500, 40);
        title.setFont(new Font("Tahoma", Font.PLAIN, 20));
        add(title);

        // Label Name
        JLabel l1 = new JLabel("Name");
        l1.setBounds(70, 80, 100, 20);
        add(l1);

        // Label Name Value:
        JLabel l11 = new JLabel();
        l11.setBounds(250, 80, 100, 20);
        add(l11);

        // Label Meter Number:
        JLabel l2 = new JLabel("Meter Number");
        l2.setBounds(70, 140, 100, 20);
        add(l2);

        // Label Meter Number Value:
        JLabel l12 = new JLabel();
        l12.setBounds(250, 140, 100, 20);
        add(l12);

        // Label Address:
        JLabel l3 = new JLabel("Address");
        l3.setBounds(70, 200, 100, 20);
        add(l3);

        // Label Address Value:
        JLabel l13 = new JLabel();
        l13.setBounds(250, 200, 100, 20);
        add(l13);

        // Label City:
        JLabel l4 = new JLabel("City");
        l4.setBounds(70, 260, 100, 20);
        add(l4);

        // Label City Value:
        JLabel l14 = new JLabel();
        l14.setBounds(250, 260, 100, 20);
        add(l14);

        // Label State:
        JLabel l5 = new JLabel("State");
        l5.setBounds(500, 80, 100, 20);
        add(l5);

        // Label State Value:
        JLabel l15 = new JLabel();
        l15.setBounds(650, 80, 100, 20);
        add(l15);

        // Label Email:
        JLabel l6 = new JLabel("Email");
        l6.setBounds(500, 140, 100, 20);
        add(l6);

        // Label Email Value:
        JLabel l16 = new JLabel();
        l16.setBounds(650, 140, 150, 20);
        add(l16);

        // Label Phone:
        JLabel l7 = new JLabel("Phone");
        l7.setBounds(500, 200, 100, 20);
        add(l7);

        // Label Phone Value:
        JLabel l17 = new JLabel();
        l17.setBounds(650, 200, 100, 20);
        add(l17);
        
        try{

            // Connection with database:
            Connection c = ConnectionProvider.getConnection();
            Statement s = c.createStatement();

            // Fetch the all records from customer table where meter = ?:
            ResultSet rs = s.executeQuery("select * from customer where meter = '"+meter+"'");

            while(rs.next()){

                // Fetch customer name from resultSet, set into l11
                l11.setText(rs.getString(1));

                // Fetch customer Meter Number from resultSet, set into l12
                l12.setText(rs.getString(2));

                // Fetch customer Address from resultSet, set into l13
                l13.setText(rs.getString(3));

                // Fetch customer city from resultSet, set into l14
                l14.setText(rs.getString(4));

                // Fetch customer state from resultSet, set into l15
                l15.setText(rs.getString(5));

                // Fetch customer Email from resultSet, set into l16
                l16.setText(rs.getString(6));

                // Fetch customer Phone from resultSet, set into l17
                l17.setText(rs.getString(7));
                
            }

        }catch(Exception e){

            e.printStackTrace();
            StringWriter errors = new StringWriter();
            e.printStackTrace(new PrintWriter(errors));
            LOGGER.info("----ViewInformation:: Getting Exception ViewInformation Constructor----" + e.toString());
        }

        // Create Back Button and set the properties into it:
        b1 = new JButton("Back");
        b1.setBackground(Color.BLACK);
        b1.setForeground(Color.WHITE);
        b1.setBounds(350, 340, 100, 25);
        b1.addActionListener(this);
        add(b1);

        // Label ImageIcon:
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/viewcustomer.jpg"));
        Image i2 = i1.getImage().getScaledInstance(600, 300, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel l8  = new JLabel(i3);
        l8.setBounds(20, 350, 600, 300);
        add(l8);

    }

    @Override
    public void actionPerformed(ActionEvent ae){

        LOGGER.info("==: ViewInformation:: Inside actionPerformed Method:==");

        // simply close the current window:
        this.setVisible(false);

    }
    
    public static void main(String[] args){

        LOGGER.info("==: ViewInformation:: Inside main Method :==");
        new ViewInformation("").setVisible(true);
    }
}

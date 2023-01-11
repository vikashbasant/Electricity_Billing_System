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

public class CalculateBill extends JFrame implements ActionListener{

    private static final Logger LOGGER = Logger.getLogger(String.valueOf(CalculateBill.class));

    JLabel l1,l2,l3,l4,l5;

    JTextField t1;

    Choice c1,c2;

    JButton b1,b2;

    JPanel p;

    CalculateBill(){

        LOGGER.info("==: CalculateBill:: Inside CalculateBill Constructor:==");

        // Create panel:
        p = new JPanel();
        p.setLayout(null);
        p.setBackground(new Color(173, 216, 230));

        // Label Calculate Electricity Bill:
        l1 = new JLabel("Calculate Electricity Bill");
        l1.setBounds(30, 10, 400, 30);

        // Label Meter No:
        l2 = new JLabel("Meter No");
        l2.setBounds(60, 70, 100, 30);

        // Choice Meter No.:
        c1 = new Choice();
        c1.setBounds(200, 70, 180, 20);

        try{

            // Connection with database:
            Connection c = ConnectionProvider.getConnection();
            Statement s = c.createStatement();

            // Fetch the all records from customer table:
            ResultSet rs = s.executeQuery("select * from customer");


            while(rs.next()){

                // Fetch the meter no adding into choice c1:
                c1.add(rs.getString("meter"));

            }

        }catch(Exception e){

            e.printStackTrace();
            StringWriter errors = new StringWriter();
            e.printStackTrace(new PrintWriter(errors));
            LOGGER.info("----CalculateBill:: Exception CalculateBill Constructor----"+e.toString());

        }

        // Label Name:
        JLabel l6 = new JLabel("Name");
        l6.setBounds(60, 120, 100, 30);

        // Label Address:
        JLabel l7 = new JLabel("Address");
        l7.setBounds(60, 170, 100, 30);

        // Label for Name and Address:
        JLabel l11 = new JLabel();
        l11.setBounds(200, 120, 180, 20);
        p.add(l11);
        
        JLabel l12 = new JLabel();
        l12.setBounds(200, 170, 180, 20);
        p.add(l12);
        
        try{

            // Connection with database:
            Connection c = ConnectionProvider.getConnection();
            Statement s = c.createStatement();

            // Fetch the records from customer table where meter no:
            ResultSet rs = s.executeQuery("select * from customer where meter = '"+c1.getSelectedItem()+"'");

            while(rs.next()){

                // Fetch the name and address from records and adding l11 and l12 label:
                l11.setText(rs.getString("name"));
                l12.setText(rs.getString("address"));

            }

        }catch(Exception e){

            e.printStackTrace();
            StringWriter errors = new StringWriter();
            e.printStackTrace(new PrintWriter(errors));
            LOGGER.info("----CalculateBill:: Exception CalculateBill Constructor----"+e.toString());

        }
        
        c1.addItemListener(new ItemListener(){
            public void itemStateChanged(ItemEvent ae){

                try{

                    // Connection with database:
                    Connection c = ConnectionProvider.getConnection();
                    Statement s = c.createStatement();

                    // Fetch the records from customer table where meter no:
                    ResultSet rs = s.executeQuery("select * from customer where meter = '"+c1.getSelectedItem()+"'");

                    while(rs.next()){

                        // Fetch the name and address from records and adding l11 and l12 label:
                        l11.setText(rs.getString("name"));
                        l12.setText(rs.getString("address"));

                    }

                }catch(Exception e){

                    e.printStackTrace();
                    StringWriter errors = new StringWriter();
                    e.printStackTrace(new PrintWriter(errors));
                    LOGGER.info("----CalculateBill:: Exception CalculateBill Constructor----"+e.toString());

                }

            }
        });

        // Label Units Consumed:
        l3 = new JLabel("Units Consumed");
        l3.setBounds(60, 220, 100, 30);

        // TextField Units Consumed:
        t1 = new JTextField();
        t1.setBounds(200, 220, 180, 20);


        // Label Month:
        l5 = new JLabel("Month");
        l5.setBounds(60, 270, 100, 30);
        
        // Choice Month:
        c2 = new Choice();
        c2.setBounds(200, 270, 180, 20);
        c2.add("January");
        c2.add("February");
        c2.add("March");
        c2.add("April");
        c2.add("May");
        c2.add("June");
        c2.add("July");
        c2.add("August");
        c2.add("September");
        c2.add("October");
        c2.add("November");
        c2.add("December");

        // Create Submit and Cancel Button and also set background and foreground color to it:
        b1 = new JButton("Submit");
        b1.setBounds(100, 350, 100, 25);

        b1.setBackground(Color.BLACK);
        b1.setForeground(Color.WHITE);

        b2 = new JButton("Cancel");
        b2.setBounds(230, 350, 100, 25);

        b2.setBackground(Color.BLACK);
        b2.setForeground(Color.WHITE);

        // Label ImageIcon
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/hicon2.jpg"));
        Image i2 = i1.getImage().getScaledInstance(180, 270,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        l4 = new JLabel(i3);
        
        
        
        l1.setFont(new Font("Senserif",Font.PLAIN,26));
        //Move the label to center
        l1.setHorizontalAlignment(JLabel.CENTER);
        
        // adding all the label to panel:
        p.add(l1);
        p.add(l2);
        p.add(l6);
        p.add(l7);
        p.add(c1);
        p.add(l5);
        p.add(c2);
        p.add(l3);
        p.add(t1);
        p.add(b1);
        p.add(b2);
        
        setLayout(new BorderLayout(30,30));
        
        
        add(p,"Center");
        add(l4,"West");
        
        // adding action listener to Submit and cancel button:
        b1.addActionListener(this);
        b2.addActionListener(this);
        
        getContentPane().setBackground(Color.WHITE);        
        setSize(750,500);
        setLocation(550,220);

    }

    @Override
    public void actionPerformed(ActionEvent ae){

        LOGGER.info("==:CalculateBill:: Inside actionPerformed Method:==");

        // If click on Submit button:
        if(ae.getSource() == b1){

            // Fetch the meter_no, units, month:
            String meter_no = c1.getSelectedItem();
            String units = t1.getText();
            String month = c2.getSelectedItem();

            // String to Integer:
            int units_consumed = Integer.parseInt(units);

            int total_bill = 0;
            try{

                // Connection with database:
                Connection c = ConnectionProvider.getConnection();
                Statement s = c.createStatement();

                // Fetch the all record from tax table:
                ResultSet rs = s.executeQuery("select * from tax");

                while(rs.next()){
                    // Calculate Bill:
                    total_bill = units_consumed * Integer.parseInt(rs.getString("cost_per_unit")); // 120 * 9
                    total_bill += Integer.parseInt(rs.getString("meter_rent"));
                    total_bill += Integer.parseInt(rs.getString("service_charge"));
                    total_bill += Integer.parseInt(rs.getString("service_tax"));
                    total_bill += Integer.parseInt(rs.getString("swachh_bharat_cess"));
                    total_bill += Integer.parseInt(rs.getString("fixed_tax"));

                }

            }catch(Exception e){
                e.printStackTrace();
                StringWriter errors = new StringWriter();
                e.printStackTrace(new PrintWriter(errors));
                LOGGER.info("----CalculateBill::Exception actionPerformed----"+e.toString());
            }

            // after calculating the bill then update into bill table:
            String q = "insert into bill values('"+meter_no+"','"+month+"','"+units+"','"+total_bill+"', 'Not Paid')";

            try{

                // Connection with database:
                Connection c = ConnectionProvider.getConnection();
                Statement s = c.createStatement();
                // Now update into bill table:
                s.executeUpdate(q);

                // after update the bill table, pop-up message is showing "Customer Bill Updated Successfully":
                JOptionPane.showMessageDialog(null,"Customer Bill Updated Successfully");

                // Then simply close the current window:
                this.setVisible(false);

            }catch(Exception aee){

                aee.printStackTrace();
                StringWriter errors = new StringWriter();
                aee.printStackTrace(new PrintWriter(errors));
                LOGGER.info("----CalculateBill:: Exception actionPerformed Method----" + aee.toString());

            }

        // If click on Cancel Button:
        }else if(ae.getSource()== b2){

            // then simply close the current window:
            this.setVisible(false);

        }        
    }

    public static void main(String[] args){
        LOGGER.info("==: CalculateBill:: Inside main Method:==");
        new CalculateBill().setVisible(true);
    }
}

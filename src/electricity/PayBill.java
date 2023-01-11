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

public class PayBill extends JFrame implements ActionListener{

    private static final Logger LOGGER = Logger.getLogger(String.valueOf(PayBill.class));

    JLabel l1,l2,l3,l4,l5, l6;

    JLabel l11, l12, l13, l14, l15;

    JTextField t1;

    Choice c1,c2;

    JButton b1,b2;

    String meter;

    PayBill(String meter){

        LOGGER.info("==:PayBill:: Inside PayBill Constructor:==");

        // set the meter:
        this.meter = meter;
        setLayout(null);
        
        setBounds(550, 220, 900, 600);

        // Label "Electricity Bill"
        JLabel title = new JLabel("Electricity Bill");
        title.setFont(new Font("Tahoma", Font.BOLD, 24));
        title.setBounds(120, 5, 400, 30);
        add(title);

        // Label Meter No
        l1 = new JLabel("Meter No");
        l1.setBounds(35, 80, 200, 20);
        add(l1);

        // Label Meter No Value:
        JLabel l11 = new JLabel();
        l11.setBounds(300, 80, 200, 20);
        add(l11);

        // Label Name:
        JLabel l2 = new JLabel("Name");
        l2.setBounds(35, 140, 200, 20);
        add(l2);

        // Label Name Value:
        JLabel l12 = new JLabel();
        l12.setBounds(300, 140, 200, 20);
        add(l12);

        // Label Month:
        l3 = new JLabel("Month");
        l3.setBounds(35, 200, 200, 20);
        add(l3);

        // choice:
        c1 = new Choice();
        c1.setBounds(300, 200, 200, 20);
        c1.add("January");
        c1.add("February");
        c1.add("March");
        c1.add("April");
        c1.add("May");
        c1.add("June");
        c1.add("July");
        c1.add("August");
        c1.add("September");
        c1.add("October");
        c1.add("November");
        c1.add("December");
        add(c1);
        

        // Label Units:
        l4 = new JLabel("Units");
        l4.setBounds(35, 260, 200, 20);
        add(l4);

        // Label Units Value:
        JLabel l13 = new JLabel();
        l13.setBounds(300, 260, 200, 20);
        add(l13);

        // Label Total Bill:
        l5 = new JLabel("Total Bill");
        l5.setBounds(35, 320, 200, 20);
        add(l5);

        // Label Total Bill Value:
        JLabel l14 = new JLabel();
        l14.setBounds(300, 320, 200, 20);
        add(l14);

        // Label Status:
        l6 = new JLabel("Status");
        l6.setBounds(35, 380, 200, 20);
        add(l6);

        // Label Status Value:
        JLabel l15 = new JLabel();
        l15.setBounds(300, 380, 200, 20);
        l15.setForeground(Color.RED);
        add(l15);
        
        
        
        try{

            // Connection with database:
            Connection c = ConnectionProvider.getConnection();
            Statement s = c.createStatement();

            // Fetch all the records from customer table where meter = ? :
            ResultSet rs = s.executeQuery("select * from customer where meter = '"+meter+"'");

            while(rs.next()){
                // Fetch the meter no and name from resultSet and set into l11 and l12:
                l11.setText(rs.getString("meter"));
                l12.setText(rs.getString("name"));

            }

            // Fetch all the records from bill table where meter = ?:
            rs = s.executeQuery("select * from bill where meter = '"+meter+"' AND month = 'January' ");

            while(rs.next()){

                // Fetch all the information and set into related label:
                l13.setText(rs.getString("units"));
                l14.setText(rs.getString("total_bill"));
                l15.setText(rs.getString("status"));

            }

        }catch(Exception e){

            e.printStackTrace();
            StringWriter errors = new StringWriter();
            e.printStackTrace(new PrintWriter(errors));
            LOGGER.info("----PayBill:: Getting Exception PayBill Constructor----" + e.getMessage());

        }
        
        c1.addItemListener(new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent ae){

                try{

                    // Connection with database:
                    Connection c = ConnectionProvider.getConnection();
                    Statement s = c.createStatement();

                    // Fetch all the records from bill table where meter = ? and month = ?:
                    ResultSet rs = s.executeQuery("select * from bill where meter = '"+meter+"' AND month = '"+c1.getSelectedItem()+"'");

                    while(rs.next()){
                        // Fetch all the information and set label:
                        l13.setText(rs.getString("units"));
                        l14.setText(rs.getString("total_bill"));
                        l15.setText(rs.getString("status"));

                    }

                }catch(Exception e){

                    e.printStackTrace();
                    StringWriter errors = new StringWriter();
                    e.printStackTrace(new PrintWriter(errors));
                    LOGGER.info("----PayBill:: Getting Exception PayBill Constructor----" + e.getMessage());

                }
            }
        });


        // Create Button Pay and Back:
        b1 = new JButton("Pay");
        b1.setBounds(100, 460, 100, 25);
        add(b1);

        b1.setBackground(Color.BLACK);
        b1.setForeground(Color.WHITE);


        b2 = new JButton("Back");
        b2.setBounds(230, 460, 100, 25);
        add(b2);

        b2.setBackground(Color.BLACK);
        b2.setForeground(Color.WHITE);

        // Label Image Icon:
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/bill.png"));
        Image i2 = i1.getImage().getScaledInstance(600, 300,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel l21 = new JLabel(i3);
        l21.setBounds(400, 120, 600, 300);
        add(l21);

        // adding action listener to Pay and Back Button:
        b1.addActionListener(this);
        b2.addActionListener(this);

        // set the contentPane color to white:
        getContentPane().setBackground(Color.WHITE);        
    }

    @Override
    public void actionPerformed(ActionEvent ae){

        LOGGER.info("==: PayBill:: Inside actionPerformed Method:==");

        // If Click on Pay Button:
        if(ae.getSource() == b1){

            try{

                // Connection with database:
                Connection c = ConnectionProvider.getConnection();
                Statement s = c.createStatement();

                // update the bill table status where meter = ? and month = ?:
                s.executeUpdate("update bill set status = 'Paid' where meter = '"+meter+"' AND month = '"+c1.getSelectedItem()+"'");

                // simply close the current window:
                this.setVisible(false);

                // open the Paytm page:
                new Paytm(meter).setVisible(true);


            }catch(Exception e){

                e.printStackTrace();
                StringWriter errors = new StringWriter();
                e.printStackTrace(new PrintWriter(errors));
                LOGGER.info("----PayBill:: Getting Exception actionPerformed Method----" + e.getMessage());

            }


        // If click on Back Button:
        }else if(ae.getSource()== b2){

            // Simply close the current window:
            this.setVisible(false);

        }        
    }

    public static void main(String[] args){
        LOGGER.info("==: PayBill:: Inside main Method:==");
        new PayBill("").setVisible(true);
    }
}

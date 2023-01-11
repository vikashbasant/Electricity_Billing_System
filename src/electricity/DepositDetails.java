package electricity;

import net.proteanit.sql.DbUtils;

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

public class DepositDetails extends JFrame implements ActionListener{

    private static final Logger LOGGER = Logger.getLogger(String.valueOf(DepositDetails.class));

    JTable t1;

    JButton b1, b2;

    JLabel l1, l2;

    Choice c1, c2;

    String[] x = {"Meter Number","Month","Units","Total Bill","Status"};

    String[][] y  = new String[40][8];

    int i=0;

    int j=0;

    DepositDetails(){


        super("Deposit Details");

        LOGGER.info("==:DepositDetails:: Inside DepositDetails Constructor:==");

        // set the size, location:
        setSize(700,750);
        setLocation(600,150);
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);

        // Label Sort by Meter Number:
        l1 = new JLabel("Sort by Meter Number");
        l1.setBounds(20, 20, 150, 20);
        add(l1);

        // Choice Sort by Meter Number:
        c1 = new Choice();

        // Label Sort by Month:
        l2 = new JLabel("Sort By Month");
        l2.setBounds(400, 20, 100, 20);
        add(l2);

        // Choice Sort by Month:
        c2 = new Choice();

        // Create table:
        t1 = new JTable(y,x);
        
        try{

            // Connection With Database:
            Connection c = ConnectionProvider.getConnection();
            Statement s = c.createStatement();

            String s1 = "select * from bill";

            // Fetch the all records from bill table:
            ResultSet rs  = s.executeQuery(s1);

            // set the model to JTable t1:
            t1.setModel(DbUtils.resultSetToTableModel(rs));
            
            String str2 = "select * from customer";

            // Fetch the all records from customer table:
            rs = s.executeQuery(str2);


            while(rs.next()){
                // Fetch all the meter from records, add into choice c1:
                c1.add(rs.getString("meter"));
            }
            
            
        }catch(Exception e){

            e.printStackTrace();
            StringWriter errors = new StringWriter();
            e.printStackTrace(new PrintWriter(errors));
            LOGGER.info("----DepositDetails:: Getting Exception DepositDetails Constructor----" + e.toString());

        }
        // add the choice c1:
        c1.setBounds(180,20, 150, 20);
        add(c1);
        
        // add the choice c2:
        c2.setBounds(520, 20, 150, 20);
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
        add(c2);
        
        // create Search Button and add:
        b1 = new JButton("Search");
        b1.setBounds(20, 70, 80, 20);
        b1.addActionListener(this);
        add(b1);

        // create Print Button and add:
        b2 = new JButton("Print");
        b2.setBounds(120, 70, 80, 20);
        b2.addActionListener(this);
        add(b2);

        // For ScrollPane:
        JScrollPane sp = new JScrollPane(t1);
        sp.setBounds(0, 100, 700, 650);
        add(sp);
        
    }
    public void actionPerformed(ActionEvent ae){

        LOGGER.info("==: DepositDetails:: Inside actionPerformed Method :==");

        // If click the Button Search:
        if(ae.getSource() == b1){

            String str = "select * from bill where meter = '"+c1.getSelectedItem()+"' AND month = '"+c2.getSelectedItem()+"'";

            try{

                // Connection with database:
                Connection c = ConnectionProvider.getConnection();
                Statement s = c.createStatement();

                // Fetch the all records from bill where meter = c1.getSelectedItem();
                ResultSet rs = s.executeQuery(str);

                // added into t1 table:
                t1.setModel(DbUtils.resultSetToTableModel(rs));

            }catch(Exception e){

                e.printStackTrace();
                StringWriter errors = new StringWriter();
                e.printStackTrace(new PrintWriter(errors));
                LOGGER.info("----DepositDetails:: Getting Exception actionPerformed Method----" + e.toString());

            }

        // If click on Button Print:
        }else if(ae.getSource() == b2){

            try{

                t1.print();

            }catch(Exception e){

                e.printStackTrace();
                StringWriter errors = new StringWriter();
                e.printStackTrace(new PrintWriter(errors));
                LOGGER.info("----DepositDetails:: Getting Exception actionPerformed Method----" + e.toString());

            }
        }
    }
    
    public static void main(String[] args){
        LOGGER.info("==: DepositDetails:: Inside main Method :==");
        new DepositDetails().setVisible(true);
    }
    
}

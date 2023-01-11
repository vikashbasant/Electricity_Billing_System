
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

public class NewCustomer extends JFrame implements ActionListener{

    private static final Logger LOGGER = Logger.getLogger(String.valueOf(NewCustomer.class));

    JLabel l1,l2,l3,l4,l5,l6,l7,l8, l11;
    JTextField t1,t2,t3,t4,t5,t6,t7;
    JButton b1,b2;


    NewCustomer(){

        LOGGER.info("==: NewCustomer:: Inside NewCustomer Constructor :=");

        // set the location and size:
        setLocation(600,200);
        setSize(700,500);

        // panel color:
        JPanel p = new JPanel();
        p.setLayout(null);
        p.setBackground(Color.WHITE);
        p.setBackground(new Color(173,216,230));

        // Title:
        JLabel title = new JLabel("New Customer");
        title.setBounds(180, 10, 200, 26);
        title.setFont(new Font("Tahoma", Font.PLAIN, 24));
        p.add(title);

        // Label Customer Name:
        l1 = new JLabel("Customer Name");
        l1.setBounds(100, 80, 100, 20);

        // TextField for Customer Name:
        t1 = new JTextField();
        t1.setBounds(240, 80, 200, 20);

        // Adding label and textfield into panel:
        p.add(l1);
        p.add(t1);


        // Label Meter No:
        l2 = new JLabel("Meter No");
        l2.setBounds(100, 120, 100, 20);

        // Label For Meter No value:
        l11 = new JLabel();
        l11.setBounds(240, 120, 200, 20);

        // Generating Randon Meter No. of 6 Digits:
        Random ran = new Random();
        long first = (ran.nextLong() % 1000000);
        l11.setText(""+Math.abs(first));

        // Adding label into panel
        p.add(l2);
        p.add(l11);


        // Label Address:
        l3 = new JLabel("Address");
        l3.setBounds(100, 160, 100, 20);

        // TextField Address:
        t3 = new JTextField();
        t3.setBounds(240, 160, 200, 20);

        // Adding label and textfield into panel:
        p.add(l3);
        p.add(t3);


        // Label City:
        l5 = new JLabel("City");
        l5.setBounds(100, 200, 100, 20);

        // TextField City:
        t5 = new JTextField();
        t5.setBounds(240, 200, 200, 20);

        // Adding label and textfield into panel:
        p.add(l5);
        p.add(t5);

        // Label State:
        l4 = new JLabel("State");
        l4.setBounds(100, 240, 100, 20);

        // TextField State:
        t4 = new JTextField();
        t4.setBounds(240, 240, 200, 20);

        // Adding label and textfield into panel:
        p.add(l4);
        p.add(t4);

        // Label Email:
        l6 = new JLabel("Email");
        l6.setBounds(100, 280, 100, 20);

        // TextField Email:
        t6 = new JTextField();
        t6.setBounds(240, 280, 200, 20);

        // Adding label and textfield into panel:
        p.add(l6);
        p.add(t6);

        // Label Phone:
        l7 = new JLabel("Phone Number");
        l7.setBounds(100, 320, 100, 20);

        // TextField Phone:
        t7 = new JTextField();
        t7.setBounds(240, 320, 200, 20);

        // Adding label and textfield into panel:
        p.add(l7);
        p.add(t7);

        // Creating Next and Cancel Button and also set the background and forground color to it:
        b1 = new JButton("Next");
        b1.setBounds(120, 390, 100, 25);

        b1.setBackground(Color.BLACK);
        b1.setForeground(Color.WHITE);

        b2 = new JButton("Cancel");
        b2.setBounds(250, 390, 100, 25);

        b2.setBackground(Color.BLACK);
        b2.setForeground(Color.WHITE);

        // Adding Next and Cancel Button to panel:
        p.add(b1);
        p.add(b2);
        setLayout(new BorderLayout());

        // adjust the panel to center:
        add(p,"Center");

        // New Customer Image:
        ImageIcon ic1 = new ImageIcon(ClassLoader.getSystemResource("icon/hicon1.jpg"));
        Image i3 = ic1.getImage().getScaledInstance(150, 300,Image.SCALE_DEFAULT);
        ImageIcon ic2 = new ImageIcon(i3);
        l8 = new JLabel(ic2);
        
        // New Customer adding into West side:
        add(l8,"West");
        //for changing the color of the whole Frame
        getContentPane().setBackground(Color.WHITE);

        // adding action listener to Next and Cancel Button:
        b1.addActionListener(this);
        b2.addActionListener(this);
        

    }
    public void actionPerformed(ActionEvent ae){

        LOGGER.info("==: NewCustomer:: Inside actionPerformed Method :==");

        // If click on Button Next:
        if(ae.getSource() == b1){

            // Fetch the all value from related textfiled put it into query q1 and q2:
            String name = t1.getText();
            String meter = l11.getText();
            String address = t3.getText();
            String state = t4.getText();
            String city = t5.getText();
            String email = t6.getText();
            String phone = t7.getText();

            String q1 = "insert into customer values('"+name+"','"+meter+"','"+address+"','"+city+"','"+state+"','"+email+"','"+phone+"')";
            String q2 = "insert into login values('"+meter+"', '', '', '', '')";

            try{
                // Connection with Database:
                Connection c1 = ConnectionProvider.getConnection();
                Statement s = c1.createStatement();

                // update information into both customer and login table:
                s.executeUpdate(q1);
                s.executeUpdate(q2);

                // after the both the table just show the pop-up message
                // Customer Details Added Successfully
                JOptionPane.showMessageDialog(null,"Customer Details Added Successfully");

                // Then simply close the current window and open MeterInfo page:
                this.setVisible(false);
                new MeterInfo(meter).setVisible(true);

            }catch(Exception ex){
                 ex.printStackTrace();
                 StringWriter errors = new StringWriter();
                ex.printStackTrace(new PrintWriter(errors));
                LOGGER.info("----NewCustomer:: Getting actionPerformed----" + ex.toString());
            }

        // If click on Cancel Button:
        }else if(ae.getSource() ==b2){
            // then simply close the current windows:
            this.setVisible(false);
        }
    }
    
    
    public static void main(String[] args){

        LOGGER.info("==: NewCustomer:: Inside main Method:==");
        new NewCustomer().setVisible(true);
    }
}

package electricity;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class CustomerDetails extends JFrame implements ActionListener{

    JTable t1;

    JButton b1;

    String[] x = {"Customer Name","Meter Number","Address","City","State","Email","Phone"};

    String[][] y = new String[40][8];

    int i=0;
    int j=0;

    CustomerDetails(){

        super("Customer Details");

        setSize(1200,650);
        setLocation(400,150);
        
        try{
            Connection c1 = ConnectionProvider.getConnection();
            Statement s = c1.createStatement();

            String s1 = "select * from customer";
            ResultSet rs  = s.executeQuery(s1);

            while(rs.next()){

                y[i][j++]=rs.getString("name");
                y[i][j++]=rs.getString("meter");
                y[i][j++]=rs.getString("address");
                y[i][j++]=rs.getString("city");
                y[i][j++]=rs.getString("state");
                y[i][j++]=rs.getString("email");
                y[i][j++]=rs.getString("phone");
                i++;
                j=0;

            }
            t1 = new JTable(y,x);
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        
        b1 = new JButton("Print");
        add(b1,"South");
        JScrollPane sp = new JScrollPane(t1);
        add(sp);
        b1.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent ae){
        try{
            t1.print();
        }catch(Exception ignored){}
    }
    
    public static void main(String[] args){
        new CustomerDetails().setVisible(true);
    }
    
}

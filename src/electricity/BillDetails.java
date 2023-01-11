package electricity;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Logger;

public class BillDetails extends JFrame{

    private static final Logger LOGGER = Logger.getLogger(String.valueOf(BillDetails.class));

    JTable t1;

    String[] x = {"Meter Number","Month","Units","Total Bill","Status"};

    String[][] y = new String[40][8];

    int i=0;

    int j=0;

    BillDetails(String meter){

        super("Bill Details");

        LOGGER.info("==: BillDetails:: Inside BillDetails Constructor:==");

        // set the size and location:
        setSize(700,650);
        setLocation(600,150);
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);

        // create a table:
        t1 = new JTable(y,x);
        
        try{

            // Connection with database:
            Connection c = ConnectionProvider.getConnection();
            Statement s = c.createStatement();

            // Fetch the all records from bill table where meter = ?:
            String s1 = "select * from bill where meter = " + meter;
            ResultSet rs  = s.executeQuery(s1);

            // set All the records into model:
            t1.setModel(DbUtils.resultSetToTableModel(rs));
            
        }catch(Exception e){

            e.printStackTrace();
            StringWriter errors = new StringWriter();
            e.printStackTrace(new PrintWriter(errors));
            LOGGER.info("==: BillDetails:: Getting Exception BillDetails Constructor:==");

        }
        
        // set the scroll pane:
        JScrollPane sp = new JScrollPane(t1);
        sp.setBounds(0, 0, 700, 650);
        add(sp);
        
    }
    
    public static void main(String[] args){

        LOGGER.info("==: BillDetails:: Inside main Method:==");
        new BillDetails("").setVisible(true);
    }
    
}

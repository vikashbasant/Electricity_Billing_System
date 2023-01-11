package electricity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.logging.Logger;

public class Project extends JFrame implements ActionListener{

    private static final Logger LOGGER = Logger.getLogger(String.valueOf(Project.class));

    String meter;

    // Parameterized Constructor:
    Project(String meter, String person){

        /*--------------------------------Header-----------------------------------------------*/
        super("Electricity Billing System");

        LOGGER.info("==: Project:: Inside Project Constructor :==");

        /*----Set the meter_no to meter variable----*/
        this.meter = meter;
        
        setSize(1920,1030);
        
        /* Adding background image */
        ImageIcon ic =  new ImageIcon(ClassLoader.getSystemResource("icon/elect3.jpg"));
        Image i3 = ic.getImage().getScaledInstance(1900, 950,Image.SCALE_DEFAULT);
        ImageIcon icc3 = new ImageIcon(i3);
        JLabel l1 = new JLabel(icc3);
        add(l1);


        /*----------------------------------First Column--------------------------------------------*/
        /*----Inside Master Menu 4 choice i.e (New Customer, Customer Details, Deposit Details, Calculate Bill)----*/
        JMenuBar mb  = new JMenuBar();
        JMenu master = new JMenu("Master");
        JMenuItem m1 = new JMenuItem("New Customer");
        JMenuItem m2 = new JMenuItem("Customer Details");
        JMenuItem m3 = new JMenuItem("Deposit Details");
        JMenuItem m4 = new JMenuItem("Calculate Bill");
        master.setForeground(Color.BLUE);
        
        
        /* ---- Customer Details ---- */
        m1.setFont(new Font("monospaced",Font.PLAIN,12));
        ImageIcon icon1 = new ImageIcon(ClassLoader.getSystemResource("icon/icon1.png"));
        Image image1 = icon1.getImage().getScaledInstance(20, 20,Image.SCALE_DEFAULT);
        m1.setIcon(new ImageIcon(image1));
        m1.setMnemonic('D');
        m1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.CTRL_MASK));
        m1.setBackground(Color.WHITE);
        
        /* ---- Meter Details ---- */
        m2.setFont(new Font("monospaced",Font.PLAIN,12));
        ImageIcon icon2 = new ImageIcon(ClassLoader.getSystemResource("icon/icon2.png"));
        Image image2 = icon2.getImage().getScaledInstance(20, 20,Image.SCALE_DEFAULT);
        m2.setIcon(new ImageIcon(image2));
        m2.setMnemonic('M');
        m2.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M, ActionEvent.CTRL_MASK));
        m2.setBackground(Color.WHITE);
        

        /* ---- Deposit Details  ----- */
        m3.setFont(new Font("monospaced",Font.PLAIN,12));
        ImageIcon icon3 = new ImageIcon(ClassLoader.getSystemResource("icon/icon3.png"));
        Image image3 = icon3.getImage().getScaledInstance(20, 20,Image.SCALE_DEFAULT);
        m3.setIcon(new ImageIcon(image3));
        m3.setMnemonic('N');
        m3.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        m3.setBackground(Color.WHITE);


        /*------- Calculate Bill -------*/
        m4.setFont(new Font("monospaced",Font.PLAIN,12));
        ImageIcon icon5 = new ImageIcon(ClassLoader.getSystemResource("icon/icon5.png"));
        Image image5 = icon5.getImage().getScaledInstance(20, 20,Image.SCALE_DEFAULT);
        m4.setIcon(new ImageIcon(image5));
        m4.setMnemonic('B');
        m4.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B, ActionEvent.CTRL_MASK));
        m4.setBackground(Color.WHITE);
        

        /*---- Adding action listener for all the menu item i.e m1:New Customer, m2:Customer Details, m3:Deposit
        Details, m4:Calculate Bill----*/
        m1.addActionListener(this);
        m2.addActionListener(this);
        m3.addActionListener(this);
        m4.addActionListener(this);

        
        /*-------------------------------- Second Column ------------------------------------------*/
        /*----Inside Information Menu there is total 2 choice i.e(Update Information, View Information)----*/
        JMenu info = new JMenu("Information");
        JMenuItem info1 = new JMenuItem("Update Information");
        JMenuItem info2 = new JMenuItem("View Information");
        
        info.setForeground(Color.RED);
        
        /* ---- Update Information ---- */
        info1.setFont(new Font("monospaced",Font.PLAIN,12));
        ImageIcon icon41 = new ImageIcon(ClassLoader.getSystemResource("icon/icon4.png"));
        Image image41 = icon41.getImage().getScaledInstance(20, 20,Image.SCALE_DEFAULT);
        info1.setIcon(new ImageIcon(image41));
        info1.setMnemonic('P');
        info1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));
        info1.setBackground(Color.WHITE);
        
        /* ---- View Information ----*/
        info2.setFont(new Font("monospaced",Font.PLAIN,12));
        ImageIcon icon42 = new ImageIcon(ClassLoader.getSystemResource("icon/icon6.png"));
        Image image42 = icon42.getImage().getScaledInstance(20, 20,Image.SCALE_DEFAULT);
        info2.setIcon(new ImageIcon(image42));
        info2.setMnemonic('L');
        info2.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, ActionEvent.CTRL_MASK));
        info2.setBackground(Color.WHITE);

        /*---- Adding action listener for all the menu item i.e info1:Update Information, info2:View Information----*/
        info1.addActionListener(this);
        info2.addActionListener(this);

        
        
        /*-----------------------------------Second Column----------------------------------------------------*/
        /*----Inside User Menu there is total 2 choice i.e(Pay Bill, Bill Details)----*/
        JMenu user = new JMenu("User");
        JMenuItem u1 = new JMenuItem("Pay Bill");
        JMenuItem u3 = new JMenuItem("Bill Details");
        user.setForeground(Color.RED);
        
        /* ---- Pay Bill ---- */
        u1.setFont(new Font("monospaced",Font.PLAIN,12));
        ImageIcon icon4 = new ImageIcon(ClassLoader.getSystemResource("icon/icon4.png"));
        Image image4 = icon4.getImage().getScaledInstance(20, 20,Image.SCALE_DEFAULT);
        u1.setIcon(new ImageIcon(image4));
        u1.setMnemonic('P');
        u1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));
        u1.setBackground(Color.WHITE);
        
        /* ---- Last Bill ----*/
        u3.setFont(new Font("monospaced",Font.PLAIN,12));
        ImageIcon icon6 = new ImageIcon(ClassLoader.getSystemResource("icon/icon6.png"));
        Image image6 = icon6.getImage().getScaledInstance(20, 20,Image.SCALE_DEFAULT);
        u3.setIcon(new ImageIcon(image6));
        u3.setMnemonic('L');
        u3.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, ActionEvent.CTRL_MASK));
        u3.setBackground(Color.WHITE);

        /*---- Adding action listener for all the menu item i.e u1:Pay Bill, u3:Bill Details----*/
        u1.addActionListener(this);
        u3.addActionListener(this);
        
        

        /*-------------------------------------Third Column-------------------------------------------------*/
        /*----Inside Report Menu there is total 1 choice i.e(Generate Bill)----*/
        JMenu report = new JMenu("Report");
        JMenuItem r1 = new JMenuItem("Generate Bill");
        report.setForeground(Color.BLUE);
        
        /* ---- Generate Bill ---- */
        r1.setFont(new Font("monospaced",Font.PLAIN,12));
        ImageIcon icon7 = new ImageIcon(ClassLoader.getSystemResource("icon/icon7.png"));
        Image image7 = icon7.getImage().getScaledInstance(20, 20,Image.SCALE_DEFAULT);
        r1.setIcon(new ImageIcon(image7));
        r1.setMnemonic('R');
        r1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.CTRL_MASK));
        r1.setBackground(Color.WHITE);

        /*---- Adding action listener for all the menu item i.e r1:Generate Bill----*/
        r1.addActionListener(this);
        
        /*-------------------------------------Fourth Column---------------------------------------------------------*/
        /*----Inside Utility Menu there is total 3 choice i.e(Notepad, Calculator, Web Browser)----*/
        JMenu utility = new JMenu("Utility");
        JMenuItem ut1 = new JMenuItem("Notepad");
        JMenuItem ut2 = new JMenuItem("Calculator");
        JMenuItem ut3 = new JMenuItem("Web Browser");
        utility.setForeground(Color.RED); 
        
        /* ---- Notepad ---- */
        ut1.setFont(new Font("monospaced",Font.PLAIN,12));
        ImageIcon icon8 = new ImageIcon(ClassLoader.getSystemResource("icon/icon12.png"));
        Image image8 = icon8.getImage().getScaledInstance(20, 20,Image.SCALE_DEFAULT);
        ut1.setIcon(new ImageIcon(image8));
        ut1.setMnemonic('C');
        ut1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
        ut1.setBackground(Color.WHITE);
        
        /* ---- Calculator ---- */
        ut2.setFont(new Font("monospaced",Font.PLAIN,12));
        ImageIcon icon9 = new ImageIcon(ClassLoader.getSystemResource("icon/icon9.png"));
        Image image9 = icon9.getImage().getScaledInstance(20, 20,Image.SCALE_DEFAULT);
        ut2.setIcon(new ImageIcon(image9));
        ut2.setMnemonic('X');
        ut2.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
        ut2.setBackground(Color.WHITE);
        
        /* ---- Web Browser ---- */
        ut3.setFont(new Font("monospaced",Font.PLAIN,12));
        ImageIcon icon10 = new ImageIcon(ClassLoader.getSystemResource("icon/icon10.png"));
        Image image10 = icon10.getImage().getScaledInstance(20, 20,Image.SCALE_DEFAULT);
        ut3.setIcon(new ImageIcon(image10));
        ut3.setMnemonic('W');
        ut3.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, ActionEvent.CTRL_MASK));
        ut3.setBackground(Color.WHITE);

        /*---- Adding action listener for all the menu item i.e ut1:NotePad, ut2:Calculator, ut3: Web Browser----*/
        ut1.addActionListener(this);
        ut2.addActionListener(this);
        ut3.addActionListener(this);
        
        /*---------------------------------------Fifth Column----------------------------------------------*/
        /*----Inside Logout Menu there is total 1 choice i.e(Logout)----*/
        JMenu exit = new JMenu("Logout");
        JMenuItem ex = new JMenuItem("Logout");
        exit.setForeground(Color.BLUE);
        
        /* ---- Logout ---- */
        ex.setFont(new Font("monospaced",Font.PLAIN,12));
        ImageIcon icon11 = new ImageIcon(ClassLoader.getSystemResource("icon/icon11.png"));
        Image image11 = icon11.getImage().getScaledInstance(20, 20,Image.SCALE_DEFAULT);
        ex.setIcon(new ImageIcon(image11));
        ex.setMnemonic('Z');
        ex.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, ActionEvent.CTRL_MASK));
        ex.setBackground(Color.WHITE);

        /*---- Adding action listener for all the menu item i.e ex:Logout----*/
        ex.addActionListener(this);
        
        
        /*---------------Main Logic for which person which cloumns is show------------------*/

        /*-----Inside Master Menu adding m1:New Customer, m2:Customer Details, m3:Deposit Details, m4:Calculate
        Bill----*/
        master.add(m1);
        master.add(m2);
        master.add(m3);
        master.add(m4);

        /*----Inside Information Menu adding info1:Update information, info2:View Information----*/
        info.add(info1);
        info.add(info2);

        /*----Inside User Menu adding u1:Pay Bill, u3: Bill Details----*/
        user.add(u1);
        user.add(u3);

        /*----Inside Report Menu adding r1: Generate Bill----*/
        report.add(r1);

        /*----Inside Utility Menu adding ut1:Notepad, ut2:Calculator, ut3:Web Browser----*/
        utility.add(ut1);
        utility.add(ut2);
        utility.add(ut3);

        /*----Inside Logout Menu adding ex:Logout----*/
        exit.add(ex);


        /**
         *
         * If the person is admin:
         *      Then 3 Columns are shows i.e Master, Utility, Logout
         *
         * If the person is customer:
         *      Then 5 Columns are shows i.e Information, User, Report, Utility and Logout
         *
         */
        if(person.equals("Admin")){
            mb.add(master);
        }else{             
            mb.add(info);
            mb.add(user);
            mb.add(report);
        }
        mb.add(utility);
        mb.add(exit);
        
        setJMenuBar(mb);    
        
        setFont(new Font("Senserif",Font.BOLD,16));
        setLayout(new FlowLayout());
        setVisible(false);
    }

    @Override
    public void actionPerformed(ActionEvent ae){

        LOGGER.info("==: Project:: Inside actionPerformed Method :==");

        String msg = ae.getActionCommand();
        LOGGER.info("msg = " + msg);

        // If msg is "Customer Details" then Customer Details page is open:
        if(msg.equals("Customer Details")){

            new CustomerDetails().setVisible(true);

        // If msg is "New Customer" then New Customer page is open:
        }else if(msg.equals("New Customer")){

            new NewCustomer().setVisible(true);

        // If msg is "Calculate Bill" then Calculate Bill page is open:
        }else if(msg.equals("Calculate Bill")){

            new CalculateBill().setVisible(true);

        // If msg is "Pay Bill" then Pay Bill page is open:
        }else if(msg.equals("Pay Bill")){

            new PayBill(meter).setVisible(true);

        // If msg is "Notepad" then Notepad is open:
        }else if(msg.equals("Notepad")){

            try{

                Runtime.getRuntime().exec("notepad.exe");

            }catch(Exception e){ }

        // If msg is "Calculator" then Calculator is open:
        }else if(msg.equals("Calculator")){

            try{
                Runtime.getRuntime().exec("calc.exe");

            }catch(Exception e){ }

        // If msg is "Web Browser" then Web Browser is open:
        }else if(msg.equals("Web Browser")){

            try{
                Runtime.getRuntime().exec("C:\\Program Files (x86)\\Microsoft\\Edge\\Application\\msedge.exe");

            }catch(Exception e){ }

        // If msg is "Logout" then simply close the window and go back to login page:
        }else if(msg.equals("Logout")){

            this.setVisible(false);
            new Login().setVisible(true);

        // If msg is "Generate Bill" then Generate Bill Page is open:
        }else if(msg.equals("Generate Bill")){

            new GenerateBill(meter).setVisible(true);

        // If msg is "Deposit Details" then Deposit Details Page is open:
        }else if(msg.equals("Deposit Details")){

            new DepositDetails().setVisible(true);

        // If msg is "View Information" then View Information page is open:
        }else if(msg.equals("View Information")){

            new ViewInformation(meter).setVisible(true);

        // If msg is "Update Information" then Update Information page is open:
        }else if(msg.equals("Update Information")){

            new UpdateInformation(meter).setVisible(true);

        // If msg is "Bill Details" then Bill Details page is open:
        }else if(msg.equals("Bill Details")){

            new BillDetails(meter).setVisible(true);

        }
        
        
    }
    
    
    public static void main(String[] args){

        new Project("", "").setVisible(true);
    }
    
}

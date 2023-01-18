package electricity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.Logger;

public class Project extends JFrame implements ActionListener {

    private static final Logger LOGGER = Logger.getLogger(String.valueOf(Project.class));

    String meter;

    // Parameterized Constructor:
    Project (String meter, String person) {

        //============================================================================================================//
        /*--------------------------------Header-----------------------------------------------*/
        super("Electricity Billing System");


        //============================================================================================================//

        LOGGER.info("==: Project:: Inside Project Constructor :==");

        //============================================================================================================//

        /*----Set the meter_no to meter variable----*/
        this.meter = meter;

        //============================================================================================================//

        /*---- Create Frame----*/
        // Here Both method are created the frame:
        setSize(1920, 1030);
        //setExtendedState(JFrame.MAXIMIZED_BOTH);

        /* Adding background image */
        ImageIcon ic = new ImageIcon(ClassLoader.getSystemResource("icon/elect3.jpg"));
        Image i3 = ic.getImage().getScaledInstance(1900, 950, Image.SCALE_DEFAULT);
        ImageIcon icc3 = new ImageIcon(i3);
        JLabel l1 = new JLabel(icc3);
        add(l1);


        //============================================================================================================//
        //==================================================For Menu Bar:=============================================//

        /*----------------------------------First Column--------------------------------------------*/
        /*----Inside Master Menu 4 choice i.e (New Customer, Customer Details, Deposit Details, Calculate Bill)----*/
        /*----For Menu Bar----*/
        JMenuBar mb = new JMenuBar();

        /*----For Menu----*/
        JMenu master = new JMenu("Master");
        master.setForeground(Color.BLUE);

        /*----For Menu Item----*/
        JMenuItem mNewCustomer = new JMenuItem("New Customer");
        JMenuItem mCustomerDetails = new JMenuItem("Customer Details");
        JMenuItem mDepositDetails = new JMenuItem("Deposit Details");
        JMenuItem mCalculateBill = new JMenuItem("Calculate Bill");



        /* ----New Customer ---- */
        mNewCustomer.setFont(new Font("monospaced", Font.BOLD, 12));
        ImageIcon icon1 = new ImageIcon(ClassLoader.getSystemResource("icon/icon1.png"));
        Image image1 = icon1.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        mNewCustomer.setIcon(new ImageIcon(image1));
        mNewCustomer.setMnemonic('D');
        mNewCustomer.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.CTRL_MASK));
        mNewCustomer.setBackground(Color.WHITE);

        /* ---- Customer Details ---- */
        mCustomerDetails.setFont(new Font("monospaced", Font.BOLD, 12));
        ImageIcon icon2 = new ImageIcon(ClassLoader.getSystemResource("icon/icon2.png"));
        Image image2 = icon2.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        mCustomerDetails.setIcon(new ImageIcon(image2));
        mCustomerDetails.setMnemonic('M');
        mCustomerDetails.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M, ActionEvent.CTRL_MASK));
        mCustomerDetails.setBackground(Color.WHITE);


        /* ---- Deposit Details  ----- */
        mDepositDetails.setFont(new Font("monospaced", Font.BOLD, 12));
        ImageIcon icon3 = new ImageIcon(ClassLoader.getSystemResource("icon/icon3.png"));
        Image image3 = icon3.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        mDepositDetails.setIcon(new ImageIcon(image3));
        mDepositDetails.setMnemonic('N');
        mDepositDetails.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        mDepositDetails.setBackground(Color.WHITE);


        /*------- Calculate Bill -------*/
        mCalculateBill.setFont(new Font("monospaced", Font.BOLD, 12));
        ImageIcon icon5 = new ImageIcon(ClassLoader.getSystemResource("icon/icon5.png"));
        Image image5 = icon5.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        mCalculateBill.setIcon(new ImageIcon(image5));
        mCalculateBill.setMnemonic('B');
        mCalculateBill.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B, ActionEvent.CTRL_MASK));
        mCalculateBill.setBackground(Color.WHITE);
        

        /*---- Adding action listener for all the menu item i.e mNewCustomer:New Customer, mCustomerDetails:Customer Details, mDepositDetails:Deposit
        Details, mCalculateBill:Calculate Bill----*/
        mNewCustomer.addActionListener(this);
        mCustomerDetails.addActionListener(this);
        mDepositDetails.addActionListener(this);
        mCalculateBill.addActionListener(this);


        //============================================================================================================//


        /*-------------------------------- Second Column ------------------------------------------*/
        /*----Inside Information Menu there is total 2 choice i.e(Update Information, View Information)----*/
        JMenu info = new JMenu("Information");
        JMenuItem updateInfo = new JMenuItem("Update Information");
        JMenuItem viewInfo = new JMenuItem("View Information");

        info.setForeground(Color.RED);

        /* ---- Update Information ---- */
        updateInfo.setFont(new Font("monospaced", Font.BOLD, 12));
        ImageIcon icon41 = new ImageIcon(ClassLoader.getSystemResource("icon/icon4.png"));
        Image image41 = icon41.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        updateInfo.setIcon(new ImageIcon(image41));
        updateInfo.setMnemonic('P');
        updateInfo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));
        updateInfo.setBackground(Color.WHITE);

        /* ---- View Information ----*/
        viewInfo.setFont(new Font("monospaced", Font.BOLD, 12));
        ImageIcon icon42 = new ImageIcon(ClassLoader.getSystemResource("icon/icon6.png"));
        Image image42 = icon42.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        viewInfo.setIcon(new ImageIcon(image42));
        viewInfo.setMnemonic('L');
        viewInfo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, ActionEvent.CTRL_MASK));
        viewInfo.setBackground(Color.WHITE);

        /*---- Adding action listener for all the menu item i.e updateInfo:Update Information, viewInfo:View Information----*/
        updateInfo.addActionListener(this);
        viewInfo.addActionListener(this);


        //============================================================================================================//



        /*-----------------------------------Second Column----------------------------------------------------*/
        /*----Inside User Menu there is total 2 choice i.e(Pay Bill, Bill Details)----*/
        JMenu user = new JMenu("User");
        JMenuItem uPayBill = new JMenuItem("Pay Bill");
        JMenuItem uBillDetails = new JMenuItem("Bill Details");
        user.setForeground(Color.RED);

        /* ---- Pay Bill ---- */
        uPayBill.setFont(new Font("monospaced", Font.BOLD, 12));
        ImageIcon icon4 = new ImageIcon(ClassLoader.getSystemResource("icon/icon4.png"));
        Image image4 = icon4.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        uPayBill.setIcon(new ImageIcon(image4));
        uPayBill.setMnemonic('P');
        uPayBill.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));
        uPayBill.setBackground(Color.WHITE);

        /* ---- Last Bill ----*/
        uBillDetails.setFont(new Font("monospaced", Font.BOLD, 12));
        ImageIcon icon6 = new ImageIcon(ClassLoader.getSystemResource("icon/icon6.png"));
        Image image6 = icon6.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        uBillDetails.setIcon(new ImageIcon(image6));
        uBillDetails.setMnemonic('L');
        uBillDetails.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, ActionEvent.CTRL_MASK));
        uBillDetails.setBackground(Color.WHITE);

        /*---- Adding action listener for all the menu item i.e uPayBill:Pay Bill, uBillDetails:Bill Details----*/
        uPayBill.addActionListener(this);
        uBillDetails.addActionListener(this);


        //============================================================================================================//


        /*-------------------------------------Third Column-------------------------------------------------*/
        /*----Inside Report Menu there is total 1 choice i.e(Generate Bill)----*/
        JMenu report = new JMenu("Report");
        JMenuItem rGenerateBill = new JMenuItem("Generate Bill");
        report.setForeground(Color.BLUE);

        /* ---- Generate Bill ---- */
        rGenerateBill.setFont(new Font("monospaced", Font.BOLD, 12));
        ImageIcon icon7 = new ImageIcon(ClassLoader.getSystemResource("icon/icon7.png"));
        Image image7 = icon7.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        rGenerateBill.setIcon(new ImageIcon(image7));
        rGenerateBill.setMnemonic('R');
        rGenerateBill.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.CTRL_MASK));
        rGenerateBill.setBackground(Color.WHITE);

        /*---- Adding action listener for all the menu item i.e rGenerateBill:Generate Bill----*/
        rGenerateBill.addActionListener(this);


        //============================================================================================================//


        /*-------------------------------------Fourth Column---------------------------------------------------------*/
        /*----Inside Utility Menu there is total 3 choice i.e(Notepad, Calculator, Web Browser)----*/
        JMenu utility = new JMenu("Utility");
        JMenuItem uNotepad = new JMenuItem("Notepad");
        JMenuItem uCalculator = new JMenuItem("Calculator");
        JMenuItem uWebBrowser = new JMenuItem("Web Browser");
        utility.setForeground(Color.RED);

        /* ---- Notepad ---- */
        uNotepad.setFont(new Font("monospaced", Font.BOLD, 12));
        ImageIcon icon8 = new ImageIcon(ClassLoader.getSystemResource("icon/icon12.png"));
        Image image8 = icon8.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        uNotepad.setIcon(new ImageIcon(image8));
        uNotepad.setMnemonic('C');
        uNotepad.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
        uNotepad.setBackground(Color.WHITE);

        /* ---- Calculator ---- */
        uCalculator.setFont(new Font("monospaced", Font.BOLD, 12));
        ImageIcon icon9 = new ImageIcon(ClassLoader.getSystemResource("icon/icon9.png"));
        Image image9 = icon9.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        uCalculator.setIcon(new ImageIcon(image9));
        uCalculator.setMnemonic('X');
        uCalculator.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
        uCalculator.setBackground(Color.WHITE);

        /* ---- Web Browser ---- */
        uWebBrowser.setFont(new Font("monospaced", Font.BOLD, 12));
        ImageIcon icon10 = new ImageIcon(ClassLoader.getSystemResource("icon/icon10.png"));
        Image image10 = icon10.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        uWebBrowser.setIcon(new ImageIcon(image10));
        uWebBrowser.setMnemonic('W');
        uWebBrowser.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, ActionEvent.CTRL_MASK));
        uWebBrowser.setBackground(Color.WHITE);

        /*---- Adding action listener for all the menu item i.e uNotepad:NotePad, uCalculator:Calculator, uWebBrowser: Web Browser----*/
        uNotepad.addActionListener(this);
        uCalculator.addActionListener(this);
        uWebBrowser.addActionListener(this);

        //============================================================================================================//


        /*---------------------------------------Fifth Column----------------------------------------------*/
        /*----Inside Logout Menu there is total 1 choice i.e(Logout)----*/
        JMenu exit = new JMenu("Logout");
        JMenuItem ex = new JMenuItem("Logout");
        exit.setForeground(Color.BLUE);

        /* ---- Logout ---- */
        ex.setFont(new Font("monospaced", Font.BOLD, 12));
        ImageIcon icon11 = new ImageIcon(ClassLoader.getSystemResource("icon/icon11.png"));
        Image image11 = icon11.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        ex.setIcon(new ImageIcon(image11));
        ex.setMnemonic('Z');
        ex.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, ActionEvent.CTRL_MASK));
        ex.setBackground(Color.WHITE);

        /*---- Adding action listener for all the menu item i.e ex:Logout----*/
        ex.addActionListener(this);


        //============================================================================================================//


        /*---------------Main Logic for which person which columns is show------------------*/

        /*-----Inside Master Menu adding mNewCustomer:New Customer, mCustomerDetails:Customer Details, mDepositDetails:Deposit Details, mCalculateBill:Calculate
        Bill----*/
        master.add(mNewCustomer);
        master.add(mCustomerDetails);
        master.add(mDepositDetails);
        master.add(mCalculateBill);

        /*----Inside Information Menu adding updateInfo:Update information, viewInfo:View Information----*/
        info.add(updateInfo);
        info.add(viewInfo);

        /*----Inside User Menu adding uPayBill:Pay Bill, uBillDetails: Bill Details----*/
        user.add(uPayBill);
        user.add(uBillDetails);

        /*----Inside Report Menu adding rGenerateBill: Generate Bill----*/
        report.add(rGenerateBill);

        /*----Inside Utility Menu adding uNotepad:Notepad, uCalculator:Calculator, uWebBrowser:Web Browser----*/
        utility.add(uNotepad);
        utility.add(uCalculator);
        utility.add(uWebBrowser);

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
        if (person.equals("Admin")) {
            mb.add(master);
        } else {
            mb.add(info);
            mb.add(user);
            mb.add(report);
        }
        mb.add(utility);
        mb.add(exit);

        setJMenuBar(mb);

        //============================================================================================================//

        setFont(new Font("Senserif", Font.BOLD, 16));
        setLayout(new FlowLayout());
        setVisible(false);
    }

    public static void main (String[] args) {

        new Project("", "").setVisible(true);
    }

    @Override
    public void actionPerformed (ActionEvent ae) {

        LOGGER.info("==: Project:: Inside actionPerformed Method :==");

        String msg = ae.getActionCommand();
        LOGGER.info("msg = " + msg);

        // If msg is "Customer Details" then Customer Details page is open:
        if (msg.equals("Customer Details")) {

            new CustomerDetails().setVisible(true);

            // If msg is "New Customer" then New Customer page is open:
        } else if (msg.equals("New Customer")) {

            new NewCustomer().setVisible(true);

            // If msg is "Calculate Bill" then Calculate Bill page is open:
        } else if (msg.equals("Calculate Bill")) {

            new CalculateBill().setVisible(true);

            // If msg is "Pay Bill" then Pay Bill page is open:
        } else if (msg.equals("Pay Bill")) {

            new PayBill(meter).setVisible(true);

            // If msg is "Notepad" then Notepad is open:
        } else if (msg.equals("Notepad")) {

            try {

                // If inside system notepad.exe is present then simply open it:
                Runtime.getRuntime().exec("notepad.exe");

            } catch (Exception e) {

                e.printStackTrace();
                StringWriter errors = new StringWriter();
                e.printStackTrace(new PrintWriter(errors));
                LOGGER.info("----Project:: Getting Exception On Notepad----" + errors);
            }

            // If msg is "Calculator" then Calculator is open:
        } else if (msg.equals("Calculator")) {

            try {

                // If inside system calc.exe is present then simply open it:
                Runtime.getRuntime().exec("calc.exe");

            } catch (Exception e) {

                e.printStackTrace();
                StringWriter errors = new StringWriter();
                e.printStackTrace(new PrintWriter(errors));
                LOGGER.info("----Project:: Getting Exception On Calculator----" + errors);

            }

            // If msg is "Web Browser" then Web Browser is open:
        } else if (msg.equals("Web Browser")) {

            try {

                // If inside system msedge.exe is present then simply open it:
                Runtime.getRuntime().exec("C:\\Program Files (x86)\\Microsoft\\Edge\\Application\\msedge.exe");

            } catch (Exception e) {

                e.printStackTrace();
                StringWriter errors = new StringWriter();
                e.printStackTrace(new PrintWriter(errors));
                LOGGER.info("----Project:: Getting Exception On Web Browser----" + errors);
            }

            // If msg is "Logout" then simply close the window and go back to login page:
        } else if (msg.equals("Logout")) {

            this.setVisible(false);
            new Login().setVisible(true);

            // If msg is "Generate Bill" then Generate Bill Page is open:
        } else if (msg.equals("Generate Bill")) {

            new GenerateBill(meter).setVisible(true);

            // If msg is "Deposit Details" then Deposit Details Page is open:
        } else if (msg.equals("Deposit Details")) {

            new DepositDetails().setVisible(true);

            // If msg is "View Information" then View Information page is open:
        } else if (msg.equals("View Information")) {

            new ViewInformation(meter).setVisible(true);

            // If msg is "Update Information" then Update Information page is open:
        } else if (msg.equals("Update Information")) {

            new UpdateInformation(meter).setVisible(true);

            // If msg is "Bill Details" then Bill Details page is open:
        } else if (msg.equals("Bill Details")) {

            new BillDetails(meter).setVisible(true);

        }


    }

}

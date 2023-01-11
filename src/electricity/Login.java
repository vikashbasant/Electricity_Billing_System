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

public class Login extends JFrame implements ActionListener {

    private static final Logger LOGGER = Logger.getLogger(String.valueOf(Login.class));

    JLabel l1, l2, l3, l4;
    JTextField tf1;
    JPasswordField pf2;
    JButton b1, b2, b3;
    JPanel p1, p2, p3, p4;
    Choice c1;

    Login() {

        /*------Header--------*/
        super("Login Page");
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);

        /*-------Label-------*/
        l1 = new JLabel("Username");
        l1.setBounds(300, 20, 100, 20);
        add(l1);

        /*-------Label-------*/
        l2 = new JLabel("Password");
        l2.setBounds(300, 60, 100, 20);
        add(l2);

        /*-------Text Field-------*/
        tf1 = new JTextField(15);
        tf1.setBounds(400, 20, 150, 20);
        add(tf1);

        /*------Password Field------*/
        pf2 = new JPasswordField(15);
        pf2.setBounds(400, 60, 150, 20);
        add(pf2);

        /*------Label-----*/
        l4 = new JLabel("Logging in as");
        l4.setBounds(300, 100, 100, 20);
        add(l4);

        /*-----Choice With Multiple Option-----*/
        c1 = new Choice();
        c1.add("Admin");
        c1.add("Customer");
        c1.setBounds(400, 100, 150, 20);
        add(c1);

        /*-----Login Button-----*/
        ImageIcon ic1 = new ImageIcon(ClassLoader.getSystemResource("icon/login.png"));
        Image i1 = ic1.getImage().getScaledInstance(16, 16, Image.SCALE_DEFAULT);
        b1 = new JButton("Login", new ImageIcon(i1));
        b1.setBounds(330, 160, 100, 20);
        add(b1);


        /*-----Cancel Button-----*/
        ImageIcon ic2 = new ImageIcon(ClassLoader.getSystemResource("icon/cancel.jpg"));
        Image i2 = ic2.getImage().getScaledInstance(16, 16, Image.SCALE_DEFAULT);
        b2 = new JButton("Cancel", new ImageIcon(i2));
        b2.setBounds(450, 160, 100, 20);
        add(b2);


        /*------Signup Button------*/
        ImageIcon ic4 = new ImageIcon(ClassLoader.getSystemResource("icon/signup.png"));
        Image i4 = ic4.getImage().getScaledInstance(16, 16, Image.SCALE_DEFAULT);
        b3 = new JButton("Signup", new ImageIcon(i4));
        b3.setBounds(380, 200, 130, 20);
        add(b3);

        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);

        /*-----Image Label------*/
        ImageIcon ic3 = new ImageIcon(ClassLoader.getSystemResource("icon/second.jpg"));
        Image i3 = ic3.getImage().getScaledInstance(250, 250, Image.SCALE_DEFAULT);
        ImageIcon icc3 = new ImageIcon(i3);
        l3 = new JLabel(icc3);
        l3.setBounds(0, 0, 250, 250);
        add(l3);

        setLayout(new BorderLayout());

        setSize(640, 300);
        setLocation(600, 300);
        setVisible(true);

    }
    @Override
    public void actionPerformed(ActionEvent ae) {

        LOGGER.info("==: Login::Inside actionPerformed Method :==");

        /*----If Click on Login Button----*/
        if (ae.getSource() == b1) {

            try {
                /*-----Get the Connection With Database-----*/
                Connection c = ConnectionProvider.getConnection();

                // Fetch the username from tf1 from textarea:
                String a = tf1.getText();
                // Fetch the password from pf2 from password area:
                String b = pf2.getText();

                // Fetch the selected item from choice c1:
                String user = c1.getSelectedItem();

                // Query For login:
                String q = "select * from login where username = '" + a + "' and password = '" + b + "' and user = '" + user + "'";

                Statement s = c.createStatement();

                // Fetch the resultset from login table where username = ? password = ? user = ?
                ResultSet rs = s.executeQuery(q);

                if (rs.next()) {

                    // Fetch the meter_no from resultset:
                    String meter = rs.getString("meter_no");

                    // Call the Project page with parameter meter_no, user i.e (admin, customer):
                    new Project(meter, user).setVisible(true);
                    // Now close the window:
                    this.setVisible(false);

                }else{
                    // If the resultset is null then one pop-up message show i.e Invalid login:
                    JOptionPane.showMessageDialog(null, "Invalid login");

                    // After showing message i.e Invalid login: set the username = "" and password="" i.e(empty):
                    tf1.setText("");
                    pf2.setText("");

                }

            } catch (Exception e) {

                e.printStackTrace();
                StringWriter errors = new StringWriter();
                e.printStackTrace(new PrintWriter(errors));
                LOGGER.info("----Login:: Getting Exception Inside button b1----" + e.getMessage());
                System.out.println("erros: " + e);

            }
        /*----If Click on Cancel Button----*/
        } else if (ae.getSource() == b2) {

            // Then window is closed:
            this.setVisible(false);

        /*----If Click on Signup Button----*/
        }else if(ae.getSource() == b3) {

            // First closed the login window
            this.setVisible(false);

            // Then call the Signup page and window is open:
            new Signup().setVisible(true);

        }

    }

    public static void main(String[] args) {
        new Login().setVisible(true);
    }
}

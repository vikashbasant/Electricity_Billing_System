package electricity;

import javax.swing.*;
import java.awt.*;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.Logger;

public class Splash extends JFrame implements Runnable {

    private static final Logger LOGGER = Logger.getLogger(String.valueOf(Login.class));

    // Multithreading Reference:
    Thread t;

    Splash () {

        LOGGER.info("==:Splash:: Inside Splash Constructor:==");

        //============================================================================================================//

        // By the use of ImageIcon we can set the image over frame:
        // ClassLoader.getSystemResource is used pick the image from system:
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/elect.jpg"));

        /*-----------Now ImageIcon i1 is placed at frame:----------------*/

        // Now i1 scale the image: So Image class present in the Awt
        Image i2 = i1.getImage().getScaledInstance(730, 550, Image.SCALE_DEFAULT);

        // scale image i2 is now converted into ImageIcon i3:
        ImageIcon i3 = new ImageIcon(i2);

        // JLabel ke ander image ka object pass nhi kr skte hn, isse me siraf ImageIcon ka object pass kr skte hn:
        JLabel image = new JLabel(i3);

        // simply add image over the frame:
        add(image);


        /*---------------------------We want frame will open dynamic---------------------------------*/

        // Frame has by-default visibility is hidden:
        setVisible(true);


        for (int i = 1, x = 1; i < 600; i += 10, x += 1) {

            // create frame of size (i+x, i) dynamic:
            setSize(i + x, i);

            // Create location of frame open (700 - ((i+x)/2), 400 - (i/2)) dynamic:
            setLocation(950 - ((i + x) / 2), 550 - (i / 2));

            /*-------For Open frame speed is very high(We want frame will open little slow) so we will use
            multithreading--------*/
            try {

                // sleep function do: code of execution will stop after 6 milliseconds:
                Thread.sleep(3);

            } catch (InterruptedException e) {

                e.printStackTrace();
                StringWriter errors = new StringWriter();
                e.printStackTrace(new PrintWriter(errors));
                LOGGER.info("----Splash:: Getting Exception in Splash Constructor----" + errors);

            }

        }


        /*---- After dynamic frame open then we need 7sec hold then open Login frame then close the Splash frame:----*/
        // that why we need to use multithreading concept i.e run function:
        t = new Thread(this);
        t.start(); // start method internally called run method


//        // create a static frame of size 730*550:
//        setSize(730, 550);
//
//        // Static Frame is open top-left most corner i.e by-default location of frame that is known as origin:
//        setLocation(600, 250);

        // Frame has by-default visibility is hidden:
        setVisible(true);


    }


    public static void main (String[] args) {

        LOGGER.info("==: Splash:: Inside main method:==");

        // anonymous object:
        new Splash();

    }


    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run () {

        LOGGER.info("==: Splash:: Inside run method:==");
        try {

            Thread.sleep(5000);
            // Now Frame is close:
            setVisible(false);

            // Now called the Login Frame:
            new Login().setVisible(true);

        } catch (InterruptedException e) {

            e.printStackTrace();
            StringWriter errors = new StringWriter();
            e.printStackTrace(new PrintWriter(errors));
            LOGGER.info("----Splash:: Getting Exception run method----" + errors);

        }
    }
}

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class about extends JPanel {
    private JLabel jcomp1;
    private JLabel jcomp2;
    private JLabel jcomp3;

    public about() {
        //construct components
        jcomp1 = new JLabel ("32nukes is a GUI based nukebot control program which allows users");
        jcomp2 = new JLabel ("to nuke servers on VoIP app 'Discord' efficiently with little to no ");
        jcomp3 = new JLabel ("technical knowledge and simple setup.");

        //adjust size and set layout
        setPreferredSize (new Dimension (410, 69));
        setLayout (null);

        //add components
        add (jcomp1);
        add (jcomp2);
        add (jcomp3);

        //set component bounds (only needed by Absolute Positioning)
        jcomp1.setBounds (10, 0, 400, 25);
        jcomp2.setBounds (10, 20, 400, 25);
        jcomp3.setBounds (10, 45, 400, 25);
    }


    public static void main () {
        JFrame frame = new JFrame ("About");
        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add (new about());
        frame.pack();
        frame.setVisible (true);
    }
}

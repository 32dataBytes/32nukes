import com.formdev.flatlaf.FlatLightLaf;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import javax.swing.*;
import javax.swing.event.*;

public class credits extends JPanel {
    private JLabel jcomp1;
    private JLabel jcomp2;
    private JButton ThirtyTwoGithub;
    private JButton KrabionGithub;
    private JLabel jcomp5;
    private JLabel jcomp6;

    public credits() {
        FlatLightLaf.install();
        //construct components
        jcomp1 = new JLabel ("32dataBytes");
        jcomp2 = new JLabel ("Krabion");
        ThirtyTwoGithub = new JButton ("Github");
        KrabionGithub = new JButton ("Github");
        jcomp5 = new JLabel ("32dataBytes#8475");
        jcomp6 = new JLabel ("code#3756");

        //adjust size and set layout
        setPreferredSize (new Dimension (261, 69));
        setLayout (null);

        //add components
        add (jcomp1);
        add (jcomp2);
        add (ThirtyTwoGithub);
        add (KrabionGithub);
        add (jcomp5);
        add (jcomp6);

        //set component bounds (only needed by Absolute Positioning)
        jcomp1.setBounds (10, 0, 100, 25);
        jcomp2.setBounds (180, 0, 100, 25);
        ThirtyTwoGithub.setBounds (10, 40, 70, 25);
        KrabionGithub.setBounds (180, 40, 70, 25);
        jcomp5.setBounds (10, 15, 110, 25);
        jcomp6.setBounds (180, 15, 100, 25);

        KrabionGithub.addActionListener(openKrabionsGithub ->{
            try {
                Desktop.getDesktop().browse(new URI("https://github.com/KabionIsGaming"));
            } catch (IOException e) {
                e.printStackTrace();
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        });

        ThirtyTwoGithub.addActionListener(openThirtyTwosGithub ->{
            try {
                Desktop.getDesktop().browse(new URI("https://github.com/32dataBytes"));
            } catch (IOException e) {
                e.printStackTrace();
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        });
    }


    public static void main () {
        JFrame frame = new JFrame ("Credits");
        frame.setDefaultCloseOperation (JFrame.HIDE_ON_CLOSE);
        frame.getContentPane().add (new credits());
        frame.pack();
        frame.setVisible (true);
    }
}

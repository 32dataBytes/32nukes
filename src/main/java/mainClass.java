import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.entity.server.Server;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.event.*;

public class mainClass extends JPanel {
    private JMenuBar menu;
    private JLabel loggedInAs;
    private JLabel selectLabel;
    private JComboBox serverList;
        private JList spamChannelList;
    private JLabel spamChannelsLabel;
        private JList spamMessagesList;
    private JLabel spamMessagesLabel;
    private JTextField addSpamChannel;
    private JButton addSpamChannelButton;
    private JTextField addSpamMessage;
    private JButton addSpamMessageButton;
    private JButton removeSpamChannelButoon;
    private JButton removeSpamMessageButton;
    private JToggleButton banEveryoneToggle;
    private JLabel extraSettingsLabel;
    private JLabel banEveryoneLabel;
    private JLabel webhookSpamLabel;
    private JToggleButton webhookSpamToggle;
    private JToggleButton adminEveryoneToggle;
    private JLabel admingEveryoneLabel;
    private JButton startButton;
    private JFileChooser fileChooser;

    public mainClass() {
        //construct preComponents
        JMenu fileMenu = new JMenu ("File");
        JMenuItem open__nuke_fileItem = new JMenuItem ("Open .nuke File");
        fileMenu.add (open__nuke_fileItem);
        JMenu infoMenu = new JMenu ("Info");
        JMenuItem discordItem = new JMenuItem ("Discord");
        infoMenu.add (discordItem);
        JMenuItem creditsItem = new JMenuItem ("Credits");
        infoMenu.add (creditsItem);
        JMenuItem aboutItem = new JMenuItem ("About");
        infoMenu.add (aboutItem);
        String[] serverListItems = {"N/A"};
        String[] spamChannelListItems = {};
        String[] spamMessageListItems = {};

        //construct components
        menu = new JMenuBar();
        menu.add (fileMenu);
        menu.add (infoMenu);
        loggedInAs = new JLabel ("Open a .nuke file!");
        selectLabel = new JLabel ("Select a Server");
        serverList = new JComboBox (serverListItems);
        spamChannelList = new JList (spamChannelListItems);
        spamChannelsLabel = new JLabel ("Spam Channel Names");
        spamMessagesList = new JList (spamMessageListItems);
        spamMessagesLabel = new JLabel ("Spam Messages List");
        addSpamChannel = new JTextField (5);
        addSpamChannelButton = new JButton ("+");
        addSpamMessage = new JTextField (5);
        addSpamMessageButton = new JButton ("+");
        removeSpamChannelButoon = new JButton ("-");
        removeSpamMessageButton = new JButton ("-");
        banEveryoneToggle = new JToggleButton ("FALSE", false);
        extraSettingsLabel = new JLabel ("Extra Settings");
        banEveryoneLabel = new JLabel ("Ban Everyone");
        webhookSpamLabel = new JLabel ("Webhook Spam");
        webhookSpamToggle = new JToggleButton ("FALSE", false);
        adminEveryoneToggle = new JToggleButton ("FALSE", false);
        admingEveryoneLabel = new JLabel ("Admin Everyone");
        startButton = new JButton ("START");

        //adjust size and set layout
        setPreferredSize (new Dimension (416, 375));
        setLayout (null);

        //add components
        add (menu);
        add (loggedInAs);
        add (selectLabel);
        add (serverList);
        add (spamChannelList);
        add (spamChannelsLabel);
        add (spamMessagesList);
        add (spamMessagesLabel);
        add (addSpamChannel);
        add (addSpamChannelButton);
        add (addSpamMessage);
        add (addSpamMessageButton);
        add (removeSpamChannelButoon);
        add (removeSpamMessageButton);
        add (banEveryoneToggle);
        add (extraSettingsLabel);
        add (banEveryoneLabel);
        add (webhookSpamLabel);
        add (webhookSpamToggle);
        add (adminEveryoneToggle);
        add (admingEveryoneLabel);
        add (startButton);

        //set component bounds (only needed by Absolute Positioning)
        menu.setBounds (0, 0, 420, 20);
        loggedInAs.setBounds (10, 20, 420, 25);
        selectLabel.setBounds (10, 50, 100, 25);
        serverList.setBounds (10, 75, 385, 30);
        spamChannelList.setBounds (10, 140, 190, 75);
        spamChannelsLabel.setBounds (10, 115, 150, 25);
        spamMessagesList.setBounds (210, 140, 190, 75);
        spamMessagesLabel.setBounds (210, 115, 150, 25);
        addSpamChannel.setBounds (10, 215, 100, 25);
        addSpamChannelButton.setBounds (110, 215, 45, 25);
        addSpamMessage.setBounds (210, 215, 100, 25);
        addSpamMessageButton.setBounds (310, 215, 45, 25);
        removeSpamChannelButoon.setBounds (155, 215, 45, 25);
        removeSpamMessageButton.setBounds (355, 215, 45, 25);
        banEveryoneToggle.setBounds (10, 300, 70, 30);
        extraSettingsLabel.setBounds (160, 250, 140, 25);
        banEveryoneLabel.setBounds (10, 275, 80, 25);
        webhookSpamLabel.setBounds (150, 275, 100, 25);
        webhookSpamToggle.setBounds (150, 300, 70, 30);
        adminEveryoneToggle.setBounds (290, 300, 70, 30);
        admingEveryoneLabel.setBounds (290, 275, 100, 25);
        startButton.setBounds (10, 340, 400, 30);


        // TODO: Add filetype checking
        open__nuke_fileItem.addActionListener(openFile ->{
            Integer returnedValue = fileChooser.showOpenDialog(mainClass.this);
            if(JFileChooser.APPROVE_OPTION == returnedValue){
                configuration configuration = new configuration(fileChooser.getSelectedFile().getAbsolutePath());
                DiscordApi bot_api = new DiscordApiBuilder().setToken(configuration.getToken()).login().join();
                loggedInAs.setText("Logged in as " + bot_api.getYourself().getDiscriminatedName());
                ArrayList<Server> nuke_targets = new ArrayList<Server>();
                for(Server server : bot_api.getServers().toArray(new Server[0])){
                    if(server.isAdmin(bot_api.getYourself())){
                        nuke_targets.add(server);
                    }
                }

                serverList.removeAllItems();
                for (int i = 0; i < nuke_targets.size(); i++) {
                    serverList.addItem(nuke_targets.get(i).getName() + " (" + nuke_targets.get(i).getIdAsString() + ")");
                }

                spamChannelList.setListData(configuration.getSpamChannelNames().toArray());
                spamMessagesList.setListData(configuration.getSpamMessages().toArray());

                banEveryoneToggle.setSelected(configuration.banAllMembers());
                webhookSpamToggle.setSelected(configuration.webhookSpam());
                adminEveryoneToggle.setSelected(configuration.giveEveryoneAdmin());
            }
        });
    }


    public static void main (String[] args) {
        JFrame frame = new JFrame ("32nukes");
        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add (new mainClass());
        frame.pack();
        frame.setVisible (true);
    }
}
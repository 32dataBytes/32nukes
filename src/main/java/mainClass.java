import com.formdev.flatlaf.FlatLightLaf;
import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.entity.server.Server;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
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
    private JButton removeSpamChannelButton;
    private JButton removeSpamMessageButton;
    private JToggleButton banEveryoneToggle;
    private JLabel extraSettingsLabel;
    private JLabel banEveryoneLabel;
    private JLabel webhookSpamLabel;
    private JToggleButton webhookSpamToggle;
    private JToggleButton adminEveryoneToggle;
    private JLabel adminEveryoneLabel;
    private JButton startButton;
    private JFileChooser fileChooser;
    private ArrayList<Server> serverListServers;
    private configuration configuration;

    public mainClass() {
        FlatLightLaf.install();

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
        removeSpamChannelButton = new JButton ("-");
        removeSpamMessageButton = new JButton ("-");
        banEveryoneToggle = new JToggleButton ("FALSE", false);
        extraSettingsLabel = new JLabel ("Extra Settings");
        banEveryoneLabel = new JLabel ("Ban Everyone");
        webhookSpamLabel = new JLabel ("Webhook Spam");
        webhookSpamToggle = new JToggleButton ("FALSE", false);
        adminEveryoneToggle = new JToggleButton ("FALSE", false);
        adminEveryoneLabel = new JLabel ("Admin Everyone");
        startButton = new JButton ("START");
        fileChooser = new JFileChooser();
        serverListServers = new ArrayList<Server>();

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
        add (removeSpamChannelButton);
        add (removeSpamMessageButton);
        add (banEveryoneToggle);
        add (extraSettingsLabel);
        add (banEveryoneLabel);
        add (webhookSpamLabel);
        add (webhookSpamToggle);
        add (adminEveryoneToggle);
        add (adminEveryoneLabel);
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
        removeSpamChannelButton.setBounds (155, 215, 45, 25);
        removeSpamMessageButton.setBounds (355, 215, 45, 25);
        banEveryoneToggle.setBounds (10, 300, 70, 30);
        extraSettingsLabel.setBounds (160, 250, 140, 25);
        banEveryoneLabel.setBounds (10, 275, 80, 25);
        webhookSpamLabel.setBounds (150, 275, 100, 25);
        webhookSpamToggle.setBounds (150, 300, 70, 30);
        adminEveryoneToggle.setBounds (290, 300, 70, 30);
        adminEveryoneLabel.setBounds (290, 275, 100, 25);
        startButton.setBounds (10, 340, 400, 30);


        aboutItem.addActionListener(openAbout ->{
            about.main();
        });

        creditsItem.addActionListener(openCredits ->{
            credits.main();
        });

        discordItem.addActionListener(openDiscordServer ->{
            try {
                Desktop.getDesktop().browse(new URI("https://discord.gg/6SryXHHYab"));
            } catch (IOException e) {
                e.printStackTrace();
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        });

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

                serverListServers = nuke_targets;

                spamChannelList.setListData(configuration.getSpamChannelNames().toArray());
                spamMessagesList.setListData(configuration.getSpamMessages().toArray());

                banEveryoneToggle.setSelected(configuration.banAllMembers());
                webhookSpamToggle.setSelected(configuration.webhookSpam());
                adminEveryoneToggle.setSelected(configuration.giveEveryoneAdmin());

                banEveryoneToggle.setText(configuration.banAllMembers().toString().toUpperCase());
                webhookSpamToggle.setText(configuration.webhookSpam().toString().toUpperCase());
                adminEveryoneToggle.setText(configuration.giveEveryoneAdmin().toString().toUpperCase());
                this.configuration = configuration;

                bot_api.addServerJoinListener(update ->{
                    loggedInAs.setText("Logged in as " + bot_api.getYourself().getDiscriminatedName());
                    for(Server server : bot_api.getServers().toArray(new Server[0])){
                        if(server.isAdmin(bot_api.getYourself())){
                            nuke_targets.add(server);
                        }
                    }

                    serverList.removeAllItems();
                    for (int i = 0; i < nuke_targets.size(); i++) {
                        serverList.addItem(nuke_targets.get(i).getName() + " (" + nuke_targets.get(i).getIdAsString() + ")");
                    }
                });

                bot_api.addServerLeaveListener(update ->{
                    loggedInAs.setText("Logged in as " + bot_api.getYourself().getDiscriminatedName());
                    for(Server server : bot_api.getServers().toArray(new Server[0])){
                        if(server.isAdmin(bot_api.getYourself())){
                            nuke_targets.add(server);
                        }
                    }

                    serverList.removeAllItems();
                    for (int i = 0; i < nuke_targets.size(); i++) {
                        serverList.addItem(nuke_targets.get(i).getName() + " (" + nuke_targets.get(i).getIdAsString() + ")");
                    }
                });
            }
        });

        banEveryoneToggle.addActionListener(toggle ->{
            configuration.setBanAllMembers(banEveryoneToggle.isSelected());
            banEveryoneToggle.setText(String.valueOf(banEveryoneToggle.isSelected()).toUpperCase());
        });

        webhookSpamToggle.addActionListener(toggle ->{
            configuration.setWebhookSpam(webhookSpamToggle.isSelected());
            webhookSpamToggle.setText(String.valueOf(webhookSpamToggle.isSelected()).toUpperCase());
        });

        adminEveryoneToggle.addActionListener(toggle ->{
            configuration.setGiveEveryoneAdmin(adminEveryoneToggle.isSelected());
            adminEveryoneToggle.setText(String.valueOf(adminEveryoneToggle.isSelected()).toUpperCase());
        });

        addSpamChannelButton.addActionListener(addChannelName ->{
            String name = addSpamChannel.getText();
            ArrayList spam_channels = configuration.getSpamChannelNames();
            spam_channels.add(name);
            configuration.setSpamChannelNames(spam_channels);
            spamChannelList.setListData(configuration.getSpamChannelNames().toArray());
        });

        addSpamMessageButton.addActionListener(addMessage ->{
            String message = addSpamMessage.getText();
            ArrayList spam_messages = configuration.getSpamMessages();
            spam_messages.add(message);
            configuration.setSpamMessages(spam_messages);
            spamMessagesList.setListData(configuration.getSpamMessages().toArray());
        });

        removeSpamChannelButton.addActionListener(removeChannelName ->{
            ArrayList spam_channels = configuration.getSpamChannelNames();
            spam_channels.remove(spamChannelList.getSelectedIndex());
            configuration.setSpamChannelNames(spam_channels);
            spamChannelList.setListData(configuration.getSpamChannelNames().toArray());
        });

        removeSpamMessageButton.addActionListener(removeMessage ->{
            ArrayList spam_messages = configuration.getSpamMessages();
            spam_messages.remove(spamMessagesList.getSelectedIndex());
            configuration.setSpamMessages(spam_messages);
            spamMessagesList.setListData(configuration.getSpamMessages().toArray());
        });

        startButton.addActionListener(startNuke ->{
            String selectedServerID = serverListServers.get(serverList.getSelectedIndex()).getIdAsString();
            String[] configs = {"assets/nuke.py", configuration.getFilepath(), selectedServerID};
            try {
                Runtime.getRuntime().exec(configs);
            } catch (IOException e) {
                e.printStackTrace();
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
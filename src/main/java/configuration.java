import java.io.*;
import java.util.ArrayList;

public class configuration {
    String filepath;
    String token;
    ArrayList<String> spam_channel_names;
    ArrayList<String> spam_messages;
    Boolean ban_all_members;
    Boolean webhook_spam;
    Boolean give_everyone_admin;

    public configuration(String filepath){
        ArrayList<String> temp = new ArrayList();
        try {
            BufferedReader file = new BufferedReader(new FileReader(filepath));
            while (file.ready()) {
                temp.add(file.readLine());
            }//end while
        } catch (IOException e) {
            System.out.println(e);
        }
        ArrayList<String> scn_temp = new ArrayList<String>();
        String[] scn_temp_array = temp.get(1).split("=")[1].replaceAll(String.valueOf('['), "").replaceAll("]", "").split(", ");
        for(String name : scn_temp_array) scn_temp.add(name);
        ArrayList<String> sm_temp = new ArrayList<String>();
        String[] sm_temp_array = temp.get(2).split("=")[1].replaceAll(String.valueOf('['), "").replaceAll("]", "").split(", ");
        for(String message : sm_temp_array) sm_temp.add(message);

        this.filepath = filepath;
        this.token = temp.get(0).split("=")[1];
        this.spam_channel_names = scn_temp;
        this.spam_messages = sm_temp;
        this.ban_all_members = temp.get(3).split("=")[1].equals("true");
        this.webhook_spam = temp.get(4).split("=")[1].equals("true");
        this.give_everyone_admin = temp.get(5).split("=")[1].equals("true");
    }

    /*
    Getter Methods
     */

    public String getFilepath() {
        return filepath;
    }

    public String getToken() {
        return token;
    }

    public ArrayList<String> getSpamChannelNames() {
        return spam_channel_names;
    }

    public ArrayList<String> getSpamMessages() {
        return spam_messages;
    }

    public Boolean banAllMembers() {
        return ban_all_members;
    }

    public Boolean webhookSpam() {
        return webhook_spam;
    }

    public Boolean giveEveryoneAdmin() {
        return give_everyone_admin;
    }

    /*
    Setter Methods
     */

    public void setToken(String token) {
        this.token = token;
        saveConfiguration(this.filepath);
    }

    public void setSpamChannelNames(ArrayList<String> spam_channel_names) {
        this.spam_channel_names = spam_channel_names;
        saveConfiguration(this.filepath);
    }

    public void setSpamMessages(ArrayList<String> spam_messages) {
        this.spam_messages = spam_messages;
        saveConfiguration(this.filepath);
    }

    public void setBanAllMembers(Boolean ban_all_members) {
        this.ban_all_members = ban_all_members;
        saveConfiguration(this.filepath);
    }

    public void setWebhookSpam(Boolean webhook_spam) {
        this.webhook_spam = webhook_spam;
        saveConfiguration(this.filepath);
    }

    public void setGiveEveryoneAdmin(Boolean give_everyone_admin) {
        this.give_everyone_admin = give_everyone_admin;
        saveConfiguration(this.filepath);
    }

    private void saveConfiguration(String filename) {
        ArrayList temp = new ArrayList();
        temp.add("token=" + token);
        temp.add("spam_channel_names=" + spam_channel_names);
        temp.add("spam_messages=" + spam_messages);
        temp.add("ban_all_members=" + ban_all_members);
        temp.add("webhook_spam=" + webhook_spam);
        temp.add("give_everyone_admin=" + give_everyone_admin);
        try {
            PrintWriter file = new PrintWriter( new FileWriter(filename)  ) ;
            for (int i = 0; i < temp.size(); i++) {
                file.println(temp.get(i));
            }
            file.close();
        } catch (IOException ex) {
            System.out.println(ex.toString());
        }
    }
}

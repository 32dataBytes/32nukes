import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class configuration {
    String filepath;
    String token;
    Boolean ban_all_members;
    ArrayList<String> spam_channel_names;
    ArrayList<String> spam_messages;
    Boolean webhook_spam;
    Boolean give_everyone_admin;

    private configuration(String filepath){
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
}

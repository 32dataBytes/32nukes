import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class loadToken {
    public static String loadTokenFromFile(String filepath) throws FileNotFoundException {
            ArrayList<String> temp = new ArrayList();
            try {
                BufferedReader file = new BufferedReader(new FileReader(filepath));
                while (file.ready()) {
                    temp.add(file.readLine());
                }//end while
            } catch (IOException e) {
                System.out.println(e);
            }
            return temp.get(2).split(":")[1];
    }
}

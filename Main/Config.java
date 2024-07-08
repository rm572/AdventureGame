import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Config {
    GamePanel gp;

    public Config(GamePanel gp) {
        this.gp = gp;
    }

    public void saveConfig() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("C:/Users/rober/OneDrive/Programming/Workspace/Workspace/2DGame_Example/config.txt"));

            if (gp.fullScreenOn) {
                bw.write("On");
            }
            else {
                bw.write("Off");
            }

            bw.newLine();

            bw.write(String.valueOf(gp.sound.volumeScale));
            bw.newLine();

            bw.write(String.valueOf(gp.se.volumeScale));
            bw.newLine();


            bw.close();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void loadConfig() {

        try {
            // BufferedReader br = new BufferedReader(new FileReader("config.txt"));
            
            BufferedReader br = new BufferedReader(new FileReader("C:/Users/rober/OneDrive/Programming/Workspace/Workspace/2DGame_Example/config.txt"));

            String s = br.readLine();

            if (s.equals("On")) {
                gp.fullScreenOn = true;
            }
            else {
                gp.fullScreenOn = false;
            }

            s = br.readLine();
            gp.sound.volumeScale = Integer.parseInt(s);

            s = br.readLine();
            gp.se.volumeScale = Integer.parseInt(s);

            br.close();


        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.imageio.ImageIO;

public class Change {
    public static void main(String[] args) throws IOException {
        // ImageIO.read(new File("C:/Users/rober/OneDrive/Programming/Workspace/Workspace/2DGame_Example/res/Tiles/sand.png"));
        BufferedReader br = new BufferedReader(new FileReader("C:/Users/rober/OneDrive/Programming/Workspace/Workspace/2DGame_Example/res/Maps/world1.txt"));

        Path dbPath2 = Paths.get("C:/Users/rober/OneDrive/Programming/Workspace/Workspace/2DGame_Example/res/Maps/testMap.txt");
        BufferedWriter bw = Files.newBufferedWriter(
            dbPath2, 
            StandardCharsets.UTF_8);

        while (br.ready()) {
            String line = br.readLine();
            bw.write(line + " " + line + "\n");
        }
        bw.close();
        br.close();
    }
}

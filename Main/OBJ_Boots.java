import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_Boots extends Entity{
    // GamePanel gp;
    
    public OBJ_Boots(GamePanel gp) {
        // this.gp = gp;
        super(gp);
        name = "Boots";
        down1 = setUp("Objects/boots", gp.tileSize, gp.tileSize);
        // try {
        //     image = ImageIO.read(new File("C:/Users/rober/OneDrive/Programming/Workspace/Workspace/2DGame_Example/res/Objects/boots.png"));
        //     uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        // }
        // catch (IOException e) {
        //     e.printStackTrace();
        // }
        
    }
}

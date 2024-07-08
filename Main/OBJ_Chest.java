import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_Chest extends Entity{
    GamePanel gp;
    Entity loot;
    boolean opened = false;
    
    public OBJ_Chest(GamePanel gp, Entity loot) {
        super(gp);
        this.loot = loot;
        this.gp = gp;
        type = typeObstacle;


        name = "Chest";
        image = setUp("Objects/chest", gp.tileSize, gp.tileSize);
        image2 = setUp("Objects/chest_opened", gp.tileSize, gp.tileSize);
        down1 = image;
        collision = true;

        solidArea.x = 4;
        solidArea.y = 16;
        solidArea.width = 40;
        solidArea.height = 32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        // try {
        //     image = ImageIO.read(new File("C:/Users/rober/OneDrive/Programming/Workspace/Workspace/2DGame_Example/res/Objects/Chest.png"));
        //     uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        // }
        // catch (IOException e) {
        //     e.printStackTrace();
        // }
    }

    public void interact() {
        gp.gameState = gp.dialogueState;
        if (!opened) {
            StringBuilder sb = new StringBuilder();
            sb.append("You opened the chest and find a " + loot.name + "!");

            if (!gp.player.canObtainItem(loot)) {
                sb.append("\n...But you cannot carry any more!");
            }
            else {
                sb.append("\nYou obtained the " + loot.name + "!");
                down1 = image2;
                opened = true;
            }

            gp.ui.currentDialogue = sb.toString();
        }
        else {
            gp.ui.currentDialogue = "It's empty";
        }
    }
}
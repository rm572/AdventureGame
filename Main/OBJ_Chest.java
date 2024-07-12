import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_Chest extends Entity{
    GamePanel gp;

    
    public OBJ_Chest(GamePanel gp) {
        super(gp);
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

    public void setLoot(Entity loot) {
        this.loot = loot;
        setDialogue();
    }

    public void setDialogue() {
        dialogues[0][0] = "You opened the chest and find a " + loot.name + "!\n...But you cannot carry any more!";
        dialogues[1][0] = "You opened the chest and find a " + loot.name + "!\nYou obtained the " + loot.name + "!";
        dialogues[2][0] = "It's empty";

    }

    public void interact() {
        gp.gameState = gp.dialogueState;
        if (!opened) {
            // StringBuilder sb = new StringBuilder();
            // sb.append("You opened the chest and find a " + loot.name + "!");

            if (!gp.player.canObtainItem(loot)) {
                // sb.append("\n...But you cannot carry any more!");
                startDialogue(this, 0);
            }
            else {
                // sb.append("\nYou obtained the " + loot.name + "!");
                startDialogue(this, 1);
                down1 = image2;
                opened = true;
            }

            // dialogues[0][0] = sb.toString();
            // startDialogue(this, 0);
        }
        else {
            // gp.ui.currentDialogue = "It's empty";
            // dialogues[1][0] = "It's empty.";
            // startDialogue(this, 1);
            startDialogue(this, 2);
        }
    }
}
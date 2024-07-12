import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_Door extends Entity{
    GamePanel gp;
    // boolean removed = false;

    public OBJ_Door(GamePanel gp) {
        super(gp);
        this.gp = gp;
        removed = false;
        name = "Door";
        type = typeObstacle;
        down1 = setUp("Objects/door", gp.tileSize, gp.tileSize);

        // try {
        //     image = ImageIO.read(new File("C:/Users/rober/OneDrive/Programming/Workspace/Workspace/2DGame_Example/res/Objects/door.png"));
        //     uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        // }
        // catch (IOException e) {
        //     e.printStackTrace();
        // }
        collision = true;
        setDialogue();
        // solidArea.x = 0;
        // solidArea.y = 16;
        // solidArea.width = 48;
        // solidArea.height = 32;
        // solidAreaDefaultX = solidArea.x;
        // solidAreaDefaultY = solidArea.y;
    }

    public void interact() {
        gp.gameState = gp.dialogueState;
        for (Entity e : gp.player.inventory) {
            if (e.name.equals("Key")) {
                // gp.ui.currentDialogue = "You used a key! The door is now open";
                startDialogue(this, 0);
                gp.player.inventory.remove(e);
                removed = true;
                break;
            }
        }
        
        if (!removed) {
            // gp.ui.currentDialogue = "You need a key to open the door!";
            startDialogue(this, 1);
        }
    }

    public void setDialogue() {
        dialogues[0][0] = "You used a key! The door is now open";
        dialogues[1][0] = "You need a key to open the door!";
    }
}
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Random;

import javax.imageio.ImageIO;

public class NPC_OldMan extends Entity {
    public NPC_OldMan(GamePanel gp) {
        super(gp);

        direction = "down";
        speed = 2;
        hasMovement = true;
        solidArea.x = 1;
        solidArea.y = 1;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 46;
        solidArea.height = 46;

        getImage();
        setDialogue();
    }

      public void getImage() {
        //Optimization
        up1 = setUp("NPC/oldman_up_1", gp.tileSize, gp.tileSize);
        up2 = setUp("NPC/oldman_up_2", gp.tileSize, gp.tileSize);
        down1 = setUp("NPC/oldman_down_1", gp.tileSize, gp.tileSize);
        down2 = setUp("NPC/oldman_down_2", gp.tileSize, gp.tileSize);
        left1 = setUp("NPC/oldman_left_1", gp.tileSize, gp.tileSize);
        left2 = setUp("NPC/oldman_left_2", gp.tileSize, gp.tileSize);
        right1 = setUp("NPC/oldman_right_1", gp.tileSize, gp.tileSize);
        right2 = setUp("NPC/oldman_right_2", gp.tileSize, gp.tileSize);
    }

    public void setAction() {

        if (onPath) {
            // Goes to a certain location
            // int goalCol = 39; //39
            // int goalRow = 7; //7

            //Follows player
            int goalCol = (gp.player.worldX + gp.player.solidArea.x)/gp.tileSize;
            int goalRow = (gp.player.worldY + gp.player.solidArea.y)/gp.tileSize;


            searchPath(goalCol, goalRow);
        }
        else {
            actionLockCounter++;
            
            if (actionLockCounter == 250) {
                Random random = new Random();
                int i = random.nextInt(100)+1; //random num from 1-100
        
                if (i <= 25) {
                    direction = "up";
                }
                else if (i <= 50) {
                    direction = "down";
                } 
                else if (i <= 75) {
                    direction = "left";
                }
                else {
                    direction = "right";
                }
                moving = true;
                actionLockCounter = 0;
            }
        }
    }

    public void setDialogue() {
        dialogues[0] = "Hello, there.";
        dialogues[1] = "So you've come to this \nisland to find the treausre?";
        dialogues[2] = "I used to be a great wizard";
        dialogues[3] = "Well, good luck on you.";

    }

    public void speak() {
        super.speak();

        onPath = true;
        if (onPath) {
            moving = true;
        }
        
    }


}

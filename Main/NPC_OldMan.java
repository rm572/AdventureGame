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

        dialogueSet = -1;

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
        dialogues[0][0] = "Hello, there.";
        dialogues[0][1] = "So you've come to this \nisland to find the treausre?";
        dialogues[0][2] = "I used to be a great wizard";
        dialogues[0][3] = "Well, good luck on you.";

        dialogues[1][0] = "If you become tired, rest at the water";
        dialogues[1][1] = "However, the monsters reappear if you rest.\nI don't know why but that's how it works.";
        dialogues[1][2] = "In any case, don't push yourself too hard.";

        dialogues[2][0] = "I wonder how to open that door...";

    }

    public void speak() {
        // super.speak();

        //Follows player
        // onPath = true;
        // if (onPath) {
        //     moving = true;
        // }

        facePlayer();
        startDialogue(this, dialogueSet);

        
        dialogueSet++;

        if (dialogues[dialogueSet][0] == null) {
            dialogueSet = 0;
        }


        
    }


}

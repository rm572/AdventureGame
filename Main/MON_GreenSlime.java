import java.util.Random;

public class MON_GreenSlime extends Entity{
    
    public MON_GreenSlime(GamePanel gp) {
        super(gp);

        type = typeMonster;
        name = "Green Slime";
        defaultSpeed = 1;
        speed = defaultSpeed;
        maxLife = 4;
        life = maxLife;
        attack = 2;
        defense = 0;
        exp = 2;
        // knockBackPower = gp.tileSize/16;
        projectile = new OBJ_Rock(gp);

        solidArea.x = 1;
        solidArea.y = 16;
        solidArea.width = 46;
        solidArea.height = 31;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getImage();
    }

    public void getImage() {
        up1 = setUp("Monster/new_slime_down_1", gp.tileSize, gp.tileSize);
        up2 = setUp("Monster/new_slime_down_2", gp.tileSize, gp.tileSize);
        down1 = setUp("Monster/new_slime_down_1", gp.tileSize, gp.tileSize);
        down2 = setUp("Monster/new_slime_down_2", gp.tileSize, gp.tileSize);
        left1 = setUp("Monster/new_slime_down_1", gp.tileSize, gp.tileSize);
        left2 = setUp("Monster/new_slime_down_2", gp.tileSize, gp.tileSize);
        right1 = setUp("Monster/new_slime_down_1", gp.tileSize, gp.tileSize);
        right2 = setUp("Monster/new_slime_down_2", gp.tileSize, gp.tileSize);
    }

    public void setAction() {
        
        // int xDistance = Math.abs(worldX - gp.player.worldX);
        // int yDistance = Math.abs(worldY - gp.player.worldY);
        // int tileDistance = (xDistance + yDistance)/gp.tileSize;

        if (onPath) {

            //Check if it stops checking
            checkStopChasingOrNot(gp.player, 4, 100);
            // if (tileDistance > 15) {
            //     onPath = false;
            // }


            // Goes to a certain location
            // int goalCol = 39; //39
            // int goalRow = 7; //7

            //Follows player
            // int goalCol = (gp.player.worldX + gp.player.solidArea.x)/gp.tileSize;
            // int goalRow = (gp.player.worldY + gp.player.solidArea.y)/gp.tileSize;


            searchPath(getGoalCol(gp.player), getGoalRow(gp.player));

            //Checks if it shoots projectile
            checkShootOrNot(200, 30);
            // int i = new Random().nextInt(200)+1;
            // if (i > 197 && projectile.alive == false && shotAvailableCounter == 30) {
            //     projectile.set(worldX, worldY, direction, true, this);
            //     // gp.projectileList.add(projectile);
            //     for (int ii = 0; ii < gp.projectile[1].length; ii++) {
            //         if (gp.projectile[gp.currentMap][ii] == null) {
            //             gp.projectile[gp.currentMap][ii] = projectile;
            //             break;
            //         }
            //     }
            //     shotAvailableCounter = 0;
            // }
        }
        else {
            //Check if it starts chasing
            checkStartChasingOrNot(gp.player, 5, 100);
            // if (tileDistance < 5) {
            //     int i = new Random().nextInt(100)+1;
            //     if (i > 50) {
                    // onPath = true;
            //     }
                
            // }
            getRandomDirection(100);
            // actionLockCounter++;
            
            // if (actionLockCounter == 100) {
            //     Random random = new Random();
            //     int i = random.nextInt(100)+1; //random num from 1-100
        
            //     if (i <= 25) {
            //         direction = "up";
            //     }
            //     else if (i <= 50) {
            //         direction = "down";
            //     } 
            //     else if (i <= 75) {
            //         direction = "left";
            //     }
            //     else {
            //         direction = "right";
            //     }
            //     moving = true;
            //     actionLockCounter = 0;
            // }
        }
    }

    //Follows player if hit
    public void damageReaction() {
        actionLockCounter = 0;
        // direction = gp.player.direction;
        onPath = true;
    }

    public void checkDrop() {
        int i = new Random().nextInt(100)+1;

        if (i < 20) {
            dropItem(new OBJ_Heart(gp));
        }
        else if (i < 30) {
            dropItem(new OBJ_Potion_Red(gp));
        }
        else if (i < 40) {
            dropItem(new OBJ_Potion_Blue(gp));
        }
        else if (i < 60) {
            dropItem(new OBJ_Mana(gp));
        }
        else if (i < 65) {
            dropItem(new OBJ_Tent(gp));
        }
        else if (i < 90) {
            dropItem(new OBJ_Coin_Bronze(gp));
        }
        else {
            dropItem(new OBJ_Lantern(gp));
        }
    }
}

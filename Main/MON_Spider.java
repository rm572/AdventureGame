import java.util.Random;

public class MON_Spider extends Entity{
    
    public MON_Spider(GamePanel gp) {
        super(gp);

        type = typeMonster;
        name = "Spider";
        defaultSpeed = 3;
        speed = defaultSpeed;
        maxLife = 10;
        life = maxLife;
        attack = 3;
        defense = 3;
        exp = 7;

        solidArea.x = 1;
        solidArea.y = 1;
        solidArea.width = 46;
        solidArea.height = 46;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getImage();
    }

    public void getImage() {
        up1 = setUp("Monster/spider_up_1", gp.tileSize, gp.tileSize);
        up2 = setUp("Monster/spider_up_2", gp.tileSize, gp.tileSize);
        down1 = setUp("Monster/spider_down_1", gp.tileSize, gp.tileSize);
        down2 = setUp("Monster/spider_down_2", gp.tileSize, gp.tileSize);
        left1 = setUp("Monster/spider_left_1", gp.tileSize, gp.tileSize);
        left2 = setUp("Monster/spider_left_2", gp.tileSize, gp.tileSize);
        right1 = setUp("Monster/spider_right_1", gp.tileSize, gp.tileSize);
        right2 = setUp("Monster/spider_right_2", gp.tileSize, gp.tileSize);
    }

    public void setAction() {
        
        // int xDistance = Math.abs(worldX - gp.player.worldX);
        // int yDistance = Math.abs(worldY - gp.player.worldY);
        // int tileDistance = (xDistance + yDistance)/gp.tileSize;

        if (onPath) {
            checkStopChasingOrNot(gp.player, 20, 100);
            searchPath(getGoalCol(gp.player), getGoalRow(gp.player));
        }
        else {
            checkStartChasingOrNot(gp.player, 12, 100);
            getRandomDirection(100);
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

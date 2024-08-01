import java.util.Random;

public class MON_Boss extends Entity{
    
    public MON_Boss(GamePanel gp) {
        super(gp);

        type = typeMonster;
        name = "Boss";
        defaultSpeed = 8;
        speed = defaultSpeed;
        maxLife = 40;
        life = maxLife;
        attack = 5;
        defense = 0;
        exp = 100;
        // knockBackPower = gp.tileSize/16;
        // projectile = new OBJ_Rock(gp);

        solidArea.x = 1;
        solidArea.y = 16;
        solidArea.width = 46;
        solidArea.height = 31;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getImage();
    }

    public void getImage() {
        up1 = setUp("Monster/boss_1", gp.tileSize, gp.tileSize);
        up2 = setUp("Monster/boss_2", gp.tileSize, gp.tileSize);
        down1 = setUp("Monster/boss_1", gp.tileSize, gp.tileSize);
        down2 = setUp("Monster/boss_2", gp.tileSize, gp.tileSize);
        left1 = setUp("Monster/boss_1", gp.tileSize, gp.tileSize);
        left2 = setUp("Monster/boss_2", gp.tileSize, gp.tileSize);
        right1 = setUp("Monster/boss_1", gp.tileSize, gp.tileSize);
        right2 = setUp("Monster/boss_2", gp.tileSize, gp.tileSize);
    }

    public void setAction() {

        if (onPath) {
            checkStopChasingOrNot(gp.player, 10, 100);
            searchPath(getGoalCol(gp.player), getGoalRow(gp.player));
            // checkShootOrNot(100, 30);
        }
        else {
            checkStartChasingOrNot(gp.player, 8, 100);
            // getRandomDirection(100);
        }
    }

    //Follows player if hit
    public void damageReaction() {
        actionLockCounter = 0;
        // direction = gp.player.direction;
        onPath = true;
    }

    public void checkDrop() {
        // dropItem(new OBJ_Treasure(gp));
    }
}

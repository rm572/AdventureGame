import java.util.Random;

public class MON_Orc extends Entity{
    
    public MON_Orc(GamePanel gp) {
        super(gp);

        type = typeMonster;
        name = "Orc";
        defaultSpeed = 1;
        speed = defaultSpeed;
        maxLife = 10;
        life = maxLife;
        attack = 4;
        defense = 2;
        exp = 10;
        knockBackPower = 4;

        solidArea.x = 4;
        solidArea.y = 4;
        solidArea.width = 40;
        solidArea.height = 44;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        motion1Duration = 40;
        motion2Duration = 95;

        attackArea.width = gp.tileSize;
        attackArea.height = gp.tileSize/2;

        getImage();
        getAttackImage();
    }

    public void getImage() {
        up1 = setUp("Monster/orc_up_1", gp.tileSize, gp.tileSize);
        up2 = setUp("Monster/orc_up_2", gp.tileSize, gp.tileSize);
        down1 = setUp("Monster/orc_down_1", gp.tileSize, gp.tileSize);
        down2 = setUp("Monster/orc_down_2", gp.tileSize, gp.tileSize);
        left1 = setUp("Monster/orc_left_1", gp.tileSize, gp.tileSize);
        left2 = setUp("Monster/orc_left_2", gp.tileSize, gp.tileSize);
        right1 = setUp("Monster/orc_right_1", gp.tileSize, gp.tileSize);
        right2 = setUp("Monster/orc_right_2", gp.tileSize, gp.tileSize);
    }

    public void getAttackImage() {
        attackUp1 = setUp("Monster/orc_attack_up_1", gp.tileSize, gp.tileSize*2);
            attackUp2 = setUp("Monster/orc_attack_up_2", gp.tileSize, gp.tileSize*2);
            attackDown1 = setUp("Monster/orc_attack_down_1", gp.tileSize, gp.tileSize*2);
            attackDown2 = setUp("Monster/orc_attack_down_2", gp.tileSize, gp.tileSize*2);
            attackLeft1 = setUp("Monster/orc_attack_left_1", gp.tileSize*2, gp.tileSize);
            attackLeft2 = setUp("Monster/orc_attack_left_2", gp.tileSize*2, gp.tileSize);
            attackRight1 = setUp("Monster/orc_attack_right_1", gp.tileSize*2, gp.tileSize);
            attackRight2 = setUp("Monster/orc_attack_right_2", gp.tileSize*2, gp.tileSize);    
        
    }

    public void setAction() {
        if (onPath) {
            checkStopChasingOrNot(gp.player, 10, 100);
            searchPath(getGoalCol(gp.player), getGoalRow(gp.player));
        }
        else {
            checkStartChasingOrNot(gp.player, 4, 100);
            getRandomDirection(100);
        }

        if (attacking == false) {
            checkAttackOrNot(30, gp.tileSize * 4, gp.tileSize*2);
        }
    }

    public void damageReaction() {
        actionLockCounter = 0;
        onPath = true;
    }

    public void checkDrop() {
        int i = new Random().nextInt(100)+1;

        if (i < 50) {
            dropItem(new OBJ_Coin_Bronze(gp));
        }
        else if (i < 75) {
            dropItem(new OBJ_Heart(gp));
        }
        else {
            dropItem(new OBJ_Mana(gp));
        }
    }
}


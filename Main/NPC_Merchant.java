import java.util.Random;

public class NPC_Merchant extends Entity{
    public NPC_Merchant(GamePanel gp) {
        super(gp);

        direction = "down";
        speed = 1;
        name = "Merchant";
        hasMovement = false;
        // solidArea.x = 1;
        // solidArea.y = 1;
        // solidAreaDefaultX = solidArea.x;
        // solidAreaDefaultY = solidArea.y;
        // solidArea.width = 46;
        // solidArea.height = 46;

        getImage();
        setDialogue();
        setItems();
    }

      public void getImage() {
        down1 = setUp("NPC/merchant_down_1", gp.tileSize, gp.tileSize);
        down2 = setUp("NPC/merchant_down_2", gp.tileSize, gp.tileSize);
        up1 = setUp("NPC/merchant_down_1", gp.tileSize, gp.tileSize);
        up2 = setUp("NPC/merchant_down_2", gp.tileSize, gp.tileSize);
        left1 = setUp("NPC/merchant_down_1", gp.tileSize, gp.tileSize);
        left2 = setUp("NPC/merchant_down_2", gp.tileSize, gp.tileSize);
        right1 = setUp("NPC/merchant_down_1", gp.tileSize, gp.tileSize);
        right2 = setUp("NPC/merchant_down_2", gp.tileSize, gp.tileSize);
    }

    public void setDialogue() {
        dialogues[0] = "He he. So you found me. \nI have some good stuff. \nDo you want to trade?";
    }

    public void setItems() {
        inventory.add(new OBJ_Potion_Red(gp));
        inventory.add(new OBJ_Key(gp));
        inventory.add(new OBJ_Sword_Normal(gp));
        inventory.add(new OBJ_Shield_Wood(gp));
        inventory.add(new OBJ_Shield_Blue(gp));
        inventory.add(new OBJ_Axe(gp));
    }

    public void speak() {
        super.speak();
        gp.gameState = gp.tradeState;
        gp.ui.npc = this;
    }
}

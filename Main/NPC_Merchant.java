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
        dialogues[0][0] = "He he. So you found me. \nI have some good stuff. \nDo you want to trade?";
        dialogues[1][0] = "Come again, hehe!";
        dialogues[2][0] = "You need more coins!";
        dialogues[3][0] = "Inventory is full!";
        dialogues[4][0] = "You cannot sell an equipped item!";
    }

    public void setItems() {
        inventory.add(new OBJ_Potion_Red(gp));
        inventory.add(new OBJ_Key(gp));
        inventory.add(new OBJ_Tent(gp));
        inventory.add(new OBJ_Lantern(gp));
        inventory.add(new OBJ_Axe(gp));
        inventory.add(new OBJ_Potion_Blue(gp));
    }

    public void speak() {
        // super.speak();

        facePlayer();
        gp.gameState = gp.tradeState;
        gp.ui.npc = this;
    }
}

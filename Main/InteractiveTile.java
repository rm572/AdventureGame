import java.awt.Graphics2D;
import java.util.Random;

public class InteractiveTile extends Entity {
    
    GamePanel gp;
    boolean destructible = false;
    boolean checkedDrop = false;

    public InteractiveTile(GamePanel gp, int col, int row) {
        super(gp);
        this.gp = gp;
    }

    public void update() {
        if (invincible) {
            invincibleCounter++;
            if (invincibleCounter > 20) {
                invincible = false;
                invincibleCounter = 0;
            }
        }

    }
    public void playSE() {}
    public InteractiveTile getDestroyedForm() {
        InteractiveTile tile = null;
        return tile;
    }

    public void checkDrop() {
        int i = new Random().nextInt(200)+1;

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
        else if (i < 100){
            dropItem(new OBJ_Lantern(gp));
        }
    }
    public boolean isCorrectItem(Entity entity) {
        boolean isCorrectItem = false;
        return isCorrectItem;
    }

    public void draw(Graphics2D g2) {
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        // // Only draws pixels on the screen
        if (worldX + gp.tileSize> gp.player.worldX - gp.player.screenX && 
            worldX - gp.tileSize< gp.player.worldX + gp.player.screenX && 
            worldY + gp.tileSize> gp.player.worldY - gp.player.screenY && 
            worldY - gp.tileSize< gp.player.worldY + gp.player.screenY) {
                g2.drawImage(down1, screenX, screenY, null);

        }
    }

}

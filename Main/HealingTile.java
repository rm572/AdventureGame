import java.awt.Graphics2D;

public class HealingTile extends Entity {
    GamePanel gp;
    int counter = 0;
    boolean active = true;
    boolean healed = false;

    public HealingTile(GamePanel gp, int col, int row) {
        super(gp);
        this.gp = gp;
        name = "Healing Tile";

        this.worldX = gp.tileSize * col;
        this.worldY = gp.tileSize * row;

        // up1 = setUp("Tiles/Healing00", gp.tileSize, gp.tileSize);
        // up2 = setUp("Tiles/Healing01", gp.tileSize, gp.tileSize);
        // down1 = setUp("Tiles/Healing02", gp.tileSize, gp.tileSize);
        // down2 = setUp("Tiles/Healing03", gp.tileSize, gp.tileSize);
        up1 = setUp("Tiles/Healing04", gp.tileSize, gp.tileSize);
        up2 = setUp("Tiles/Healing05", gp.tileSize, gp.tileSize);
        down1 = setUp("Tiles/Healing06", gp.tileSize, gp.tileSize);
        down2 = setUp("Tiles/Healing07", gp.tileSize, gp.tileSize);
        image = up1;
    }


    public void update() {
        switch (counter) {
            case 0: image = up1; break;
            case 50: image = up2; break;
            case 100: image = down1; break;
            case 150: image = down2; break;
        }
        counter = (counter + 1) % 200;

        if (gp.player.worldX == worldX && gp.player.worldY == worldY) {
            active = false;
        }
    } 

    public void draw(Graphics2D g2) {
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        // // Only draws pixels on the screen
        if (worldX + gp.tileSize> gp.player.worldX - gp.player.screenX && 
            worldX - gp.tileSize< gp.player.worldX + gp.player.screenX && 
            worldY + gp.tileSize> gp.player.worldY - gp.player.screenY && 
            worldY - gp.tileSize< gp.player.worldY + gp.player.screenY) {
                g2.drawImage(image, screenX, screenY, null);

        }
    }



    
}

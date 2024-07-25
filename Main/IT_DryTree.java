import java.awt.Color;
import java.util.Random;

public class IT_DryTree extends InteractiveTile {

    GamePanel gp;
    
    public IT_DryTree(GamePanel gp, int col, int row) {
        super(gp, col, row);
        this.gp = gp;
        name = "Dry tree";

        this.worldX = gp.tileSize * col;
        this.worldY = gp.tileSize * row;

        down1 = setUp("Tiles_Interactive/drytree00", gp.tileSize, gp.tileSize);
        destructible = true;
        life = 1;
    }

    public boolean isCorrectItem(Entity entity) {
        boolean isCorrectItem = false;

        if (entity.currentWeapon.type == typeAxe) {
            isCorrectItem = true;
        }
        return isCorrectItem;
    }

    public InteractiveTile getDestroyedForm() {
        InteractiveTile tile = new IT_Trunk(gp, worldX/gp.tileSize, worldY/gp.tileSize);
        return tile;
    }

    public Color getParticleColor() {
        Color color = new Color(65, 50, 30);
        return color;
    }

    public int getParticleSize() {
        int size = 6; //6 pixels
        return size;
    }

    public int getParticleSpeed() {
        int speed = 1;
        return speed;
    }

    public int getParticleMaxLife() {
        int maxLife = 20;
        return maxLife;
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
    
}

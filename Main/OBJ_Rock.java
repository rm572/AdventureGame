import java.awt.Color;

public class OBJ_Rock extends Projectile {
    GamePanel gp;
    static final String objName = "Rock";

    public OBJ_Rock(GamePanel gp) {
        super(gp);
        this.gp = gp;

        name = objName;
        speed = 2;
        // knockBackPower = 0;
        maxLife = 120;
        life = maxLife;
        attack = 2;
        useCost = 1;
        alive = false;
        getImage();
    }

    public void getImage() {
        up1 = setUp("Projectile/rock_down_1", gp.tileSize, gp.tileSize);
        up2 = setUp("Projectile/rock_down_1", gp.tileSize, gp.tileSize);
        down1 = setUp("Projectile/rock_down_1", gp.tileSize, gp.tileSize);
        down2 = setUp("Projectile/rock_down_1", gp.tileSize, gp.tileSize);
        left1 = setUp("Projectile/rock_down_1", gp.tileSize, gp.tileSize);
        left2 = setUp("Projectile/rock_down_1", gp.tileSize, gp.tileSize);
        right1 = setUp("Projectile/rock_down_1", gp.tileSize, gp.tileSize);
        right2 = setUp("Projectile/rock_down_1", gp.tileSize, gp.tileSize);
    }

    public boolean haveResource(Entity user) {
        boolean haveResource = false;
        if (user.ammo >= useCost) {
            haveResource = true;
        }

        return haveResource;
    }

    public void subtractResource(Entity user) {
        user.ammo -= useCost;
    }

    public Color getParticleColor() {
        Color color = new Color(40, 50, 0);
        return color;
    }

    public int getParticleSize() {
        int size = 10; //6 pixels
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
}

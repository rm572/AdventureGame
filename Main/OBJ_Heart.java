import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_Heart extends Entity{
    GamePanel gp;
    static final String objName = "Heart";

    public OBJ_Heart(GamePanel gp) {
        
        super(gp);
        this.gp = gp;
        name = objName;

        image = setUp("Objects/heart_full", gp.tileSize, gp.tileSize);
        image2 = setUp("Objects/heart_half", gp.tileSize, gp.tileSize);
        image3 = setUp("Objects/heart_blank", gp.tileSize, gp.tileSize);
        down1 = setUp("Objects/heart_full", gp.tileSize, gp.tileSize);
        type = typePickupOnly;
        value = 2;
    }

    public boolean use(Entity entity) {
        gp.ui.addMessage("Life +" + value);
        
        entity.life += value;
        if (entity.life > entity.maxLife) {
            entity.life = entity.maxLife;
        }
        return true;
    }
}

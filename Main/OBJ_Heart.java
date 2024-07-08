import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_Heart extends Entity{
        GamePanel gp;

    public OBJ_Heart(GamePanel gp) {
        
        super(gp);
        this.gp = gp;
        name = "Heart";

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
        return true;
    }
}

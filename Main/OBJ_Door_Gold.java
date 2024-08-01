import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_Door_Gold extends Entity{
    GamePanel gp;
    // boolean removed = false;
    static final String objName = "Door";

    public OBJ_Door_Gold(GamePanel gp) {
        super(gp);
        this.gp = gp;
        removed = false;
        name = objName;
        type = typeObstacle;
        down1 = setUp("Objects/door_gold", gp.tileSize, gp.tileSize);
        collision = true;
        setDialogue();
    }

    public void interact() {
        gp.gameState = gp.dialogueState;
        startDialogue(this, 0);
    }

    public void setDialogue() {
        dialogues[0][0] = "This door cannot be opened";
    }
}
// import java.io.File;
// import java.io.IOException;

// import javax.imageio.ImageIO;

public class OBJ_Key extends Entity{

    GamePanel gp;
    
    public OBJ_Key(GamePanel gp) {
        super(gp);

        this.gp = gp;
        name = "Key";
        type = typeConsumable;
        down1 = setUp("Objects/key", gp.tileSize, gp.tileSize);
        description = "{" + name + "}\nIt opens a door.";
        price = 100;
        stackable = true;
        // try {
        //     image = ImageIO.read(new File("C:/Users/rober/OneDrive/Programming/Workspace/Workspace/2DGame_Example/res/Objects/key.png"));
        //     uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        // }
        // catch (IOException e) {
        //     e.printStackTrace();
        // }

        // solidArea.x = 5;
        
    }

    public boolean use(Entity entity) {
    //     gp.gameState = gp.dialogueState;

    //     int objIndex = getDetected(entity, gp.obj, "Door");

    //     if (objIndex != 999) {
    //         gp.ui.currentDialogue = "You use the " + name + " and open the door.";
    //         gp.obj[gp.currentMap][objIndex] = null;
    //     }
    //     else {
    //         gp.ui.currentDialogue = "What are you doing?";
    //     }
        gp.gameState = gp.dialogueState;
        gp.ui.currentDialogue = "What are you doing?";
        return false;
    }
}

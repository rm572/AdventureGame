// import java.io.File;
// import java.io.IOException;

// import javax.imageio.ImageIO;

public class OBJ_Key extends Entity{

    GamePanel gp;
    
    static final String objName = "Key";
    public OBJ_Key(GamePanel gp) {
        super(gp);


        this.gp = gp;
        name = objName;
        type = typeConsumable;
        down1 = setUp("Objects/key", gp.tileSize, gp.tileSize);
        description = "{" + name + "}\nIt opens a door.";
        price = 20;
        stackable = true;
        setDialogue();
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
        //Open door by pressing enter -> doesn't work
        // gp.gameState = gp.dialogueState;

        int objIndex = getDetected(entity, gp.obj, "Door");

        if (objIndex != 999) {
            // gp.ui.currentDialogue = "You use the " + name + " and open the door.";
            startDialogue(this, 0);
            gp.obj[gp.currentMap][objIndex] = null;
            return true;
        }
        else {
            // gp.ui.currentDialogue = "What are you doing?";
            startDialogue(this, 1);
            return false;
        }

        //Automatically opens the door -> works
        // gp.gameState = gp.dialogueState;
        // gp.ui.currentDialogue = "What are you doing?";
        // return false;
    }

    public void setDialogue() {
        dialogues[0][0] = "You use the " + name + " and open the door.";
        dialogues[1][0] = "What are you doing?";
    }
}

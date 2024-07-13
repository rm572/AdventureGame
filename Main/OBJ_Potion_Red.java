public class OBJ_Potion_Red extends Entity{
    GamePanel gp;

    static final String objName = "Red Potion";
    public OBJ_Potion_Red(GamePanel gp) {
        super(gp);
        this.gp = gp;
        value = 5;

        type = typeConsumable;
        name = objName;
        down1 = setUp("Objects/potion_red", gp.tileSize, gp.tileSize);
        description = "{Red Potion}\nHeals your life by " + value;
        price = 25;
        stackable = true;
        setDialogue();
    }

    public void setDialogue() {
        dialogues[0][0] = "You drink the " + name + "!\n" + 
            "You life has been recovered by \n" + value + ".";
    }


    public boolean use(Entity entity) {
        // gp.gameState = gp.dialogueState;

        // gp.ui.currentDialogue = "You drink the " + name + "!\n" + 
        //     "You life has been recovered by \n" + value + ".";
        startDialogue(this, 0);
        entity.life += value;
        return true;
    }
    
}

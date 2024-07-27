public class OBJ_Potion_Blue extends Entity{
    GamePanel gp;

    static final String objName = "Blue Potion";
    public OBJ_Potion_Blue(GamePanel gp) {
        super(gp);
        this.gp = gp;
        value = 5;

        type = typeConsumable;
        name = objName;
        down1 = setUp("Objects/potion_blue", gp.tileSize, gp.tileSize);
        description = "{Blue Potion}\nRestores mana by " + value;
        price = 5;
        stackable = true;
        setDialogue();
    }

    public void setDialogue() {
        dialogues[0][0] = "You drink the " + name + "!\n" + 
            "Your mana has been recovered by \n" + value + ".";
    }


    public boolean use(Entity entity) {
        // gp.gameState = gp.dialogueState;

        // gp.ui.currentDialogue = "You drink the " + name + "!\n" + 
        //     "You life has been recovered by \n" + value + ".";
        startDialogue(this, 0);
        entity.mana += value;
        return true;
    }
    
}

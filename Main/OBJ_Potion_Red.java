public class OBJ_Potion_Red extends Entity{
    GamePanel gp;
    public OBJ_Potion_Red(GamePanel gp) {
        super(gp);
        this.gp = gp;
        value = 5;

        type = typeConsumable;
        name = "Red Potion";
        down1 = setUp("Objects/potion_red", gp.tileSize, gp.tileSize);
        description = "{Red Potion}\nHeals your life by " + value;
        price = 25;
        stackable = true;
    }


    public boolean use(Entity entity) {
        gp.gameState = gp.dialogueState;

        gp.ui.currentDialogue = "You drink the " + name + "!\n" + 
            "You life has been recovered by \n" + value + ".";
        entity.life += value;
        return true;
    }
    
}

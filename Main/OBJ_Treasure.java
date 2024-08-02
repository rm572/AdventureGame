public class OBJ_Treasure extends Entity {
    GamePanel gp;
    static final String objName = "Treasure";
    public OBJ_Treasure(GamePanel gp) {
        super(gp);
        this.gp = gp;
        type = typeTreasure;
        name = objName;
        description = "{Treasure}\nUltra Rare Treasure";
        down1 = setUp("Objects/treasure", gp.tileSize, gp.tileSize);
    }

    // public boolean use(Entity entity) {
    //     gp.gameState = gp.endState;
    //     return true;
    // }
    
}
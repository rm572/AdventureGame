public class OBJ_Shield_Blue extends Entity {
    static final String objName = "Blue Shield";
    
    public OBJ_Shield_Blue(GamePanel gp) {
        super(gp);
        type = typeShield;
        name = objName;
        down1 = setUp("Objects/shield_blue", gp.tileSize, gp.tileSize);
        defenseValue = 2;
        description = "{" + name + "}\nA shiny blue shield.";
        price = 250;
    }
    
}

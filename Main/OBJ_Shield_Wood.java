public class OBJ_Shield_Wood extends Entity {
    static final String objName = "Wood Shield";
    public OBJ_Shield_Wood(GamePanel gp) {
        super(gp);
        type = typeShield;
        name = objName;
        down1 = setUp("Objects/shield_wood", gp.tileSize, gp.tileSize);
        defenseValue = 1;
        description = "{" + name + "}\nMade with wood.";
        price = 35;
    }
    
}

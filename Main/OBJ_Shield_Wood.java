public class OBJ_Shield_Wood extends Entity {

    public OBJ_Shield_Wood(GamePanel gp) {
        super(gp);
        type = typeShield;
        name = "Wood Shield";
        down1 = setUp("Objects/shield_wood", gp.tileSize, gp.tileSize);
        defenseValue = 1;
        description = "{" + name + "}\nMade with wood.";
        price = 35;
    }
    
}

public class OBJ_Shield_Blue extends Entity {

    public OBJ_Shield_Blue(GamePanel gp) {
        super(gp);
        type = typeShield;
        name = "Blue Shield";
        down1 = setUp("Objects/shield_blue", gp.tileSize, gp.tileSize);
        defenseValue = 2;
        description = "{" + name + "}\nA shiny blue shield.";
        price = 250;
    }
    
}

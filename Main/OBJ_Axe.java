public class OBJ_Axe extends Entity {

    static final String objName = "Woodcutter's Axe";

    public OBJ_Axe(GamePanel gp) {
        super(gp);
        type = typeAxe;
        name = objName;
        down1 = setUp("Objects/axe", gp.tileSize, gp.tileSize);
        attackValue = 2;
        attackArea.width = 30;
        attackArea.height = 30;

        description = "{" + name + "}\nA bit rusty but \ncan still cut \nsome trees";
        price = 15;
        knockBackPower = 10;
        motion1Duration = 20;
        motion2Duration = 40;
    }
    
}

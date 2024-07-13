public class OBJ_Sword_Normal extends Entity{
    static final String objName = "Normal sword";
    public OBJ_Sword_Normal(GamePanel gp) {
        super(gp);
        
        type = typeSword;
        name = objName;
        down1 = setUp("Objects/new_sword_normal", gp.tileSize, gp.tileSize);

        attackValue = 1;
        attackArea.width = 36;
        attackArea.height = 36;

        description = "{" + name + "}\nAn old sword";
        price = 20;
        knockBackPower = 2;
        motion1Duration = 5;
        motion2Duration = 25;



    }
    
}

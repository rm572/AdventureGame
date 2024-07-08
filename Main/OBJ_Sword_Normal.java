public class OBJ_Sword_Normal extends Entity{

    public OBJ_Sword_Normal(GamePanel gp) {
        super(gp);
        
        type = typeSword;
        name = "Normal sword";
        down1 = setUp("Objects/new_sword_normal", gp.tileSize, gp.tileSize);

        attackValue = 1;
        attackArea.width = 36;
        attackArea.height = 36;

        description = "{" + name + "}\nAn old sword";
        price = 20;
        knockBackPower = 2;



    }
    
}

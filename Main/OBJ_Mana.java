public class OBJ_Mana extends Entity{

    GamePanel gp;
    static final String objName = "Mana";
    public OBJ_Mana(GamePanel gp) {
        
        super(gp);

        this.gp = gp;
        name = objName;

        type = typePickupOnly;
        value = 1;
        down1 = setUp("Objects/manacrystal_full", gp.tileSize, gp.tileSize);
        image = setUp("Objects/manacrystal_full", gp.tileSize, gp.tileSize);
        image2 = setUp("Objects/manacrystal_blank", gp.tileSize, gp.tileSize);
    }

    public boolean use(Entity entity) {
        gp.ui.addMessage("Mana +" + value);
        entity.mana += value;
        return true;
    }
}

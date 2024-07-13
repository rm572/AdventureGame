public class OBJ_Coin_Bronze extends Entity{
    GamePanel gp;
    static final String objName = "Bronze Coin";

    public OBJ_Coin_Bronze(GamePanel gp) {
        super(gp);
        this.gp = gp;

        type = typePickupOnly;
        name = objName;
        value = 1;
        down1 = setUp("Objects/coin_bronze", gp.tileSize, gp.tileSize);
    }

    public boolean use(Entity entity) {
        gp.ui.addMessage("Coin +" + value);
        gp.player.coin += value;
        return true;
    }
    
}

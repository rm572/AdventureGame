public class OBJ_Lantern extends Entity{
    static final String objName = "Lantern";
    

    public OBJ_Lantern(GamePanel gp) {
        super(gp);
        type = typeLight;
        name = objName;
        down1 = setUp("Objects/lantern", gp.tileSize, gp.tileSize);
        description = "{Lantern}\nIlluminates your \nsurroundings";
        price = 200;
        lightRadius = 400;
    }
    
}

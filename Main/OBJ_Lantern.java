public class OBJ_Lantern extends Entity{


    public OBJ_Lantern(GamePanel gp) {
        super(gp);
        type = typeLight;
        name = "Lantern";
        down1 = setUp("Objects/lantern", gp.tileSize, gp.tileSize);
        description = "{Lantern}\nIlluminates your \nsurroundings";
        price = 200;
        lightRadius = 400;
    }
    
}

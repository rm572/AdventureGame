public class AssetSetter {
    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject() {
        int mapNum = 0;
        int i = 0;
        gp.obj[mapNum][i] = new OBJ_Key(gp);
        gp.obj[mapNum][i].worldX = 26 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 25 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new OBJ_Axe(gp);
        gp.obj[mapNum][i].worldX = 21 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 31 * gp.tileSize;
        i++;


        gp.obj[mapNum][i] = new OBJ_Shield_Blue(gp);
        gp.obj[mapNum][i].worldX = 31 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 25 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new OBJ_Potion_Red(gp);
        gp.obj[mapNum][i].worldX = 32 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 25 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new OBJ_Coin_Bronze(gp);
        gp.obj[mapNum][i].worldX = 21 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 25 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new OBJ_Coin_Bronze(gp);
        gp.obj[mapNum][i].worldX = 20 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 25 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new OBJ_Coin_Bronze(gp);
        gp.obj[mapNum][i].worldX = 19 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 25 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new OBJ_Heart(gp);
        gp.obj[mapNum][i].worldX = 18 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 25 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new OBJ_Mana(gp);
        gp.obj[mapNum][i].worldX = 17 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 25 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new OBJ_Mana(gp);
        gp.obj[mapNum][i].worldX = 16 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 25 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new OBJ_Door(gp);
        gp.obj[mapNum][i].worldX = 11 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 16 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new OBJ_Chest(gp);
        gp.obj[mapNum][i].setLoot(new OBJ_Heart(gp));
        gp.obj[mapNum][i].worldX = 13 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 14 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new OBJ_Potion_Red(gp);
        gp.obj[mapNum][i].worldX = 15 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 25 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new OBJ_Potion_Red(gp);
        gp.obj[mapNum][i].worldX = 14 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 25 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new OBJ_Lantern(gp);
        gp.obj[mapNum][i].worldX = 13 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 25 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new OBJ_Tent(gp);
        gp.obj[mapNum][i].worldX = 13 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 24 * gp.tileSize;
        i++;

        // mapNum = 1;


    }

    public void setNPC() {
        int mapNum = 0;
        int i = 0;

        //Map 0
        gp.npc[mapNum][i] = new NPC_OldMan(gp);
        gp.npc[mapNum][i].worldX = gp.tileSize*24;
        gp.npc[mapNum][i].worldY = gp.tileSize*25;
        i++;

        

        //Map 1
        mapNum = 1;
        i = 0;
        gp.npc[mapNum][i] = new NPC_Merchant(gp);
        gp.npc[mapNum][i].worldX = gp.tileSize*12;
        gp.npc[mapNum][i].worldY = gp.tileSize*7;
        i++;
    }


    public void setMonster() {
        int mapNum = 0;
        int i = 0;
        gp.monster[mapNum][i] = new MON_GreenSlime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*25;
        gp.monster[mapNum][i].worldY = gp.tileSize*29;
        i++;

        gp.monster[mapNum][i] = new MON_GreenSlime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*26;
        gp.monster[mapNum][i].worldY = gp.tileSize*29;
        i++;

        gp.monster[mapNum][i] = new MON_GreenSlime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*26;
        gp.monster[mapNum][i].worldY = gp.tileSize*28;
        i++;

        gp.monster[mapNum][i] = new MON_GreenSlime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*25;
        gp.monster[mapNum][i].worldY = gp.tileSize*28;
        i++;

        gp.monster[mapNum][i] = new MON_Orc(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*13;
        gp.monster[mapNum][i].worldY = gp.tileSize*29;
        i++;
        // gp.monster[2] = new MON_GreenSlime(gp);
        // gp.monster[2].worldX = gp.tileSize*24;
        // gp.monster[2].worldY = gp.tileSize*29;

        // gp.monster[3] = new MON_GreenSlime(gp);
        // gp.monster[3].worldX = gp.tileSize*25;
        // gp.monster[3].worldY = gp.tileSize*28;

        // gp.monster[4] = new MON_GreenSlime(gp);
        // gp.monster[4].worldX = gp.tileSize*25;
        // gp.monster[4].worldY = gp.tileSize*27;
    }

    public void setInteractiveTile() {
        int mapNum = 0;
        int i = 0;

        gp.iTile[mapNum][i] = new IT_DryTree(gp, 9, 31);
        i++;

        gp.iTile[mapNum][i] = new IT_DryTree(gp, 10, 31);
        i++;

        gp.iTile[mapNum][i] = new IT_DryTree(gp, 11, 31);
        i++;

        gp.iTile[mapNum][i] = new IT_DryTree(gp, 12, 31);
        i++;

        gp.iTile[mapNum][i] = new IT_DryTree(gp, 13, 31);
        i++;

        gp.iTile[mapNum][i] = new IT_DryTree(gp, 14, 31);
        i++;

        gp.iTile[mapNum][i] = new IT_DryTree(gp, 15, 31);
        i++;

        gp.iTile[mapNum][i] = new IT_DryTree(gp, 16, 31);
        i++;

        gp.iTile[mapNum][i] = new IT_DryTree(gp, 17, 31);
        i++;

        gp.iTile[mapNum][i] = new IT_DryTree(gp, 18, 31);
        i++;

        gp.iTile[mapNum][i] = new IT_DryTree(gp, 19, 31);
        i++;

        gp.iTile[mapNum][i] = new IT_DryTree(gp, 20, 31);
        i++;

        // gp.iTile[mapNum][i] = new IT_DryTree(gp, 16, 24);
        // i++;



        // gp.iTile[i] = new IT_DryTree(gp, 15, 31);
        // i++;

        // gp.iTile[i] = new IT_DryTree(gp, 16, 31);
        // i++;

        // gp.iTile[i] = new IT_DryTree(gp, 17, 31);
        // i++;
    }
}

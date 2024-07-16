public class AssetSetter {
    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject() {
        int mapNum = 0;
        int i = 0;
        // gp.obj[mapNum][i] = new OBJ_Key(gp);
        // gp.obj[mapNum][i].worldX = 26 * gp.tileSize;
        // gp.obj[mapNum][i].worldY = 25 * gp.tileSize;
        // i++;

        // gp.obj[mapNum][i] = new OBJ_Axe(gp);
        // gp.obj[mapNum][i].worldX = 21 * gp.tileSize;
        // gp.obj[mapNum][i].worldY = 31 * gp.tileSize;
        // i++;


        // gp.obj[mapNum][i] = new OBJ_Shield_Blue(gp);
        // gp.obj[mapNum][i].worldX = 31 * gp.tileSize;
        // gp.obj[mapNum][i].worldY = 25 * gp.tileSize;
        // i++;

        // gp.obj[mapNum][i] = new OBJ_Potion_Red(gp);
        // gp.obj[mapNum][i].worldX = 32 * gp.tileSize;
        // gp.obj[mapNum][i].worldY = 25 * gp.tileSize;
        // i++;

        // gp.obj[mapNum][i] = new OBJ_Coin_Bronze(gp);
        // gp.obj[mapNum][i].worldX = 21 * gp.tileSize;
        // gp.obj[mapNum][i].worldY = 25 * gp.tileSize;
        // i++;

        // gp.obj[mapNum][i] = new OBJ_Coin_Bronze(gp);
        // gp.obj[mapNum][i].worldX = 20 * gp.tileSize;
        // gp.obj[mapNum][i].worldY = 25 * gp.tileSize;
        // i++;

        // gp.obj[mapNum][i] = new OBJ_Coin_Bronze(gp);
        // gp.obj[mapNum][i].worldX = 19 * gp.tileSize;
        // gp.obj[mapNum][i].worldY = 25 * gp.tileSize;
        // i++;

        // gp.obj[mapNum][i] = new OBJ_Heart(gp);
        // gp.obj[mapNum][i].worldX = 18 * gp.tileSize;
        // gp.obj[mapNum][i].worldY = 25 * gp.tileSize;
        // i++;

        // gp.obj[mapNum][i] = new OBJ_Mana(gp);
        // gp.obj[mapNum][i].worldX = 17 * gp.tileSize;
        // gp.obj[mapNum][i].worldY = 25 * gp.tileSize;
        // i++;

        // gp.obj[mapNum][i] = new OBJ_Mana(gp);
        // gp.obj[mapNum][i].worldX = 16 * gp.tileSize;
        // gp.obj[mapNum][i].worldY = 25 * gp.tileSize;
        // i++;

        // gp.obj[mapNum][i] = new OBJ_Door(gp);
        // gp.obj[mapNum][i].worldX = 11 * gp.tileSize;
        // gp.obj[mapNum][i].worldY = 16 * gp.tileSize;
        // i++;

        // gp.obj[mapNum][i] = new OBJ_Chest(gp);
        // gp.obj[mapNum][i].setLoot(new OBJ_Heart(gp));
        // gp.obj[mapNum][i].worldX = 12 * gp.tileSize;
        // gp.obj[mapNum][i].worldY = 14 * gp.tileSize;
        // i++;

        // gp.obj[mapNum][i] = new OBJ_Potion_Red(gp);
        // gp.obj[mapNum][i].worldX = 15 * gp.tileSize;
        // gp.obj[mapNum][i].worldY = 25 * gp.tileSize;
        // i++;

        // gp.obj[mapNum][i] = new OBJ_Potion_Red(gp);
        // gp.obj[mapNum][i].worldX = 14 * gp.tileSize;
        // gp.obj[mapNum][i].worldY = 25 * gp.tileSize;
        // i++;

        // gp.obj[mapNum][i] = new OBJ_Lantern(gp);
        // gp.obj[mapNum][i].worldX = 13 * gp.tileSize;
        // gp.obj[mapNum][i].worldY = 25 * gp.tileSize;
        // i++;

        // gp.obj[mapNum][i] = new OBJ_Tent(gp);
        // gp.obj[mapNum][i].worldX = 13 * gp.tileSize;
        // gp.obj[mapNum][i].worldY = 24 * gp.tileSize;
        // i++;

        // mapNum = 1;


    }

    public void setNPC() {
        int mapNum = 0;
        int i = 0;

        //Map 0
        gp.npc[mapNum][i] = new NPC_OldMan(gp);
        gp.npc[mapNum][i].worldX = gp.tileSize*17;
        gp.npc[mapNum][i].worldY = gp.tileSize*88;
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

        // gp.monster[mapNum][i] = new MON_GreenSlime(gp);
        // gp.monster[mapNum][i].worldX = gp.tileSize*25;
        // gp.monster[mapNum][i].worldY = gp.tileSize*29;
        // i++;

        // gp.monster[mapNum][i] = new MON_GreenSlime(gp);
        // gp.monster[mapNum][i].worldX = gp.tileSize*26;
        // gp.monster[mapNum][i].worldY = gp.tileSize*29;
        // i++;

        // // gp.monster[mapNum][i] = new MON_GreenSlime(gp);
        // // gp.monster[mapNum][i].worldX = gp.tileSize*26;
        // // gp.monster[mapNum][i].worldY = gp.tileSize*28;
        // // i++;

        // // gp.monster[mapNum][i] = new MON_GreenSlime(gp);
        // // gp.monster[mapNum][i].worldX = gp.tileSize*25;
        // // gp.monster[mapNum][i].worldY = gp.tileSize*28;
        // // i++;

        // gp.monster[mapNum][i] = new MON_Orc(gp);
        // gp.monster[mapNum][i].worldX = gp.tileSize*13;
        // gp.monster[mapNum][i].worldY = gp.tileSize*29;
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

        gp.iTile[mapNum][i] = new IT_DryTree(gp, 24, 58);
        i++;

        gp.iTile[mapNum][i] = new IT_DryTree(gp, 24, 59);
        i++;

        gp.iTile[mapNum][i] = new IT_DryTree(gp, 24, 60);
        i++;

        gp.iTile[mapNum][i] = new IT_DryTree(gp, 24, 61);
        i++;

        gp.iTile[mapNum][i] = new IT_DryTree(gp, 24, 62);
        i++;

        gp.iTile[mapNum][i] = new IT_DryTree(gp, 24, 63);
        i++;

        gp.iTile[mapNum][i] = new IT_DryTree(gp, 24, 64);
        i++;

        gp.iTile[mapNum][i] = new IT_DryTree(gp, 24, 65);
        i++;

        gp.iTile[mapNum][i] = new IT_DryTree(gp, 24, 66);
        i++;

        gp.iTile[mapNum][i] = new IT_DryTree(gp, 24, 67);
        i++;

        gp.iTile[mapNum][i] = new IT_DryTree(gp, 24, 68);
        i++;

        gp.iTile[mapNum][i] = new IT_DryTree(gp, 25, 63);
        i++;

        gp.iTile[mapNum][i] = new IT_DryTree(gp, 26, 63);
        i++;

        gp.iTile[mapNum][i] = new IT_DryTree(gp, 27, 63);
        i++;

        gp.iTile[mapNum][i] = new IT_DryTree(gp, 28, 63);
        i++;

        gp.iTile[mapNum][i] = new IT_DryTree(gp, 16, 11);
        i++;

        gp.iTile[mapNum][i] = new IT_DryTree(gp, 16, 12);
        i++;

        gp.iTile[mapNum][i] = new IT_DryTree(gp, 16, 13);
        i++;

        gp.iTile[mapNum][i] = new IT_DryTree(gp, 16, 14);
        i++;

        gp.iTile[mapNum][i] = new IT_DryTree(gp, 16, 15);
        i++;

        gp.iTile[mapNum][i] = new IT_DryTree(gp, 16, 16);
        i++;

        gp.iTile[mapNum][i] = new IT_DryTree(gp, 16, 17);
        i++;

        gp.iTile[mapNum][i] = new IT_DryTree(gp, 16, 18);
        i++;

        gp.iTile[mapNum][i] = new IT_DryTree(gp, 16, 19);
        i++;

        gp.iTile[mapNum][i] = new IT_DryTree(gp, 16, 20);
        i++;

        gp.iTile[mapNum][i] = new IT_DryTree(gp, 16, 21);
        i++;

        gp.iTile[mapNum][i] = new IT_DryTree(gp, 80, 14);
        i++;

        gp.iTile[mapNum][i] = new IT_DryTree(gp, 81, 14);
        i++;

        gp.iTile[mapNum][i] = new IT_DryTree(gp, 68, 33);
        i++;

        gp.iTile[mapNum][i] = new IT_DryTree(gp, 68, 34);
        i++;

        gp.iTile[mapNum][i] = new IT_DryTree(gp, 68, 35);
        i++;

        gp.iTile[mapNum][i] = new IT_DryTree(gp, 68, 36);
        i++;

        gp.iTile[mapNum][i] = new IT_DryTree(gp, 68, 37);
        i++;

        gp.iTile[mapNum][i] = new IT_DryTree(gp, 68, 38);
        i++;

        gp.iTile[mapNum][i] = new IT_DryTree(gp, 68, 39);
        i++;

        
        gp.iTile[mapNum][i] = new IT_DryTree(gp, 68, 40);
        i++;

        gp.iTile[mapNum][i] = new IT_DryTree(gp, 68, 41);
        i++;

        gp.iTile[mapNum][i] = new IT_DryTree(gp, 69, 33);
        i++;

        gp.iTile[mapNum][i] = new IT_DryTree(gp, 69, 34);
        i++;

        gp.iTile[mapNum][i] = new IT_DryTree(gp, 69, 35);
        i++;

        gp.iTile[mapNum][i] = new IT_DryTree(gp, 69, 36);
        i++;

        gp.iTile[mapNum][i] = new IT_DryTree(gp, 69, 37);
        i++;

        gp.iTile[mapNum][i] = new IT_DryTree(gp, 69, 38);
        i++;

        gp.iTile[mapNum][i] = new IT_DryTree(gp, 69, 39);
        i++;
     
        gp.iTile[mapNum][i] = new IT_DryTree(gp, 69, 40);
        i++;

        gp.iTile[mapNum][i] = new IT_DryTree(gp, 69, 41);
        i++;

        gp.iTile[mapNum][i] = new IT_DryTree(gp, 79, 52);
        i++;

        gp.iTile[mapNum][i] = new IT_DryTree(gp, 80, 52);
        i++;
     
        gp.iTile[mapNum][i] = new IT_DryTree(gp, 81, 52);
        i++;
     
        gp.iTile[mapNum][i] = new IT_DryTree(gp, 49, 53);
        i++;

        gp.iTile[mapNum][i] = new IT_DryTree(gp, 49, 54);
        i++;

        gp.iTile[mapNum][i] = new IT_DryTree(gp, 49, 55);
        i++;

        gp.iTile[mapNum][i] = new IT_DryTree(gp, 49, 56);
        i++;

        gp.iTile[mapNum][i] = new IT_DryTree(gp, 49, 57);
        i++;
     
        gp.iTile[mapNum][i] = new IT_DryTree(gp, 49, 58);
        i++;

        gp.iTile[mapNum][i] = new IT_DryTree(gp, 49, 59);
        i++;

        gp.iTile[mapNum][i] = new IT_DryTree(gp, 49, 60);
        i++;

        gp.iTile[mapNum][i] = new IT_DryTree(gp, 49, 72);
        i++;

        gp.iTile[mapNum][i] = new IT_DryTree(gp, 57, 78);
        i++;

        gp.iTile[mapNum][i] = new IT_DryTree(gp, 54, 72);
        i++;

        gp.iTile[mapNum][i] = new IT_DryTree(gp, 55, 72);
        i++;

        gp.iTile[mapNum][i] = new IT_DryTree(gp, 43, 77);
        i++;

        gp.iTile[mapNum][i] = new IT_DryTree(gp, 43, 78);
        i++;

        gp.iTile[mapNum][i] = new IT_DryTree(gp, 43, 79);
        i++;

        gp.iTile[mapNum][i] = new IT_DryTree(gp, 43, 80);
        i++;

        gp.iTile[mapNum][i] = new IT_DryTree(gp, 43, 81);
        i++;

        gp.iTile[mapNum][i] = new IT_DryTree(gp, 44, 81);
        i++;

        gp.iTile[mapNum][i] = new IT_DryTree(gp, 44, 82);
        i++;

        gp.iTile[mapNum][i] = new IT_DryTree(gp, 41, 87);
        i++;

        gp.iTile[mapNum][i] = new IT_DryTree(gp, 32, 70);
        i++;

        gp.iTile[mapNum][i] = new IT_DryTree(gp, 33, 70);
        i++;

        gp.iTile[mapNum][i] = new IT_DryTree(gp, 45, 73);
        i++;

        gp.iTile[mapNum][i] = new IT_DryTree(gp, 46, 73);
        i++;

        gp.iTile[mapNum][i] = new IT_DryTree(gp, 26, 26);
        i++;

        gp.iTile[mapNum][i] = new IT_DryTree(gp, 27, 26);
        i++;

        // gp.iTile[mapNum][i] = new IT_DryTree(gp, 84, 80);
        // i++;

        // gp.iTile[mapNum][i] = new IT_DryTree(gp, 84, 81);
        // i++;
        
    }
}

public class AssetSetter {
    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject() {
        int mapNum = 0;
        int i = 0;

        // gp.obj[mapNum][i] = new OBJ_Chest(gp);
        // gp.obj[mapNum][i].setLoot(new OBJ_Axe(gp));
        // gp.obj[mapNum][i].worldX = 15 * gp.tileSize;
        // gp.obj[mapNum][i].worldY = 86 * gp.tileSize;
        // i++;


        //Chests
        gp.obj[mapNum][i] = new OBJ_Chest(gp);
        gp.obj[mapNum][i].setLoot(new OBJ_Lantern(gp));
        gp.obj[mapNum][i].worldX = 15 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 86 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new OBJ_Chest(gp);
        gp.obj[mapNum][i].setLoot(new OBJ_Tent(gp));
        gp.obj[mapNum][i].worldX = 20 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 91 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new OBJ_Chest(gp);
        gp.obj[mapNum][i].setLoot(new OBJ_Axe(gp));
        gp.obj[mapNum][i].worldX = 35 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 40 * gp.tileSize;
        i++;
        
        gp.obj[mapNum][i] = new OBJ_Chest(gp);
        gp.obj[mapNum][i].setLoot(new OBJ_Potion_Red(gp));
        gp.obj[mapNum][i].worldX = 80 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 71 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new OBJ_Chest(gp);
        gp.obj[mapNum][i].setLoot(new OBJ_Potion_Blue(gp));
        gp.obj[mapNum][i].worldX = 49 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 49 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new OBJ_Chest(gp);
        gp.obj[mapNum][i].setLoot(new OBJ_Potion_Red(gp));
        gp.obj[mapNum][i].worldX = 79 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 66 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new OBJ_Chest(gp);
        gp.obj[mapNum][i].setLoot(new OBJ_Potion_Red(gp));
        gp.obj[mapNum][i].worldX = 32 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 75 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new OBJ_Chest(gp);
        gp.obj[mapNum][i].setLoot(new OBJ_Lantern(gp));
        gp.obj[mapNum][i].worldX = 49 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 84 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new OBJ_Chest(gp);
        gp.obj[mapNum][i].setLoot(new OBJ_Tent(gp));
        gp.obj[mapNum][i].worldX = 54 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 78 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new OBJ_Chest(gp);
        gp.obj[mapNum][i].setLoot(new OBJ_Potion_Red(gp));
        gp.obj[mapNum][i].worldX = 57 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 63 * gp.tileSize;
        i++;

        //Keys
        gp.obj[mapNum][i] = new OBJ_Chest(gp);
        gp.obj[mapNum][i].setLoot(new OBJ_Key(gp));
        gp.obj[mapNum][i].worldX = 49 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 49 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new OBJ_Chest(gp);
        gp.obj[mapNum][i].setLoot(new OBJ_Key(gp));
        gp.obj[mapNum][i].worldX = 33 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 75 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new OBJ_Chest(gp);
        gp.obj[mapNum][i].setLoot(new OBJ_Key(gp));
        gp.obj[mapNum][i].worldX = 44 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 83 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new OBJ_Chest(gp);
        gp.obj[mapNum][i].setLoot(new OBJ_Key(gp));
        gp.obj[mapNum][i].worldX = 81 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 71 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new OBJ_Chest(gp);
        gp.obj[mapNum][i].setLoot(new OBJ_Key(gp));
        gp.obj[mapNum][i].worldX = 56 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 12 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new OBJ_Chest(gp);
        gp.obj[mapNum][i].setLoot(new OBJ_Key(gp));
        gp.obj[mapNum][i].worldX = 16 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 23 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new OBJ_Chest(gp);
        gp.obj[mapNum][i].setLoot(new OBJ_Key(gp));
        gp.obj[mapNum][i].worldX = 49 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 78 * gp.tileSize;
        i++;


        //Doors
        gp.obj[mapNum][i] = new OBJ_Door(gp);
        gp.obj[mapNum][i].worldX = 54 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 85 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new OBJ_Door(gp);
        gp.obj[mapNum][i].worldX = 35 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 43 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new OBJ_Door(gp);
        gp.obj[mapNum][i].worldX = 78 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 74 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new OBJ_Door(gp);
        gp.obj[mapNum][i].worldX = 49 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 61 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new OBJ_Door(gp);
        gp.obj[mapNum][i].worldX = 84 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 80 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new OBJ_Door(gp);
        gp.obj[mapNum][i].worldX = 65 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 82 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new OBJ_Door(gp);
        gp.obj[mapNum][i].worldX = 65 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 85 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new OBJ_Door(gp);
        gp.obj[mapNum][i].worldX = 75 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 87 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new OBJ_Door(gp);
        gp.obj[mapNum][i].worldX = 84 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 11 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new OBJ_Door(gp);
        gp.obj[mapNum][i].worldX = 49 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 72 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new OBJ_Door(gp);
        gp.obj[mapNum][i].worldX = 84 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 82 * gp.tileSize;
        i++;
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
        
        gp.monster[mapNum][i] = new MON_GreenSlime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*43;
        gp.monster[mapNum][i].worldY = gp.tileSize*89;
        i++;

        gp.monster[mapNum][i] = new MON_GreenSlime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*46;
        gp.monster[mapNum][i].worldY = gp.tileSize*90;
        i++;

        gp.monster[mapNum][i] = new MON_GreenSlime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*50;
        gp.monster[mapNum][i].worldY = gp.tileSize*88;
        i++;

        gp.monster[mapNum][i] = new MON_GreenSlime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*54;
        gp.monster[mapNum][i].worldY = gp.tileSize*87;
        i++;

        gp.monster[mapNum][i] = new MON_GreenSlime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*37;
        gp.monster[mapNum][i].worldY = gp.tileSize*78;
        i++;

        gp.monster[mapNum][i] = new MON_GreenSlime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*39;
        gp.monster[mapNum][i].worldY = gp.tileSize*73;
        i++;

        gp.monster[mapNum][i] = new MON_GreenSlime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*46;
        gp.monster[mapNum][i].worldY = gp.tileSize*75;
        i++;

        gp.monster[mapNum][i] = new MON_GreenSlime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*43;
        gp.monster[mapNum][i].worldY = gp.tileSize*76;
        i++;

        gp.monster[mapNum][i] = new MON_GreenSlime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*41;
        gp.monster[mapNum][i].worldY = gp.tileSize*74;
        i++;

        gp.monster[mapNum][i] = new MON_GreenSlime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*37;
        gp.monster[mapNum][i].worldY = gp.tileSize*69;
        i++;

        gp.monster[mapNum][i] = new MON_GreenSlime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*45;
        gp.monster[mapNum][i].worldY = gp.tileSize*65;
        i++;

        gp.monster[mapNum][i] = new MON_GreenSlime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*42;
        gp.monster[mapNum][i].worldY = gp.tileSize*63;
        i++;

        gp.monster[mapNum][i] = new MON_Orc(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*34;
        gp.monster[mapNum][i].worldY = gp.tileSize*63;
        i++;

        gp.monster[mapNum][i] = new MON_GreenSlime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*15;
        gp.monster[mapNum][i].worldY = gp.tileSize*71;
        i++;

        gp.monster[mapNum][i] = new MON_GreenSlime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*16;
        gp.monster[mapNum][i].worldY = gp.tileSize*67;
        i++;

        gp.monster[mapNum][i] = new MON_Orc(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*15;
        gp.monster[mapNum][i].worldY = gp.tileSize*60;
        i++;

        gp.monster[mapNum][i] = new MON_GreenSlime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*15;
        gp.monster[mapNum][i].worldY = gp.tileSize*56;
        i++;

        gp.monster[mapNum][i] = new MON_GreenSlime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*15;
        gp.monster[mapNum][i].worldY = gp.tileSize*47;
        i++;

        gp.monster[mapNum][i] = new MON_Orc(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*19;
        gp.monster[mapNum][i].worldY = gp.tileSize*47;
        i++;

        gp.monster[mapNum][i] = new MON_Orc(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*18;
        gp.monster[mapNum][i].worldY = gp.tileSize*42;
        i++;

        gp.monster[mapNum][i] = new MON_GreenSlime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*19;
        gp.monster[mapNum][i].worldY = gp.tileSize*33;
        i++;

        gp.monster[mapNum][i] = new MON_Orc(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*19;
        gp.monster[mapNum][i].worldY = gp.tileSize*24;
        i++;


        gp.monster[mapNum][i] = new MON_GreenSlime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*24;
        gp.monster[mapNum][i].worldY = gp.tileSize*56;
        i++;

        gp.monster[mapNum][i] = new MON_GreenSlime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*30;
        gp.monster[mapNum][i].worldY = gp.tileSize*57;
        i++;

        gp.monster[mapNum][i] = new MON_Orc(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*35;
        gp.monster[mapNum][i].worldY = gp.tileSize*54;
        i++;

        gp.monster[mapNum][i] = new MON_Orc(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*34;
        gp.monster[mapNum][i].worldY = gp.tileSize*47;
        i++;

        gp.monster[mapNum][i] = new MON_Spider(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*27;
        gp.monster[mapNum][i].worldY = gp.tileSize*24;
        i++;

        gp.monster[mapNum][i] = new MON_GreenSlime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*33;
        gp.monster[mapNum][i].worldY = gp.tileSize*25;
        i++;

        gp.monster[mapNum][i] = new MON_Spider(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*43;
        gp.monster[mapNum][i].worldY = gp.tileSize*24;
        i++;

        gp.monster[mapNum][i] = new MON_Orc(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*53;
        gp.monster[mapNum][i].worldY = gp.tileSize*23;
        i++;

        gp.monster[mapNum][i] = new MON_GreenSlime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*29;
        gp.monster[mapNum][i].worldY = gp.tileSize*28;
        i++;

        gp.monster[mapNum][i] = new MON_Spider(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*22;
        gp.monster[mapNum][i].worldY = gp.tileSize*30;
        i++;

        gp.monster[mapNum][i] = new MON_GreenSlime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*24;
        gp.monster[mapNum][i].worldY = gp.tileSize*40;
        i++;

        gp.monster[mapNum][i] = new MON_GreenSlime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*25;
        gp.monster[mapNum][i].worldY = gp.tileSize*47;
        i++;

        gp.monster[mapNum][i] = new MON_Orc(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*25;
        gp.monster[mapNum][i].worldY = gp.tileSize*51;
        i++;

        gp.monster[mapNum][i] = new MON_Orc(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*31;
        gp.monster[mapNum][i].worldY = gp.tileSize*51;
        i++;

        gp.monster[mapNum][i] = new MON_Spider(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*31;
        gp.monster[mapNum][i].worldY = gp.tileSize*40;
        i++;

        gp.monster[mapNum][i] = new MON_GreenSlime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*46;
        gp.monster[mapNum][i].worldY = gp.tileSize*22;
        i++;

        gp.monster[mapNum][i] = new MON_Orc(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*40;
        gp.monster[mapNum][i].worldY = gp.tileSize*24;
        i++;

        gp.monster[mapNum][i] = new MON_Spider(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*49;
        gp.monster[mapNum][i].worldY = gp.tileSize*23;
        i++;

        gp.monster[mapNum][i] = new MON_Spider(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*48;
        gp.monster[mapNum][i].worldY = gp.tileSize*23;
        i++;

        gp.monster[mapNum][i] = new MON_Spider(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*49;
        gp.monster[mapNum][i].worldY = gp.tileSize*24;
        i++;

        gp.monster[mapNum][i] = new MON_GreenSlime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*26;
        gp.monster[mapNum][i].worldY = gp.tileSize*14;
        i++;

        gp.monster[mapNum][i] = new MON_GreenSlime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*26;
        gp.monster[mapNum][i].worldY = gp.tileSize*12;
        i++;

        gp.monster[mapNum][i] = new MON_Orc(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*16;
        gp.monster[mapNum][i].worldY = gp.tileSize*10;
        i++;

        gp.monster[mapNum][i] = new MON_GreenSlime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*19;
        gp.monster[mapNum][i].worldY = gp.tileSize*9;
        i++;

        gp.monster[mapNum][i] = new MON_Spider(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*15;
        gp.monster[mapNum][i].worldY = gp.tileSize*8;
        i++;

        gp.monster[mapNum][i] = new MON_Spider(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*18;
        gp.monster[mapNum][i].worldY = gp.tileSize*8;
        i++;

        gp.monster[mapNum][i] = new MON_Spider(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*29;
        gp.monster[mapNum][i].worldY = gp.tileSize*16;
        i++;

        gp.monster[mapNum][i] = new MON_GreenSlime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*31;
        gp.monster[mapNum][i].worldY = gp.tileSize*16;
        i++;

        gp.monster[mapNum][i] = new MON_GreenSlime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*34;
        gp.monster[mapNum][i].worldY = gp.tileSize*17;
        i++;

        gp.monster[mapNum][i] = new MON_GreenSlime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*37;
        gp.monster[mapNum][i].worldY = gp.tileSize*16;
        i++;

        gp.monster[mapNum][i] = new MON_Orc(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*39;
        gp.monster[mapNum][i].worldY = gp.tileSize*15;
        i++;

        gp.monster[mapNum][i] = new MON_Orc(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*41;
        gp.monster[mapNum][i].worldY = gp.tileSize*16;
        i++;

        gp.monster[mapNum][i] = new MON_Spider(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*45;
        gp.monster[mapNum][i].worldY = gp.tileSize*16;
        i++;

        gp.monster[mapNum][i] = new MON_Spider(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*46;
        gp.monster[mapNum][i].worldY = gp.tileSize*16;
        i++;

        gp.monster[mapNum][i] = new MON_Spider(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*45;
        gp.monster[mapNum][i].worldY = gp.tileSize*15;
        i++;

        gp.monster[mapNum][i] = new MON_Spider(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*45;
        gp.monster[mapNum][i].worldY = gp.tileSize*17;
        i++;

        gp.monster[mapNum][i] = new MON_Spider(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*50;
        gp.monster[mapNum][i].worldY = gp.tileSize*16;
        i++;

        gp.monster[mapNum][i] = new MON_GreenSlime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*54;
        gp.monster[mapNum][i].worldY = gp.tileSize*16;
        i++;

        gp.monster[mapNum][i] = new MON_GreenSlime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*55;
        gp.monster[mapNum][i].worldY = gp.tileSize*17;
        i++;

        gp.monster[mapNum][i] = new MON_Orc(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*60;
        gp.monster[mapNum][i].worldY = gp.tileSize*15;
        i++;

        gp.monster[mapNum][i] = new MON_GreenSlime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*64;
        gp.monster[mapNum][i].worldY = gp.tileSize*16;
        i++;

        gp.monster[mapNum][i] = new MON_Orc(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*67;
        gp.monster[mapNum][i].worldY = gp.tileSize*15;
        i++;

        gp.monster[mapNum][i] = new MON_Orc(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*71;
        gp.monster[mapNum][i].worldY = gp.tileSize*17;
        i++;

        gp.monster[mapNum][i] = new MON_Spider(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*80;
        gp.monster[mapNum][i].worldY = gp.tileSize*16;
        i++;

        gp.monster[mapNum][i] = new MON_Spider(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*81;
        gp.monster[mapNum][i].worldY = gp.tileSize*16;
        i++;

        gp.monster[mapNum][i] = new MON_Spider(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*81;
        gp.monster[mapNum][i].worldY = gp.tileSize*15;
        i++;

        gp.monster[mapNum][i] = new MON_Spider(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*81;
        gp.monster[mapNum][i].worldY = gp.tileSize*17;
        i++;

        gp.monster[mapNum][i] = new MON_GreenSlime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*77;
        gp.monster[mapNum][i].worldY = gp.tileSize*12;
        i++;

        gp.monster[mapNum][i] = new MON_GreenSlime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*76;
        gp.monster[mapNum][i].worldY = gp.tileSize*13;
        i++;

        gp.monster[mapNum][i] = new MON_GreenSlime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*75;
        gp.monster[mapNum][i].worldY = gp.tileSize*13;
        i++;

        // gp.monster[mapNum][i] = new MON_GreenSlime(gp);
        // gp.monster[mapNum][i].worldX = gp.tileSize*75;
        // gp.monster[mapNum][i].worldY = gp.tileSize*12;
        // i++;

        gp.monster[mapNum][i] = new MON_GreenSlime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*74;
        gp.monster[mapNum][i].worldY = gp.tileSize*12;
        i++;

        // gp.monster[mapNum][i] = new MON_GreenSlime(gp);
        // gp.monster[mapNum][i].worldX = gp.tileSize*71;
        // gp.monster[mapNum][i].worldY = gp.tileSize*13;
        // i++;

        // gp.monster[mapNum][i] = new MON_GreenSlime(gp);
        // gp.monster[mapNum][i].worldX = gp.tileSize*70;
        // gp.monster[mapNum][i].worldY = gp.tileSize*12;
        // i++;

        gp.monster[mapNum][i] = new MON_GreenSlime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*69;
        gp.monster[mapNum][i].worldY = gp.tileSize*12;
        i++;

        // gp.monster[mapNum][i] = new MON_GreenSlime(gp);
        // gp.monster[mapNum][i].worldX = gp.tileSize*69;
        // gp.monster[mapNum][i].worldY = gp.tileSize*13;
        // i++;

        gp.monster[mapNum][i] = new MON_GreenSlime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*68;
        gp.monster[mapNum][i].worldY = gp.tileSize*13;
        i++;

        gp.monster[mapNum][i] = new MON_GreenSlime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*64;
        gp.monster[mapNum][i].worldY = gp.tileSize*12;
        i++;

        // gp.monster[mapNum][i] = new MON_GreenSlime(gp);
        // gp.monster[mapNum][i].worldX = gp.tileSize*62;
        // gp.monster[mapNum][i].worldY = gp.tileSize*13;
        // i++;

        gp.monster[mapNum][i] = new MON_GreenSlime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*58;
        gp.monster[mapNum][i].worldY = gp.tileSize*12;
        i++;

        // gp.monster[mapNum][i] = new MON_GreenSlime(gp);
        // gp.monster[mapNum][i].worldX = gp.tileSize*58;
        // gp.monster[mapNum][i].worldY = gp.tileSize*13;
        // i++;

        // gp.monster[mapNum][i] = new MON_Spider_Red(gp);
        // gp.monster[mapNum][i].worldX = gp.tileSize*78;
        // gp.monster[mapNum][i].worldY = gp.tileSize*9;
        // i++;
        
    }

    public void setHealingTile() {
        int mapNum = 0;
        int i = 0;

        gp.healTile[mapNum][i] = new HealingTile(gp, 84, 8);
        i++;

        gp.healTile[mapNum][i] = new HealingTile(gp, 84, 66);
        i++;
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

        // gp.iTile[mapNum][i] = new IT_DryTree(gp, 49, 72);
        // i++;

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

        gp.iTile[mapNum][i] = new IT_DryTree(gp, 27, 12);
        i++;

        gp.iTile[mapNum][i] = new IT_DryTree(gp, 27, 13);
        i++;

        gp.iTile[mapNum][i] = new IT_DryTree(gp, 27, 14);
        i++;

        // gp.iTile[mapNum][i] = new IT_DryTree(gp, 84, 80);
        // i++;

        // gp.iTile[mapNum][i] = new IT_DryTree(gp, 84, 81);
        // i++;
        
    }
}

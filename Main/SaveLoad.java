import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.File;
import java.io.FileInputStream;

public class SaveLoad {
    GamePanel gp;

    public SaveLoad(GamePanel gp) {
        this.gp = gp;
    }

    public Entity getObject(String itemName) {
        Entity obj = null;

        switch (itemName) {
            case "Woodcutter's Axe": obj = new OBJ_Axe(gp); break;
            case "Boots": obj = new OBJ_Boots(gp); break;
            case "Key": obj = new OBJ_Key(gp); break;
            case "Lantern": obj = new OBJ_Lantern(gp); break;
            case "Red Potion": obj = new OBJ_Potion_Red(gp); break;
            case "Blue Shield": obj = new OBJ_Shield_Blue(gp); break;
            case "Wood Shield": obj = new OBJ_Shield_Wood(gp); break;
            case "Tent": obj = new OBJ_Tent(gp); break;
            case "Door": obj = new OBJ_Door(gp); break;
            case "Chest": obj = new OBJ_Chest(gp); break;
            case "Normal sword": obj = new OBJ_Sword_Normal(gp); break;
            case "Heart": obj = new OBJ_Heart(gp); break;
        }

        return obj;
    }

    public void save() {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("C:/Users/rober/OneDrive/Programming/AdventureGame/data/save.dat")));
        
            DataStorage ds = new DataStorage();
            ds.level = gp.player.level;
            ds.maxLife = gp.player.maxLife;
            ds.life = gp.player.life;
            ds.maxMana = gp.player.maxMana;
            ds.mana = gp.player.mana;
            ds.strength = gp.player.strength;
            ds.dexterity = gp.player.dexterity;
            ds.exp = gp.player.exp;
            ds.nextLevelExp = gp.player.nextLevelExp;
            ds.coin = gp.player.coin;

            for (int i = 0; i < gp.player.inventory.size(); i++) {
                ds.itemNames.add(gp.player.inventory.get(i).name);
                ds.itemAmount.add(gp.player.inventory.get(i).amount);
            }

            ds.currentWeaponSlot = gp.player.getCurrentWeaponSlot();
            ds.currentShieldSlot = gp.player.getCurrentShieldSlot();

            ds.mapObjectNames = new String[gp.maxMap][gp.obj[1].length];
            ds.mapObjectWorldX = new int[gp.maxMap][gp.obj[1].length];
            ds.mapObjectWorldY = new int[gp.maxMap][gp.obj[1].length];
            ds.mapObjectLootName = new String[gp.maxMap][gp.obj[1].length];
            ds.mapObjectOpened = new boolean[gp.maxMap][gp.obj[1].length];

            for (int mapNum = 0; mapNum < gp.maxMap; mapNum++) {
                for (int i = 0; i < gp.obj[1].length; i++) {
                    if (gp.obj[mapNum][i] == null) {
                        ds.mapObjectNames[mapNum][i] = "NA";

                    }
                    else {
                        ds.mapObjectNames[mapNum][i] = gp.obj[mapNum][i].name;
                        ds.mapObjectWorldX[mapNum][i] = gp.obj[mapNum][i].worldX;
                        ds.mapObjectWorldY[mapNum][i] = gp.obj[mapNum][i].worldY;
                        if (gp.obj[mapNum][i].loot != null) {
                            ds.mapObjectLootName[mapNum][i] = gp.obj[mapNum][i].loot.name;
                        }
                        ds.mapObjectOpened[mapNum][i] = gp.obj[mapNum][i].opened;
                        
                    }
                }
            }

            oos.writeObject(ds);

            oos.close();
        
        } catch (Exception e) {
            System.out.println("Save Exception!");
        }
    }
    public void load() {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("C:/Users/rober/OneDrive/Programming/AdventureGame/data/save.dat")));

            DataStorage ds = (DataStorage)ois.readObject();

            gp.player.level = ds.level;
            gp.player.maxLife = ds.maxLife;
            gp.player.life = ds.life;
            gp.player.maxMana = ds.maxMana;
            gp.player.mana = ds.mana;
            gp.player.strength = ds.strength;
            gp.player.dexterity = ds.dexterity;
            gp.player.exp = ds.exp;
            gp.player.nextLevelExp = ds.nextLevelExp;
            gp.player.coin = ds.coin;

            // System.out.println("1");
            gp.player.inventory.clear();
            for (int i = 0; i < ds.itemNames.size(); i++) {
                gp.player.inventory.add(getObject(ds.itemNames.get(i)));
                // System.out.println("Name: " + getObject(ds.itemNames.get(i)));
                gp.player.inventory.get(i).amount = ds.itemAmount.get(i);
                // System.out.println("Amount: " + ds.itemAmount.get(i));
            }


            // System.out.println("2");
            gp.player.currentWeapon = gp.player.inventory.get(ds.currentWeaponSlot);
            gp.player.currentShield = gp.player.inventory.get(ds.currentShieldSlot);
            gp.player.getAttack();
            gp.player.getDefense();
            gp.player.getPlayerAttackImage();


            // System.out.println("3");
            for (int mapNum = 0; mapNum < gp.maxMap; mapNum++) {
                for (int i = 0; i < gp.obj[1].length; i++) {
                    // System.out.println("The index is: " + i);
                    // System.out.println("The item's name is: " + ds.mapObjectNames[mapNum][i]);
                    if (ds.mapObjectNames[mapNum][i].equals("NA")) {
                        gp.obj[mapNum][i] = null;
                    }
                    else {
                        gp.obj[mapNum][i] = getObject(ds.mapObjectNames[mapNum][i]);
                        gp.obj[mapNum][i].worldX = ds.mapObjectWorldX[mapNum][i];
                        gp.obj[mapNum][i].worldY = ds.mapObjectWorldY[mapNum][i];
                        if (ds.mapObjectLootName[mapNum][i] != null) {
                            gp.obj[mapNum][i].loot = getObject(ds.mapObjectLootName[mapNum][i]);

                        }
                        gp.obj[mapNum][i].opened = ds.mapObjectOpened[mapNum][i];

                        if (gp.obj[mapNum][i].opened) {
                            gp.obj[mapNum][i].down1 = gp.obj[mapNum][i].image2;
                        }
                        
                    }
                }
            }

            ois.close();
            
        }
        catch (Exception e) {
            System.out.println("Load Exception!");
        }
    }
}

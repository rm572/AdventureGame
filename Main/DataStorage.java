import java.io.Serializable;
import java.util.ArrayList;

public class DataStorage implements Serializable{
    //Player stats
    int level;
    int maxLife;
    int life;
    int mana;
    int maxMana;
    int strength;
    int dexterity;
    int exp;
    int nextLevelExp;
    int coin;

    ArrayList<String> itemNames = new ArrayList<>();
    ArrayList<Integer> itemAmount = new ArrayList<>();

    int currentWeaponSlot;
    int currentShieldSlot;

    String mapObjectNames[][];
    int mapObjectWorldX[][];
    int mapObjectWorldY[][];
    String mapObjectLootName[][];
    boolean mapObjectOpened[][];
}

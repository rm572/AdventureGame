import java.awt.Rectangle;

public class EventHandler {
    GamePanel gp;
    EventRect eventRect[][][];


    boolean disruptedMovement = false;
    boolean teleport = false;
    int previousEventX, previousEventY;
    boolean canTouchEvent = true;
    boolean eventHappened = false;
    int tempMap, tempCol, tempRow;

    public EventHandler(GamePanel gp) {
        this.gp = gp;
        eventRect = new EventRect[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];
        int map = 0;

        int col = 0;
        int row = 0;
        while (map < gp.maxMap && col < gp.maxWorldCol && row < gp.maxWorldRow) {
            eventRect[map][col][row] = new EventRect();
            eventRect[map][col][row].x = gp.tileSize/2;
            eventRect[map][col][row].y = gp.tileSize/2;
            eventRect[map][col][row].width = 2;
            eventRect[map][col][row].height = 2;
            eventRect[map][col][row].eventRectDefaultX = eventRect[map][col][row].x;
            eventRect[map][col][row].eventRectDefaultY = eventRect[map][col][row].y;

            col++;
            if (col == gp.maxWorldCol) {
                col = 0;
                row++;

                if (row == gp.maxWorldRow) {
                    row = 0;
                    map++;
                }
            }
        }
    }

    public void checkEvent() {
        int xDistance = Math.abs(gp.player.worldX - previousEventX);
        int yDistance = Math.abs(gp.player.worldY - previousEventY);
        int distance = Math.max(xDistance, yDistance);
        if (distance > gp.tileSize) {
            canTouchEvent = true;
        }
        if (canTouchEvent) {
            if (hit(0, 28, 25, "right")) {damagePit(gp.dialogueState);}
            else if (hit(0, 16, 23, "any")) {teleport(1, 12, 12);}
            else if (hit(1, 12, 14, "any")) {teleport(0, 16, 23);}
            else if (hit(0, 13, 25, "left")) {healingPool(gp.dialogueState);}
            else if (hit(1, 12, 9, "up")) {speak(gp.npc[1][0]);}
            
        }
        
        // if (hit(0, 25, 23, "any")) {teleport(25, 23, 36, 39, gp.dialogueState);}
        // if (hit(0, 30, 39, "any")) {teleport(1,30, 39, 25, 25, gp.dialogueState);}
        // if (hit(1, 30, 39, "any")) {teleport(30, 39, 25, 25, gp.dialogueState);}
   

    }

    //After
    public boolean hit(int map, int col, int row, String reqDirection) {

        boolean hit = false;
        if (map == gp.currentMap) {
            gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
            gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
            eventRect[map][col][row].x = col*gp.tileSize + eventRect[map][col][row].x;
            eventRect[map][col][row].y = row*gp.tileSize + eventRect[map][col][row].y;
    
            if (gp.player.solidArea.intersects(eventRect[map][col][row]) && eventRect[map][col][row].eventDone == false) {
                if (gp.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")) {
                    hit = true;
                    eventHappened = true;
                    previousEventX = gp.player.worldX;
                    previousEventY = gp.player.worldY;
                }
            }
    
    
            gp.player.solidArea.x = gp.player.solidAreaDefaultX;
            gp.player.solidArea.y = gp.player.solidAreaDefaultY;
            eventRect[map][col][row].x = eventRect[map][col][row].eventRectDefaultX;
            eventRect[map][col][row].y = eventRect[map][col][row].eventRectDefaultY;
        }




        return hit;
    }

    public void damagePit( int gameState) {
        gp.gameState = gameState;
        gp.ui.currentDialogue = "You fall into a pit!";
        gp.player.life -= 1;
        disruptedMovement = true;

        //Pit doesn't work anymore
        // eventRect[col][row].eventDone = true;

        //Pit always works

        //Pit works when you move a tile away
        canTouchEvent = false;


        
    }

    public void healingPool(int gameState) {

        if (gp.keyH.enterPressed == true) {
            gp.gameState = gameState;
            // gp.player.attackCancel = true;
            gp.ui.currentDialogue = "You drink the water.\nYour life and mana have been \nrecovered.";
            gp.player.life = gp.player.maxLife;
            gp.player.mana = gp.player.maxMana;

            gp.aSetter.setMonster();
        }
    }
    /*
    public void teleport(int hitCol, int hitRow, int toCol, int toRow, int gameState) {
        gp.gameState = gameState;
        gp.ui.currentDialogue = "Teleport2.";
        gp.player.worldX = gp.tileSize*toCol;
        gp.player.worldY = gp.tileSize*toRow;
        disruptedMovement = true;
        teleport = true;

    }
    */

    public void teleport(int map, int col, int row) {
        gp.gameState = gp.transitionState;
        tempMap = map;
        tempCol = col;
        tempRow = row;
        // gp.currentMap = map;
        // gp.player.worldX = gp.tileSize * col;
        // gp.player.worldY = gp.tileSize * row + gp.player.displacement;
        // previousEventX = gp.player.worldX;
        // previousEventY = gp.player.worldY;
        canTouchEvent = false;
    }

    public void speak(Entity entity) {
        if (gp.keyH.enterPressed) {
            gp.gameState = gp.dialogueState;
            gp.player.attacking = false;
            entity.speak();

        }
        
    }
}

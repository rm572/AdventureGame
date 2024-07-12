import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.Buffer;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class UI {
    GamePanel gp;
    Font calisto_mt, consolas, colonna, agency, pixelify, pixel2;
    // BufferedImage keyImage;
    BufferedImage heart_full, heart_half, heart_blank, crystal_full, crystal_blank, coin;
    Graphics2D g2;
    public boolean messageOn = false;
    // public String message = "";
    // int messageCounter = 0;
    ArrayList<String> message = new ArrayList<>();
    ArrayList<Integer> messageCounter = new ArrayList<>();
    boolean gameFinished = false;
    String currentDialogue = "";

    public int commandNum = 0;
    public int playerSlotCol = 0;
    public int playerSlotRow = 0;

    public int npcSlotCol = 0;
    public int npcSlotRow = 0;
    int counter = 0;
    Entity npc;

    int substate = 0;

    int titleScreenState = 0; //0: First screen, 1: second screen

    // double playTime;
    // DecimalFormat dFormat = new DecimalFormat("#0.00");

    public UI(GamePanel gp) {
        this.gp = gp;
        
        try {
            // InputStream is = getClass().getResourceAsStream("C:/Users/rober/OneDrive/Programming/Workspace/Workspace/2DGame_Example/res/Font/CALIST.TTF");
            calisto_mt = Font.createFont(Font.TRUETYPE_FONT, new File("C:/Users/rober/OneDrive/Programming/Workspace/Workspace/2DGame_Example/res/Font/CALIST.TTF"));
            // is = getClass().getResourceAsStream("C:/Users/rober/OneDrive/Programming/Workspace/Workspace/2DGame_Example/res/Font/consola.ttf");
            consolas = Font.createFont(Font.TRUETYPE_FONT, new File("C:/Users/rober/OneDrive/Programming/Workspace/Workspace/2DGame_Example/res/Font/consola.ttf"));
            // dosapp = Font.createFont(Font., new File("C:/Users/rober/OneDrive/Programming/Workspace/Workspace/2DGame_Example/res/Font/dosapp.fon"));
            // vgaoem = Font.createFont(Font.TRUETYPE_FONT, new File("C:/Users/rober/OneDrive/Programming/Workspace/Workspace/2DGame_Example/res/Font/vgaoem.fon"));
            colonna = Font.createFont(Font.TRUETYPE_FONT, new File("C:/Users/rober/OneDrive/Programming/Workspace/Workspace/2DGame_Example/res/Font/COLONNA.TTF"));
            agency = Font.createFont(Font.TRUETYPE_FONT, new File("C:/Users/rober/OneDrive/Programming/Workspace/Workspace/2DGame_Example/res/Font/AGENCYR.TTF"));
            pixelify = Font.createFont(Font.TRUETYPE_FONT, new File("C:/Users/rober/OneDrive/Programming/Workspace/Workspace/2DGame_Example/res/Font/PixelifySans-Regular.TTF"));
            pixel2 = Font.createFont(Font.TRUETYPE_FONT, new File("C:/Users/rober/OneDrive/Programming/Workspace/Workspace/2DGame_Example/res/Font/pixel font-7.TTF"));
            

        
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        Entity heart = new OBJ_Heart(gp);
        heart_full = heart.image;
        heart_half = heart.image2;
        heart_blank = heart.image3;

        Entity crystal = new OBJ_Mana(gp);
        crystal_full = crystal.image;
        crystal_blank = crystal.image2;

        Entity bronzeCoin = new OBJ_Coin_Bronze(gp);
        coin = bronzeCoin.down1;
    }

    public void addMessage(String text) {
        // message = text;
        // messageOn = true;

        message.add(text);
        messageCounter.add(0);
    }

    public void draw(Graphics2D g2) {

        this.g2 = g2;
        // g2.setFont(consolas);
        g2.setFont(pixel2);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.setColor(Color.WHITE);


        // TITLE STATE
        if (gp.gameState == gp.titleState) {
            drawTitleScreen();
        }

        // PLAY STATE
        if (gp.gameState == gp.playState) {
            drawPlayerLife();
            drawPlayerMana();
            drawMessage();
        }

        // PAUSE STATE
        if (gp.gameState == gp.pauseState) {
            drawPlayerLife();
            drawPauseScreen();
        }

        // DIALOGUE STATE
        if (gp.gameState == gp.dialogueState) {
            // drawPlayerLife();
            drawDialogueScreen();
        }

        // CHARACTER STATE
        if (gp.gameState == gp.characterState) {
            drawCharacterScreen();
            drawInventory(gp.player, true);
        }

        // OPTIONS STATE
        if (gp.gameState == gp.optionsState) {
            drawOptionsScreen();
        }

        // GAME OVER STATE
        if (gp.gameState == gp.gameOverState) {
            drawGameOverScreen();
        }

        // TRANSITION STATE
        if (gp.gameState == gp.transitionState) {
            drawTransition();
        }

        // TRADE STATE
        if (gp.gameState == gp.tradeState) {
            drawTradeScreen();
        }

        // SLEEP STATE
        if (gp.gameState == gp.sleepState) {
            drawSleepScreen();
        }
    }

    public void drawSleepScreen() {
        counter++;

        if (counter < 120) {
            gp.eManager.lighting.filterAlpha += 0.01f;
            if (gp.eManager.lighting.filterAlpha > 1f) {
                gp.eManager.lighting.filterAlpha = 1f;
            }
        }

        if (counter >= 120) {
            gp.eManager.lighting.filterAlpha -= 0.01f;
            if (gp.eManager.lighting.filterAlpha <= 0) {
                gp.eManager.lighting.filterAlpha = 0f;
                counter = 0;
                gp.eManager.lighting.dayState = gp.eManager.lighting.day;
                gp.eManager.lighting.dayCounter = 0;
                gp.gameState = gp.playState;
                gp.player.getPlayerImage();
            }
        }

    }

    public void drawTradeScreen() {
        switch(substate) {
            case 0: trade_select(); break;
            case 1: trade_buy(); break;
            case 2: trade_sell(); break;
        }
        gp.keyH.enterPressed = false;
    }

    public void trade_select() {

        npc.dialogueSet = 0;
        drawDialogueScreen();
        g2.setFont(g2.getFont().deriveFont(28F));

        int x = gp.tileSize * 15;
        int y = gp.tileSize * 4;
        int width = gp.tileSize * 3;
        int height = (int) (gp.tileSize * 3.5);
        drawSubWindow(x, y, width, height);

        x += gp.tileSize;
        y += gp.tileSize;
        g2.drawString("Buy", x, y);

        if (commandNum == 0) {
            g2.drawString(">", x-25, y);
            if (gp.keyH.enterPressed) {
                substate = 1;
            }
        }

        y += gp.tileSize;
        g2.drawString("Sell", x, y);
        if (commandNum == 1) {
            g2.drawString(">", x-25, y);
            if (gp.keyH.enterPressed) {
                substate = 2;
            }
        }

        y += gp.tileSize;
        g2.drawString("Leave", x, y);
        if (commandNum == 2) {
            g2.drawString(">", x-25, y);
            if (gp.keyH.enterPressed) {
                commandNum = 0;
                npc.startDialogue(npc, 1);
                // gp.gameState = gp.dialogueState;
                // currentDialogue = "Come again, hehe!";
            }
        }
    }

    public void trade_buy() {
        g2.setFont(g2.getFont().deriveFont(32f));
        //Draw player inventory
        drawInventory(gp.player, false);

        //draw npc inventory
        drawInventory(npc, true);

        

        int x = gp.tileSize * 2;
        int y = gp.tileSize * 9;
        int width = gp.tileSize*6;
        int height = gp.tileSize*2;
        drawSubWindow(x, y, width, height);
        g2.drawString("[ESC] Back", x+gp.tileSize/2, y+60);

        x = gp.tileSize * 12;
        y = gp.tileSize * 9;
        width = gp.tileSize*6;
        height = gp.tileSize*2;
        drawSubWindow(x, y, width, height);
        g2.drawString("Your Coin: " + gp.player.coin, x+24, y+60);

        int itemIndex = getItemIndexSlot(npcSlotCol, npcSlotRow);
        if (itemIndex < npc.inventory.size()) {
            x = (int)(gp.tileSize*5.5);
            y = (int)(gp.tileSize*5.5);
            width = (int)(gp.tileSize*2.5);
            height = gp.tileSize;
            drawSubWindow(x, y, width, height);
            g2.drawImage(coin, x+10, y + 8, 32, 32, null);

            int price = npc.inventory.get(itemIndex).price;
            String text = ""+price;
            x = getXForAlignRightText(text, gp.tileSize*8-20);
            g2.drawString(text, x, y+32);

            if (gp.keyH.enterPressed) {
                // int p = npc.inventory.get(itemIndex).price;
                if (price > gp.player.coin) {
                    substate = 0;
                    // gp.gameState = gp.dialogueState;
                    // currentDialogue = "You need more coins!";
                    
                    // drawDialogueScreen();
                    npc.startDialogue(npc, 2);
                }
                else {
                    if (gp.player.canObtainItem(npc.inventory.get(itemIndex))) {
                        System.out.println("The item is:" + npc.inventory.get(itemIndex).name);
                        System.out.println("At index: " + itemIndex);
                        gp.player.coin -= price;
                    }
                    else {
                        substate = 0;
                        // gp.gameState = gp.dialogueState;
                        // currentDialogue = "Inventory is full!";
                        npc.startDialogue(npc, 3);
                    }
                }

                // else if (gp.player.inventory.size() == gp.player.maxInventorySize) {
                //     substate = 0;
                //     gp.gameState = gp.dialogueState;
                //     currentDialogue = "Inventory is full!";
                //     drawDialogueScreen();
                // }
                // else {
                //     gp.player.coin -= price;
                //     gp.player.inventory.add(npc.inventory.get(itemIndex));
                // }
            }
        }
        
    }

    public void trade_sell() {
        drawInventory(gp.player, true);
        int x;
        int y;
        int width;
        int height;

        x = gp.tileSize * 2;
        y = gp.tileSize * 9;
        width = gp.tileSize*6;
        height = gp.tileSize*2;
        drawSubWindow(x, y, width, height);
        g2.drawString("[ESC] Back", x+gp.tileSize/2, y+60);

        x = gp.tileSize * 12;
        y = gp.tileSize * 9;
        width = gp.tileSize*6;
        height = gp.tileSize*2;
        drawSubWindow(x, y, width, height);
        g2.drawString("Your Coin: " + gp.player.coin, x+24, y+60);

        int itemIndex = getItemIndexSlot(playerSlotCol, playerSlotRow);
        if (itemIndex < gp.player.inventory.size()) {
            x = (int)(gp.tileSize*15.5);
            y = (int)(gp.tileSize*5.5);
            width = (int)(gp.tileSize*2.5);
            height = gp.tileSize;
            drawSubWindow(x, y, width, height);
            g2.drawImage(coin, x+10, y + 8, 32, 32, null);

            int price = gp.player.inventory.get(itemIndex).price/2;
            String text = ""+price;
            x = getXForAlignRightText(text, gp.tileSize*18-20);
            g2.drawString(text, x, y+32);

            if (gp.keyH.enterPressed) {
                if (gp.player.inventory.get(itemIndex) == gp.player.currentWeapon || 
                    gp.player.inventory.get(itemIndex) == gp.player.currentShield) {
                    
                    commandNum = 0;
                    substate = 0;
                    // gp.gameState = gp.dialogueState;
                    // currentDialogue = "You cannot sell an equipped item!";
                    npc.startDialogue(npc, 4);
                }
                else {
                    if (gp.player.inventory.get(itemIndex).amount > 1) {
                        gp.player.inventory.get(itemIndex).amount--;
                    }
                    else {
                        gp.player.inventory.remove(itemIndex);
                    }
                    gp.player.coin += price;
                }
            }
        }

    }

    public void drawTransition() {
        counter++;
        g2.setColor(new Color(0, 0, 0, counter*5));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
        int offset = 0;
        switch(gp.player.direction) {
            case "up": offset = 4; break;
            case "down": offset = 0; break;

        }
        if (counter == 50) {
            counter = 0;
            gp.gameState = gp.playState;
            gp.currentMap = gp.eHandler.tempMap;
            gp.player.worldX = gp.tileSize * gp.eHandler.tempCol;
            gp.player.worldY = gp.tileSize * gp.eHandler.tempRow+offset;
            gp.eHandler.previousEventX = gp.player.worldX;
            gp.eHandler.previousEventY = gp.player.worldY;
        }


    }

    public void drawGameOverScreen() {
        g2.setColor(new Color(0, 0, 0, 130));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        int x;
        int y;
        String text;
        g2.setFont(pixel2);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 110));

        text = "Game Over";

        //Shadow
        g2.setColor(Color.BLACK);
        x = getXForCenteredText(text);
        y = gp.tileSize * 4;
        g2.drawString(text, x, y);

        //Main
        g2.setColor(Color.WHITE);
        g2.drawString(text, x - 4, y - 4);

        //Retry
        g2.setFont(g2.getFont().deriveFont(50f));
        text = "Retry";
        x = getXForCenteredText(text);
        y += gp.tileSize*4;
        g2.drawString(text, x, y);
        if (commandNum == 0) {
            g2.drawString(">", x - 40, y);
        }

        //Back to title Screen
        text = "Quit";
        x = getXForCenteredText(text);
        y += 55;
        g2.drawString(text, x, y);
        if (commandNum == 1) {
            g2.drawString(">", x - 40, y);
        }
    }

    public void drawOptionsScreen() {
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(32F));

        int frameX = gp.tileSize * 6;
        int frameY = gp.tileSize;
        int width = gp.tileSize * 8;
        int height = gp.tileSize*10;
        drawSubWindow(frameX, frameY, width, height);

        switch(substate) {
            case 0: options_top(frameX, frameY); break;
            case 1: options_fullScreenNotification(frameX, frameY);break;
            case 2:options_control(frameX, frameY); break;
            case 3: options_endGameConfirmation(frameX, frameY); break;

        }

        gp.keyH.enterPressed = false;

    }

    public void options_endGameConfirmation(int frameX, int frameY) {
        int textX = frameX + gp.tileSize/2;
        int textY = frameY + gp.tileSize*3;
        g2.setFont(g2.getFont().deriveFont(24F));
        currentDialogue = "Quit the game and \nreturn to the \ntitle screen?";
        for (String line : currentDialogue.split("\n")) {
            g2.drawString(line, textX, textY);
            textY += 40;
        }

        //Yes
        String text = "Yes";
        textX = getXForCenteredText(text);
        textY += gp.tileSize*3; 
        g2.drawString(text, textX, textY);
        if (commandNum == 0) {
            g2.drawString(">", textX - 25, textY);
            if (gp.keyH.enterPressed) {
                substate = 0;
                gp.gameState = gp.titleState;
                gp.resetGame(true);
            }
        }
        //No
        text = "No";
        textX = getXForCenteredText(text);
        textY += gp.tileSize; 
        g2.drawString(text, textX, textY);
        if (commandNum == 1) {
            g2.drawString(">", textX - 25, textY);
            if (gp.keyH.enterPressed) {
                substate = 0;
                commandNum = 4;
            }
        }
    }

    public void options_fullScreenNotification(int frameX, int frameY) {
        int textX = frameX + gp.tileSize/2;
        int textY = frameY + gp.tileSize*3;

        currentDialogue = "The change will take \neffect after restarting \nthe game";
        g2.setFont(g2.getFont().deriveFont(28F));
        for (String line : currentDialogue.split("\n")) {
            g2.drawString(line, textX, textY);
            textY += 40;
        }

        textY = frameY + gp.tileSize*9;
        textX = frameX + gp.tileSize * 3 + 23;
        g2.drawString("Back", textX, textY);
        if (commandNum == 0) {
            g2.drawString(">", textX - 25, textY);
            if (gp.keyH.enterPressed) {
                substate = 0;
            }
        }
    }

    public void options_top(int frameX, int frameY) {
        int textX;
        int textY;

        //Title
        String text = "Options";
        textX = getXForCenteredText(text);
        textY = frameY + gp.tileSize;
        g2.drawString(text, textX, textY);

        //Full screen on/off
        textX = frameX + gp.tileSize;
        textY += gp.tileSize*2;
        g2.drawString("Full Screen", textX, textY);
        if (commandNum == 0) {
            g2.drawString(">", textX - 25, textY);
            if (gp.keyH.enterPressed) {
                gp.fullScreenOn = !gp.fullScreenOn;
                substate = 1;
            }
            
        }

        //Music
        textY += gp.tileSize;
        g2.drawString("Music", textX, textY);
        if (commandNum == 1) {
            g2.drawString(">", textX - 25, textY);
        }


        //SE
        textY += gp.tileSize;
        g2.drawString("SE", textX, textY);
        if (commandNum == 2) {
            g2.drawString(">", textX - 25, textY);
        }

        //Control
        textY += gp.tileSize;
        g2.drawString("Control", textX, textY);
        if (commandNum == 3) {
            g2.drawString(">", textX - 25, textY);
            if (gp.keyH.enterPressed) {
                substate = 2;
                commandNum = 0;
            }
        }

        //End Game
        textY += gp.tileSize;
        g2.drawString("End Game", textX, textY);
        if (commandNum == 4) {
            g2.drawString(">", textX - 25, textY);
            if (gp.keyH.enterPressed) {
                substate = 3;
                commandNum = 0;
            }
        }

        //Back
        textY += gp.tileSize*2;
        g2.drawString("Back", textX, textY);
        if (commandNum == 5) {
            g2.drawString(">", textX - 25, textY);
            if (gp.keyH.enterPressed) {
                gp.gameState = gp.playState;
                commandNum = 0;
            }
        }

        //Full screen text box
        textX = frameX + (int) (gp.tileSize*5.5);
        textY = frameY + gp.tileSize*2 + 24;
        g2.setStroke(new BasicStroke(3));
        g2.drawRect(textX, textY, gp.tileSize/2, gp.tileSize/2);
        if (gp.fullScreenOn) {
            g2.fillRect(textX, textY, gp.tileSize/2, gp.tileSize/2);
        }
        g2.setStroke(new BasicStroke(1));
        // g2.setStroke(null);

        //Music Volume
        textY += gp.tileSize;
        g2.drawRect(textX, textY, 100, gp.tileSize/2);
        int volumeWidth = 20 * gp.sound.volumeScale;
        g2.fillRect(textX, textY, volumeWidth, gp.tileSize/2);

        //SE Volume
        textY += gp.tileSize;
        g2.drawRect(textX, textY, 100, gp.tileSize/2);
        volumeWidth = 20 * gp.se.volumeScale;
        g2.fillRect(textX, textY, volumeWidth, gp.tileSize/2);

        gp.config.saveConfig();
    }

    public void options_control(int frameX, int frameY) {
        int textX;
        int textY;

        String text = "Control";
        textX = getXForCenteredText(text);
        textY = frameY + gp.tileSize;
        g2.drawString(text, textX, textY);

        textX = frameX + gp.tileSize;
        textY += gp.tileSize;
        g2.setFont(g2.getFont().deriveFont(24F));
        g2.drawString("Move", textX, textY); textY += gp.tileSize;
        g2.drawString("Confirm/Attack", textX, textY); textY += gp.tileSize;
        g2.drawString("Magic", textX, textY); textY += gp.tileSize;
        g2.drawString("Character Screen", textX, textY); textY += gp.tileSize;
        g2.drawString("Pause", textX, textY); textY += gp.tileSize;
        g2.drawString("Options", textX, textY); textY += gp.tileSize;

        textX = frameX + gp.tileSize*6;
        textY = frameY + gp.tileSize*2;
        g2.drawString("WASD", textX, textY); textY += gp.tileSize;
        g2.drawString("ENTER", textX, textY); textY += gp.tileSize;
        g2.drawString("F", textX, textY); textY += gp.tileSize;
        g2.drawString("C", textX, textY); textY += gp.tileSize;
        g2.drawString("P", textX, textY); textY += gp.tileSize;
        g2.drawString("ESC", textX, textY); textY += gp.tileSize;

        //Back button
        textY = frameY + gp.tileSize*9;
        textX = frameX + gp.tileSize * 3 + 23;
        g2.drawString("Back", textX, textY);
        if (commandNum == 0) {
            g2.drawString(">", textX - 25, textY);
            if (gp.keyH.enterPressed) {
                substate = 0;
                commandNum = 3;
            }
        }
    }

    public void drawMessage() {
        int messageX = gp.tileSize;
        int messageY = gp.tileSize * 4;
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 32F));

        for (int i = 0; i < message.size(); i++) {
            
            String text = message.get(i);
            if (text != null) {
                g2.setColor(Color.black);
                g2.drawString(text, messageX+2, messageY+2);

                g2.setColor(Color.white);
                g2.drawString(text, messageX, messageY);
                int counter = messageCounter.get(i) + 1;
                messageCounter.set(i, counter);
                messageY += 50;

                if (counter > 180) {
                    message.remove(i);
                    messageCounter.remove(i);
                }
            }
        }
    }

    public void drawPlayerMana() {
        int x = gp.tileSize/2 - 5;
        int y = (int) (gp.tileSize*1.5);
        int i = 0;
        while (i < gp.player.maxMana) {
            g2.drawImage(crystal_blank, x, y, null);
            i++;
            x += gp.tileSize/4 * 3;
        }

        x = gp.tileSize/2 - 5;
        y = (int) (gp.tileSize*1.5);
        i = 0;

        while (i < gp.player.mana) {
            g2.drawImage(crystal_full, x, y, null);
            i++;
            x += gp.tileSize/4 * 3;
        }
    }
    
    public void drawPlayerLife() {
        int x = gp.tileSize/2;
        int y = gp.tileSize/2;
        int i = 0;

        //Draw max life
        while (i < gp.player.maxLife/2) {
            g2.drawImage(heart_blank, x, y, null);
            i++;
            x += gp.tileSize;
        }

        //Reset
        x = gp.tileSize/2;
        y = gp.tileSize/2;
        i = 0;
        
        //Draw current heart
        while (i < gp.player.life) {
            g2.drawImage(heart_half, x, y, null);
            i++;
            if (i < gp.player.life) {
                g2.drawImage(heart_full, x, y, null);

            }
            i++;
            x += gp.tileSize;
        }
    }

    public void drawTitleScreen() {
        // if (titleScreenState == 0) {
            g2.setColor(new Color(0, 0, 0));
            g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
    
            //Title Name
            // g2.setFont(colonna);
            g2.setFont(pixel2);
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 75));
            String text = "Blue Boy Adventure";
    
            int x = getXForCenteredText(text);
            int y = gp.tileSize * 3;
    
            //Shadow
            g2.setColor(Color.GRAY);
            g2.drawString(text, x+3, y+3);
    
            //Main Text
            g2.setColor(Color.WHITE);
            g2.drawString(text, x, y);
    
            //Character image
            x = gp.screenWidth/2 - gp.tileSize;
            y += gp.tileSize*2;
            g2.drawImage(gp.player.down1, x, y, gp.tileSize*2, gp.tileSize*2, null);
    
            //menu
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 38));
    
            text = "NEW GAME";
            x = getXForCenteredText(text);
            y += gp.tileSize*3;
            g2.drawString(text, x, y);
            if (commandNum == 0) {
                g2.drawString(">", x - gp.tileSize, y);
            }
    
            text = "LOAD GAME";
            x = getXForCenteredText(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if (commandNum == 1) {
                g2.drawString(">", x - gp.tileSize, y);
            }
    
            text = "QUIT";
            x = getXForCenteredText(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if (commandNum == 2) {
                g2.drawString(">", x - gp.tileSize, y);
            }
        // }
        // else if (titleScreenState == 1) {
        //     //Class selection screen - OPTIONAL
        //     g2.setColor(Color.white);
        //     g2.setFont(g2.getFont().deriveFont(42F));

        //     String text = "Select your class!";
        //     int x = getXForCenteredText(text);
        //     int y = gp.tileSize * 3;
        //     g2.drawString(text, x, y);

        //     text = "Fighter";
        //     x = getXForCenteredText(text);
        //     y += gp.tileSize*3;
        //     g2.drawString(text, x, y);
        //     if (commandNum == 0) {
        //         g2.drawString(">", x - gp.tileSize, y);
        //     }

        //     text = "Thief";
        //     x = getXForCenteredText(text);
        //     y += gp.tileSize;
        //     g2.drawString(text, x, y);
        //     if (commandNum == 1) {
        //         g2.drawString(">", x - gp.tileSize, y);
        //     }

        //     text = "Sorcerer";
        //     x = getXForCenteredText(text);
        //     y += gp.tileSize;
        //     g2.drawString(text, x, y);
        //     if (commandNum == 2) {
        //         g2.drawString(">", x - gp.tileSize, y);
        //     }

        //     text = "Back";
        //     x = getXForCenteredText(text);
        //     y += gp.tileSize*2;
        //     g2.drawString(text, x, y);
        //     if (commandNum == 3) {
        //         g2.drawString(">", x - gp.tileSize, y);
        //     }
        // }
        

    }

    public void drawDialogueScreen() {
        int x = gp.tileSize * 3;
        int y = gp.tileSize/2;
        int width = gp.screenWidth - (gp.tileSize * 6);
        int height = gp.tileSize*4;

        drawSubWindow(x, y, width, height);


        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 32));
        x += gp.tileSize;
        y += gp.tileSize;

        if (npc.dialogues[npc.dialogueSet][npc.dialogueIndex] != null) {
            currentDialogue = npc.dialogues[npc.dialogueSet][npc.dialogueIndex];
            if (gp.keyH.enterPressed) {
                if (gp.gameState == gp.dialogueState) {
                    npc.dialogueIndex++;
                    gp.keyH.enterPressed = false;
                }
            }
        }
        else {
            npc.dialogueIndex = 0;
            if (gp.gameState == gp.dialogueState) {
                gp.gameState = gp.playState;
            }
        }

        for (String line : currentDialogue.split("\n")) {
            g2.drawString(line, x, y);
            y += 40;
        } 
        
    }

    public void drawSubWindow(int x, int y, int width, int height) {
        Color c = new Color(0, 0, 0, 210);
        g2.setColor(c);

        g2.fillRoundRect(x, y, width, height, 35, 35);

        c = new Color(255, 255, 255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x+5, y+5, width-10, height-10, 25, 25);
        g2.setStroke(new BasicStroke(1));
    }

    public void drawPauseScreen() {
        // g2.setFont(calisto_mt);
        g2.setFont(pixel2);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80));
        String text = "PAUSED";
        int x = getXForCenteredText(text);
        int y = gp.screenHeight/2;

        g2.drawString(text, x, y);
    }

    public void drawCharacterScreen() {
        // Create a frame
        final int frameX = gp.tileSize*2;
        final int frameY = gp.tileSize;
        final int frameWidth = gp.tileSize * 5;
        final int frameHeight = gp.tileSize * 10;
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        //text
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(26F));

        int textX = frameX + gp.tileSize/2;
        int textY = frameY + gp.tileSize;
        final int lineHeight = 35;

        // Names
        g2.drawString("Level", textX, textY);
        textY += lineHeight;
        g2.drawString("Life", textX, textY);
        textY += lineHeight;
        g2.drawString("Mana", textX, textY);
        textY += lineHeight;
        g2.drawString("Strength", textX, textY);
        textY += lineHeight;
        g2.drawString("Dexterity", textX, textY);
        textY += lineHeight;
        g2.drawString("Attack", textX, textY);
        textY += lineHeight;
        g2.drawString("Defense", textX, textY);
        textY += lineHeight;
        g2.drawString("Exp", textX, textY);
        textY += lineHeight;
        g2.drawString("Next Level", textX, textY);
        textY += lineHeight;
        g2.drawString("Coin", textX, textY);
        textY += lineHeight + 10;
        g2.drawString("Weapon", textX, textY);
        textY += lineHeight + 15;
        g2.drawString("Shield", textX, textY);
        textY += lineHeight;

        //values
        int tailX = (frameX + frameWidth) - 30;

        textY = frameY + gp.tileSize;
        String value;

        value = String.valueOf(gp.player.level);
        textX = getXForAlignRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.life + "/" + gp.player.maxLife);
        textX = getXForAlignRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.mana + "/" + gp.player.maxMana);
        textX = getXForAlignRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        
        value = String.valueOf(gp.player.strength);
        textX = getXForAlignRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.dexterity);
        textX = getXForAlignRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.attack);
        textX = getXForAlignRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.defense);
        textX = getXForAlignRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.exp);
        textX = getXForAlignRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.nextLevelExp);
        textX = getXForAlignRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.coin);
        textX = getXForAlignRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        g2.drawImage(gp.player.currentWeapon.down1, tailX - gp.tileSize, textY-24, null);
        textY += gp.tileSize;
        g2.drawImage(gp.player.currentShield.down1, tailX - gp.tileSize, textY-24, null);

    }

    public void drawInventory(Entity entity, boolean cursor) {
        int frameX = 0;
        int frameY = 0;
        int frameWidth = 0;
        int frameHeight = 0;
        int slotCol = 0;
        int slotRow = 0;

        if (entity == gp.player) {
            frameX = gp.tileSize * 12;
            frameY = gp.tileSize;
            frameWidth = gp.tileSize * 6;
            frameHeight = gp.tileSize * 5;
            slotCol = playerSlotCol;
            slotRow = playerSlotRow;
        }
        else {
            frameX = gp.tileSize * 2;
            frameY = gp.tileSize;
            frameWidth = gp.tileSize * 6;
            frameHeight = gp.tileSize * 5;
            slotCol = npcSlotCol;
            slotRow = npcSlotRow;
        }

        //frame
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        //Slot
        final int slotXStart = frameX + 20;
        final int slotYStart = frameY + 20;
        int slotX = slotXStart;
        int slotY = slotYStart;
        int slotSize = gp.tileSize + 3;
        boolean weaponNotSelected = true;
        boolean shieldNotSelected = true;

        //Draw player items
        for (int i = 0; i < entity.inventory.size(); i++) {
            Entity e = entity.inventory.get(i);


            // if (weaponNotSelected && (e == entity.currentWeapon)) {
            //     g2.setColor(Color.CYAN);
            //     g2.fillRoundRect(slotX, slotY, gp.tileSize, gp.tileSize, 10, 10);
            //     weaponNotSelected = false;
            // }
            // if (shieldNotSelected && (e == entity.currentShield)) {
            //     g2.setColor(Color.CYAN);
            //     g2.fillRoundRect(slotX, slotY, gp.tileSize, gp.tileSize, 10, 10);
            //     shieldNotSelected = false;
            // }

            // if (e.highlight ) {
            //     g2.setColor(Color.CYAN);
            //     g2.fillRoundRect(slotX, slotY, gp.tileSize, gp.tileSize, 10, 10);
                
            // }

            if (e != null && (e == entity.currentShield || e == entity.currentWeapon || e == entity.currentLight)) {

                g2.setColor(Color.CYAN);
                g2.fillRoundRect(slotX, slotY, gp.tileSize, gp.tileSize, 10, 10);
                // shieldNotSelected = false;
            }

            g2.drawImage(e.down1, slotX, slotY, null);

            if (entity == gp.player && e.amount > 1) {
                g2.setFont(g2.getFont().deriveFont(28f));
                int amountX;
                int amountY;
                String s = "" + e.amount;
                amountX = getXForAlignRightText(s, slotX + 44);
                amountY = slotY + gp.tileSize;

                g2.setColor(new Color(40, 60, 40));
                g2.drawString(s, amountX, amountY);

                g2.setColor(Color.white);
                g2.drawString(s, amountX - 3, amountY-3);


            }
            slotX += slotSize;
            if (i == 4 || i == 9 || i == 14) {
                slotX = slotXStart;
                slotY += slotSize;
            }
        }

        //Cursor
        if (cursor) {
            int cursorX = slotXStart + (slotSize * slotCol);
            int cursorY = slotYStart + (slotSize * slotRow);
            int cursorWidth = gp.tileSize;
            int cursorHeight = gp.tileSize;
    
            //Draw the cursor
            g2.setColor(Color.YELLOW);
            g2.setStroke(new BasicStroke(3));
            g2.drawRoundRect(cursorX, cursorY, cursorWidth, cursorHeight, 10, 10);
            g2.setStroke(new BasicStroke(1));
    
            // Description frame
            int dFrameX = frameX;
            int dFrameY = frameY + frameHeight;;
            int dFrameWidth = frameWidth;
            int dFrameHeight = gp.tileSize * 3;
            
    
            //draw description text
            int textX = dFrameX + 20;
            int textY = dFrameY + gp.tileSize;
            g2.setFont(g2.getFont().deriveFont(26F));
    
            int itemIndex = getItemIndexSlot(slotCol, slotRow);
            if (itemIndex < entity.inventory.size()) {
                drawSubWindow(dFrameX, dFrameY, dFrameWidth, dFrameHeight);
                for (String line : entity.inventory.get(itemIndex).description.split("\n")) {
                    g2.drawString(line, textX, textY);
                    textY += 25;
                }
    
                
            }
        }



    }

    public int getItemIndexSlot(int slotCol, int slotRow) {
        int itemIndex = slotCol + slotRow * 5;
        return itemIndex;
    }

    public int getXForCenteredText(String text) {
        int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth/2 - length/2;
        return x;
    }

    public int getXForAlignRightText(String text, int tailX) {
        int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = tailX - length;
        return x;
    }
}

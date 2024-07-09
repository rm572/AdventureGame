import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

// import Main.GamePanel;
// import Main.KeyHandler;

public class Player extends Entity {
    
    GamePanel gp;
    KeyHandler keyH;

    public final int screenX;
    public final int screenY;
    // int hasKey = 0;
    int standCounter = 0;
    boolean moving = false;
    int pixelCounter = 0;
    int displacement = 0;
    int cooldown = 0;
    public boolean attackCancel = false;
    boolean lightUpdated = false;



    public Player(GamePanel gp, KeyHandler keyH) {
        super(gp);
        this.gp = gp;
        this.keyH = keyH;

        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - (gp.tileSize/2);

        // //Regular movement
        // solidArea = new Rectangle();
        // solidArea.x = 8;
        // solidArea.y = 16;
        // solidAreaDefaultX = solidArea.x;
        // solidAreaDefaultY = solidArea.y;
        // solidArea.width = 24;
        // solidArea.height = 24;

        //tile-based movement
        solidArea = new Rectangle();
        solidArea.x = 1;
        solidArea.y = 1;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = gp.tileSize-2;
        solidArea.height = gp.tileSize-2;


        setDefaultValues();
        getPlayerImage();
        getPlayerAttackImage();
        setItems();
    }
    
    public void setDefaultValues() {
        worldX = gp.tileSize * 16;
        worldY = gp.tileSize * 25;
        defaultSpeed = 4;
        speed = defaultSpeed;
        direction = "down";

        //Player status
        maxLife = 6;
        life = maxLife;
        maxMana = 4;
        mana = maxMana;
        ammo = 10;
        level = 1;
        strength = 1;
        dexterity = 1;
        exp = 0;
        nextLevelExp = 5;
        coin = 1000;
        currentWeapon = new OBJ_Sword_Normal(gp);
        currentShield = new OBJ_Shield_Wood(gp);
        projectile = new OBJ_Fireball(gp);
        
        // projectile = new OBJ_Rock(gp);
        attack = getAttack();
        defense = getDefense();
    
    }
    
    public void setItems() {
        inventory.clear();
        inventory.add(currentWeapon);
        currentWeapon.highlight = true;
        inventory.add(currentShield);
        currentShield.highlight = true;
        inventory.add(new OBJ_Key(gp));
    }

    public void setKnockBack(Entity entity, int knockBackPower) {
        entity.direction = direction;
        entity.speed += knockBackPower;
        entity.knockBack = true;
    }

    public int getAttack() {
        attackArea = currentWeapon.attackArea;
        motion1Duration = currentWeapon.motion1Duration;
        motion2Duration = currentWeapon.motion2Duration;
        return attack = strength * currentWeapon.attackValue;
    }

    public int getDefense() {
        return defense = dexterity * currentShield.defenseValue;
    }

    public void getPlayerImage() {
        // try {
        //     up1 = ImageIO.read(new File("C:/Users/rober/OneDrive/Programming/Workspace/Workspace/2DGame_Example/res/Player/boy_up_1.png"));
        //     up2 = ImageIO.read(new File("C:/Users/rober/OneDrive/Programming/Workspace/Workspace/2DGame_Example/res/Player/boy_up_2.png"));
        //     down1 = ImageIO.read(new File("C:/Users/rober/OneDrive/Programming/Workspace/Workspace/2DGame_Example/res/Player/boy_down_1.png"));
        //     down2 = ImageIO.read(new File("C:/Users/rober/OneDrive/Programming/Workspace/Workspace/2DGame_Example/res/Player/boy_down_2.png"));
        //     left1 = ImageIO.read(new File("C:/Users/rober/OneDrive/Programming/Workspace/Workspace/2DGame_Example/res/Player/boy_left_1.png"));
        //     left2 = ImageIO.read(new File("C:/Users/rober/OneDrive/Programming/Workspace/Workspace/2DGame_Example/res/Player/boy_left_2.png"));
        //     right1 = ImageIO.read(new File("C:/Users/rober/OneDrive/Programming/Workspace/Workspace/2DGame_Example/res/Player/boy_right_1.png"));
        //     right2 = ImageIO.read(new File("C:/Users/rober/OneDrive/Programming/Workspace/Workspace/2DGame_Example/res/Player/boy_right_2.png"));
            
        // }
        // catch (IOException e) {
        //     e.printStackTrace();
        // }

        //Optimization
        up1 = setUp("Player/new_up_1", gp.tileSize, gp.tileSize);
        up2 = setUp("Player/new_up_2", gp.tileSize, gp.tileSize);
        down1 = setUp("Player/new_down_1", gp.tileSize, gp.tileSize);
        down2 = setUp("Player/new_down_2", gp.tileSize, gp.tileSize);
        left1 = setUp("Player/new_left_1", gp.tileSize, gp.tileSize);
        left2 = setUp("Player/new_left_2", gp.tileSize, gp.tileSize);
        right1 = setUp("Player/new_right_1", gp.tileSize, gp.tileSize);
        right2 = setUp("Player/new_right_2", gp.tileSize, gp.tileSize);
    }

    public void getPlayerAttackImage() {
        if (currentWeapon.type == typeSword) {
            attackUp1 = setUp("Player/new_attack_up_11", gp.tileSize, gp.tileSize*2);
            attackUp2 = setUp("Player/new_attack_up_22", gp.tileSize, gp.tileSize*2);
            attackDown1 = setUp("Player/new_attack_down_11", gp.tileSize, gp.tileSize*2);
            attackDown2 = setUp("Player/new_attack_down_33", gp.tileSize, gp.tileSize*2);
            attackLeft1 = setUp("Player/new_attack_left_1", gp.tileSize*2, gp.tileSize);
            attackLeft2 = setUp("Player/new_attack_left_2", gp.tileSize*2, gp.tileSize);
            attackRight1 = setUp("Player/new_attack_right_1", gp.tileSize*2, gp.tileSize);
            attackRight2 = setUp("Player/new_attack_right_2", gp.tileSize*2, gp.tileSize);    
        }
        else if (currentWeapon.type == typeAxe) {
            attackUp1 = setUp("Player/new_axe_up_1", gp.tileSize, gp.tileSize*2);
            attackUp2 = setUp("Player/new_axe_up_2", gp.tileSize, gp.tileSize*2);
            attackDown1 = setUp("Player/new_axe_down_1", gp.tileSize, gp.tileSize*2);
            attackDown2 = setUp("Player/new_axe_down_2", gp.tileSize, gp.tileSize*2);
            attackLeft1 = setUp("Player/new_axe_left_1", gp.tileSize*2, gp.tileSize);
            attackLeft2 = setUp("Player/new_axe_left_2", gp.tileSize*2, gp.tileSize);
            attackRight1 = setUp("Player/new_axe_right_1", gp.tileSize*2, gp.tileSize);
            attackRight2 = setUp("Player/new_axe_right_2", gp.tileSize*2, gp.tileSize);    
        }
    }

    public void getSleepingImage(BufferedImage image) {
        up1 = image;
        up2 = image;
        down1 = image;
        down2 = image;
        left1 = image;
        left2 = image;
        right1 = image;
        right2 = image;
    }

    /*// public BufferedImage setUp(String imageName) {
    //     UtilityTool uTool = new UtilityTool();
    //     BufferedImage image = null;

    //     try {
    //         image = ImageIO.read(new File("C:/Users/rober/OneDrive/Programming/Workspace/Workspace/2DGame_Example/res/Player/"+imageName+".png"));
    //         image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);
    //     }
    //     catch (Exception e) {

    //     }
    //     return image;

    // }*/

    public void update() {
    
        //Tile movement
        int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
        interactNPC(npcIndex);

        int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
        contactMonster(monsterIndex);
        gp.eHandler.checkEvent();

        gp.cChecker.checkEntity(this, gp.iTile);

        // attacking = false;
        // if (moving) {attacking = false;}
        

        //Standing still
        if (!moving) {        

            if (attacking) {
                attacking();
                // spriteCounter = 0;
                // attacking = false;
                
            }
            
            else if (keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true
            || keyH.rightPressed == true || keyH.enterPressed) {
        
                if (keyH.upPressed == true) {
                    direction = "up";
                }
                
                else if (keyH.downPressed == true) {
                    direction = "down";
                }
                
                else if (keyH.leftPressed == true) {
                    direction = "left";
                }
                
                else if (keyH.rightPressed == true) {
                    direction = "right";   
                }

                moving = true;

                collisionOn = false;
                upCollision = false;
                downCollision = false;
                leftCollision = false;
                rightCollision = false;
                gp.cChecker.checkTile(this);

                int objIndex = gp.cChecker.checkObject(this, true);
                
                pickUpObject(objIndex);

                keyH.enterPressed = false;
            }

            else {
                standCounter++;
                if (standCounter == 20) {
                    spriteNum = 1;
                    standCounter = 0;
                }
            }
            
        }
        //Moving
        else {
            //Checks if there needs to be recoil from an event
            // if (attacking) {
            //     attacking();
            //     // spriteCounter = 0;
            // }
            keyH.enterPressed = false;
            if (gp.eHandler.disruptedMovement) {
                if (!(gp.eHandler.teleport)) {
                    switchCases(direction, displacement, false);

                }
                else {
                    gp.eHandler.teleport = false;
                }
                gp.eHandler.disruptedMovement = false;
                moving = false;
                displacement = 0;
                
            }
            //Pixel movement
            else if (collisionOn == false) {
                switchCases(direction, speed, true);
                displacement = (displacement + speed) % (gp.tileSize/3);
                if (displacement == 0) {
                    moving = false;
                }

            }
            //checks recoil from npc collision
            else if (displacement != 0){
                switchCases(direction, displacement, false);
                moving = false;
                displacement = 0;
            }
            //not moving
            else {
                moving = false;
            }

            spriteCounter++;
            if(spriteCounter > 10) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                }
                else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }

        if (gp.keyH.shotKeyPressed && projectile.alive == false 
            && shotAvailableCounter == 30 && projectile.haveResource(this)) {
            projectile.set(worldX, worldY, direction, true, this);

            // gp.projectileList.add(projectile);
            for (int i = 0; i < gp.projectile[1].length; i++) {
                if (gp.projectile[gp.currentMap][i] == null) {
                    gp.projectile[gp.currentMap][i] = projectile;
                    break;
                }
            }


            shotAvailableCounter = 0;

            projectile.subtractResource(this);
        }

        if (invincible) {
            invincibleCounter++;
            if (invincibleCounter > 60) {
                invincible = false;
                invincibleCounter = 0;
            }
        }

        if (shotAvailableCounter < 30) {
            shotAvailableCounter++;
        }

        if (life > maxLife) {
            life = maxLife;
        }

        if (mana > maxMana) {
            mana = maxMana;
        }

        if (life <= 0) {
            gp.ui.commandNum = -1;
            gp.gameState = gp.gameOverState;
        }



        keyH.enterPressed = false;
        // attackCancel = true;
        


        // //Regular movement
        // if (keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true
        //     || keyH.rightPressed == true) {
                
        
        //     if (keyH.upPressed == true) {
        //         direction = "up";
        //     }
            
        //     else if (keyH.downPressed == true) {
        //         direction = "down";
        //     }
            
        //     else if (keyH.leftPressed == true) {
        //         direction = "left";
        //     }
            
        //     else if (keyH.rightPressed == true) {
        //         direction = "right";   
        //     }

        //     collisionOn = false;
        //     gp.cChecker.checkTile(this);

        //     int objIndex = gp.cChecker.checkObject(this, true);

        //     pickUpObject(objIndex);

        //     int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
        //     interactNPC(npcIndex);
        //     gp.eHandler.checkEvent();

        //     // if collision is false, player can move
        //     if (collisionOn == false) {
        //         switch (direction) {
        //             case "up": 
        //                 worldY -= speed;
        //                 break;
        //             case "down":
        //                 worldY += speed;
        //                 break;
        //             case "left":
        //                 worldX -= speed;
        //                 break;
        //             case "right":
        //                 worldX += speed;
        //                 break;
        //         }
        //     }

        //     spriteCounter++;
        //     if(spriteCounter > 10) {
        //         if (spriteNum == 1) {
        //             spriteNum = 2;
        //         }
        //         else if (spriteNum == 2) {
        //             spriteNum = 1;
        //         }
        //         spriteCounter = 0;
        //     }
        // else {
        //     standCounter++;
        //     if (standCounter == 20) {
        //         spriteNum = 1;
        //         standCounter = 0;
        //     }
        // }
        // }
    }
    /*
    public void attacking() {
        spriteCounter++;

        if (spriteCounter <= 5) {
            spriteNum = 1;
        }
        if (spriteCounter > 5 && spriteCounter <= 25) {
            spriteNum = 2;

            int currentWorldX = worldX;
            int currentWorldY = worldY;
            int solidAreaWidth = solidArea.width;
            int solidAreaHeight = solidArea.height;

            switch(direction) {
                case "up": worldY -= attackArea.height; break;
                case "down": worldY += attackArea.height; break;
                case "left": worldX -= attackArea.width; break;
                case "right": worldX += attackArea.width; break;
            }

            solidArea.width = attackArea.width;
            solidArea.height = attackArea.height;
            
            int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
            damageMonster(monsterIndex, this, attack, currentWeapon.knockBackPower);

            int iTileIndex = gp.cChecker.checkEntity(this, gp.iTile);
            damageInteractiveTile(iTileIndex);

            int projectileIndex = gp.cChecker.checkEntity(this, gp.projectile);
            damageProjectile(projectileIndex);

            worldX = currentWorldX;
            worldY = currentWorldY;
            solidArea.width = solidAreaWidth;
            solidArea.height = solidAreaHeight;

        }



        if (spriteCounter> 25) {
            spriteNum = 1;
            spriteCounter = 0;
            attacking = false;
        }

    }
    */
    public void damageProjectile(int i) {
        if (i != 999) {
            Entity projectile = gp.projectile[gp.currentMap][i];
            projectile.alive = false;
            generateParticle(projectile, projectile);
        }
    }

    public void setDefaultPositions() {
        worldX = gp.tileSize * 16;
        worldY = gp.tileSize * 25;
        direction = "down";
    }

    public void restoreLifeandMana() {
        life = maxLife;
        mana = maxMana;
        invincible = false;
    }

    public void contactMonster(int i) {
        if (i != 999) {
            if (invincible == false && gp.monster[gp.currentMap][i].dying == false) {
                int damage = gp.monster[gp.currentMap][i].attack - defense;
                if (damage < 0) {
                    damage = 0;
                }
                life -= damage;
                invincible = true;
            }
            if (gp.monster[gp.currentMap][i].dying) {
                gp.monster[gp.currentMap][i].solidArea.width = 0;
                gp.monster[gp.currentMap][i].solidArea.height = 0;
            }

            
        }
    }

    public void damageMonster(int i, Entity attacker, int attack, int knockBackPower) {
        if (i != 999) {

            
            
            if (gp.monster[gp.currentMap][i].invincible == false) {


                if (knockBackPower > 0) {
                    setKnockBack(gp.monster[gp.currentMap][i], attacker, knockBackPower);
                }
                
                int damage = attack - gp.monster[gp.currentMap][i].defense;
                if (damage < 0) {
                    damage = 0;
                }
                gp.monster[gp.currentMap][i].life -= damage;
                gp.ui.addMessage(damage + " damage!");

                gp.monster[gp.currentMap][i].invincible = true;
                gp.monster[gp.currentMap][i].damageReaction();



                if (gp.monster[gp.currentMap][i].life <= 0) {
                    gp.monster[gp.currentMap][i].dying = true;
                    gp.ui.addMessage("Killed the " + gp.monster[gp.currentMap][i].name + "!");
                    gp.ui.addMessage("Exp " + gp.monster[gp.currentMap][i].exp + "!");
                    exp += gp.monster[gp.currentMap][i].exp;
                    checkLevelUp();
                }
            }
            

        }

    }

    public void damageInteractiveTile(int i) {
        if (i != 999 && gp.iTile[gp.currentMap][i].destructible == true && gp.iTile[gp.currentMap][i].isCorrectItem(this)
            && gp.iTile[gp.currentMap][i].invincible == false) {
            gp.iTile[gp.currentMap][i].life--;
            gp.iTile[gp.currentMap][i].invincible = true;


            generateParticle(gp.iTile[gp.currentMap][i], gp.iTile[gp.currentMap][i]);

            if (gp.iTile[gp.currentMap][i].life == 0) {
                gp.iTile[gp.currentMap][i] = gp.iTile[gp.currentMap][i].getDestroyedForm();
            }
        }
    }

    public void checkLevelUp() {
        if (exp >= nextLevelExp) {
            level++;
            nextLevelExp *= 2;
            maxLife += 2;
            life += 1;
            strength++;
            dexterity++;
            attack = getAttack();
            defense = getDefense();

            gp.gameState = gp.dialogueState;
            gp.ui.currentDialogue = "You are level " + level + " now!\n"+"You feel stronger";

        }
    }

    public void selectItem() {
        int itemIndex = gp.ui.getItemIndexSlot(gp.ui.playerSlotCol, gp.ui.playerSlotRow);

        if (itemIndex < inventory.size()) {
            Entity selectedItem = inventory.get(itemIndex);

            if ((selectedItem.type == typeSword || selectedItem.type == typeAxe)) {
                // currentWeapon.highlight = false;
                currentWeapon = selectedItem;
                // currentWeapon.highlight = true;
                attack = getAttack();
                getPlayerAttackImage();
            } 

            if (selectedItem.type == typeShield) {
                currentShield = selectedItem;
                defense = getDefense();
            }
            if (selectedItem.type == typeConsumable) {
                if (selectedItem.use(this)) {
                    if (selectedItem.amount > 1) {
                        selectedItem.amount--;
                    }
                    else {
                        inventory.remove(itemIndex);
                    }
                    
                }
                
            }
            if (selectedItem.type == typeLight) {
                if (currentLight == selectedItem) {
                    currentLight = null;
                }
                else {
                    currentLight = selectedItem;

                }
                lightUpdated = true;
            }
        }
    }

    public int searchItemInInventory(String itemName) {
        int itemIndex = 999;
        
        for (int i = 0; i < inventory.size(); i++) {
            Entity item = inventory.get(i);
            if (item.name.equals(itemName)) {
                itemIndex = i;
                break;
            }
        }
        return itemIndex;
    }

    public boolean canObtainItem(Entity item) {

        boolean canObtain = false;

        if (item.stackable) {
            int index = searchItemInInventory(item.name);
            if (index != 999) {
                inventory.get(index).amount++;
                canObtain = true;
            }
            else {
                if (inventory.size() != maxInventorySize) {
                    inventory.add(item);
                    canObtain = true;
                }
            }
        }
        else {
            if (inventory.size() != maxInventorySize) {
                inventory.add(item);
                canObtain = true;
            }
        }

        return canObtain;
    }

    public void pickUpObject(int i) {
        if (i != 999) {
            if (gp.obj[gp.currentMap][i].type == typePickupOnly) {
                gp.obj[gp.currentMap][i].use(this);
                gp.obj[gp.currentMap][i] = null;
            }

            else if (gp.obj[gp.currentMap][i].type == typeObstacle) {
                // System.out.println("In the if");
                // System.out.println("Enter pressed? " + keyH.enterPressed);
                gp.obj[gp.currentMap][i].interact();
                if (gp.obj[gp.currentMap][i].removed) {
                    gp.obj[gp.currentMap][i] = null;
                }
                // attackCancel = true;
                // if (keyH.enterPressed == true) {
                //     System.out.println("In the interact");
                //     gp.obj[gp.currentMap][i].interact();
                // }
            }

            else {
                String text;
                if (canObtainItem(gp.obj[gp.currentMap][i])) {
                    text = "Got a " + gp.obj[gp.currentMap][i].name + "!";
                }
                else {
                    text = "You cannot carry anymore items!";
                }

                gp.ui.addMessage(text);
                gp.obj[gp.currentMap][i] = null;
            }
        }

    }

    public void switchCases(String direction, int changeVal, boolean positiveChange) {
        if (!positiveChange) {
            changeVal *= (-1);
        }

        switch (direction) {
            case "up": 
                worldY -= changeVal;
                break;
            case "down":
                worldY += changeVal;
                break;
            case "left":
                worldX -= changeVal;
                break;
            case "right":
                worldX += changeVal;
                break;
        } 
    }

    public void interactNPC(int i) {

        if (gp.keyH.enterPressed) {
            if (i != 999) {
                attackCancel = true;
                attacking = false;
                if (!gp.npc[gp.currentMap][i].moving && gp.npc[gp.currentMap][i].ready == true) {
                    gp.gameState = gp.dialogueState;
                    gp.npc[gp.currentMap][i].speak();
                }
                gp.keyH.enterPressed = false;
            }
            else {
                attacking = true;
            }

        }

        
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;

        int tempScreenX = screenX;
        int tempScreenY = screenY;

        switch (direction) {
            case "up":
                if (!attacking) {
                    if (spriteNum == 1) {image = up1;}
                    if (spriteNum == 2) {image = up2;}
                }
                else {
                    tempScreenY = screenY - gp.tileSize;
                    if (spriteNum == 1) {image = attackUp1;}
                    if (spriteNum == 2) {image = attackUp2;}
                }
                break;
            case "down":
                if (!attacking) {
                    if (spriteNum == 1) {image = down1;}
                    if (spriteNum == 2) {image = down2;}
                }
                else {
                    if (spriteNum == 1) {image = attackDown1;}
                    if (spriteNum == 2) {image = attackDown2;}
                }
                break;
            case "left":
                if (!attacking) {
                    if (spriteNum == 1) {image = left1;}
                    if (spriteNum == 2) {image = left2;}
                }
                else {
                    tempScreenX = screenX - gp.tileSize;
                    if (spriteNum == 1) {image = attackLeft1;}
                    if (spriteNum == 2) {image = attackLeft2;}
                }
                break;
            case "right":
                if (!attacking) {
                    if (spriteNum == 1) {image = right1;}
                    if (spriteNum == 2) {image = right2;}
                }
                else {
                    if (spriteNum == 1) {image = attackRight1;}
                    if (spriteNum == 2) {image = attackRight2;}
                }
                break;
        }

        if (invincible) {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4F));
        }
        g2.drawImage(image, tempScreenX, tempScreenY, null);
        // g2.setStroke(new BasicStroke(2));
        // g2.setColor(Color.red);
        // g2.drawRect(tempScreenX + solidArea.x, tempScreenY + solidArea.y, solidArea.width, solidArea.height);

        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1F));

        // g2.setFont(new Font("Arial", Font.PLAIN, 26));
        // g2.setColor(Color.WHITE);
        // g2.drawString("Invincible Counter: "+invincibleCounter, 10, 400);
    }
}

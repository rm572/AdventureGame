import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;

public class Entity {
    
    public int worldX, worldY;
    public int speed;

    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public BufferedImage attackUp1, attackUp2, attackDown1, attackDown2, attackLeft1,
        attackLeft2, attackRight1, attackRight2, 
        guardUp, guardDown, guardLeft, guardRight;
    public String direction = "down";


    public int spriteNum = 1;

    public Rectangle solidArea = new Rectangle(1, 1, 46, 46);
    Rectangle attackArea = new Rectangle(0, 0, 0, 0);

    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collisionOn = false;
    boolean leftCollision = false;
    boolean rightCollision = false;
    boolean upCollision = false;
    boolean downCollision = false;
    boolean hasMovement;


    GamePanel gp;
    boolean moving = false;
    boolean ready = true;
    String dialogues[][] = new String[20][20];
    int dialogueIndex = 0;
    boolean entityCollision = false;
    boolean attacking = false;
    boolean guarding = false;
    boolean offBalance = false;

    boolean alive = true;
    boolean dying = false;
    boolean hpBarOn = false;


    int spriteCounter = 0;
    int hpBarCounter = 0;
    int shotAvailableCounter = 0;
    int dyingCounter = 0;
    int guardCounter = 0;
    int offBalanceCounter = 0;
    int pixelCounter = 0;
    int actionLockCounter = 0;
    int invincibleCounter = 0;
    int knockBackCounter = 0;

    public BufferedImage image, image2, image3;
    public String name;
    public boolean collision = false;

    public ArrayList<Entity> inventory = new ArrayList<>();
    public final int maxInventorySize = 20;
    int knockBackPower = 0;

    public boolean invincible = false;


    Entity attacker;
    String knockBackDirection;

    //Character status
    public int maxLife;
    public int defaultSpeed;
    public int life;
    public int maxMana;
    public int mana;
    public int ammo;
    public int level;
    public int strength;
    public int dexterity;
    public int attack;
    public int defense;
    public int exp;
    public int nextLevelExp;
    public int coin;
    public Entity currentWeapon;
    public Entity currentShield;
    public Projectile projectile;
    public int motion1Duration;
    public int motion2Duration;
    int value;
    int attackValue;
    int defenseValue;
    String description = "";
    public int useCost;
    int price;
    public boolean onPath = false;
    boolean knockBack = false;

    boolean removed = false;
    boolean highlight = false;
    Entity currentLight;
    int lightRadius;
    boolean transparent = false;

    Entity loot;
    boolean opened = false;

    boolean stackable = false;
    int amount = 1;

    int weaponSlotCol;
    int weaponSlotRow;
    int shieldSlotCol;
    int shieldSlotRow;

    int dialogueSet = 0;

    int type; //0 - player, 1 - NPC, 2 - monster
    public final int typePlayer = 0;
    final int typeNPC = 1;
    final int typeMonster = 2;
    final int typeSword = 3;
    final int typeAxe = 4;
    final int typeShield = 5;
    final int typeConsumable = 6;
    final int typePickupOnly = 7;
    final int typeObstacle = 8;
    final int typeLight = 9;

    public Entity(GamePanel gp) {
        this.gp = gp;
    }

    public void setAction() {}
    public void damageReaction() {}
    public boolean use(Entity entity) {return false;}
    public void checkDrop() {}
    public void setLoot(Entity loot) {}

    public void resetCounter() {
        spriteCounter = 0;
        hpBarCounter = 0;
        shotAvailableCounter = 0;
        dyingCounter = 0;
        guardCounter = 0;
        offBalanceCounter = 0;
        pixelCounter = 0;
        actionLockCounter = 0;
        invincibleCounter = 0;
        knockBackCounter = 0;
    }
    
    public int getXDistance(Entity target) {
        int xDistance = Math.abs(worldX - target.worldX);
        return xDistance;
    }
    public int getYDistance(Entity target) {
        int yDistance = Math.abs(worldY - target.worldY);
        return yDistance;
    }

    public int getTileDistance(Entity target) {
        int tileDistance = (getXDistance(target) + getYDistance(target))/gp.tileSize;
        return tileDistance;
    }

    public int getGoalCol(Entity target) {
        int goalCol = (gp.player.worldX + gp.player.solidArea.x)/gp.tileSize;
        return goalCol;
    }

    public void checkStopChasingOrNot(Entity target, int distance, int rate) {
        if (getTileDistance(target) > distance) {
            int i = new Random().nextInt(rate);
            if (i == 0) {
                onPath = false;
            }
        }
    }

    public void checkStartChasingOrNot(Entity target, int distance, int rate) {
        if (getTileDistance(target) < distance) {
            int i = new Random().nextInt(rate);
            if (i == 0) {
                onPath = true;
            }
        }
    }

    public void getRandomDirection(int rate) {
        actionLockCounter++;
            
        if (actionLockCounter == rate) {
            Random random = new Random();
            int i = random.nextInt(100)+1; //random num from 1-100
    
            if (i <= 25) {direction = "up";}
            else if (i <= 50) {direction = "down";} 
            else if (i <= 75) {direction = "left";}
            else {direction = "right";}
            moving = true;
            actionLockCounter = 0;
        }
    }

    public void checkShootOrNot(int rate, int shotInterval) {
        int i = new Random().nextInt(rate);
        if (i == 0 && projectile.alive == false && shotAvailableCounter == shotInterval) {
            projectile.set(worldX, worldY, direction, true, this);
            // gp.projectileList.add(projectile);
            for (int ii = 0; ii < gp.projectile[1].length; ii++) {
                if (gp.projectile[gp.currentMap][ii] == null) {
                    gp.projectile[gp.currentMap][ii] = projectile;
                    break;
                }
            }
            shotAvailableCounter = 0;
        }
    }
    public int getGoalRow(Entity target) {
        int goalRow = (gp.player.worldY + gp.player.solidArea.y)/gp.tileSize;
        return goalRow;
    }

    public void setKnockBack(Entity target, Entity attacker, int knockBackPower) {
        if (target.type == typePlayer && target.moving == true) {

        }
        else {
            // System.out.println("The knock back attacker is: " + name);
            this.attacker = attacker;
            target.knockBackDirection = attacker.direction;
            // System.out.println("Before speed: " + target.speed);
            target.speed += knockBackPower;
            // System.out.println("After speed: " + target.speed);
            // switchCases(direction, gp.tileSize, false);
            target.knockBack = true;
        }

    }

    // public void knock(Entity target, Entity attacker, int knockBackPower) {
    //     this.attacker = attacker;
    //     target.knockBackDirection = attacker.direction;
    //     switchCases(target, direction, knockBackPower, true);
    //     target.knockBack = true;
    // }

    public Color getParticleColor() {
        Color color = null;
        return color;
    }

    public int getParticleSize() {
        int size = 0; //6 pixels
        return size;
    }

    public int getParticleSpeed() {
        int speed = 0;
        return speed;
    }

    public int getParticleMaxLife() {
        int maxLife = 0;
        return maxLife;
    }

    public int getLeftX() {
        return worldX + solidArea.x;
    }

    public int getRightX() {
        return worldX + solidArea.x + solidArea.width;
    }

    public int getTopY() {
        return worldY + solidArea.y;
    }

    public int getBottomY() {
        return worldY + solidArea.y + solidArea.height;
    }

    public int getCol() {
        return (worldX + solidArea.x)/gp.tileSize;
    }

    
    public int getRow() {
        return (worldY + solidArea.y)/gp.tileSize;
    }

    public void generateParticle(Entity generator, Entity target)  {
        
        Color color = generator.getParticleColor();
        int size = generator.getParticleSize();
        int speed = generator.getParticleSpeed();
        int maxLife = generator.getParticleMaxLife();

        Particle p1 = new Particle(gp, target, color, size, speed, maxLife, -2, -1);
        Particle p2 = new Particle(gp, target, color, size, speed, maxLife, 2, -1);
        Particle p3 = new Particle(gp, target, color, size, speed, maxLife, -2, 1);
        Particle p4 = new Particle(gp, target, color, size, speed, maxLife, 2, 1);


        gp.particleList.add(p1);
        gp.particleList.add(p2);
        gp.particleList.add(p3);
        gp.particleList.add(p4);

    }

    public void dropItem(Entity droppedItem) {
        for (int i = 0; i < gp.obj[1].length; i++) {
            if (gp.obj[gp.currentMap][i] == null) {
                gp.obj[gp.currentMap][i] = droppedItem;
                gp.obj[gp.currentMap][i].worldX = worldX;
                gp.obj[gp.currentMap][i].worldY = worldY;
                break;
            }
        }
    }

    public void interact() {

    }

    public void speak() {

        // if (dialogues[dialogueIndex] == null) {
        //     dialogueIndex = 0;
        // }
        // gp.ui.currentDialogue = dialogues[dialogueIndex];
        // dialogueIndex++;


        
    }

    public void startDialogue(Entity entity, int setNum) {
        gp.gameState = gp.dialogueState;
        gp.ui.npc = entity;
        dialogueSet = setNum;
    }

    public void facePlayer() {
        switch (gp.player.direction) {
            case "up": direction = "down"; break;
            case "down": direction = "up"; break;
            case "left": direction = "right"; break;
            case "right": direction = "left"; break;
        }
    }


    public void checkCollision() {
        collisionOn = false;
        gp.cChecker.checkTile(this);
        gp.cChecker.checkEntity(this, gp.npc);
        gp.cChecker.checkEntity(this, gp.monster);
        gp.cChecker.checkObject(this, false);
        gp.cChecker.checkEntity(this, gp.iTile);
        boolean contactPlayer = gp.cChecker.checkPlayer(this);
        if (this.type == typeMonster && contactPlayer == true) {
            damagePlayer(attack);
        }
    }

    public void update() {

        if (knockBack) {
            // if (!(this == gp.player && gp.player.moving = true)) {
            //     checkCollision();

            //     System.out.println("Knockback");
            //     if (collisionOn) {
            //         knockBackCounter = 0;
            //         knockBack = false;
            //         speed = defaultSpeed;
            //     }
            //     else if (!collisionOn) {
            //         switchCases(this, knockBackDirection, gp.tileSize/10, true);
    
            //     }
            //     knockBackCounter++;
            //     if (knockBackCounter == 10) {
            //         knockBackCounter = 0;
            //         knockBack = false;
            //         speed = defaultSpeed;
            //     }
            // }
            checkCollision();

            // System.out.println("Knockback");
            if (collisionOn) {
                knockBackCounter = 0;
                knockBack = false;
                speed = defaultSpeed;
            }
            else if (!collisionOn) {
                switchCases(this, knockBackDirection, gp.tileSize/16, true);

            }
            knockBackCounter++;
            if (knockBackCounter == 12) {
                knockBackCounter = 0;
                knockBack = false;
                speed = defaultSpeed;
            }
        }
        else if (attacking) {
            attacking();
        }
        else {
            setAction();

            checkCollision();
    
            // if (collisionOn == false) {
                if (moving) {
                    ready = false;
                    // if (collisionOn == false) {
                    //     switch (direction) {
                    //         case "up": 
                    //             worldY -= speed;
                    //             break;
                    //         case "down":
                    //             worldY += speed;
                    //             break;
                    //         case "left":
                    //             worldX -= speed;
                    //             break;
                    //         case "right":
                    //             worldX += speed;
                    //             break;
                    //     }
                    // }
                    gp.cChecker.checkPlayer(this);
                    // if (onPath) {
                    //     checkCollision();
                    // }
                    switch (direction) {
                        case "up":
                            if (!upCollision) {
                                worldY -= speed;
                                // pixelCounter+=speed;
                                // if (pixelCounter == gp.tileSize/3) {
                                //     moving = false;
                                //     ready = true;
                                //     pixelCounter = 0;
                                // }
                            }
                            else {
                                // System.out.println("Up collision");
                            }
                            break;
                        case "down":
                            if (!downCollision) {
                                worldY += speed;
                                // pixelCounter+=speed;
                                // if (pixelCounter == gp.tileSize/3) {
                                //     moving = false;
                                //     ready = true;
                                //     pixelCounter = 0;
                                // }
                            }
                            else {
                                // System.out.println("Down collision");
                            }
                            break;
                        case "left":
                            if (!leftCollision) {
                                worldX -= speed;
                                // pixelCounter+=speed;
                                // if (pixelCounter == gp.tileSize/3) {
                                //     moving = false;
                                //     ready = true;
                                //     pixelCounter = 0;
                                // }
                            }
                            else {
                                // System.out.println("left collision");
                            }
                            break;
                        case "right":
                            if (!rightCollision) {
                                worldX += speed;
                                // pixelCounter+=speed;
                                // if (pixelCounter == gp.tileSize/3) {
                                //     moving = false;
                                //     ready = true;
                                //     pixelCounter = 0;
                                // }
                            }
                            else {
                                // System.out.println("Right collision");
                            }
                            break;
                    }
    
                    upCollision = false;
                    downCollision = false;
                    leftCollision = false;
                    rightCollision = false;

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
                
                if (this.name != null && this.name.equals("Green Slime")) {
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


                pixelCounter+=speed;
                if (pixelCounter == gp.tileSize/3) {
                    moving = false;
                    ready = true;
                    pixelCounter = 0;
                }
            }
            // if (!hasMovement) {
            //     spriteCounter++;
            //     if(spriteCounter > 20) {
            //         if (spriteNum == 1) {
            //             spriteNum = 2;
            //         }
            //         else if (spriteNum == 2) {
            //             spriteNum = 1;
            //         }
            //         spriteCounter = 0;
            //     }
            // }

            if (invincible) {
                invincibleCounter++;
                if (invincibleCounter > 40) {
                    invincible = false;
                    invincibleCounter = 0;
                }
            }
            if (shotAvailableCounter < 30) {
                shotAvailableCounter++;
            }
            if (offBalance) {
                offBalanceCounter++;
                if (offBalanceCounter >= 60) {
                    offBalance = false;
                    offBalanceCounter = 0;
                }
            }
        // }


        // if (collisionOn == false) {
        //     switch (direction) {
        //         case "up": 
        //             worldY -= speed;
        //             break;
        //         case "down":
        //             worldY += speed;
        //             break;
        //         case "left":
        //             worldX -= speed;
        //             break;
        //         case "right":
        //             worldX += speed;
        //             break;
        //     }
        // }

        // spriteCounter++;
        // if(spriteCounter > 10) {
        //     if (spriteNum == 1) {
        //         spriteNum = 2;
        //     }
        //     else if (spriteNum == 2) {
        //         spriteNum = 1;
        //     }
        //     spriteCounter = 0;
        // }
        

    }

    public void attacking() {
        spriteCounter++;

        if (spriteCounter <= motion1Duration) {
            spriteNum = 1;
        }
        if (spriteCounter > motion1Duration && spriteCounter <= motion2Duration) {
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
            
            if (type == typeMonster) {
                if (gp.cChecker.checkPlayer(this)) {
                    damagePlayer(attack);
                }
            }
            else { //Player
                int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);

                gp.player.damageMonster(monsterIndex, this, attack, currentWeapon.knockBackPower);
    
                int iTileIndex = gp.cChecker.checkEntity(this, gp.iTile);
                gp.player.damageInteractiveTile(iTileIndex);
    
                int projectileIndex = gp.cChecker.checkEntity(this, gp.projectile);
                gp.player.damageProjectile(projectileIndex);
    
            }
            
            worldX = currentWorldX;
            worldY = currentWorldY;
            solidArea.width = solidAreaWidth;
            solidArea.height = solidAreaHeight;

        }



        if (spriteCounter> motion2Duration) {
            spriteNum = 1;
            spriteCounter = 0;
            attacking = false;
        }

    }

    public void checkAttackOrNot(int rate, int straight, int horizontal) {
        boolean targetInRange = false;
        int xDistance = getXDistance(gp.player);
        int yDistance = getYDistance(gp.player);

        switch(direction) {
            case "up":
                if (gp.player.worldY < worldY && yDistance < straight && xDistance <= horizontal) {
                    targetInRange = true;
                }
                break;
            case "down":
                if (gp.player.worldY > worldY && yDistance < straight && xDistance < horizontal) {
                    targetInRange = true;
                }
                break;
            case "left":
                if (gp.player.worldX < worldX && yDistance < straight && xDistance <= horizontal) {
                    targetInRange = true;
                }
                break;
            case "right":
                if (gp.player.worldX > worldX && yDistance < straight && xDistance <= horizontal) {
                    targetInRange = true;
                }
                break;
        }

        // System.out.println("inrange?" + targetInRange);
        if (targetInRange) {
            int i = new Random().nextInt(rate);
            if (i == 0) {
                attacking = true;
                spriteNum = 1;
                spriteCounter = 0;
                shotAvailableCounter = 0;
                // System.out.println("The monster is attacking");
            }
        }
    }

    public String getOppositeDirection(String direction) {
        String opposite = "";
        switch (direction) {
            case "up": opposite = "down"; break;
            case "down": opposite = "up"; break;
            case "left": opposite = "right"; break;
            case "right": opposite = "left"; break;
        }

        return opposite;
    }

    public void damagePlayer(int attack) {
        if (gp.player.invincible == false) {
            int damage = attack - gp.player.defense;

            String canGuardDirection = getOppositeDirection(direction);

            if (gp.player.guarding && gp.player.direction.equals(canGuardDirection)) {
                if (gp.player.guardCounter < 10) {
                    damage = 0;
                    setKnockBack(this, gp.player, knockBackPower);
                    // knock(this, gp.player, knockBackPower);
                    offBalance = true;
                    spriteCounter -= 60;
                }
                else {
                    damage /= 3;
                }
                
                
            }
            else {
                if (damage < 1) {
                    damage = 1;
                }
            }

            if (damage != 0) {
                gp.player.transparent = true;
                if (gp.player.moving == false) {
                    setKnockBack(gp.player, this, knockBackPower);
                    // System.out.println("This knockback power is: " + knockBackPower);
                }
                
                // knock(gp.player, this, knockBackPower);
            }

            gp.player.life -= damage;
            gp.player.invincible = true;
        }

    }

    public void draw(Graphics2D g2) {
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        BufferedImage image = null;

        // // Only draws pixels on the screen
        if (worldX + gp.tileSize> gp.player.worldX - gp.player.screenX && 
            worldX - gp.tileSize< gp.player.worldX + gp.player.screenX && 
            worldY + gp.tileSize> gp.player.worldY - gp.player.screenY && 
            worldY - gp.tileSize< gp.player.worldY + gp.player.screenY) {
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

                if (type == 2 && hpBarOn) {

                    double oneScale = (double) gp.tileSize/maxLife;
                    double hpBarValue = oneScale * life;

                    g2.setColor(new Color(35, 35, 35));
                    g2.fillRect(screenX-1, screenY-16, gp.tileSize+2, 12);
                    g2.setColor(new Color(255, 0, 30));
                    g2.fillRect(screenX, screenY-15, (int)hpBarValue, 10);

                    hpBarCounter++;
                    if (hpBarCounter > 600) {
                        hpBarCounter = 0;
                        hpBarOn = false;
                    }
                }



                if (invincible) {
                    hpBarOn = true;
                    hpBarCounter = 0;
                    changeAlpha(g2, 0.4f);
                }

                if (dying) {
                    dyingAnimation(g2);
                    // attack = 0;
                }
                g2.drawImage(image, tempScreenX, tempScreenY, null);
                g2.setColor(Color.BLACK);
                // g2.drawRect(screenX + solidArea.x, screenY + solidArea.y, solidArea.width, solidArea.height);
                changeAlpha(g2, 1f);
       
        }
    }

    public void dyingAnimation(Graphics2D g2) {

        dyingCounter++;

        int i = 5;

        if (dyingCounter <= i) {changeAlpha(g2, 0f);}
        else if (dyingCounter <= i*2) {changeAlpha(g2, 1f);}
        else if (dyingCounter <= i*3) {changeAlpha(g2, 0f);}
        else if (dyingCounter <= i*4) {changeAlpha(g2, 1f);}
        else if (dyingCounter <= i*5) {changeAlpha(g2, 0f);}
        else if (dyingCounter <= i*6) {changeAlpha(g2, 1f);}
        else if (dyingCounter <= i*7) {changeAlpha(g2, 0f);}
        else if (dyingCounter <= i*8) {changeAlpha(g2, 1f);}
        else {
            // dying = false;
            alive = false;
        }
        
    }

    public void changeAlpha(Graphics2D g2, float alphaValue) {
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alphaValue));
    }

    public BufferedImage setUp(String imagePath, int width, int height) {
        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;

        try {
            image = ImageIO.read(new File("C:/Users/rober/OneDrive/Programming/AdventureGame/res/"+imagePath+".png"));
            image = uTool.scaleImage(image, width, height);
        }
        catch (Exception e) {

        }
        return image;

    }

    public void switchCases(Entity entity, String direction, int changeVal, boolean positiveChange) {
        if (!positiveChange) {
            changeVal *= (-1);
        }

        switch (direction) {
            case "up": 
                entity.worldY -= changeVal;
                break;
            case "down":
                entity.worldY += changeVal;
                break;
            case "left":
                entity.worldX -= changeVal;
                break;
            case "right":
                entity.worldX += changeVal;
                break;
        } 
    }

    public void searchPath(int goalCol, int goalRow) {
        int startCol = (worldX + solidArea.x)/gp.tileSize;
        int startRow = (worldY + solidArea.y)/gp.tileSize;

        gp.pFinder.setNodes(startCol, startRow, goalCol, goalRow);

        if (gp.pFinder.search()) {
            moving = true;
            int nextX = gp.pFinder.pathList.get(0).col * gp.tileSize;
            int nextY = gp.pFinder.pathList.get(0).row * gp.tileSize;

            int enLeftX = worldX + solidArea.x;
            int enRightX = worldX + solidArea.x + solidArea.width;

            int enTopY = worldY + solidArea.y;
            int enBottomY = worldY + solidArea.y + solidArea.height;

            if (enTopY > nextY && enLeftX >= nextX && enRightX <= nextX + gp.tileSize) {
                // System.out.println("1");
                direction = "up";
            }
            
            else if (enTopY < nextY && enLeftX >= nextX && enRightX <= nextX + gp.tileSize) {
                // System.out.println("2");
                direction = "down";
            }
            
            else if (enTopY >= nextY && enBottomY < nextY + gp.tileSize) {

                if (enLeftX > nextX) {
                    direction = "left";
                    // System.out.println("Moving: " + moving);
                    // System.out.println("3.1");
                }
                if (enLeftX < nextX) {
                    // System.out.println("3.2");
                    direction = "right";
                }
            }
            else if (enTopY > nextY && enLeftX > nextX) {
                // System.out.println("4");
                direction = "left";
                checkCollision();
                if (leftCollision == true) {
                    // System.out.println("4.1");
                    direction = "up";
                }
            }
            else if (enTopY > nextY && enLeftX < nextX) {
                // System.out.println("5");
                direction = "up";
                checkCollision();
                if (upCollision) {
                    // System.out.println("5.1");
                    direction = "right";
                }
            }
            else if (enTopY < nextY && enLeftX > nextX) {
                direction = "down";
                // System.out.println("6");
                checkCollision();
                if (downCollision) {
                    // System.out.println("6.1");
                    direction = "left";
                }
            }
            else if (enTopY < nextY && enLeftX < nextX) {
                // System.out.println("7");
                direction = "down";
                checkCollision();
                if (downCollision) {
                    // System.out.println("7.1");
                    direction = "right";
                }
            }

            


            //Goes to a specific location
            // int nextCol = gp.pFinder.pathList.get(0).col;
            // int nextRow = gp.pFinder.pathList.get(0).row;
            // if (nextCol == goalCol && nextRow == goalRow) {
            //     moving = false;
            // }
            

        }
        
        
    }

    public int getDetected(Entity user, Entity target[][], String targetName) {
        int index = 999;

        int nextWorldX = user.getLeftX();
        int nextWorldY = user.getTopY();

        switch (user.direction) {
            case "up": nextWorldY = user.getTopY()-gp.player.speed; break;
            case "down": nextWorldY = user.getBottomY()+gp.player.speed; break;
            case "left": nextWorldX = user.getLeftX() - gp.player.speed; break;
            case "right": nextWorldX = user.getRightX() + gp.player.speed; break;
        }

        int col = nextWorldX/gp.tileSize;
        int row = nextWorldY/gp.tileSize;

        for (int i = 0; i < target[1].length; i++) {
            if (target[gp.currentMap][i] != null) {
                if (target[gp.currentMap][i].getCol() == col && target[gp.currentMap][i].getRow() == row
                    && target[gp.currentMap][i].name.equals(targetName)) {
                        index = i;
                        break;
                    }
            }
        }
        return index;

    }

}



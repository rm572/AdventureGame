import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Entity {
    
    public int worldX, worldY;
    public int speed;

    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public BufferedImage attackUp1, attackUp2, attackDown1, attackDown2, attackLeft1,
        attackLeft2, attackRight1, attackRight2;
    public String direction = "down";

    public int spriteCounter = 0;
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
    int pixelCounter = 0;
    int actionLockCounter = 0;
    boolean moving = false;
    boolean ready = true;
    String dialogues[] = new String[20];
    int dialogueIndex = 0;
    boolean entityCollision = false;
    boolean attacking = false;

    boolean alive = true;
    boolean dying = false;
    boolean hpBarOn = false;

    int hpBarCounter = 0;
    public int shotAvailableCounter = 0;
    int dyingCounter = 0;

    public BufferedImage image, image2, image3;
    public String name;
    public boolean collision = false;

    public ArrayList<Entity> inventory = new ArrayList<>();
    public final int maxInventorySize = 20;
    int knockBackPower = 0;

    public boolean invincible = false;
    int invincibleCounter = 0;

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
    
    int value;
    int attackValue;
    int defenseValue;
    String description = "";
    public int useCost;
    int price;
    public boolean onPath = false;
    boolean knockBack = false;
    int knockBackCounter = 0;
    boolean removed = false;
    boolean highlight = false;
    Entity currentLight;
    int lightRadius;

    boolean stackable = false;
    int amount = 1;

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

        if (dialogues[dialogueIndex] == null) {
            dialogueIndex = 0;
        }
        gp.ui.currentDialogue = dialogues[dialogueIndex];
        dialogueIndex++;

        switch (gp.player.direction) {
            case "up":
                direction = "down";
                break;
            case "down":
                direction = "up";
                break;
            case "left":
                direction = "right";
                break;
            case "right":
                direction = "left";
                break;
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
            checkCollision();

            if (collisionOn) {
                knockBackCounter = 0;
                knockBack = false;
                speed = defaultSpeed;
            }
            else if (!collisionOn) {
                switchCases(direction, speed, true);

            }
            knockBackCounter++;
            if (knockBackCounter == 10) {
                knockBackCounter = 0;
                knockBack = false;
                speed = defaultSpeed;
            }
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
                                System.out.println("Up collision");
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
                                System.out.println("Down collision");
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
                                System.out.println("left collision");
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
                                System.out.println("Right collision");
                            }
                            break;
                    }
    
                    upCollision = false;
                    downCollision = false;
                    leftCollision = false;
                    rightCollision = false;
    
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

                pixelCounter+=speed;
                if (pixelCounter == gp.tileSize/3) {
                    moving = false;
                    ready = true;
                    pixelCounter = 0;
                }
            }
            if (!hasMovement) {
                spriteCounter++;
                if(spriteCounter > 20) {
                    if (spriteNum == 1) {
                        spriteNum = 2;
                    }
                    else if (spriteNum == 2) {
                        spriteNum = 1;
                    }
                    spriteCounter = 0;
                }
            }

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

    public void damagePlayer(int attack) {
        if (gp.player.invincible == false) {
            int damage = attack - gp.player.defense;
            if (damage < 0) {
                damage = 0;
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
                switch (direction) {
                    case "up":
                        if (spriteNum == 1) {image = up1;}
                        if (spriteNum == 2) {image = up2;}
                        break;
                    case "down":
                        if (spriteNum == 1) {image = down1;}
                        if (spriteNum == 2) {image = down2;}
                        break;
                    case "left":
                        if (spriteNum == 1) {image = left1;}
                        if (spriteNum == 2) {image = left2;}
                        break;
                    case "right":
                        if (spriteNum == 1) {image = right1;}
                        if (spriteNum == 2) {image = right2;}
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
                g2.drawImage(image, screenX, screenY, null);
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
            image = ImageIO.read(new File("C:/Users/rober/OneDrive/Programming/Workspace/Workspace/2DGame_Example/res/"+imagePath+".png"));
            image = uTool.scaleImage(image, width, height);
        }
        catch (Exception e) {

        }
        return image;

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
                System.out.println("1");
                direction = "up";
            }
            
            else if (enTopY < nextY && enLeftX >= nextX && enRightX <= nextX + gp.tileSize) {
                System.out.println("2");
                direction = "down";
            }
            
            else if (enTopY >= nextY && enBottomY < nextY + gp.tileSize) {

                if (enLeftX > nextX) {
                    direction = "left";
                    System.out.println("Moving: " + moving);
                    System.out.println("3.1");
                }
                if (enLeftX < nextX) {
                    System.out.println("3.2");
                    direction = "right";
                }
            }
            else if (enTopY > nextY && enLeftX > nextX) {
                System.out.println("4");
                direction = "left";
                checkCollision();
                if (leftCollision == true) {
                    System.out.println("4.1");
                    direction = "up";
                }
            }
            else if (enTopY > nextY && enLeftX < nextX) {
                System.out.println("5");
                direction = "up";
                checkCollision();
                if (upCollision) {
                    System.out.println("5.1");
                    direction = "right";
                }
            }
            else if (enTopY < nextY && enLeftX > nextX) {
                direction = "down";
                System.out.println("6");
                checkCollision();
                if (downCollision) {
                    System.out.println("6.1");
                    direction = "left";
                }
            }
            else if (enTopY < nextY && enLeftX < nextX) {
                System.out.println("7");
                direction = "down";
                checkCollision();
                if (downCollision) {
                    System.out.println("7.1");
                    direction = "right";
                }
            }

            


            //Goes to a specific location
            int nextCol = gp.pFinder.pathList.get(0).col;
            int nextRow = gp.pFinder.pathList.get(0).row;
            if (nextCol == goalCol && nextRow == goalRow) {
                moving = false;
            }
            

        }
        
        
    }

    public int getDetected(Entity user, Entity target[][], String targetName) {
        int index = 999;

        int nextWorldX = user.getLeftX();
        int nextWorldY = user.getTopY();

        switch (user.direction) {
            case "up": nextWorldY = user.getTopY()-1; break;
            case "down": nextWorldY = user.getBottomY()+1; break;
            case "left": nextWorldX = user.getLeftX() - 1; break;
            case "right": nextWorldX = user.getRightX() + 1; break;
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



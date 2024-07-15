import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable {
    // SCREEN SETTINGS
    final int originalTileSize = 16; //16x16 tile
    final int scale = 3;

    public final int tileSize = originalTileSize * scale; //48x48 tile
    // public final int maxScreenCol = 16;

    //Full screen mode code
    public final int maxScreenCol = 20;
    //

    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol; // 768 pixels -> 960 with full screen
    public final int screenHeight = tileSize * maxScreenRow; // 576 pixels

    //World settings
    // public final int maxWorldCol = 50;
    // public final int maxWorldRow = 50;
    public int maxWorldCol;
    public int maxWorldRow;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;
    public final int maxMap = 10;
    int currentMap = 0;

    //Full screen code
    int screenWidth2 = screenWidth;
    int screenHeight2 = screenHeight;
    BufferedImage tempScreen;
    Graphics2D g2;
    boolean fullScreenOn = false;

    //


    // FPS
    int FPS = 60;

    TileManager tileM = new TileManager(this);
    KeyHandler keyH = new KeyHandler(this);
    Sound sound = new Sound();
    Sound se = new Sound();
    Thread gameThread;
    CollisionChecker cChecker = new CollisionChecker(this);
    AssetSetter aSetter = new AssetSetter(this);
    UI ui = new UI(this);
    EventHandler eHandler = new EventHandler(this);
    Config config = new Config(this);
    PathFinder pFinder = new PathFinder(this);
    Map map = new Map(this);
    SaveLoad saveLoad = new SaveLoad(this);


    Player player = new Player(this, keyH);
    Entity obj[][] = new Entity[maxMap][20];
    Entity npc[][] = new Entity[maxMap][10];
    InteractiveTile iTile[][] = new InteractiveTile[maxMap][50];
    Entity[][] monster = new Entity[maxMap][20];
    ArrayList<Entity> entityList = new ArrayList<>();
    // ArrayList<Entity> projectileList = new ArrayList<>();
    Entity[][] projectile = new Entity[maxMap][20];
    ArrayList<Entity> particleList = new ArrayList<>();
    EnvironmentManager eManager = new EnvironmentManager(this);
    EntityGenerator eGenerator = new EntityGenerator(this);
    

    public int gameState;
    
    public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogueState = 3;
    public final int characterState = 4;
    public final int optionsState = 5;
    public final int gameOverState = 6;
    public final int transitionState = 7;
    public final int tradeState = 8;
    public final int sleepState = 9;
    public final int mapState = 10;



    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void setUpGame() {
        
        aSetter.setObject();
        aSetter.setNPC();
        aSetter.setMonster();
        aSetter.setInteractiveTile();
        eManager.setup();
        // playMusic(0);
        gameState = titleState;
 
        //Full screen code
        tempScreen = new BufferedImage(screenWidth, screenHeight, BufferedImage.TYPE_INT_ARGB);
        g2 = (Graphics2D) tempScreen.getGraphics();
        if (fullScreenOn) {
            setFullScreen();
        }

        //
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void resetGame(boolean restart) {
        
        player.setDefaultPositions();
        player.restoreStatus();
        player.resetCounter();
        aSetter.setNPC();
        aSetter.setMonster();

        if (restart) {
            player.setDefaultValues();
            // player.setItems();
            aSetter.setObject();
            aSetter.setInteractiveTile();
            eManager.lighting.resetDay();
        }

    }
    /*
    public void retry() {

        player.setDefaultPositions();
        player.restoreLifeandMana();
        aSetter.setNPC();
        aSetter.setMonster();
    }

    public void restart() {
        player.setDefaultValues();
        player.setDefaultPositions();
        player.restoreLifeandMana();
        player.setItems();
        aSetter.setObject();
        aSetter.setNPC();
        aSetter.setMonster();
        aSetter.setInteractiveTile();
    }*/

    /*// @Override
    // public void run() {
    //     double drawInterval = 1000000000/FPS;
    //     double nextDrawTime = System.nanoTime() + drawInterval;

    //     //Game loop
    //     while (gameThread != null) {

    //         update();
            
    //         repaint();
            

    //         try {
    //             double remainingTime = nextDrawTime - System.nanoTime();
    //             remainingTime = remainingTime/1000000;

    //             if (remainingTime < 0) {
    //                 remainingTime = 0;
    //             }

    //             Thread.sleep((long) remainingTime);

    //             nextDrawTime += drawInterval;
    //         } catch (InterruptedException e) {
    //             // TODO Auto-generated catch block
    //             e.printStackTrace();
    //         }
    //     }
    // }*/

    @Override
    public void run() {
        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        //Game loop
        while (gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime)/drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if (delta >= 1) {
                update();
                

                //Full screen code
                // if (fullScreenOn) {
                    drawToTempScreen();
                    drawToScreen();
                // }
                // else {
                    // repaint(); //-> regular code
                // }

                
                delta--;
                drawCount++;
            }

            if (timer >= 1000000000) {
                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            } 
        }
    }


    //Full screen code
    public void drawToScreen() {
        Graphics g = getGraphics();
        g.drawImage(tempScreen, 0, 0, screenWidth2, screenHeight2, null);
        g.dispose();
    }

    //Full screen code
    public void setFullScreen() {

        //RYI snow solution:
        // Get local screen device
        // GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        // GraphicsDevice gd = ge.getDefaultScreenDevice();

        // gd.setFullScreenWindow(Main.window);
        // screenWidth2 = Main.window.getWidth();
        // screenHeight2 = Main.window.getHeight();

        //YT comment:
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double width = screenSize.getWidth();
		double height = screenSize.getHeight();
		Main.window.setExtendedState(JFrame.MAXIMIZED_BOTH);
		screenWidth2 = (int) width;
		screenHeight2 = (int) height;
    }

    public void update() {

        if (gameState == playState) {
            player.update();
            for (int i = 0; i < npc[1].length; i++) {
                if (npc[currentMap][i] != null) {
                    npc[currentMap][i].update();
                }
            }

            for (int i = 0; i < monster[1].length; i++) {
                if (monster[currentMap][i] != null) {
                    if (monster[currentMap][i].alive && monster[currentMap][i].dying == false) {
                        monster[currentMap][i].update();
                    }

                    if (!monster[currentMap][i].alive) {
                        monster[currentMap][i].checkDrop();
                        monster[currentMap][i] = null;
                    }
                    
                }
            }
            // for (int i = 0; i < projectileList.size(); i++) {
            //     if (projectileList.get(i) != null) {
                    
            //         if (projectileList.get(i).alive) {
            //             projectileList.get(i).update();
            //         }

            //         if (!projectileList.get(i).alive) {
            //             projectileList.remove(i);
            //         }
                    
            //     }
            // }

            for (int i = 0; i < projectile[1].length; i++) {
                if (projectile[currentMap][i] != null) {
                    
                    if (projectile[currentMap][i].alive) {
                        projectile[currentMap][i].update();
                    }

                    if (!projectile[currentMap][i].alive) {
                        projectile[currentMap][i] = null;
                    }
                    
                }
            }

            for (int i = 0; i < particleList.size(); i++) {
                if (particleList.get(i) != null) {
                    
                    if (particleList.get(i).alive) {
                        particleList.get(i).update();
                    }

                    if (!particleList.get(i).alive) {
                        particleList.remove(i);
                    }
                    
                }
            }

            for (int i = 0; i < iTile[1].length; i++) {
                if (iTile[currentMap][i] != null) {
                    iTile[currentMap][i].update();
                }
            }
            if (currentMap == 0) {
                eManager.update();
            }
            // eManager.update();
        }
        if (gameState == pauseState) {
            //nothing
        }
        
    }

    // Regular Code
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        long drawStart = 0;
        if (keyH.showDebugText) {
            drawStart = System.nanoTime();
        }
        
        //title screen
        if (gameState == titleState) {
            ui.draw(g2);
        }
        //others
        else {
            //tile
            tileM.draw(g2);

            //Interactive Tiles
            for (int i = 0; i < iTile[1].length; i++) {
                if (iTile[currentMap][i] != null) {
                    iTile[currentMap][i].draw(g2);
                }
            }
            
            // This one works fine for tile-based movement
            //object
            for (int i = 0; i < obj[1].length; i++) {
                if (obj[currentMap][i] != null) {
                    obj[currentMap][i].draw(g2);
                }
            }

            //npc
            for (int i = 0; i < npc[1].length; i++) {
                if (npc[currentMap][i] != null) {
                    npc[currentMap][i].draw(g2);
                }
            }

            //monster
            for (int i = 0; i < monster[1].length; i++) {
                if (monster[currentMap][i] != null) {
                    monster[currentMap][i].draw(g2);
                }
            }

            //projectile
            // for (int i = 0; i < projectileList.size(); i++) {
            //     if (projectileList.get(i) != null) {
            //         projectileList.get(i).draw(g2);
            //     }
            // }

            for (int i = 0; i < projectile[1].length; i++) {
                if (projectile[currentMap][i] != null) {
                    projectile[currentMap][i].draw(g2);
                }
            }
            

            //particle
            for (int i = 0; i < particleList.size(); i++) {
                if (particleList.get(i) != null) {
                    particleList.get(i).draw(g2);
                }
            }





            //player
            player.draw(g2);

            // // //Non-tile based movement: Draw them in order, with the smallest WorldY being drawn first
            // //Sort the list
            // entityList.add(player);

            // for (int i = 0; i < npc.length; i++) {
            //     if (npc[i] != null) {
            //         entityList.add(npc[i]);
            //     }
            // }

            // for (int i = 0; i < obj.length; i++) {
            //     if (obj[i] != null) {
            //         entityList.add(obj[i]);
            //     }
            // }

            // for (int i = 0; i < monster.length; i++) {
            //     if (monster[i] != null) {
            //         entityList.add(monster[i]);
            //     }
            // }

            // //Sort
            // Collections.sort(entityList, new Comparator<Entity>() {

            //     @Override
            //     public int compare(Entity e1, Entity e2) {
            //         int result = Integer.compare(e1.worldY, e2.worldY);
            //         return result;
            //     }
                
            // });

            // //draw entities
            // for (Entity e : entityList) {
            //     e.draw(g2);
            // }

            // //empty the list
            // entityList.clear();

            ui.draw(g2);
        }

        

        if (keyH.showDebugText) {
            long drawEnd = System.nanoTime();
            long passed = drawEnd - drawStart;

            g2.setFont(new Font("Arial", Font.PLAIN, 20));
            g2.setColor(Color.WHITE);
            int x = 10;
            int y = 400;
            int lineHeight = 20;

            g2.drawString("WorldX: " + player.worldX, x, y);
            y += lineHeight;
            g2.drawString("WorldY: " + player.worldY, x, y);
            y += lineHeight;
            g2.drawString("Col: " + (player.worldX + player.solidArea.x)/tileSize, x, y);
            y += lineHeight;
            g2.drawString("Row: " + (player.worldY + player.solidArea.y)/tileSize, x, y);
            y += lineHeight;

            g2.drawString("Draw Time: " + passed, x, y);
            System.out.println("Draw Time: " + passed);

        }



        g2.dispose();

    }


    //Full screen code
    public void drawToTempScreen() {
        long drawStart = 0;
        if (keyH.showDebugText) {
            drawStart = System.nanoTime();
        }
        
        //title screen
        if (gameState == titleState) {
            ui.draw(g2);
        }

        //map screen
        else if (gameState == mapState) {
            map.drawFullMapScreen(g2);
        }
        //others
        else {
            //tile
            tileM.draw(g2);

            //Interactive Tiles
            for (int i = 0; i < iTile[1].length; i++) {
                if (iTile[currentMap][i] != null) {
                    iTile[currentMap][i].draw(g2);
                }
            }
            
            // This one works fine for tile-based movement
            //object
            for (int i = 0; i < obj[1].length; i++) {
                if (obj[currentMap][i] != null) {
                    obj[currentMap][i].draw(g2);
                }
            }

            //npc
            for (int i = 0; i < npc[1].length; i++) {
                if (npc[currentMap][i] != null) {
                    npc[currentMap][i].draw(g2);
                }
            }

            //monster
            for (int i = 0; i < monster[1].length; i++) {
                if (monster[currentMap][i] != null) {
                    monster[currentMap][i].draw(g2);
                }
            }

            //projectile
            // for (int i = 0; i < projectileList.size(); i++) {
            //     if (projectileList.get(i) != null) {
            //         projectileList.get(i).draw(g2);
            //     }
            // }

            for (int i = 0; i < projectile[1].length; i++) {
                if (projectile[currentMap][i] != null) {
                    projectile[currentMap][i].draw(g2);
                }
            }

            //particle
            for (int i = 0; i < particleList.size(); i++) {
                if (particleList.get(i) != null) {
                    particleList.get(i).draw(g2);
                }
            }





            //player
            player.draw(g2);

            // // //Non-tile based movement: Draw them in order, with the smallest WorldY being drawn first
            // //Sort the list
            // entityList.add(player);

            // for (int i = 0; i < npc.length; i++) {
            //     if (npc[i] != null) {
            //         entityList.add(npc[i]);
            //     }
            // }

            // for (int i = 0; i < obj.length; i++) {
            //     if (obj[i] != null) {
            //         entityList.add(obj[i]);
            //     }
            // }

            // for (int i = 0; i < monster.length; i++) {
            //     if (monster[i] != null) {
            //         entityList.add(monster[i]);
            //     }
            // }

            // //Sort
            // Collections.sort(entityList, new Comparator<Entity>() {

            //     @Override
            //     public int compare(Entity e1, Entity e2) {
            //         int result = Integer.compare(e1.worldY, e2.worldY);
            //         return result;
            //     }
                
            // });

            // //draw entities
            // for (Entity e : entityList) {
            //     e.draw(g2);
            // }

            // //empty the list
            // entityList.clear();

            eManager.draw(g2);

            //minimap
            map.drawMiniMap(g2);

            ui.draw(g2);
        }

        

        if (keyH.showDebugText) {
            long drawEnd = System.nanoTime();
            long passed = drawEnd - drawStart;

            g2.setFont(new Font("Arial", Font.PLAIN, 20));
            g2.setColor(Color.WHITE);
            int x = 10;
            int y = 400;
            int lineHeight = 20;

            g2.drawString("WorldX: " + player.worldX, x, y);
            y += lineHeight;
            g2.drawString("WorldY: " + player.worldY, x, y);
            y += lineHeight;
            g2.drawString("Col: " + (player.worldX + player.solidArea.x)/tileSize, x, y);
            y += lineHeight;
            g2.drawString("Row: " + (player.worldY + player.solidArea.y)/tileSize, x, y);
            y += lineHeight;

            g2.drawString("Draw Time: " + passed, x, y);
            System.out.println("Draw Time: " + passed);

        }
    }

    public void playMusic(int i) {
        sound.setFile(i);
        sound.play();
        sound.loop();
    }

    public void stopMusic() {
        sound.stop();
    }

    public void playSE(int i) {
        //for sound effects
        se.setFile(i);
        se.play();
    }
}

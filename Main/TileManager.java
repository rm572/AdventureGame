import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

import javax.imageio.ImageIO;

public class TileManager {
    GamePanel gp;
    Tile[] tile;
    int mapTileNum[][][];
    boolean drawPath = true;
    
    //tile editor
    ArrayList<String> fileNames = new ArrayList<>();
    ArrayList<String> collisionStatus = new ArrayList<>();



    public TileManager(GamePanel gp) {
        this.gp = gp;

        //tile editor
        // InputStream is = getClass().getResourceAsStream("C:/Users/rober/OneDrive/Programming/AdventureGame/res/Maps/editorMap.txt");
       
        String line;
        try {
            BufferedReader br = new BufferedReader(new FileReader("C:/Users/rober/OneDrive/Programming/AdventureGame/res/Maps/editorMapTileData.txt"));

            while ((line = br.readLine()) != null) {
                fileNames.add(line);
                collisionStatus.add(br.readLine());
            }
            br.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        tile = new Tile[fileNames.size()];
        getTileImage();

        // is = getClass().getResourceAsStream("C:/Users/rober/OneDrive/Programming/AdventureGame/res/Maps/editorMap.txt");
       
        try {
            BufferedReader br = new BufferedReader(new FileReader("C:/Users/rober/OneDrive/Programming/AdventureGame/res/Maps/editorMap.txt"));

            String line2 = br.readLine();
            String maxTile[] = line2.split(" ");

            gp.maxWorldCol = maxTile.length;
            gp.maxWorldRow = maxTile.length;


            mapTileNum = new int[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];

            br.close();
            
        }
        catch (IOException e) {

        }

        loadMap("C:/Users/rober/OneDrive/Programming/AdventureGame/res/Maps/editorMap.txt", 0);
        
        //

        //Not tile editor

        // tile = new Tile[100];
        // mapTileNum = new int[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];
        

        // getTileImage();
        // loadMap("C:/Users/rober/OneDrive/Programming/AdventureGame/res/Maps/world1.txt", 0);
        // loadMap("C:/Users/rober/OneDrive/Programming/AdventureGame/res/Maps/indoor01.txt", 1);
        // print();
    }

    public void getTileImage() {

        for (int i = 0; i < fileNames.size(); i++) {
            String fileName;
            boolean collision;

            fileName = fileNames.get(i);
            if (collisionStatus.get(i).equals("true")) {
                collision = true;
            }
            else {
                collision = false;
            }

            setUp(i, fileName, collision);
            
        }



        // try {
        //     int i = 0;
        //     setUp(i, "blank", false); //0
        //     i++;

        //     setUp(i, "blank", false); //1
        //     i++;

        //     setUp(i, "blank", false); //2
        //     i++;

        //     setUp(i, "blank", false); //3
        //     i++;

        //     setUp(i, "blank", false); //4
        //     i++;

        //     setUp(i, "blank", false); //5
        //     i++;

        //     setUp(i, "blank", false); //6
        //     i++;

        //     setUp(i, "blank", false); //7
        //     i++;

        //     setUp(i, "blank", false); //8
        //     i++;

        //     setUp(i, "blank", false); //9
        //     i++;


        //     setUp(i, "grass00", false); //10
        //     i++;

        //     setUp(i, "grass01", false); //11
        //     i++;
            
        //     setUp(i, "water11", true); //12
        //     i++;
            
        //     setUp(i, "earth", false); //13
        //     i++;
            
        //     setUp(i, "tree00", true); //14
        //     i++;
            
        //     setUp(i, "sand11", false); //15
        //     i++;
            
        //     setUp(i, "hut00", false); //16
        //     i++;
            
        //     setUp(i, "floor01", false); //17
        //     i++;
            
        //     setUp(i, "table01", true); //18
        //     i++;
            
        //     setUp(i, "blank", false); //19
        //     i++;

        //     setUp(i, "wall", true); //20
        //     i++;

        //     setUp(i, "water10", true); //21
        //     i++;

        //     setUp(i, "water12", true); //22
        //     i++;

        //     setUp(i, "water00", true); //23
        //     i++;

        //     setUp(i, "water02", true); //24
        //     i++;

        //     setUp(i, "water20", true); //25
        //     i++;

        //     setUp(i, "water22", true); //26
        //     i++;

        //     setUp(i, "water21", true); //27
        //     i++;

        //     setUp(i, "water01", true); //28
        //     i++;

        //     setUp(i, "sand00", true); //29
        //     i++;

        //     setUp(i, "sand01", true); //30
        //     i++;

        //     setUp(i, "sand02", true); //31
        //     i++;

        //     setUp(i, "sand10", true); //32
        //     i++;

        //     setUp(i, "sand21", true); //33
        //     i++;

        //     setUp(i, "sand20", true); //34
        //     i++;

        //     setUp(i, "sand12", true); //35
        //     i++;

        //     setUp(i, "sand22", true); //36
        //     i++;
            
            // tile[0] = new Tile();
            // tile[0].image = ImageIO.read(new File("C:/Users/rober/OneDrive/Programming/Workspace/Workspace/2DGame_Example/res/Tiles/grass3.png"));           

            // tile[1] = new Tile();
            // tile[1].image = ImageIO.read(new File("C:/Users/rober/OneDrive/Programming/Workspace/Workspace/2DGame_Example/res/Tiles/wall.png"));
            // tile[1].collision = true;
            
            // tile[2] = new Tile();
            // tile[2].image = ImageIO.read(new File("C:/Users/rober/OneDrive/Programming/Workspace/Workspace/2DGame_Example/res/Tiles/water3.png"));
            // tile[2].collision = true;

            // tile[3] = new Tile();
            // tile[3].image = ImageIO.read(new File("C:/Users/rober/OneDrive/Programming/Workspace/Workspace/2DGame_Example/res/Tiles/earth.png"));
            
            // tile[4] = new Tile();
            // tile[4].image = ImageIO.read(new File("C:/Users/rober/OneDrive/Programming/Workspace/Workspace/2DGame_Example/res/Tiles/tree.png"));
            // tile[4].collision = true;

            // tile[5] = new Tile();
            // tile[5].image = ImageIO.read(new File("C:/Users/rober/OneDrive/Programming/Workspace/Workspace/2DGame_Example/res/Tiles/sand3.png"));
            
        // }
        // catch(Exception e) {
        //     e.printStackTrace();
        // }
    }

    public void setUp(int index, String imageName, boolean collision) {
        UtilityTool uTool = new UtilityTool();
        try {
            tile[index] = new Tile();
            tile[index].image = ImageIO.read(new File("C:/Users/rober/OneDrive/Programming/AdventureGame/res/Tile_editor/" + imageName/* + ".png"*/));
            tile[index].image = uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
            tile[index].collision = collision;
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadMap(String filePath, int map) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath));

            int col = 0;
            int row = 0;
            // System.out.println("before while loop");
            while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
                String line = br.readLine();
                System.out.println("line: " + line);
                String numbers[] = line.split(" ");
                while (col < gp.maxWorldCol) {
                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[map][col][row] = num;
                    col++;
                    // System.out.println("in the first while loop");
                }
                // System.out.println("Out of the first while loop");

                if (col == gp.maxWorldCol) {
                    col = 0;
                    row++;
                }
                // System.out.println("row: " + row);
                
            }
            br.close();

        }
        catch (Exception e) {
            
        }
    }

    public void draw(Graphics2D g2) {
        int worldCol = 0;
        int worldRow = 0;

        //Used for no camera
        /* int x = 0;
        int y = 0; */

        // print();

        while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {

            int tileNum = mapTileNum[gp.currentMap][worldCol][worldRow];

            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;



            // Only draws pixels on the screen
            if (worldX + gp.tileSize> gp.player.worldX - gp.player.screenX && 
                worldX - gp.tileSize< gp.player.worldX + gp.player.screenX && 
                worldY + gp.tileSize> gp.player.worldY - gp.player.screenY && 
                worldY - gp.tileSize< gp.player.worldY + gp.player.screenY) {
                    g2.drawImage(tile[tileNum].image, screenX, screenY, null);
           
            }
            // g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
            
            worldCol++;
            //Used for no camera
            // x += gp.tileSize;

            if (worldCol == gp.maxWorldCol) {
                worldCol = 0;
                //Used for no camera
                // x = 0;
                // y += gp.tileSize;
                worldRow++;
                
            }


        }

        if (drawPath) {
            g2.setColor(new Color(255, 0, 0, 70));
            for (int i = 0; i < gp.pFinder.pathList.size(); i++) {
                int worldX = gp.pFinder.pathList.get(i).col * gp.tileSize;
                int worldY = gp.pFinder.pathList.get(i).row * gp.tileSize;
                int screenX = worldX - gp.player.worldX + gp.player.screenX;
                int screenY = worldY - gp.player.worldY + gp.player.screenY;

                g2.fillRect(screenX, screenY, gp.tileSize, gp.tileSize);
            }
        }

        
        // g2.drawImage(tile[0].image, 0, 0, gp.tileSize, gp.tileSize, null);
        // g2.drawImage(tile[1].image, 48, 0, gp.tileSize, gp.tileSize, null);
        // g2.drawImage(tile[2].image, 96, 0, gp.tileSize, gp.tileSize, null);
    
    }
}

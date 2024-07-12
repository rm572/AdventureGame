public class CollisionChecker {
    GamePanel gp;

    public CollisionChecker(GamePanel gp) {
        this.gp = gp;
    }

    public void checkTile(Entity entity) {
        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityToptWorldY = entity.worldY + entity.solidArea.y;
        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol = entityLeftWorldX/gp.tileSize;
        int entityRightCol = entityRightWorldX/gp.tileSize;
        int entityTopRow = entityToptWorldY/gp.tileSize;
        int entityBottomRow = entityBottomWorldY/gp.tileSize;

        int tileNum1, tileNum2;

        String direction = entity.direction;
        if (entity.knockBack) {
            direction = entity.knockBackDirection;
        }

        switch(direction) {
            case "up":
                entityTopRow = (entityToptWorldY - entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[gp.currentMap][entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[gp.currentMap][entityRightCol][entityTopRow];
                if (gp.tileM.tile[tileNum1].collision == true || 
                    gp.tileM.tile[tileNum2].collision == true) {
                        entity.collisionOn = true;

                        entity.upCollision = true;
                        entity.downCollision = false;
                        entity.leftCollision = false;
                        entity.rightCollision = false;
                        // System.out.println("Tile Collision: up");
                    }
                break;
            case "down":
                entityBottomRow = (entityBottomWorldY + entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[gp.currentMap][entityLeftCol][entityBottomRow];
                tileNum2 = gp.tileM.mapTileNum[gp.currentMap][entityRightCol][entityBottomRow];
                if (gp.tileM.tile[tileNum1].collision == true || 
                    gp.tileM.tile[tileNum2].collision == true) {
                        entity.collisionOn = true;
                        // entity.downCollision = true;
                        
                        entity.upCollision = false;
                        entity.downCollision = true;
                        entity.leftCollision = false;
                        entity.rightCollision = false;
                        // System.out.println("Tile Collision: down");
                    }
                break;
            case "left":
                entityLeftCol = (entityLeftWorldX - entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[gp.currentMap][entityLeftCol][entityBottomRow];
                tileNum2 = gp.tileM.mapTileNum[gp.currentMap][entityLeftCol][entityTopRow];
                if (gp.tileM.tile[tileNum1].collision == true || 
                    gp.tileM.tile[tileNum2].collision == true) {
                        entity.collisionOn = true;
                        // entity.leftCollision = true;

                        
                        entity.upCollision = false;
                        entity.downCollision = false;
                        entity.leftCollision = true;
                        entity.rightCollision = false;
                        // System.out.println("Tile Collision: left");
                    }
                break;
            case "right":
                entityRightCol = (entityRightWorldX + entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[gp.currentMap][entityRightCol][entityBottomRow];
                tileNum2 = gp.tileM.mapTileNum[gp.currentMap][entityRightCol][entityTopRow];
                if (gp.tileM.tile[tileNum1].collision == true || 
                    gp.tileM.tile[tileNum2].collision == true) {
                        entity.collisionOn = true;
                        // entity.rightCollision = true;

                        
                        entity.upCollision = false;
                        entity.downCollision = false;
                        entity.leftCollision = false;
                        entity.rightCollision = true;
                        // System.out.println("Tile Collision: right");
                    }
                break;
        }
    
    }

    public int checkObject(Entity entity, boolean player) {
        int index = 999;

        for (int i = 0; i < gp.obj[1].length; i++) {
            if (gp.obj[gp.currentMap][i] != null) {
                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;

                gp.obj[gp.currentMap][i].solidArea.x = gp.obj[gp.currentMap][i].worldX + gp.obj[gp.currentMap][i].solidArea.x;
                gp.obj[gp.currentMap][i].solidArea.y = gp.obj[gp.currentMap][i].worldY + gp.obj[gp.currentMap][i].solidArea.y;

                switch(entity.direction) {
                    case "up":
                        entity.solidArea.y -= entity.speed;
                        if (entity.solidArea.intersects(gp.obj[gp.currentMap][i].solidArea)) {
                            if (gp.obj[gp.currentMap][i].collision == true) {
                                entity.collisionOn = true;
                                entity.upCollision = true;
                                entity.downCollision = false;
                                entity.leftCollision = false;
                                entity.rightCollision = false;
                                
                            }
                            if (player == true) {
                                index = i;
                            }
                        }
                        break;
                    case "down":
                        entity.solidArea.y += entity.speed;
                        if (entity.solidArea.intersects(gp.obj[gp.currentMap][i].solidArea)) {
                            if (gp.obj[gp.currentMap][i].collision == true) {
                                entity.collisionOn = true;
                                entity.upCollision = false;
                                entity.downCollision = true;
                                entity.leftCollision = false;
                                entity.rightCollision = false;
                            }
                            if (player == true) {
                                index = i;
                            }
                        }
                        break;
                    case "left":
                        entity.solidArea.x -= entity.speed;
                        if (entity.solidArea.intersects(gp.obj[gp.currentMap][i].solidArea)) {
                            if (gp.obj[gp.currentMap][i].collision == true) {
                                entity.collisionOn = true;
                                entity.upCollision = false;
                                entity.downCollision = false;
                                entity.leftCollision = true;
                                entity.rightCollision = false;
                            }
                            if (player == true) {
                                index = i;
                            }
                        }
                        break;
                    case "right":
                        if (entity.solidArea.intersects(gp.obj[gp.currentMap][i].solidArea)) {
                            if (gp.obj[gp.currentMap][i].collision == true) {
                                entity.collisionOn = true;
                                entity.upCollision = false;
                                entity.downCollision = false;
                                entity.leftCollision = false;
                                entity.rightCollision = true;
                            }
                            if (player == true) {
                                index = i;
                            }
                        }
                        entity.solidArea.x += entity.speed;
                        break;
                
                }
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                gp.obj[gp.currentMap][i].solidArea.x = gp.obj[gp.currentMap][i].solidAreaDefaultX;
                gp.obj[gp.currentMap][i].solidArea.y = gp.obj[gp.currentMap][i].solidAreaDefaultY;
            }
        }

        return index;

    }

    public int checkEntity(Entity entity, Entity[][] target) {
        int index = 999;

        String direction = entity.direction;
        if (entity.knockBack) {
            direction = entity.knockBackDirection;
        }

        for (int i = 0; i < target[1].length; i++) {
            if (target[gp.currentMap][i] != null) {
                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;

                target[gp.currentMap][i].solidArea.x = target[gp.currentMap][i].worldX + target[gp.currentMap][i].solidArea.x;
                target[gp.currentMap][i].solidArea.y = target[gp.currentMap][i].worldY + target[gp.currentMap][i].solidArea.y;

                switch(direction) {
                    case "up":
                        entity.solidArea.y -= entity.speed;
                        // entity.solidArea.y -= gp.tileSize;
                        if (entity.solidArea.intersects(target[gp.currentMap][i].solidArea)) {
                            if (target[gp.currentMap][i] != entity) {
                                entity.collisionOn = true;
                                // entity.upCollision = true;
                                
                                entity.upCollision = true;
                                entity.downCollision = false;
                                entity.leftCollision = false;
                                entity.rightCollision = false;
                                
                                entity.entityCollision = true;
                                index = i;
                                // System.out.println("Entity Collision: up");
                            }

                        }
                        break;
                    case "down":
                        entity.solidArea.y += entity.speed;
                        // entity.solidArea.y += gp.tileSize;
                        if (entity.solidArea.intersects(target[gp.currentMap][i].solidArea)) {
                            if (target[gp.currentMap][i] != entity) {
                                entity.collisionOn = true;
                                // entity.downCollision = true;
                                index = i;

                                
                                entity.upCollision = false;
                                entity.downCollision = true;
                                entity.leftCollision = false;
                                entity.rightCollision = false;
                                
                                entity.entityCollision = true;
                                // System.out.println("Entity Collision: down");
                            }
                        }
                        break;
                    case "left":
                        entity.solidArea.x -= entity.speed;
                        // entity.solidArea.x -= gp.tileSize;
                        if (entity.solidArea.intersects(target[gp.currentMap][i].solidArea)) {
                            if (target[gp.currentMap][i] != entity) {
                                entity.collisionOn = true;
                                // entity.leftCollision = true;
                                index = i;
                                entity.upCollision = false;
                                entity.downCollision = false;
                                entity.leftCollision = true;
                                entity.rightCollision = false;
                                
                                entity.entityCollision = true;
                                // System.out.println("Entity Collision: left");
                            }
                        }
                        break;
                    case "right":
                        entity.solidArea.x += entity.speed;
                        // entity.solidArea.x += gp.tileSize;
                        if (entity.solidArea.intersects(target[gp.currentMap][i].solidArea)) {
                            if (target[gp.currentMap][i] != entity) {
                                entity.collisionOn = true;
                                // entity.rightCollision = true;
                                index = i;
                                entity.upCollision = false;
                                entity.downCollision = false;
                                entity.leftCollision = false;
                                entity.rightCollision = true;
                                
                                entity.entityCollision = true;
                                // System.out.println("Entity Collision: right");
                            }
                        }
                        
                        break;
                
                }
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                target[gp.currentMap][i].solidArea.x = target[gp.currentMap][i].solidAreaDefaultX;
                target[gp.currentMap][i].solidArea.y = target[gp.currentMap][i].solidAreaDefaultY;
            }
        }

        return index;
    }

    public boolean checkPlayer(Entity entity) {

        boolean contactPlayer = false;

        entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;

                gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
                gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;

                switch(entity.direction) {
                    case "up":
                        entity.solidArea.y -= entity.speed;
                        if (entity.solidArea.intersects(gp.player.solidArea)) {
                            entity.collisionOn = true;
                            entity.upCollision = true;
                            entity.downCollision = false;
                            entity.leftCollision = false;
                            entity.rightCollision = false;
                            entity.entityCollision = true;
                            contactPlayer = true;
                        }
                        break;
                    case "down":
                        entity.solidArea.y += entity.speed;
                        if (entity.solidArea.intersects(gp.player.solidArea)) {
                            entity.collisionOn = true;
                            entity.upCollision = false;
                            entity.downCollision = true;
                            entity.leftCollision = false;
                            entity.rightCollision = false;
                            
                            entity.entityCollision = true;
                            contactPlayer = true;
                        }
                        break;
                    case "left":
                        entity.solidArea.x -= entity.speed;
                        if (entity.solidArea.intersects(gp.player.solidArea)) {
                            entity.collisionOn = true;
                            entity.upCollision = false;
                            entity.downCollision = false;
                            entity.leftCollision = true;
                            entity.rightCollision = false;
                            
                            entity.entityCollision = true;
                            contactPlayer = true;
                        }
                        break;
                    case "right":
                        entity.solidArea.x += entity.speed;
                        if (entity.solidArea.intersects(gp.player.solidArea)) {
                            entity.collisionOn = true;
                            entity.upCollision = false;
                            entity.downCollision = false;
                            entity.leftCollision = false;
                            entity.rightCollision = true;
                            
                            entity.entityCollision = true;
                            contactPlayer = true;
                        }
                        
                        break;
                
                }
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                gp.player.solidArea.x = gp.player.solidAreaDefaultX;
                gp.player.solidArea.y = gp.player.solidAreaDefaultY;

                return contactPlayer;

    }


}

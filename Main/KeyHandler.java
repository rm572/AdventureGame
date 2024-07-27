import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{
    public boolean upPressed, downPressed, leftPressed, rightPressed, shiftPressed, enterPressed, shotKeyPressed,
    spacePressed;

    boolean showDebugText = false;


    GamePanel gp;

    public KeyHandler(GamePanel gp) {
        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if (gp.gameState == gp.titleState) {
            titleState(code);
        }

        else if (gp.gameState == gp.playState) {
            playState(code);
        }

        else if (gp.gameState == gp.pauseState) {
            pauseState(code);
        }

        else if (gp.gameState == gp.dialogueState) {
            dialogueState(code);
        }

        else if (gp.gameState == gp.characterState) {
            characterState(code);
        }
        else if (gp.gameState == gp.optionsState) {
            optionsState(code);
        }
        else if (gp.gameState == gp.gameOverState) {
            gameOverState(code);
        }
        else if (gp.gameState == gp.tradeState) {
            tradeState(code);
        }
        else if (gp.gameState == gp.mapState) {
            mapState(code);
        }
    }

    public void mapState(int code) {
        if (code == KeyEvent.VK_M) {
            gp.gameState = gp.playState;
        }
    }

    public void tradeState(int code) {
        if (code == KeyEvent.VK_ENTER) {
            enterPressed = true;
        }
        // gp.stopMusic();
        // gp.playMusic(1);

        if (gp.ui.substate == 0) {
            if (code == KeyEvent.VK_W) {
                gp.ui.commandNum--;
                if (gp.ui.commandNum < 0) {
                    gp.ui.commandNum = 2;
                }
            }

            if (code == KeyEvent.VK_S) {
                gp.ui.commandNum++;
                if (gp.ui.commandNum > 2) {
                    gp.ui.commandNum = 0;
                }
            }

            if (code == KeyEvent.VK_ESCAPE) {
                // gp.stopMusic();
                // gp.playMusic(0);
                gp.gameState = gp.playState;
            }
        }

        if (gp.ui.substate == 1) {
            npcInventory(code);
            if (code == KeyEvent.VK_ESCAPE) {
                gp.ui.substate = 0;
            }
        }

        if (gp.ui.substate == 2) {
            playerInventory(code);
            if (code == KeyEvent.VK_ESCAPE) {
                gp.ui.substate = 0;
            }
        }
    }


    public void gameOverState(int code) {
        if (code == KeyEvent.VK_W) {
            gp.ui.commandNum--;
            if (gp.ui.commandNum < 0) {
                gp.ui.commandNum = 1;
            }
        }

        if (code == KeyEvent.VK_S) {
            gp.ui.commandNum++;
            if (gp.ui.commandNum > 1) {
                gp.ui.commandNum = 0;
                
            }
        }
        if (code == KeyEvent.VK_ENTER) {
            if (gp.ui.commandNum == 0) {
                gp.gameState = gp.playState;
                gp.resetGame(false);
            }
            else if (gp.ui.commandNum == 1) {
                gp.gameState = gp.titleState;
                gp.resetGame(true);
            }
        }

    }

    public void optionsState(int code) {
        if (code == KeyEvent.VK_ESCAPE) {
            gp.gameState = gp.playState;
        }
        if (code == KeyEvent.VK_ENTER) {
            enterPressed = true;
        }

        int maxCommandNum = 0;
        switch(gp.ui.substate) {
            case 0: maxCommandNum = 5; break;
            case 3: maxCommandNum = 1; break;
        }
        if (code == KeyEvent.VK_W) {
            gp.ui.commandNum--;
            if (gp.ui.commandNum < 0) {
                gp.ui.commandNum = maxCommandNum;
            }
        }
        if (code == KeyEvent.VK_S) {
            gp.ui.commandNum++;
            if (gp.ui.commandNum > maxCommandNum) {
                gp.ui.commandNum = 0;
            }
        }
        if (code == KeyEvent.VK_A) {
            if (gp.ui.substate == 0) {
                if (gp.ui.commandNum == 1 && gp.sound.volumeScale > 0) {
                    gp.sound.volumeScale--;
                    gp.sound.checkVolume();
                }
                if (gp.ui.commandNum == 2 && gp.se.volumeScale > 0) {
                    gp.se.volumeScale--;
                    gp.se.checkVolume();
                }
            }
        }

        if (code == KeyEvent.VK_D) {
            if (gp.ui.substate == 0) {
                if (gp.ui.commandNum == 1 && gp.sound.volumeScale < 5) {
                    gp.sound.volumeScale++;
                    gp.sound.checkVolume();
                }
                if (gp.ui.commandNum == 2 && gp.sound.volumeScale < 5) {
                    gp.se.volumeScale++;
                    gp.se.checkVolume();
                }
            }
        }
    }

    public void titleState(int code) {
    //Used for character selection
            // if (gp.ui.titleScreenState == 0) {
                if (code == KeyEvent.VK_S) {
                    gp.ui.commandNum = (gp.ui.commandNum + 1) % 3;
                    gp.playSE(2);
                }
    
                if (code == KeyEvent.VK_W) {
                    if (gp.ui.commandNum == 0) {
                        gp.ui.commandNum = 2;
                    }
                    else {
                        gp.ui.commandNum--;
                    }
                    gp.playSE(2);
                }
    
                if (code == KeyEvent.VK_ENTER) {
                    //New game
                    if (gp.ui.commandNum == 0) {
                        gp.gameState = gp.playState;
                        // gp.stopMusic();
                        gp.playMusic(0);

                        //Goes to character selection screen
                        // gp.ui.titleScreenState = 1;
                    }
    
                    //load game
                    else if (gp.ui.commandNum == 1) {
                        gp.saveLoad.load();
                        gp.gameState = gp.playState;
                        // gp.stopMusic();
                        gp.playMusic(0);
                    }
    
                    //quit
                    else if (gp.ui.commandNum == 2) {
                        System.exit(0);
                    }
                    
                }
            // }

            //Second screen -> choose character type
            // else if (gp.ui.titleScreenState == 1) {
            //     if (code == KeyEvent.VK_S) {
            //         gp.ui.commandNum = (gp.ui.commandNum + 1) % 4;
            //     }
    
            //     if (code == KeyEvent.VK_W) {
            //         if (gp.ui.commandNum == 0) {
            //             gp.ui.commandNum = 3;
            //         }
            //         else {
            //             gp.ui.commandNum--;
            //         }
            //     }
    
            //     if (code == KeyEvent.VK_ENTER) {
                
            //         if (gp.ui.commandNum == 0) {
            //             System.out.println("Do some fighter specific stuff");
            //             gp.gameState = gp.playState;
            //             // gp.ui.titleScreenState = 1;
            //         }

            //         else if (gp.ui.commandNum == 1) {
            //             System.out.println("Do some thief specific stuff");
            //             gp.gameState = gp.playState;
                        
            //         }
    
            //         else if (gp.ui.commandNum == 2) {
            //             System.out.println("Do some sorcerer specific stuff");
            //             gp.gameState = gp.playState;
            //         }
    
            //         //back
            //         else if (gp.ui.commandNum == 3) {
            //             gp.ui.titleScreenState = 0;
            //         }
                    
            //     }
            // }
    }

    public void playState(int code) {

        if (code == KeyEvent.VK_W) {
            upPressed = true;
        }
        if (code == KeyEvent.VK_A) {
            leftPressed = true;
            
        }
        if (code == KeyEvent.VK_S) {
            downPressed = true;
            
        }
        if (code == KeyEvent.VK_D) {
            rightPressed = true;
            
        }
        if (code == KeyEvent.VK_SHIFT) {
            shiftPressed = true;
        }

        if (code == KeyEvent.VK_T) {
            showDebugText = !showDebugText;
        }
        if (code == KeyEvent.VK_P) {
            gp.gameState = gp.pauseState;
        }
        if (code == KeyEvent.VK_ENTER) {
            enterPressed = true;
        }
        if (code == KeyEvent.VK_F) {
            shotKeyPressed = true;
        }
        if (code == KeyEvent.VK_C) {
            gp.gameState = gp.characterState;
        }
        if (code == KeyEvent.VK_M) {
            gp.gameState = gp.mapState;
        }
        if (code == KeyEvent.VK_X) {
            gp.map.miniMapOn = !gp.map.miniMapOn;
        }
        if (code == KeyEvent.VK_SPACE) {
            spacePressed = true;
        }
        if (code == KeyEvent.VK_Q) {
            gp.saveLoad.save();
        }
        if (code == KeyEvent.VK_R) {
            switch (gp.currentMap) {
                case 0: gp.tileM.loadMap("C:/Users/rober/OneDrive/Programming/AdventureGame/res/Maps/world1.txt", 0); break;
                case 1: gp.tileM.loadMap("C:/Users/rober/OneDrive/Programming/AdventureGame/res/Maps/indoor01.txt", 1); break;
                
            }
            }

        if (code == KeyEvent.VK_ESCAPE) {
            gp.gameState = gp.optionsState;
        }

    }
    public void pauseState(int code) {
        if (code == KeyEvent.VK_P) {
            gp.gameState = gp.playState;
        }
    }
    public void dialogueState(int code) {
        if (code == KeyEvent.VK_ENTER) {
            // gp.gameState = gp.playState;
            enterPressed = true;
        }
    }
    public void characterState(int code) {
        if (code == KeyEvent.VK_C) {
            gp.gameState = gp.playState;
        }
        playerInventory(code);

        if (code == KeyEvent.VK_ENTER) {
            gp.player.selectItem();
        }

        if (code == KeyEvent.VK_ESCAPE) {
            gp.gameState = gp.playState;
        }

    }

    public void playerInventory(int code) {
        if (code == KeyEvent.VK_W) {
            if (gp.ui.playerSlotRow != 0) {
                gp.ui.playerSlotRow--;
            }
        }
        if (code == KeyEvent.VK_A) {
            if (gp.ui.playerSlotCol != 0) {
                gp.ui.playerSlotCol--;
            }
            
        }
        if (code == KeyEvent.VK_S) {
            if (gp.ui.playerSlotRow != 3) {
                gp.ui.playerSlotRow++;
            }
            
        }
        if (code == KeyEvent.VK_D) {
            if (gp.ui.playerSlotCol != 4) {
                gp.ui.playerSlotCol++;
            }
        }
           
    }

    public void npcInventory(int code) {
        if (code == KeyEvent.VK_W) {
            if (gp.ui.npcSlotRow != 0) {
                gp.ui.npcSlotRow--;
            }
        }
        if (code == KeyEvent.VK_A) {
            if (gp.ui.npcSlotCol != 0) {
                gp.ui.npcSlotCol--;
            }
            
        }
        if (code == KeyEvent.VK_S) {
            if (gp.ui.npcSlotRow != 3) {
                gp.ui.npcSlotRow++;
            }
            
        }
        if (code == KeyEvent.VK_D) {
            if (gp.ui.npcSlotCol != 4) {
                gp.ui.npcSlotCol++;
            }
        }
           
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W) {
            upPressed = false;
        }
        if (code == KeyEvent.VK_A) {
            leftPressed = false;
            
        }
        if (code == KeyEvent.VK_S) {
            downPressed = false;
            
        }
        if (code == KeyEvent.VK_D) {
            rightPressed = false;
            
        }
        if (code == KeyEvent.VK_SHIFT) {
            shiftPressed = false;
        }
        if (code == KeyEvent.VK_F) {
            shotKeyPressed = false;
        }
        if (code == KeyEvent.VK_SPACE) {
            spacePressed = false;
        }
        if (code == KeyEvent.VK_ENTER) {
            enterPressed = false;
        }
    }
    
}

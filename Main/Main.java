import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Main {


    //Full screen code
    public static JFrame window;
    public static void main(String[] args) {
        // JFrame    window = new JFrame(); -> regular code

        window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        // window.setResizable(true);
        window.setTitle("2D Adventure");
        new Main().setIcon();

        //Full screen code
        // window.setUndecorated(true); //Removes white bar at top
        //

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        gamePanel.config.loadConfig();
        if (gamePanel.fullScreenOn) {
            window.setUndecorated(true);
        }

        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.setUpGame();
        gamePanel.startGameThread();
    }

    public void setIcon() {
        ImageIcon icon = new ImageIcon("C:/Users/rober/OneDrive/Programming/AdventureGame/res/Player/new_down_1.png");
        window.setIconImage(icon.getImage());
    }
}

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.GraphicsConfigTemplate;
import java.awt.RadialGradientPaint;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.Shape;

public class Lighting {
    GamePanel gp;
    BufferedImage darknessFilter;
    int dayCounter;
    float filterAlpha = 0f;

    final int day = 0;
    final int dusk = 1;
    final int night = 2;
    final int dawn = 3;
    int dayState = day;
    

    public Lighting(GamePanel gp) {
        this.gp = gp;
        setLightSource();
        


    }

    public void setLightSource() {
        //Create a buffered image
        darknessFilter = new BufferedImage(gp.screenWidth, gp.screenHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = (Graphics2D) darknessFilter.getGraphics();

        if (gp.player.currentLight == null) {
            g2.setColor(new Color(0, 0, 0.1f, 0.97f));
        }
        else {
            // //Create a screen-sized rectangle area
        // Area screenArea = new Area(new Rectangle2D.Double(0, 0, gp.screenWidth, gp.screenHeight));

        //Get the center x and y of the light circlr
        int centerX = gp.player.screenX + gp.tileSize/2;
        int centerY = gp.player.screenY + gp.tileSize/2;

        // Get the top left x and y of the light circle
        // double x = centerX - (circleSize/2);
        // double y = centerY - (circleSize/2);

        // //Create a light circle shape
        // Shape circleShape = new Ellipse2D.Double(x, y, circleSize, circleSize);

        // //Create a light circle area
        // Area lightArea = new Area(circleShape);

        // //Subtract the light circle from the screen rectangle
        // screenArea.subtract(lightArea);

        //Create a gradiation effect within the light circle
        Color color[] = new Color[12];
        float fraction[] = new float[12];

        //0f -> completely transparent
        //1f -> completely black
        color[0] = new Color(0, 0, 0.1f, 0.1f);
        color[1] = new Color(0, 0, 0.1f, 0.42f);
        color[2] = new Color(0, 0, 0.1f, 0.52f);
        color[3] = new Color(0, 0, 0.1f, 0.61f);
        color[4] = new Color(0, 0, 0.1f, 0.69f);
        color[5] = new Color(0, 0, 0.1f, 0.76f);
        color[6] = new Color(0, 0, 0.1f, 0.82f);
        color[7] = new Color(0, 0, 0.1f, 0.87f);
        color[8] = new Color(0, 0, 0.1f, 0.91f);
        color[9] = new Color(0, 0, 0.1f, 0.94f);
        color[10] = new Color(0, 0, 0.1f, 0.96f);
        color[11] = new Color(0, 0, 0.1f, 0.98f);

        //Indicates the distance to the center of the circle
        fraction[0] = 0f; //center of the cirlce
        fraction[1] = 0.4f;
        fraction[2] = 0.5f;
        fraction[3] = 0.6f;
        fraction[4] = 0.65f; 
        fraction[5] = 0.7f;
        fraction[6] = 0.75f;
        fraction[7] = 0.8f;
        fraction[8] = 0.85f;
        fraction[9] = 0.9f;
        fraction[10] = 0.95f;
        fraction[11] = 1f;


        //Create a gradiation paint settings for the light circle
        RadialGradientPaint gPaint = new RadialGradientPaint(centerX, centerY, gp.player.currentLight.lightRadius/2, fraction, color);

        //Set the gradient data on g2
        g2.setPaint(gPaint);
        }
        
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        // //Draw the light circle
        // g2.fill(lightArea);

        //Set a color to draw the rectangle
        // g2.setColor(new Color(0, 0, 0, 0.95f));

        // //draw the screen retangle without the light circle area
        // g2.fill(screenArea);

        g2.dispose();

        
    }

    public void resetDay() {
        dayState = day;
        filterAlpha = 0f;
    }

    public void draw(Graphics2D g2) {
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, filterAlpha));
        g2.drawImage(darknessFilter, 0, 0, null);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));


        //Debug
        // String situation = "";
        // switch (dayState) {
        //     case day: situation = "day"; break;
        //     case dusk: situation = "dusk"; break;
        //     case night: situation = "night"; break;
        //     case dawn: situation = "dawn"; break;
        // }

        // // g2.setColor(Color.white);
        // // g2.setFont(g2.getFont().deriveFont(50f));
        // // g2.drawString(situation, 800, 500);
    }

    public void update() {
        if (gp.player.lightUpdated) {
            setLightSource();
            gp.player.lightUpdated = false;
        }

        if (dayState == day) {
            dayCounter++;

            if (dayCounter > 600000) {
                dayState = dusk;
                dayCounter = 0;
            }
        }
        if (dayState == dusk) {
            filterAlpha += 0.001f;

            if (filterAlpha> 1f) {
                filterAlpha = 1f;
                dayState = night;
            }
        }
        if (dayState == night) {
            dayCounter++;

            if (dayCounter > 6000000) {
                dayState = dawn;
                dayCounter = 0;
            }
        }

        if (dayState == dawn) {
            filterAlpha -= 0.001f;

            if (filterAlpha < 0f) {
                filterAlpha = 0f;
                dayState = day;
            }
        }
    }
}

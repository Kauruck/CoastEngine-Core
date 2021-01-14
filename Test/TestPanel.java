import com.kauruck.CoastEngine.Core.Math.Vector2;

import javax.swing.*;
import java.awt.*;

public class TestPanel extends JPanel {
    Vector2 pos = new Vector2(50,50);

    @Override
    protected void paintComponent(Graphics g) {
        g.fillRect(0,0,this.getWidth(),this.getHeight());
        g.setColor(Color.WHITE);
        pos = pos.rotateAround(new Vector2(), TestMain.deg);
        g.drawRect(this.getWidth()/2 + (int)pos.getX(),this.getHeight()/2 + (int)pos.getY(), 20,20);
    }
}

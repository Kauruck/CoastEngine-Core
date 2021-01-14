import com.kauruck.CoastEngine.Core.Input.Input;
import com.kauruck.CoastEngine.Core.Input.KeyCode;
import com.kauruck.CoastEngine.Core.Math.Vector2;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TestFrame extends JFrame {


    TestPanel mainPanel = new TestPanel();

    public TestFrame(){
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                Input.onKeyDown(KeyCode.fromEvent(e));
            }

            @Override
            public void keyReleased(KeyEvent e) {
                Input.onKeyUp(KeyCode.fromEvent(e));
            }
        });
        this.add(mainPanel);
        this.setLocationRelativeTo(null);
        this.setSize(200,200);
    }


}

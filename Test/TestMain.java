import com.kauruck.coastEngine.core.input.Input;
import com.kauruck.coastEngine.core.input.KeyCode;

import java.util.Timer;
import java.util.TimerTask;

public class TestMain {

    public static int deg = 1;
    public static Timer timer;
    static TestFrame frame;

    public static void main(String[] args){
        frame = new TestFrame();
        frame.setVisible(true);
        timer = new Timer();
        timer.schedule(new Updater(), 10);
    }

    static class Updater extends TimerTask {

        @Override
        public void run() {
            if(Input.getKey(KeyCode.ArrowLeft))
                deg = -1;
            else if(Input.getKey(KeyCode.ArrowRight))
                deg = 1;
            else
                deg = 0;
            Input.update();
            frame.repaint();
            timer.schedule(new Updater(),10);
        }
    }
}

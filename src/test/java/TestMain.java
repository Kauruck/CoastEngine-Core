import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.kauruck.coastEngine.core.Core;
import com.kauruck.coastEngine.core.exception.NoHandlerException;
import com.kauruck.coastEngine.core.exception.NoSuchProcessException;
import com.kauruck.coastEngine.core.input.Input;
import com.kauruck.coastEngine.core.input.KeyCode;
import com.kauruck.coastEngine.core.resources.ResourceLoader;
import com.kauruck.coastEngine.core.resources.ResourceLocation;
import com.kauruck.coastEngine.core.threding.Thread;
import com.kauruck.coastEngine.core.threding.ThreadManger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class TestMain {

    public static int deg = 1;
    public static Timer timer;
    static TestFrame frame;
    static Logger logger = LoggerFactory.getLogger(TestMain.class);

    public static void main(String[] args) throws IOException, NoHandlerException {
        logger.warn("Starting");
        /*int pid = ThreadManger.addThread(new Thread(15) {
            @Override
            public void onTick(float deltaTime) {
                logger.info("Tick");
            }

            @Override
            public void onStart() {
                logger.info("Start");
            }

            @Override
            public void onEnd() {
                logger.info("End");
            }
        });
        try {
            ThreadManger.startTread(pid);
        } catch (NoSuchProcessException e) {
            e.printStackTrace();
        }
        frame = new TestFrame();
        frame.setVisible(true);
        timer = new Timer();
        timer.schedule(new Updater(), 10);
        try {
            ThreadManger.stopThread(pid);
        } catch (NoSuchProcessException e) {
            e.printStackTrace();
        }*/

        Core.init();
        String s = ResourceLoader.loadResources(new ResourceLocation("test", "assets/a"), String.class);
        JsonObject json = ResourceLoader.loadResources(new ResourceLocation("test", "assets/a"), JsonObject.class);
        System.out.println(s);
        System.out.println(json);
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

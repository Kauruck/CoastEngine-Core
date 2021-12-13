package com.kauruck.test;

import com.google.gson.JsonObject;
import com.kauruck.coastEngine.core.Core;
import com.kauruck.coastEngine.core.exception.NoHandlerException;
import com.kauruck.coastEngine.core.input.Input;
import com.kauruck.coastEngine.core.input.KeyCode;
import com.kauruck.coastEngine.core.resources.ResourceLoader;
import com.kauruck.coastEngine.core.resources.ResourceLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.stream.Collectors;

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
        frame = new com.kauruck.test.TestFrame();
        frame.setVisible(true);
        timer = new Timer();
        timer.schedule(new Updater(), 10);
        try {
            ThreadManger.stopThread(pid);
        } catch (NoSuchProcessException e) {
            e.printStackTrace();
        }*/

        Core.init();
        //TestMain test = new TestMain();
        //System.out.println(test.test());





        String s = ResourceLoader.loadResources(new ResourceLocation("test", "assets/a"), String.class);
        JsonObject json = ResourceLoader.loadResources(new ResourceLocation("test", "assets/a"), JsonObject.class);
        System.out.println(s);
        System.out.println(json);
    }

    public List<String> test() throws IOException {
        try (InputStream resource = this.getClass().getClassLoader().getResourceAsStream("/")) {
            System.out.println(resource);
            List<String> blacklistedWordsDatabase = new BufferedReader(
                    new InputStreamReader(
                            resource,
                            StandardCharsets.UTF_8
                    )
            ).lines()
                    .collect(Collectors.toList());

            return blacklistedWordsDatabase;

        }
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

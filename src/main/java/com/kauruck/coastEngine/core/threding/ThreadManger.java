package com.kauruck.coastEngine.core.threding;

import com.kauruck.coastEngine.core.exception.NoSuchProcessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public class ThreadManger {
    public static final Logger TREAD_LOGGER = LoggerFactory.getLogger("Thread Manger");

    private static final List<Thread> threads = new ArrayList<>();

    public static int addThread(Thread thread){
        threads.add(thread);
        thread.setPid(threads.size() - 1);
        return threads.size() - 1;
    }

    public static void startTread(int pid) throws NoSuchProcessException {
        if(pid < threads.size()){
            threads.get(pid).start();
            return;
        }

        throw new NoSuchProcessException(pid);
    }

    public static void stopThread(int pid) throws NoSuchProcessException {
        if(pid < threads.size()){
            threads.get(pid).stop();
            return;
        }
        throw new NoSuchProcessException(pid);
    }

    public static float getFps(int pid) throws NoSuchProcessException {
        if(pid < threads.size()){
            return threads.get(pid).getFps();
        }
        throw new NoSuchProcessException(pid);
    }

    public static void setMaxFps(int pid, float maxFps) throws NoSuchProcessException{
        if(pid < threads.size()){
            threads.get(pid).setMaxFps(maxFps);
            return;
        }
        throw new NoSuchProcessException(pid);
    }

    public static ThreadStatus getStatus(int pid) throws NoSuchProcessException{
        if(pid < threads.size()){
            return threads.get(pid).getStatus();
        }
        throw new NoSuchProcessException(pid);
    }
}

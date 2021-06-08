package com.kauruck.coastEngine.core.threding;

@SuppressWarnings("unused")
public abstract class Thread implements Runnable{

    //For the fps
    private float fps;
    private float maxFps;
    private long targetTime;

    //Status of the thread
    private ThreadStatus status = ThreadStatus.Stopped;

    //The thread
    private java.lang.Thread thread;

    private int pid = -1;

    public Thread(float maxFps){
        this.setMaxFps(maxFps);
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public void setMaxFps(float maxFps){
        this.maxFps = maxFps;
        this.targetTime = (long) (( 1e9)/maxFps);
    }

    public float getFps() {
        return fps;
    }

    public float getMaxFps() {
        return maxFps;
    }

    public ThreadStatus getStatus(){
        return status;
    }

    public void start(){
        if(status == ThreadStatus.Stopped) {
            status = ThreadStatus.Starting;
            thread = new java.lang.Thread(this);
            thread.start();
        }

    }

    public void stop(){
        if(status != ThreadStatus.Stopped)
            status = ThreadStatus.Stopping;
    }


    @Override
    public void run() {
        ThreadManger.TREAD_LOGGER.info("Starting thread with pid " + pid + "");
        onStart();
        status = ThreadStatus.Running;
        long deltaTime = 0;
        while (status == ThreadStatus.Running){
            //Start time of the frame
            long frameStartTime = System.nanoTime();
            //Execute run
            onTick(deltaTime);
            long frameDeltaTime = System.nanoTime() - frameStartTime;
            if(frameDeltaTime == 0){
                try {
                    java.lang.Thread.sleep(20);
                } catch (InterruptedException e) {
                    status = ThreadStatus.Stopping;
                    return;
                }
            }else {
                long waitTime = targetTime - frameDeltaTime;
                if (waitTime > 0) {
                    long delayMS = 0;
                    if (waitTime > 999999) {
                        delayMS = waitTime / 1000000;
                        waitTime = waitTime % 1000000;
                    }

                    if (waitTime > 0) {
                        try {
                            java.lang.Thread.sleep(delayMS, (int) waitTime);
                        } catch (InterruptedException e) {
                            status = ThreadStatus.Stopping;
                            return;
                        }
                    }
                }
                deltaTime = System.nanoTime() - frameStartTime;
            }
        }
        onEnd();
        status = ThreadStatus.Stopped;
    }



    public abstract void onTick(float deltaTime);
    public abstract void onStart();
    public abstract void onEnd();
}

package com.example.myapplication;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class GameLoop extends Thread {
    private SurfaceHolder surfaceHolder;
    private GameView gameView;
    private boolean running;

    public GameLoop(SurfaceHolder surfaceHolder, GameView gameView) {
        this.surfaceHolder = surfaceHolder;
        this.gameView = gameView;
    }

    public void startLoop() {
        running = true;
        start();
    }

    public void stopLoop() {
        running = false;
        try {
            join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {

        long lastFPScheck = System.currentTimeMillis();
        int fps = 0;

        long lastDelta = System.nanoTime();
        long nanSec = 1_000_000_000;


        while (running) {

            Canvas canvas = null;
            try {
                canvas = surfaceHolder.lockCanvas();
                synchronized (surfaceHolder) {

                    long now = System.currentTimeMillis();
                    long nowDelta = System.nanoTime();
                    double timeSinceLastDelta = nowDelta - lastDelta;
                    double delta = timeSinceLastDelta / nanSec;





                    if(now - lastFPScheck >= 1000) {

                        System.out.println("FPS" + fps + " " + System.currentTimeMillis());
                        fps = 0;
                        lastFPScheck += 1000;

                    }




                    gameView.update(delta);
                    gameView.draw(canvas);

                    fps++;


                }
            } finally {
                if (canvas != null) {
                    surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }
        }
    }
}

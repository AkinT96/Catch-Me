package com.example.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

import com.example.myapplication.player.Player;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {
    private GameLoop gameLoop;
    private Player player;

    // Dieser Konstruktor wird für XML benötigt
    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public GameView(Context context) {
        super(context);
        init();
    }

    private void init() {
        getHolder().addCallback(this);
        gameLoop = new GameLoop(getHolder(), this);
        player = new Player(500, 500); // Startposition des Spielers
    }

    public void movePlayer(float dx, float dy) {
        player.move(dx, dy);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        gameLoop.startLoop();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        gameLoop.stopLoop();
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (canvas != null) {
            canvas.drawColor(Color.BLACK); // Hintergrundfarbe
            Paint paint = new Paint();
            player.draw(canvas, paint); // Spieler zeichnen
        }
    }

    public void update() {
        player.update();
    }
}

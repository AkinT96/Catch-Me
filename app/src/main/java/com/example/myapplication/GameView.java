package com.example.myapplication;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

import com.example.myapplication.player.Player;

import java.util.Arrays;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {
    private GameLoop gameLoop;
    private Player player;
    private GameView gameView;
    private Bitmap tile1; // Gras-Textur
    private Bitmap tile2;  // Wand-Textur
    private Bitmap fieldBitmap;


    // Dieser Konstruktor wird für XML benötigt
    public GameView(Context context, AttributeSet attrs) {

        super(context, attrs);

        tile1 = BitmapFactory.decodeResource(getResources(), R.drawable.background1); // z. B. Gras
        tile2 = BitmapFactory.decodeResource(getResources(), R.drawable.background2); // z. B. Sand

        if (tile1 == null || tile2 == null) {
            throw new IllegalStateException("Tiles wurden nicht korrekt geladen.");
        }
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

    public void movePlayer(double dx, double dy, double delta) {
        if (dx != 0 || dy != 0) { // Bewegung nur durchführen, wenn nötig
            int[] displayData = getDisplayData(getContext());
            int screenWidth = displayData[0];
            int screenHeight = displayData[1];

            player.move(dx, dy, screenWidth, screenHeight, delta);
        }
    }

    public int[] getDisplayData(Context context) {
        DisplayMetrics metrics = new DisplayMetrics();

        // Bildschirmdaten abrufen
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(metrics);

        // Breite und Höhe des Bildschirms in Pixel
        int screenWidth = metrics.widthPixels;
        int screenHeight = metrics.heightPixels;

        // Breite und Höhe als Array zurückgeben
        return new int[]{screenWidth, screenHeight};
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

    public GameLoop getGameLoop() {
        return gameLoop;
    }


    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        if (canvas != null) {
            // Zeichne das vorbereitete Spielfeld
            drawField(canvas);

            // Zeichne den Spieler
            player.draw(canvas, tile1);
        }
    }

    private void drawField(Canvas canvas) {
        if (fieldBitmap == null) {
            fieldBitmap = Bitmap.createBitmap(canvas.getWidth(), canvas.getHeight(), Bitmap.Config.ARGB_8888);
            Canvas fieldCanvas = new Canvas(fieldBitmap);

            // Zeichne das Spielfeld nur einmal
            int cols = fieldCanvas.getWidth() / tile1.getWidth();
            int rows = fieldCanvas.getHeight() / tile1.getHeight();

            for (int row = 0; row < rows; row++) {
                for (int col = 0; col < cols; col++) {
                    Bitmap tileBitmap = (row + col) % 2 == 0 ? tile1 : tile2;
                    fieldCanvas.drawBitmap(tileBitmap, col * tile1.getWidth(), row * tile1.getHeight(), null);
                }
            }
        }

        // Spielfeld-Bitmap auf das aktuelle Canvas zeichnen
        canvas.drawBitmap(fieldBitmap, 0, 0, null);
    }


    public void update(Double delta) {
        int x = 0;
        int y = 0;

        this.movePlayer(x, y, delta);
    }
}

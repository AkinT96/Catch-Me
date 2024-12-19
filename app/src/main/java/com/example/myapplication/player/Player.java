package com.example.myapplication.player;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

public class Player {
    private float x, y; // Position des Spielers

    public Player(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void move(double dx, double dy, int screenWidth, int screenHeight, double delta) {
        // Neue Position berechnen
        float newX = (float) (this.x + dx * delta * 10);
        float newY = (float) (this.y + dy * delta * 10);

        // Begrenzungen für x
        if (newX < 0) {
            this.x = 0; // Linker Rand
        } else if (newX > screenWidth) {
            this.x = screenWidth; // Rechter Rand
        } else {
            this.x = newX; // Innerhalb der Grenzen
        }

        // Begrenzungen für y
        if (newY < 0) {
            this.y = 0; // Oberer Rand
        } else if (newY > screenHeight) {
            this.y = screenHeight; // Unterer Rand
        } else {
            this.y = newY; // Innerhalb der Grenzen
        }
    }



    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void update() {
        // Hier könnten zusätzliche Bewegungen oder Checks hinzugefügt werden
    }

    public void draw(Canvas canvas, Bitmap tile) {
        int tileWidth = tile.getWidth();
        int tileHeight = tile.getHeight();

        // Berechne, wie viele Tiles in Breite und Höhe passen
        int cols = canvas.getMaximumBitmapWidth() / tileWidth;
        int rows = canvas.getMaximumBitmapHeight() / tileHeight;

        // Optional: Berechne die exakte Breite und Höhe des Canvas
        int canvasWidth = canvas.getMaximumBitmapWidth();
        int canvasHeight = canvas.getMaximumBitmapHeight();

        // Zeichne die Tiles
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                // Berechne die Zielposition des aktuellen Tiles
                int x = col * tileWidth;
                int y = row * tileHeight;

                // Überprüfe, ob das Tile noch ins Canvas passt
                if (x + tileWidth <= canvasWidth && y + tileHeight <= canvasHeight) {
                    canvas.drawBitmap(tile, x, y, null);
                }
            }
        }

        Paint paint = new Paint();
        paint.setColor(Color.BLUE);
        canvas.drawCircle(this.x,this.y, 30, paint);

    }
}

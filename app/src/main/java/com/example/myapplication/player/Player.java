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

    public void move(double delta) {
        x += (float) (delta * 5); // Bewegung multiplizieren für Geschwindigkeit
        y += (float) (delta * 5);
        System.out.println("Methode move genutzt");
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

    }
}

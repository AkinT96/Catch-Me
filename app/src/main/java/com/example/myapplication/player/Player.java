package com.example.myapplication.player;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Player {
    private float x, y; // Position des Spielers

    public Player(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void move(float dx, float dy) {
        x += dx * 5; // Bewegung multiplizieren für Geschwindigkeit
        y += dy * 5;
    }

    public void update() {
        // Hier könnten zusätzliche Bewegungen oder Checks hinzugefügt werden
    }

    public void draw(Canvas canvas, Paint paint) {
        paint.setColor(Color.BLUE);
        canvas.drawCircle(x, y, 30, paint); // Spieler als blauer Kreis
    }
}

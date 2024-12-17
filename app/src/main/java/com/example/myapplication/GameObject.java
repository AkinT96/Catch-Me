package com.example.myapplication;

import android.graphics.Canvas;
import android.graphics.Paint;

public abstract class GameObject {
    protected float x, y; // Position

    public GameObject(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public abstract void draw(Canvas canvas, Paint paint);
    public abstract void update();
}


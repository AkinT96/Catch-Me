package com.example.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class TouchPadView extends View {
    private float centerX, centerY; // Zentrum des Touchpads
    private float touchX, touchY;   // Touch-Position
    private boolean isTouching = false;

    private OnTouchPadListener listener;

    // Interface, um Bewegungen weiterzugeben
    public interface OnTouchPadListener {
        void onMove(float dx, float dy);
    }

    public TouchPadView(Context context) {
        super(context);
    }

    public TouchPadView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setOnTouchPadListener(OnTouchPadListener listener) {
        this.listener = listener;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                touchX = event.getX();
                touchY = event.getY();
                isTouching = true;

                float dx = touchX - centerX;
                float dy = touchY - centerY;

                if (listener != null) {
                    listener.onMove(dx / 50, dy / 50); // Skaliere Bewegungen
                }
                invalidate();
                break;

            case MotionEvent.ACTION_UP:
                isTouching = false;
                if (listener != null) listener.onMove(0, 0); // Bewegung stoppen
                invalidate();
                break;
        }
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        centerX = getWidth() / 2f;
        centerY = getHeight() / 2f;

        Paint paint = new Paint();
        paint.setColor(Color.GRAY);
        paint.setAlpha(150);

        // Hintergrund des Touchpads
        canvas.drawCircle(centerX, centerY, 150, paint);

        // Touch-Punkt zeichnen
        if (isTouching) {
            paint.setColor(Color.RED);
            canvas.drawCircle(touchX, touchY, 30, paint);
        }
    }
}

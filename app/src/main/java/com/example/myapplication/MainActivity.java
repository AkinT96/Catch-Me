package com.example.myapplication;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private GameView gameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gameView = findViewById(R.id.gameView);
        TouchPadView touchPad = findViewById(R.id.touchPad);

        // Touchpad-Bewegungen an die GameView weitergeben
        touchPad.setOnTouchPadListener((dx, dy) -> gameView.movePlayer(dx, dy));
    }
}

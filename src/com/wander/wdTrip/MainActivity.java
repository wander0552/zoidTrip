package com.wander.wdTrip;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.graphics.*;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import com.wander.appCreate;
import com.wander.model.BackGround;

import java.io.IOException;
import java.io.InputStream;
import java.nio.channels.Pipe;

public class MainActivity extends Activity {
    /**
     * Called when the activity is first created.
     */

    private SurfaceView surfaceView_background, sf_game, sf_show;
    private BackGroundDraw backGroundDraw;
    private GameDraw gameDraw;
    private SurfaceHolder holder;
    private SurfaceHolder gameHolder;
    private SurfaceHolder showHolder;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        initBackground();
        initGame();
        initShow();

    }

    //用于显示障碍物和结果以及菜单界面
    private void initShow() {
        sf_show = (SurfaceView) findViewById(R.id.surfaceView_show);
        showHolder = sf_game.getHolder();
        showHolder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                Log.d("show_surface", holder.toString());

            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                Log.d("show", holder.toString());

            }
        });

    }

    void initGame() {
        sf_game = (SurfaceView) findViewById(R.id.surfaceView_game);//1
        sf_game.setZOrderOnTop(true);
        gameHolder = sf_game.getHolder();
        gameHolder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {

                holder.setFormat(PixelFormat.TRANSPARENT);
                gameDraw = new GameDraw(holder, MainActivity.this);//1
                Log.d("surfaceGame---create", holder.toString() + "gameDraw" + gameDraw.toString());
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                Log.d("game_surface", "destroy");
                if (gameDraw != null) {
                    Log.d("game_surface", "destroy" + gameDraw.toString());
                    gameDraw.stopGame();
                    gameDraw = null;
                }

            }
        });
    }

    void initBackground() {
        surfaceView_background = (SurfaceView) findViewById(R.id.surfaceView_background);

        holder = surfaceView_background.getHolder();
        holder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                Log.d("back_create", holder.toString());
                backGroundDraw = new BackGroundDraw(holder, MainActivity.this);
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {

                Log.d("back_--destroy", holder.toString());
            }
        });
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:

                break;

            case MotionEvent.ACTION_UP:

                break;
        }

        return super.onTouchEvent(event);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
        }
        return super.onKeyDown(keyCode, event);
    }
}

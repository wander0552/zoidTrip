package com.wander.model;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.*;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.SurfaceHolder;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by wander on IDEA.
 * Date:15-4-29
 * Email:18955260352@163.com
 */
public class BackGround implements Runnable {
    private SurfaceHolder holder;
    private Context context;
    private Bitmap particles1;
    private Bitmap particles2;
    private int sHeight;
    private int sWidth;
    Random random = new Random();
    private boolean running;

    public BackGround(SurfaceHolder holder, Context context) {
        this.holder = holder;
        this.context = context;
        try {
            //获取bitmap
            if (context != null) {
                AssetManager assetManager = context.getAssets();
                InputStream inputStream = assetManager.open("main/particles_bg_01.png");
                BitmapFactory.Options opts = new BitmapFactory.Options();
                opts.inSampleSize = 2;
                particles1 = BitmapFactory.decodeStream(inputStream, new Rect(2, 2, 2, 2), opts);
                particles2 = BitmapFactory.decodeStream(assetManager.open("main/particles_bg_02.png"), new Rect(2, 2, 2, 2), opts);
                Log.d("particle2", particles2.toString());
                DisplayMetrics metrics = context.getResources().getDisplayMetrics();
                sHeight = metrics.heightPixels;
                sWidth = metrics.widthPixels;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        Paint paint = new Paint();
        paint.setAlpha(150);
        running = true;
        List<Position> positions = new ArrayList<Position>();
        for (int i = 0; i < 12; i++) {
            Position position1 = new Position(getWidth(), getSpeed(), getHeight());
            positions.add(position1);
        }


        while (running) {
            Canvas canvas = holder.lockCanvas();
            //清屏
            canvas.drawColor(Color.GREEN);
            //保存当前的状态
            canvas.save();

            int size1 = positions.size();
            for (int i = 0; i < size1; i++) {
                Position position = positions.get(i);
                if (i % 2 == 0) {
                    canvas.drawBitmap(particles1, position.width, position.height, paint);
                } else {
                    canvas.drawBitmap(particles2, position.width, position.height, paint);
                }
                position.goUp();
                if (position.height < -50) {
                    position.initPosition();
                }
            }

            //TODO  图片旋转

            //恢复save 的状态
            canvas.restore();
            //显示到屏幕上
            holder.unlockCanvasAndPost(canvas);
            try {
                Thread.sleep(150);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public float getWidth() {
        float v = random.nextFloat() * sWidth;
        return v;
    }

    public float getHeight() {
        return random.nextFloat() * sHeight;
    }

    public float getSpeed() {
        return random.nextFloat() * 7 + 0.5f;
    }

    class Position {
        float width;
        float speed;
        float height;

        public Position(float width, float speed, float height) {
            this.width = width;
            this.speed = speed;
            this.height = height;
        }

        void initPosition() {
            width = getWidth();
            speed = getSpeed();
            height = sHeight;
        }

        void goUp() {
            height -= speed;
        }
    }

}

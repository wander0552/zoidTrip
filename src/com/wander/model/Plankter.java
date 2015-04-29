package com.wander.model;

import android.content.Context;
import android.graphics.*;
import android.view.SurfaceHolder;
import com.wander.wdTrip.R;

/**
 * Created by wander on IDEA.
 * Date:15-4-29
 * Email:18955260352@163.com
 */
public class Plankter implements Runnable {
    private boolean direction;
    private Context context;
    private SurfaceHolder holder;
    private boolean running;
    private int[] tent = new int[]{R.drawable.tentacles_2, R.drawable.tentacles_1, R.drawable.tentacles_3, R.drawable.tentacles_4, R.drawable.tentacles_5,
            R.drawable.tentacles_6, R.drawable.tentacles_7, R.drawable.tentacles_8, R.drawable.tentacles_9, R.drawable.tentacles_10};

    private Bitmap[] tentacles = new Bitmap[tent.length];
    private int tentacle=0;

    public Plankter(Context context, SurfaceHolder holder) {
        this.context = context;
        this.holder = holder;
        for (int i = 0; i < tentacles.length; i++) {
            tentacles[i] = BitmapFactory.decodeResource(context.getResources(), tent[i]);
        }

    }


    @Override
    public void run() {
        while (running) {
            Canvas canvas = holder.lockCanvas();
            int argb = Color.argb(50, 255, 0, 0);
            canvas.drawColor(argb);

            canvas.save();
            Paint paint = new Paint();
            canvas.drawBitmap(tentacles[tentacle],10,200, paint);
            tentacle=++tentacle%tentacles.length;
            canvas.rotate(20);
            canvas.restore();
            holder.unlockCanvasAndPost(canvas);

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }


    }

}

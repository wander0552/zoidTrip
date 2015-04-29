package com.wander.model;

import android.content.Context;
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
    private int tentacle[] = new int[]{R.drawable.tentacles_2, R.drawable.tentacles_1, R.drawable.tentacles_3, R.drawable.tentacles_4, R.drawable.tentacles_5,
            R.drawable.tentacles_6, R.drawable.tentacles_7, R.drawable.tentacles_8, R.drawable.tentacles_9, R.drawable.tentacles_10};


    public Plankter(Context context, SurfaceHolder holder) {
        this.context = context;
        this.holder = holder;
    }


    @Override
    public void run() {


    }
}

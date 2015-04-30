package com.wander.model;

import android.content.Context;
import android.view.SurfaceHolder;

/**
 * Created by wander on IDEA.
 * Date:15-4-30
 * Email:18955260352@163.com
 */
public class Block implements  Runnable{
    private boolean direction;
    private Context context;
    private SurfaceHolder holder;
    private int sHeight;
    private int sWidth;
    private boolean running;
    private int score;

    public Block(Context context, SurfaceHolder holder) {
        this.context = context;
        this.holder = holder;

    }

    @Override
    public void run() {

    }
}

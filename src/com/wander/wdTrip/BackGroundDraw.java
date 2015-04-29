package com.wander.wdTrip;

import android.content.Context;
import android.view.SurfaceHolder;
import com.wander.appCreate;
import com.wander.model.BackGround;

/**
 * Created by wander on IDEA.
 * Date:15-4-29
 * Email:18955260352@163.com
 */
public class BackGroundDraw {
    private SurfaceHolder holder;
    private Context context;
    boolean isRunning;
    private int flag;
    BackGround particles1;


    public BackGroundDraw(SurfaceHolder holder, Context context) {
        this.holder = holder;
        this.context = context;
        particles1 = new BackGround(holder, context);
        new Thread(particles1).start();
    }
//    public void exitThread() {
//        appCreate application = (appCreate) context.getApplicationContext();
//        application.stopMusic();
//        isRunning = false;
//    }
}

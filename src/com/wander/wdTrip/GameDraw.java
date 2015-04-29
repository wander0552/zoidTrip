package com.wander.wdTrip;

import android.content.Context;
import android.view.SurfaceHolder;
import com.wander.model.Plankter;

/**
 * Created by wander on IDEA.
 * Date:15-4-29
 * Email:18955260352@163.com
 */
public class GameDraw {
    private SurfaceHolder holder;
    private Context context;
    private Plankter plankter;

    public GameDraw(SurfaceHolder holder, Context context) {
        this.holder = holder;
        this.context = context;
        plankter=new Plankter(context,holder);
        new Thread(plankter).start();
    }
}
